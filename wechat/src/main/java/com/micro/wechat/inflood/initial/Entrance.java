package com.micro.wechat.inflood.initial;

import android.content.Context;

import com.micro.hook.config.HookParam;
import com.micro.hook.setup.Setup;
import com.micro.wechat.Const;
import com.micro.wechat.inflood.inner.TestHook;
import com.micro.wechat.inflood.inner.execute.HideHook;
import com.micro.wechat.inflood.inner.execute.LogContent;
import com.micro.wechat.inflood.version.WeChatParam;
import com.micro.wechat.notice.CollectNotice;

import static com.micro.wechat.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:16
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "WX-Entrance";

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
        CollectNotice.createShowNotice(getIContext(), "", "快手采集已准备");
    }

    @Override
    protected void log() {
        new LogContent(getHookParam().getHook()).monitor();
    }

    @Override
    protected void executeSQL() {
        monitorLogger.i(TAG, "executeSQL", "数据库");
    }

    @Override
    protected void config() {
        monitorLogger.i(TAG, "config", "配置");
        try {
            if (Const.collectType != 0) {
                if (Const.isWeChatForegroundApply(getIContext()) == 500) {
                    Const.openWeChatApply(getIContext());
                } else if (Const.isWeChatForegroundApply(getIContext()) == 400) {
                    Const.setWeChatTopApply(getIContext());
                }
            }
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

    }

    @Override
    protected void execute() {

    }

    @Override
    public void initParam(String version) {
        monitorLogger.i(TAG, "initParam", "参数初始化： " + version);
        WeChatParam.init(version);
    }

    @Override
    public void hide() {
        monitorLogger.i(TAG, "hide", "隐藏");
        new HideHook(getHookParam().getHook());
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