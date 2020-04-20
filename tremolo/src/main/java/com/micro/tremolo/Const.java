package com.micro.tremolo;

import android.content.Context;

import com.micro.HookConst;
import com.micro.root.Logger;
import com.micro.tremolo.broad.DataBroadcast;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 * 抖音配置接口
 */
public interface Const extends HookConst {

    String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    String APPLICATION = "android.app.Application";
    // com.ss.android.ugc.aweme.app.AwemeApplication
    // com.ss.android.ugc.aweme.app.host.HostApplication
    // com.ss.android.ugc.aweme.app.BaseMediaApplication
    // android.app.Application;
    List<String> VERSIONS = Arrays.asList("9.6.0");
    String CREATE_DIR = "Micro/Backup/Tremolo/init";

    Logger rootLogger = Logger.getLogger("TremoloLog", "RootLog");
    Logger monitorLogger = Logger.getLogger("TremoloLog", "MonitorLog");
    Logger controlLogger = Logger.getLogger("TremoloLog", "ControlLog");
    Logger taskLogger = Logger.getLogger("TremoloLog", "TaskLog");

    int collectType = 2;//采集类型 0-未知;1-控制布局UI;2-广域获取数据;3-狭域监控数据

    boolean isAutoUI = collectType == 1;
    boolean isWideArea = collectType == 2;
    boolean isNarrowArea = collectType == 3;
    int fansCount = 10000;

    static void initTremoloApp(Context context) {
        LitePal.initialize(context);
        DataBroadcast.registerReceiver(context);
        HookConst.installApp(context, PACKAGE_NAME, "douyin_v9.6.0.apk", "9.6.0");
    }

    static int isTremoloForegroundApply(Context context) {
        return HookConst.isAppOnForeground(context, PACKAGE_NAME);
    }

    static void openTremoloApply(Context context) {
        HookConst.openApply(context, PACKAGE_NAME);
    }

    static void setTremoloTopApply(Context context) {
        HookConst.setTopApply(context, PACKAGE_NAME);
    }
}