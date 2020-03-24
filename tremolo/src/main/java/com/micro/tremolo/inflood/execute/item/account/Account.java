package com.micro.tremolo.inflood.execute.item.account;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.execute.PluginDeploy;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-18 上午10:21
 */
public class Account extends Plugin<AccountPresenter, AccountInter> implements AccountInter {

    public Account(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    protected AccountPresenter getPresenter() {
        return new AccountPresenter();
    }

    @Override
    protected void execute() {
        monitor(getHook());
    }

    @Override
    public void monitor(Hook hook) {

    }
}