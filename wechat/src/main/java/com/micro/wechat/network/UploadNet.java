package com.micro.wechat.network;

import android.content.Context;

import com.micro.network.InitNetwork;
import com.micro.root.Logger;

/**
 * @Author KiLin
 * @Time 2020/4/6 9:53
 */
public class UploadNet {
    private static Logger netLogger = Logger.getLogger("WeChatLog", "NetLog");

    private static Context context;

    public static void initNet(Context context) {
        UploadNet.context = context;
        InitNetwork.loadNetwork(context, ApiService.class);
    }

    private interface ApiService {

    }
}