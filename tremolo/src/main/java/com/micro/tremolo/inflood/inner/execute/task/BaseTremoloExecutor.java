package com.micro.tremolo.inflood.inner.execute.task;

import com.micro.root.Logger;
import com.micro.root.task.BaseCode;
import com.micro.root.task.BaseRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author KiLin
 * @Time 2020/4/13 10:03
 */
public abstract class BaseTremoloExecutor extends BaseCode {

    private static final Logger logger = Logger.getLogger("TremoloLog", "PluginLog");
    private static BlockingQueue<BaseRunnable> singleQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<BaseRunnable> parallelQueue = new LinkedBlockingQueue<>();

    protected BaseTremoloExecutor() {
        super(2, 0, "tremolo");
    }

    @Override
    protected void loadScheduled(int core, ScheduledExecutorService scheduledPool) {
        if (core != 2) {
            logger.e("抖音定时线程无新增");
            return;
        }
        scheduledPool.scheduleAtFixedRate(distribution(), 10, 10, TimeUnit.SECONDS);
        scheduledPool.scheduleAtFixedRate(changeUI(), 20, 20, TimeUnit.SECONDS);
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

    protected abstract Runnable changeUI();

    public void removeTask(BaseRunnable task) {
        removeQueue(task);
    }

    public void addTask(BaseRunnable task) {
        if (task.isSingle()) {
            singleQueue.offer(task);
        } else {
            parallelQueue.add(task);
        }
    }
}