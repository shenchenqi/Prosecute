package com.micro.tremolo.inflood;

import android.os.Handler;

import com.micro.hook.ExecuteMonitor;
import com.micro.hook.config.HookParam;
import com.micro.root.Logger;
import com.micro.tremolo.Const;

import java.util.List;

/**
 * 抖音
 * created by kilin on 20-3-17 下午2:40
 */
public class TremoloModule extends ExecuteMonitor {

    public static final Logger logger = Logger.getLogger("Tremolo-Log");
    private static final String TAG = "Tremolo-Module";

    public static void init(HookParam hookParam) {
        logger.i(TAG, "init");
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