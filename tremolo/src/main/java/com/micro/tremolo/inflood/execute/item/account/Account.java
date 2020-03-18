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

    static {
        PluginDeploy.registerPlugin(Account.class.getSimpleName(), Account.class);
    }

    public static void start(Hook hook, Context context) {
        try {
            new Account(hook, context);
        } catch (Throwable throwable) {
            logger.e(throwable, "账户创建报错");
        }
    }

    private Account(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    protected AccountPresenter getPresenter() {
        return new AccountPresenter();
    }

    @Override
    protected void run() {
        monitor(getHook());
    }

    @Override
    public void monitor(Hook hook) {

    }
}