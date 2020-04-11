package com.micro.root.task;

/**
 * created by kilin on 19-12-23 下午3:44
 */
public abstract class BaseRunnable implements Runnable {
    private static final long DEFAULT_TIMEOUT = 60 * 1000L;

    private final long timeout;

    protected BaseRunnable() {
        this(DEFAULT_TIMEOUT);
    }

    protected BaseRunnable(long timeout) {
        this.timeout = timeout;
    }

    private String taskName;
    private long startTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private boolean timeOut() {
        return startTime > 0 && System.currentTimeMillis() > startTime + timeout;
    }

    @Override
    public void run() {
        try {
            if (timeOut()) {
                timeout();
            } else {
                process();
            }
        } catch (Throwable e) {
            error(e);
        } finally {
            finish();
        }
    }

    protected abstract void process();

    protected void timeout() {

    }

    protected abstract void error(Throwable e);

    protected abstract void finish();
}