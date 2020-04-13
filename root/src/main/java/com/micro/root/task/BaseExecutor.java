package com.micro.root.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @Author KiLin
 * @Time 2020/4/11 9:13
 */
public class BaseExecutor {

    public static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static Handler getMainHandleMessage(final MessageCallback callback) {
        return new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                callback.handleMessage(msg);
            }
        };
    }

    private static Looper getSubLooper(String name) {
        HandlerThread handlerThread = new HandlerThread(name);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    public static Handler getSubHandler(String name) {
        return new Handler(getSubLooper(name));
    }

    public static Handler getSubHandleMessage(String name, final MessageCallback callback) {
        return new Handler(getSubLooper(name)) {
            @Override
            public void handleMessage(Message msg) {
                callback.handleMessage(msg);
            }
        };
    }

    public static ExecutorService getFixedThreadPool(int nThreads, ThreadFactory factory) {
        //核心线程
        return Executors.newFixedThreadPool(nThreads, factory);
    }

    public static ExecutorService getCachedThreadPool() {
        //缓存线程
        return Executors.newCachedThreadPool();
    }

    public static ExecutorService getSingleThreadPool() {
        //单线程
        return Executors.newSingleThreadExecutor();
    }

    public static ScheduledExecutorService getScheduledThreadPool(int corePoolSize, ThreadFactory factory) {
        //定时线程
        return Executors.newScheduledThreadPool(corePoolSize, factory);
    }

    public interface MessageCallback {
        void handleMessage(Message msg);
    }
}