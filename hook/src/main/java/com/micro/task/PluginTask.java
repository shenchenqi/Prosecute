package com.micro.task;

import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * created by kilin on 20-3-18 上午11:03
 */
public abstract class PluginTask implements Runnable {

    private final String taskName;
    private final long timeout;
    private final boolean isCycle;
    private final boolean isSingle;

    protected PluginTask() {
        this.taskName = taskName();
        this.timeout = timeout();
        this.isCycle = isCycleConfig();
        this.isSingle = isSingleConfig();
    }

    protected abstract String taskName();

    protected long timeout() {
        return 60 * 1000L;
    }

    protected boolean isCycleConfig() {
        return false;
    }

    protected boolean isSingleConfig() {
        return false;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public boolean isSingle() {
        return isSingle;
    }

    private long startTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isTimeOut() {
        return startTime > 0 && System.currentTimeMillis() > startTime + timeout;
    }

    public String getTaskName() {
        return taskName;
    }

    private Future future;
    private Semaphore semaphore;

    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Throwable e) {
            error(e);
        } finally {
            if (semaphore != null) {
                semaphore.release();
            }
            finish(true);
        }
    }

    protected abstract void process();

    protected abstract void error(Throwable throwable);

    public abstract void finish(boolean success);
}