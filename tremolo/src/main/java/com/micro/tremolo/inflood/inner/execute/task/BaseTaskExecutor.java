package com.micro.tremolo.inflood.inner.execute.task;

import com.micro.root.Logger;
import com.micro.root.task.BaseExecutor;
import com.micro.task.PluginTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author KiLin
 * @Time 2020/4/13 10:03
 */
public abstract class BaseTaskExecutor {

    private static final Logger logger = Logger.getLogger("TremoloLog", "PluginLog");
    private static BlockingQueue<PluginTask> cycleQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<PluginTask> singleQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<PluginTask> parallelQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<PluginTask> complyQueue = new LinkedBlockingQueue<>();

    private ScheduledExecutorService mScheduledThreadPool;

    protected BaseTaskExecutor() {
        if (this.mScheduledThreadPool == null) {
            this.mScheduledThreadPool = BaseExecutor.getScheduledThreadPool(3, new ThreadFactory() {

                private final AtomicInteger c = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("task-pool-" + c.incrementAndGet());
                    return thread;
                }
            });
        }
    }

    protected void loadScheduled() {
        this.mScheduledThreadPool.scheduleAtFixedRate(changeUI(), 20, 20, TimeUnit.SECONDS);
        this.mScheduledThreadPool.scheduleAtFixedRate(new WideAreaTask.UserTask(), 10, 10, TimeUnit.SECONDS);
        this.mScheduledThreadPool.scheduleAtFixedRate(new WideAreaTask.VideoTask(), 15, 15, TimeUnit.SECONDS);
    }

    protected abstract Runnable changeUI();

    protected Runnable executeTask() {
        return () -> {
            logger.i("执行队列任务 执行");
            if (complyQueue.isEmpty()) {
                logger.i("执行队列任务 无任务");
                return;
            }
            for (PluginTask task : complyQueue) {
                logger.i(String.format("执行队列任务[%s], 循环[%s], 单线程[%s]", task.getTaskName(), task.isCycle(), task.isSingle()));
                task.run();
            }
        };
    }

    protected void removeTask(PluginTask task) {
        complyQueue.remove(task);
    }

    protected Runnable distributionTask() {
        return () -> {
            logger.i("分配任务 分配");
            if (!cycleQueue.isEmpty()) {
                logger.i("分配任务 循环线程 有数据");
                setComplyQueue(cycleQueue, 3);
            }
            if (!singleQueue.isEmpty()) {
                logger.i("分配任务 单线程 有数据");
                setComplyQueue(singleQueue, 1);
            }
            if (!parallelQueue.isEmpty()) {
                logger.i("分配任务 多线程 有数据");
                setComplyQueue(parallelQueue, 2);
            }
        };
    }

    private void setComplyQueue(BlockingQueue<PluginTask> taskQueue, int type) {
        BlockingQueue<PluginTask> queue = new LinkedBlockingQueue<>(taskQueue);
        for (PluginTask task : queue) {
            if (type != 3) {
                complyQueue.add(task);
                taskQueue.remove(task);
                if (task.isCycle()) {
                    task.setStartTime(System.currentTimeMillis());
                    cycleQueue.add(task);
                }
            } else if (task.isTimeOut()) {
                complyQueue.add(task);
                task.setStartTime(System.currentTimeMillis());
                taskQueue.add(task);
            }
        }
    }

    protected synchronized void setDistribution(PluginTask task) {
        if (task.isSingle()) {
            singleQueue.offer(task);
        } else {
            parallelQueue.add(task);
        }
    }
}