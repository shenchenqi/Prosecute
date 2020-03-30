package com.micro.tremolo.inflood.inner.execute.account;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

import static com.micro.tremolo.inflood.inner.execute.Deploy.logger;

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
    public void autoControl() {

    }

    @Override
    protected String taskName() {
        return Account.class.getSimpleName();
    }

    @Override
    protected void process() {

    }

    @Override
    protected void error(Throwable throwable) {

    }

    @Override
    public void finish(boolean success) {
        logger.i(String.format("账户任务[%s]", success));
    }

    @Override
    public void loadAccount() {

    }
}