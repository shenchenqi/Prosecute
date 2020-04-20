package com.micro.wechat.inflood.inner.execute.task;

import com.micro.root.Logger;
import com.micro.root.task.BaseCode;
import com.micro.root.task.BaseRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 15:00
 */
public abstract class BaseWeChatExecutor extends BaseCode {
    private static final Logger logger = Logger.getLogger("WeChatLog", "PluginLog");
    private static BlockingQueue<BaseRunnable> singleQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<BaseRunnable> parallelQueue = new LinkedBlockingQueue<>();

    protected BaseWeChatExecutor() {
        super(1, 0, "wechat");
    }

    @Override
    protected void loadScheduled(int core, ScheduledExecutorService scheduledPool) {
        if (core != 1) {
            logger.e("微信定时线程无新增");
            return;
        }
        scheduledPool.scheduleAtFixedRate(distribution(), 10, 10, TimeUnit.SECONDS);
    }

    private Runnable distribution(){
        return () -> {
            if (!singleQueue.isEmpty()){
                for (BaseRunnable runnable : singleQueue) {
                    addQueue(runnable);
                }
                singleQueue.clear();
            }
            if (!parallelQueue.isEmpty()){
                for (BaseRunnable runnable : parallelQueue) {
                    addQueue(runnable);
                }
                parallelQueue.clear();
            }
        };
    }

    protected void removeTask(BaseRunnable task) {
        removeQueue(task);
    }

    protected void addTask(BaseRunnable task) {
        if (task.isSingle()) {
            singleQueue.offer(task);
        } else {
            parallelQueue.add(task);
        }
    }
}