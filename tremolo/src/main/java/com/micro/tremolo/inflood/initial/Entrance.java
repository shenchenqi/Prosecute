package com.micro.tremolo.inflood.initial;

import android.content.Context;

import com.micro.hook.setup.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApiTremolo;
import com.micro.tremolo.inflood.inner.execute.api.SearchUserApiTremolo;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApiTremolo;
import com.micro.tremolo.inflood.inner.execute.monitor.MainActivityOversee;
import com.micro.tremolo.inflood.inner.execute.monitor.ProfileFragmentOversee;
import com.micro.tremolo.inflood.inner.execute.task.NarrowAreaTask;
import com.micro.tremolo.inflood.inner.execute.task.TremoloExecutor;
import com.micro.tremolo.inflood.inner.execute.task.WideAreaTask;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.inflood.inner.TestHook;
import com.micro.tremolo.inflood.inner.execute.LogContent;
import com.micro.tremolo.inflood.inner.execute.HideDialog;
import com.micro.tremolo.inflood.inner.execute.monitor.MainFragmentOversee;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.notice.CollectNotice;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * created by kilin on 20-3-17 下午5:23
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "DY-Entrance";

    private static Entrance mEntrance;

    public static Entrance getInstance(HookParam hookParam) {
        if (mEntrance == null) {
            try {
                monitorLogger.i(TAG, "步骤事件: " + hookParam.getVersion());
                mEntrance = new Entrance(hookParam);
                mEntrance.init();
            } catch (Throwable throwable) {
                monitorLogger.e(throwable, TAG, "步骤事件失败");
            }
        }
        return mEntrance;
    }

    private Entrance(HookParam hookParam) throws Throwable {
        super(hookParam);
        CollectNotice.createShowNotice(getIContext(), "", "抖音采集已准备");
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
        monitorLogger.i(TAG, "initParam", "参数初始化： " + version);
        TremoloParam.init(version);
    }

    @Override
    public void hide() {
        monitorLogger.i(TAG, "hide", "隐藏");
    }

    @Override
    public void executeSQL() {
        monitorLogger.i(TAG, "executeSQL", "数据库");
    }

    private HideDialog dialog;
    private MainActivityOversee mainActivityOversee;
    private MainFragmentOversee mainFragmentOversee;
    private ProfileFragmentOversee profileFragmentOversee;

    @Override
    public void config() {
        monitorLogger.i(TAG, "config", "配置");
        try {
            if (Const.collectType != 0) {
                if (Const.isTremoloForegroundApply(getIContext()) == 500) {
                    Const.openTremoloApply(getIContext());
                } else if (Const.isTremoloForegroundApply(getIContext()) == 400) {
                    Const.setTremoloTopApply(getIContext());
                }
            }
            SearchUserApiTremolo.setInstance(getHookParam().getHook(), getIContext());
            ProfileOtherApiTremolo.setInstance(getHookParam().getHook(), getIContext());
            VideoListApiTremolo.setInstance(getHookParam().getHook(), getIContext());
            dialog = new HideDialog(getHookParam().getHook(), getIContext());
            WideAreaTask.getInstance(getIContext());
            NarrowAreaTask.getInstance(getIContext());
            mainActivityOversee = new MainActivityOversee(getHookParam().getHook(), getIContext());
            mainFragmentOversee = new MainFragmentOversee(getHookParam().getHook(), getIContext());
            profileFragmentOversee = new ProfileFragmentOversee(getHookParam().getHook(), getIContext());
            new TremoloExecutor();
        } catch (Throwable throwable) {
            monitorLogger.e(throwable, "配置报错");
        }
    }

    @Override
    protected void test() {
        TestHook.test(getHookParam().getHook());
    }

    @Override
    protected void monitor() {
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
    public void execute() {

    }

    @Override
    public Context getIContext() {
        return getHookParam().getApplication();
    }
}