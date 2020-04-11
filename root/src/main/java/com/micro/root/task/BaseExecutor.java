package com.micro.root.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author KiLin
 * @Time 2020/4/11 9:13
 */
public abstract class BaseExecutor {

    protected Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    protected Handler getMainHandleMessage(final MessageCallback callback) {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                callback.handleMessage(msg);
            }
        };
    }

    private Looper getSubLooper(String name) {
        HandlerThread handlerThread = new HandlerThread(name);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    protected Handler getSubHandler(String name) {
        return new Handler(getSubLooper(name));
    }

    protected Handler getSubHandleMessage(String name, final MessageCallback callback) {
        return new Handler(getSubLooper(name)) {
            @Override
            public void handleMessage(Message msg) {
                callback.handleMessage(msg);
            }
        };
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

    public interface MessageCallback {
        void handleMessage(Message msg);
    }
}