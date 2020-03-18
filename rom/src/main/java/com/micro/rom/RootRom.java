package com.micro.rom;

import android.content.Context;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * created by kilin on 20-3-11 下午2:49
 */
public abstract class RootRom<P extends RomPresenter<I>, I extends RomInter> implements RomInter {

    private final Context context;
    private final P presenter;

    protected RootRom(Context context) {
        this.context = context;
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setClazz((I) this);
            new ScheduledHandler(() -> bindSetup(presenter));
        }
    }

    protected Context getContext() {
        return context;
    }

    protected abstract P getPresenter();

    protected abstract void bindSetup(P presenter);

    private static class ScheduledHandler {
        private ScheduledExecutorService scheduledThreadPool;

        private ScheduledHandler(Runnable nativeTask) {
            if (this.scheduledThreadPool != null) {
                return;
            }
            this.scheduledThreadPool = Executors.newScheduledThreadPool(1);
            this.scheduledThreadPool.scheduleAtFixedRate(nativeTask, (1L), (20L), TimeUnit.SECONDS);
        }
    }
}