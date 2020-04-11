package com.micro.worker.inflood;

import com.micro.hook.ExecuteMonitor;
import com.micro.hook.config.HookParam;
import com.micro.worker.Const;
import com.micro.worker.inflood.initial.Entrance;

import java.util.List;

import static com.micro.worker.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:09
 */
public class WorkerModule extends ExecuteMonitor {

    private static final String TAG = "Worker-Module";

    public static void init(HookParam hookParam) {
        monitorLogger.i(TAG, "init");
        new WorkerModule(hookParam);
    }

    private WorkerModule(HookParam hookParam) {
        super(hookParam);
    }

    @Override
    protected String getPackageName() {
        return Const.PACKAGE_NAME;
    }

    @Override
    protected String applicationPath() {
        return Const.APPLICATION;
    }

    @Override
    protected List<String> versions() {
        return Const.VERSIONS;
    }

    @Override
    public void hookEvent(HookParam hookParam) {
        Entrance.getInstance(hookParam);
    }
}