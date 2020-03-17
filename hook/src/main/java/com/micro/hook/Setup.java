package com.micro.hook;

import com.micro.hook.config.HookParam;

/**
 * created by kilin on 20-3-17 下午5:16
 */
public abstract class Setup {
    protected final HookParam hookParam;

    protected Setup(HookParam hookParam) {
        this.hookParam = hookParam;
    }

    public void init() throws Throwable {
        initParam(hookParam.getVersion());
        hide();
        executeSQL();
        config();
        execute();
    }

    protected abstract void initParam(String version);

    protected abstract void hide();

    protected abstract void executeSQL();

    protected abstract void config();

    protected abstract void execute();
}