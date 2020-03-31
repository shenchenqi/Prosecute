package com.micro.hook.setup;

import com.micro.hook.BuildConfig;
import com.micro.hook.config.HookParam;
import com.micro.root.model.BaseModel;

/**
 * created by kilin on 20-3-17 下午5:16
 */
public abstract class Setup<P extends SetupPresenter<I>, I extends SetupInter> extends BaseModel<I, P> implements SetupInter {

    private final HookParam hookParam;

    protected Setup(HookParam hookParam) {
        super();
        this.hookParam = hookParam;
        if (this.presenter != null) {
            this.presenter.setContext(hookParam.getContext());
            this.presenter.getClazz().initParam(hookParam.getVersion());
            this.presenter.getClazz().hide();
        }
    }

    protected HookParam getHookParam() {
        return hookParam;
    }

    public void init() throws Throwable {
        if (this.presenter == null) {
            throw new Throwable("未实例接口");
        }
        log();
        executeSQL();
        config();
        if (BuildConfig.DEBUG) {
            test();
        }
        execute();
    }

    protected abstract void log();

    protected abstract void executeSQL();

    protected abstract void config();

    protected abstract void test();

    protected abstract void execute();
}