package com.micro.tremolo;

import com.micro.hook.ExecuteMonitor;
import com.micro.hook.config.HookParam;
import com.micro.root.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * 抖音
 * created by kilin on 20-3-17 下午2:40
 */
public class TremoloModule extends ExecuteMonitor {

    public static final Logger logger = Logger.getLogger("TremoloLog");
    private static final String TAG = "Tremolo-Module";

    static {
        setHookRegister(TremoloModule.class.getSimpleName(), TremoloModule.class);
    }

    public static void init(HookParam hookParam) {
        new TremoloModule(hookParam);
    }

    private TremoloModule(HookParam hookParam) {
        super(hookParam);
    }

    @Override
    protected String getPackageName() {
        return "com.ss.android.ugc.aweme";
    }

    @Override
    protected String applicationPath() {
        return "com.ss.android.ugc.aweme.app.AwemeApplication";
    }

    @Override
    protected List<String> versions() {
        return Arrays.asList("7.1.0", "7.1.1");
    }

    @Override
    public void hookEvent(HookParam hookParam) {
        try {
            logger.i(TAG, "步骤事件");
            new Entrance(hookParam).init();
        } catch (Throwable throwable) {
            logger.e(throwable, TAG, "步骤事件失败");
        }
    }
}