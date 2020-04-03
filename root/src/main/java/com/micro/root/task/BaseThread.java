package com.micro.root.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.micro.root.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * created by kilin on 19-12-23 下午3:44
 */
public abstract class BaseThread implements Runnable {
    private static final String TAG = "BaseThread";

    private static final long DEFAULT_TIMEOUT = 60 * 1000L;

    private final long timeout;

    protected BaseThread() {
        this(DEFAULT_TIMEOUT);
    }

    protected BaseThread(long timeout) {
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
            Logger.getLogger("root", "Base-Thread").e(e, TAG, "run", "报错");
        } finally {
            finish();
        }
    }

    protected abstract void process();

    protected void timeout() {

    }

    protected abstract void finish();

    protected Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    protected Handler getMainHandleMessage() {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                mainHandleMessage(msg.what);
            }
        };
    }

    protected void mainHandleMessage(int what) {
        Logger.getLogger("root", "Main-Thread").i("获取到主线程状态：　" + what);
    }

    private Looper getSubLooper(String name) {
        HandlerThread handlerThread = new HandlerThread(name);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    protected Handler getSubHandler(String name) {
        return new Handler(getSubLooper(name));
    }

    protected Handler getSubHandleMessage(String name) {
        return new Handler(getSubLooper(name)) {
            @Override
            public void handleMessage(Message msg) {
                subHandleMessage(msg.what);
            }
        };
    }

    private void subHandleMessage(int what) {
        Logger.getLogger("root", "Sub-Thread").i("获取到子线程状态：　" + what);
    }

    protected ExecutorService getFixedThreadPool(int nThreads) {
        //核心线程
        return Executors.newFixedThreadPool(nThreads);
    }

    protected ExecutorService getCachedThreadPool() {
        //缓存线程
        return Executors.newCachedThreadPool();
    }

    protected ExecutorService getSingleThreadPool() {
        //单线程
        return Executors.newSingleThreadExecutor();
    }

    protected ScheduledExecutorService getScheduledThreadPool(int corePoolSize) {
        //定时线程
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}