package com.micro.tremolo.folder;

import android.content.Context;

import com.micro.CacheDefault;
import com.micro.rom.RootRom;

import org.litepal.LitePal;

/**
 * created by kilin on 20-3-17 下午5:40
 */
public class InitRoot extends RootRom {

    protected InitRoot(Context context) {
        super(context, "com.ss.android.ugc.aweme", "Micro/Backup/init");
    }

    @Override
    protected void initConfig() {
        LitePal.initialize(getContext());
        CacheDefault.setInstance(getContext());
    }
}