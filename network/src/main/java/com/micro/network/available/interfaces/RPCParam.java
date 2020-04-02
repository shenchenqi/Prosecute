package com.micro.network.available.interfaces;

import android.app.Application;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:02
 */
public interface RPCParam {
    Application getApp();
    String getUUID();
    boolean isDebug();
    TokenCheck getTokenCheck();
}