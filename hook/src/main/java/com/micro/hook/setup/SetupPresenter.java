package com.micro.hook.setup;

import android.content.Context;

import com.micro.root.mvp.BasePresenter;

/**
 * created by kilin on 20-3-18 上午9:36
 */
public abstract class SetupPresenter<Inter extends SetupInter> extends BasePresenter<Inter> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}