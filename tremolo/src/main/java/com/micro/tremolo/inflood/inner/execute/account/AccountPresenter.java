package com.micro.tremolo.inflood.inner.execute.account;

import com.micro.hook.plugin.PluginPresenter;

/**
 * created by kilin on 20-3-18 上午10:25
 */
public class AccountPresenter extends PluginPresenter<AccountInter> {
    @Override
    public void onAttached() {

    }

    private Object accountInfo;

    public Object getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Object accountInfo) {
        this.accountInfo = accountInfo;
    }

    /*public void obtainAuthor(User user){
        getClazz().loadUser(user);
    }*/
}