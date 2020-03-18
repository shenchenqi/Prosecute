package com.micro.tremolo.execute.item.account;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.execute.PluginDeploy;

import static com.micro.tremolo.TremoloModule.logger;

/**
 * created by kilin on 20-3-18 上午10:21
 */
public class Account extends Plugin<AccountPresenter, AccountInter> implements AccountInter {

    static {
        PluginDeploy.registerPlugin(Account.class.getSimpleName(), Account.class);
    }

    public static void start(Hook hook) {
        try {
            new Account(hook);
        } catch (Throwable throwable) {
            logger.e(throwable, "账户创建报错");
        }
    }

    private Account(Hook hook) throws Throwable {
        super(hook);
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