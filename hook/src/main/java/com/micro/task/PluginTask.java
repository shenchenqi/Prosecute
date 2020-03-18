package com.micro.task;

import com.micro.root.Logger;
import com.micro.root.task.BaseAsync;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * created by kilin on 20-3-18 上午11:03
 */
public abstract class PluginTask extends BaseAsync {

    private static final Logger logger = Logger.getLogger("PluginLog");

    private final TaskProcess taskProcess;

    public PluginTask() {
        super(null);
        taskProcess = new TaskProcess();
    }

    protected void executeSingleThread() {
        executeOnExecutor(taskProcess.singleExecutor);
    }

    protected void executeCachedThread() {
        executeOnExecutor(taskProcess.cachedExecutor);
    }

    @Override
    protected void preExecute() {
        super.preExecute();
    }

    @Override
    protected Object asyncRun(Object[] objects) {
        run();
        return null;
    }

    protected abstract void run();

    @Override
    protected void postExecute(Object object) {
        super.postExecute(object);
    }

    private static class TaskProcess {
        private Executor cachedExecutor, singleExecutor;

        TaskProcess() {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable throwable) {
                    logger.e(throwable, thread.getName(), "任务执行异常");
                }
            };
            singleExecutor = Executors.newSingleThreadExecutor(new BasicThreadFactory.Builder().uncaughtExceptionHandler(uncaughtExceptionHandler).build());
            cachedExecutor = Executors.newCachedThreadPool(new BasicThreadFactory.Builder().namingPattern("plugin-%d").uncaughtExceptionHandler(uncaughtExceptionHandler).build());
        }
    }
}