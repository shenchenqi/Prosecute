package com.micro.root.task;

import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

/**
 * created by kilin on 19-12-23 下午3:44
 */
public abstract class BaseRunnable implements Runnable {
    private static final long DEFAULT_TIMEOUT = 60 * 1000L;

    private final String taskName;
    private final long timeout;
    private final boolean isCycle;
    private final boolean isSingle;

    protected BaseRunnable() {
        this(DEFAULT_TIMEOUT);
    }

    protected BaseRunnable(long timeout) {
        this.timeout = timeout;
        this.taskName = taskName();
        this.isCycle = isCycleConfig();
        this.isSingle = isSingleConfig();
    }

    protected abstract String taskName();

    protected abstract boolean isCycleConfig();

    protected abstract boolean isSingleConfig();

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

    private boolean isTimeOut() {
        return startTime > 0 && System.currentTimeMillis() > startTime + timeout;
    }

    @Override
    public void run() {
        try {
            if (isTimeOut()) {
                timeout();
            } else {
                process();
            }
        } catch (Throwable e) {
            error(e);
        } finally {
            if (semaphore != null) {
                semaphore.release();
            }
            finish();
        }
    }

    protected abstract void process();

    protected abstract void timeout();

    protected abstract void error(Throwable e);

    protected abstract void finish();
}