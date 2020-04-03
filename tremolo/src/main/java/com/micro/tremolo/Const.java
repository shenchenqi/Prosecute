package com.micro.tremolo;

import android.content.Context;

import com.micro.network.InitNetwork;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.DataBroadcast;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 */
public class Const {

    private static Context context;

    public static void setContext(Context context) {
        Const.context = context;
        init();
    }

    public static Context getContext() {
        return context;
    }

    private static void init() {
        LitePal.initialize(context);
        DataBroadcast.registerReceiver(context);
        InitNetwork.loadNetwork(context, ApiService.class);
    }

    public final static Logger monitorLogger = com.micro.root.Logger.getLogger("DY", "MonitorLog");
    public final static Logger controlLogger = com.micro.root.Logger.getLogger("DY", "ControlLog");
    public final static Logger netLogger = com.micro.root.Logger.getLogger("DY", "NetLog");

    public final static boolean isAuto = false;

    public final static String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    public final static String APPLICATION = "android.app.Application";
    // "com.ss.android.ugc.aweme.app.AwemeApplication"
    // "com.ss.android.ugc.aweme.app.host.HostApplication"
    // "com.ss.android.ugc.aweme.app.BaseMediaApplication";
    public final static List<String> VERSIONS = Arrays.asList("9.6.0");
    public final static String CREATE_DIR = "Micro/Backup/init";
}