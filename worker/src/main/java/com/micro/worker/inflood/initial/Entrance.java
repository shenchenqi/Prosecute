package com.micro.worker.inflood.initial;

import android.content.Context;

import com.micro.hook.config.HookParam;
import com.micro.hook.setup.Setup;
import com.micro.worker.Const;
import com.micro.worker.inflood.inner.TestHook;
import com.micro.worker.inflood.inner.execute.HideDialog;
import com.micro.worker.inflood.inner.execute.LogContent;
import com.micro.worker.inflood.inner.execute.api.UserProfileApi;
import com.micro.worker.inflood.inner.execute.monitor.HomeActivityOversee;
import com.micro.worker.inflood.version.WorkerParam;
import com.micro.worker.notice.CollectNotice;

import static com.micro.worker.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:16
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "KS-Entrance";

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

    private Entrance(HookParam hookParam) {
        super(hookParam);
        CollectNotice.createShowNotice(getIContext(), "快手助手", "快手采集已准备");
    }

    @Override
    protected void log() {
        new LogContent(getHookParam().getHook()).monitor();
    }

    @Override
    protected void executeSQL() {
        monitorLogger.i(TAG, "executeSQL", "数据库");
    }

    private HideDialog dialog;
    private HomeActivityOversee homeActivityOversee;

    @Override
    protected void config() {
        monitorLogger.i(TAG, "config", "配置");
        try {
            if (Const.collectType != 0) {
                if (Const.isAppOnForeground(getIContext()) == 500) {
                    Const.openApply(getIContext());
                } else if (Const.isAppOnForeground(getIContext()) == 400) {
                    Const.setTopApply(getIContext());
                }
            }
            dialog = new HideDialog(getHookParam().getHook(), getIContext());
            UserProfileApi.initApi(getHookParam().getHook(), getIContext());
            homeActivityOversee = new HomeActivityOversee(getHookParam().getHook(), getIContext());
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
        if (homeActivityOversee != null) {
            homeActivityOversee.monitor();
        }
    }

    @Override
    protected void execute() {

    }

    @Override
    public void initParam(String version) {
        monitorLogger.i(TAG, "initParam", "参数初始化： " + version);
        WorkerParam.init(version);
    }

    @Override
    public void hide() {
        monitorLogger.i(TAG, "hide", "隐藏");
    }

    @Override
    protected EntrancePresenter getPresenter() {
        return new EntrancePresenter();
    }

    @Override
    public Context getIContext() {
        return getHookParam().getApplication();
    }
}