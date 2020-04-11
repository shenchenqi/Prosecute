package com.micro.tremolo.inflood;

import com.micro.hook.ExecuteMonitor;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.initial.Entrance;

import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * 抖音模块
 * created by kilin on 20-3-17 下午2:40
 */
public class TremoloModule extends ExecuteMonitor {

    private static final String TAG = "Tremolo-Module";

    public static void init(HookParam hookParam) {
        monitorLogger.i(TAG, "init");
        new TremoloModule(hookParam);
    }

    private TremoloModule(HookParam hookParam) {
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