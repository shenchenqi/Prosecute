package com.micro.tremolo.inflood.inner.execute.monitor.account;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

/**
 * created by kilin on 20-3-18 上午10:21
 */
public class Account extends Plugin<AccountPresenter, AccountInter> implements AccountInter {

    public Account(Hook hook, Context context) throws Throwable {
        super(hook, context);
        //logger.i("账户初始化");
    }

    @Override
    protected AccountPresenter getPresenter() {
        return new AccountPresenter();
    }

    @Override
    public void monitor(Hook hook) {

    }

    @Override
    public void loadAccount() {

    }

    @Override
    public Context getIContext() {
        if (presenter != null) {
            return presenter.getContext();
        } else {
            return null;
        }
    }
}