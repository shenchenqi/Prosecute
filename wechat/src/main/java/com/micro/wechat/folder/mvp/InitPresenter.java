package com.micro.wechat.folder.mvp;

import android.content.Context;

import com.micro.rom.RomPresenter;

import org.litepal.LitePal;

/**
 * created by kilin on 20-3-18 下午1:48
 */
public class InitPresenter extends RomPresenter<InitInter> {

    public InitPresenter(Context context, String packageName, String createDir) {
        super(context, packageName, createDir);
    }

    @Override
    protected void onAttached() {

    }

    @Override
    protected void initConfig() {
        LitePal.initialize(getContext());
    }
}