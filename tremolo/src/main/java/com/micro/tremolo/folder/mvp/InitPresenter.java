package com.micro.tremolo.folder.mvp;

import android.content.Context;

import com.micro.rom.RomPresenter;
import com.micro.tremolo.inflood.utils.CacheDefault;

import org.litepal.LitePal;

import static com.micro.tremolo.Const.rootLogger;

/**
 * created by kilin on 20-3-18 下午1:48
 */
public class InitPresenter extends RomPresenter<InitInter> {

    public InitPresenter(Context context, String packageName, String createDir) {
        super(context, packageName, createDir);
    }

    @Override
    protected void onAttached() {
        rootLogger.i("配置成功");
    }

    @Override
    protected void initConfig() {
        LitePal.initialize(getContext());
        CacheDefault.setInstance(getContext());
    }
}