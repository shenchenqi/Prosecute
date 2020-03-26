package com.micro.tremolo.inflood.inner;

import android.os.Handler;
import android.os.HandlerThread;

import com.micro.root.Logger;
import com.micro.task.PluginTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * created by kilin on 20-3-18 上午10:10
 */
public class PluginTaskExecutor {

    private static final List<PluginTask> taskList = new ArrayList<>();

    public static void addPluginTask(PluginTask pluginTask) {
        taskList.add(pluginTask);
    }

    public static void executePluginTasks() {
        PluginTaskExecutor taskExecutor = new PluginTaskExecutor();
        for (PluginTask pluginTask : taskList) {
            if (pluginTask.isSingle()) {
                taskExecutor.singleOffer(pluginTask);
            } else {
                taskExecutor.parallelAdd(pluginTask);
            }
        }
    }

    private static final Logger logger = Logger.getLogger("PluginTaskLog");
    private static Semaphore single = new Semaphore(1);
    private static Semaphore parallel = new Semaphore((int) (Runtime.getRuntime().availableProcessors() * 1.5));

    private static BlockingQueue<PluginTask> singleQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<PluginTask> parallelQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<PluginTask> complyQueue = new LinkedBlockingQueue<>();

    public static Handler getHandler(String name) {
        HandlerThread handlerThread = new HandlerThread(name);
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    private ExecutorService mFixedThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;
    private Runnable deleteQueue, distributeQueue;

    private PluginTaskExecutor() {
        this.mFixedThreadPool = Executors.newFixedThreadPool(single.availablePermits() + parallel.availablePermits());
        this.mScheduledThreadPool = Executors.newScheduledThreadPool(2);
        this.deleteQueue = new Runnable() {
            @Override
            public void run() {
                clearComply();
            }
        };
        this.mScheduledThreadPool.scheduleAtFixedRate(this.deleteQueue, 0, 5, TimeUnit.SECONDS);
        this.distributeQueue = new Runnable() {
            @Override
            public void run() {
                distributeComply(singleQueue, single);
                distributeComply(parallelQueue, parallel);
            }
        };
        this.mScheduledThreadPool.scheduleAtFixedRate(this.distributeQueue, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void singleOffer(PluginTask pluginTask) {
        singleQueue.offer(pluginTask);
    }

    private void parallelAdd(PluginTask pluginTask) {
        parallelQueue.add(pluginTask);
    }

    private void clearComply() {
        if (complyQueue.isEmpty()) {
            return;
        }
        for (Iterator<PluginTask> iterator = complyQueue.iterator(); iterator.hasNext(); ) {
            PluginTask pluginTask = iterator.next();
            if (pluginTask.isTimeOut()) {
                cancelRun(pluginTask);
                iterator.remove();
                logger.i(String.format("取消超时任务: %s", pluginTask.getTaskName()));
            }
        }
    }

    private void cancelRun(PluginTask pluginTask) {
        Future future = pluginTask.getFuture();
        boolean success = null != future && !future.isDone() && !future.isCancelled() && future.cancel(true);
        pluginTask.finish(success);
    }

    private synchronized void distributeComply(BlockingQueue<PluginTask> queue, Semaphore semaphore) {
        while (semaphore.tryAcquire()) {
            PluginTask pluginTask = queue.poll();
            logger.i(String.format("任务[%s], 循环[%s], 单线程[%s]", pluginTask.getTaskName(), pluginTask.isCycle(), pluginTask.isSingle()));
            if (null == pluginTask) {
                semaphore.release();
                break;
            } else {
                pluginTask.setSemaphore(semaphore);
                pluginTask.setFuture(mFixedThreadPool.submit(pluginTask));
                complyQueue.add(pluginTask);
            }
        }
    }
}