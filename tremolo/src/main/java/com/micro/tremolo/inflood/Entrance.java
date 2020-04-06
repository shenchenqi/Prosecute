package com.micro.tremolo.inflood;

import android.content.Context;

import com.micro.hook.setup.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.inflood.inner.TestHook;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.logcat.LogContent;
import com.micro.tremolo.inflood.inner.execute.dialog.HideDialog;
import com.micro.tremolo.inflood.inner.execute.monitor.main_activity.MainActivityOversee;
import com.micro.tremolo.inflood.inner.execute.monitor.main_fragment.MainFragmentOversee;
import com.micro.tremolo.inflood.inner.execute.monitor.profile_fragment.ProfileFragmentOversee;
import com.micro.tremolo.inflood.mvp.EntranceInter;
import com.micro.tremolo.inflood.mvp.EntrancePresenter;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:23
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "DY-Entrance";

    private static Entrance mEntrance;

    public static Entrance getInstance(HookParam hookParam) {
        if (mEntrance == null) {
            try {
                logger.i(TAG, "步骤事件: " + hookParam.getVersion());
                mEntrance = new Entrance(hookParam);
                mEntrance.init();
            } catch (Throwable throwable) {
                logger.e(throwable, TAG, "步骤事件失败");
            }
        }
        return mEntrance;
    }

    private Entrance(HookParam hookParam) throws Throwable {
        super(hookParam);
        UploadNet.initNet(hookParam.getApplication());
    }

    @Override
    protected EntrancePresenter getPresenter() {
        return new EntrancePresenter();
    }

    @Override
    protected void log() {
        new LogContent(getHookParam().getHook()).monitor();
    }

    @Override
    public void initParam(String version) {
        logger.i(TAG, "initParam", "参数初始化： " + version);
        TremoloParam.init(version);
    }

    @Override
    public void hide() {
        logger.i(TAG, "hide", "隐藏");
    }

    @Override
    public void executeSQL() {
        logger.i(TAG, "executeSQL", "数据库");
    }
    
    private HideDialog dialog;
    private MainActivityOversee mainActivityOversee;
    private MainFragmentOversee mainFragmentOversee;
    private ProfileFragmentOversee profileFragmentOversee;

    @Override
    public void config() {
        logger.i(TAG, "config", "配置");
        try {
            AutoUiControl autoUiControl = new AutoUiControl(getHookParam().getHook(), getIContext());
            if (AutoUiControl.isAppOnForeground(getIContext()) == 500) {
                AutoUiControl.openApply(getIContext());
            } else if (AutoUiControl.isAppOnForeground(getIContext()) == 400) {
                AutoUiControl.setTopApply(getIContext());
            }
            dialog = new HideDialog(getHookParam().getHook(), getIContext());
            mainActivityOversee = new MainActivityOversee(getHookParam().getHook(), getIContext(), autoUiControl);
            mainFragmentOversee = new MainFragmentOversee(getHookParam().getHook(), getIContext(), autoUiControl);
            profileFragmentOversee = new ProfileFragmentOversee(getHookParam().getHook(), getIContext(), autoUiControl);
        } catch (Throwable throwable) {
            logger.e(throwable, "配置报错");
        }
    }

    @Override
    protected void test() {
        TestHook.test(getHookParam().getHook());
    }

    @Override
    public void execute() {
        if (dialog != null) {
            dialog.monitor();
        }
        if (mainActivityOversee != null) {
            mainActivityOversee.monitor();
        }
        if (mainFragmentOversee != null) {
            mainFragmentOversee.monitor();
        }
        if (profileFragmentOversee != null) {
            profileFragmentOversee.monitor();
        }
    }

    @Override
    public Context getIContext() {
        return getHookParam().getApplication();
    }
}