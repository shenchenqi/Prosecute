package com.micro.hook.setup;

import com.micro.hook.config.HookParam;

/**
 * created by kilin on 20-3-17 下午5:16
 */
public abstract class Setup<P extends SetupPresenter<I>, I extends SetupInter> implements SetupInter {

    private final HookParam hookParam;
    private final P presenter;

    protected Setup(HookParam hookParam) throws Throwable {
        this.hookParam = hookParam;
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setContext(hookParam.getContext());
            this.presenter.setClazz((I) this);
            this.presenter.getClazz().initParam(hookParam.getVersion());
            this.presenter.getClazz().hide();
        }
    }

    protected HookParam getHookParam() {
        return hookParam;
    }

    protected abstract P getPresenter();

    public void init() throws Throwable {
        if (this.presenter == null) {
            throw new Throwable("未实例接口");
        }
        log();
        executeSQL();
        config();
        test();
        execute();
    }

    protected abstract void log();

    protected abstract void executeSQL();

    protected abstract void config();

    protected abstract void test();

    protected abstract void execute();
}