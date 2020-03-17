package com.micro;

import android.content.Context;

import com.micro.root.preferences.BaseShared;

/**
 * created by kilin on 20-3-17 下午5:44
 */
public class CacheDefault extends BaseShared {
    private static CacheDefault instance;

    public static CacheDefault getInstance() {
        return instance;
    }

    public static void setInstance(Context context) {
        if (instance == null) {
            instance = new CacheDefault(context);
        }
    }

    private CacheDefault(Context context) {
        super(context);
    }

    @Override
    protected String getCacheName() {
        return "Micro_SP";
    }
}