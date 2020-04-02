package com.micro.tremolo.inflood;

import android.content.Context;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.setup.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.inflood.inner.TestHook;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.monitor.account.Account;
import com.micro.tremolo.inflood.inner.execute.monitor.author.Author;
import com.micro.tremolo.inflood.inner.execute.dialog.HideDialog;
import com.micro.tremolo.inflood.inner.execute.monitor.video.Video;
import com.micro.tremolo.inflood.inner.logcat.Logcat;
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
        //InspectApply.openApply(hookParam.getApplication(), "com.micro.prosecute");
    }

    @Override
    protected EntrancePresenter getPresenter() {
        return new EntrancePresenter();
    }

    @Override
    protected void log() {
        getHookParam().getHook().methodMonitor(TremoloParam.LOG_CLASS, TremoloParam.LOG_SET_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                new Logcat(getHookParam().getHook(), param.getArgs()[0]);
            }
        }, getHookParam().getHook().findClass(TremoloParam.LOG_MSG_CLASS));
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

    private Account account;
    private Author author;
    private Video video;
    private HideDialog dialog;

    @Override
    public void config() {
        logger.i(TAG, "config", "配置");
        try {
            AutoUiControl autoUiControl = new AutoUiControl(getHookParam().getHook(), getHookParam().getApplication());
            AutoUiControl.openApply(getHookParam().getApplication());
            account = new Account(getHookParam().getHook(), getHookParam().getApplication());
            author = new Author(getHookParam().getHook(), getHookParam().getApplication(), autoUiControl);
            video = new Video(getHookParam().getHook(), getHookParam().getApplication(), autoUiControl);
            dialog = new HideDialog(getHookParam().getHook(), getHookParam().getApplication());
        } catch (Throwable throwable) {
            logger.e(throwable, "配置报错");
        }
    }

    @Override
    protected void test() {
        TestHook.testMain(getHookParam().getHook());
        //TestHook.testApi(getHookParam().getHook());
        //TestHook.testUser(getHookParam().getHook());
    }

    @Override
    public void execute() {
        if (account != null) {
            account.monitor();
        }
        if (author != null) {
            author.monitor();
        }
        if (video != null) {
            video.monitor();
        }
        if (dialog != null) {
            dialog.monitor();
        }
    }

    @Override
    public Context getIContext() {
        if (presenter != null) {
            return presenter.getContext();
        }
        return null;
    }
}