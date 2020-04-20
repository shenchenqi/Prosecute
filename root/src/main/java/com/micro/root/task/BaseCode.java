package com.micro.root.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 13:51
 */
public abstract class BaseCode {
    private static BlockingQueue<BaseRunnable> complyQueue = new LinkedBlockingQueue<>();

    private final String name;

    private final ScheduledExecutorService scheduledPool;
    private final int core;

    protected BaseCode(int core, int pool, String name) {
        this.core = core + 1;
        this.name = String.format("%s-task-pool-%s", name, pool);
        ThreadFactory factory = runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName(getName());
            return thread;
        };
        this.scheduledPool = BaseExecutor.getScheduledThreadPool(this.core, factory);
        this.scheduledPool.scheduleAtFixedRate(runnable(), 0, 500, TimeUnit.MILLISECONDS);
    }

    private Runnable runnable() {
        return () -> {
            if (complyQueue.isEmpty()) {
                return;
            }
            for (BaseRunnable runnable : complyQueue) {
                runnable.run();
            }
        };
    }

    protected void loadScheduled() {
        loadScheduled(core - 1, scheduledPool);
    }

    protected abstract void loadScheduled(int core, ScheduledExecutorService scheduledPool);

    private String getName() {
        return name;
    }

    protected void addQueue(BaseRunnable runnable) {
        BaseCode.complyQueue.add(runnable);
    }

    protected void removeQueue(BaseRunnable runnable) {
        BaseCode.complyQueue.remove(runnable);
    }
}