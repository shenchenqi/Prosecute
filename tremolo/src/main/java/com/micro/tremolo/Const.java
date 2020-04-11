package com.micro.tremolo;

import android.content.Context;

import com.micro.root.Logger;
import com.micro.root.utils.InspectApply;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 * 抖音配置接口
 */
public interface Const {

    String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    String APPLICATION = "android.app.Application";
    // com.ss.android.ugc.aweme.app.AwemeApplication
    // com.ss.android.ugc.aweme.app.host.HostApplication
    // com.ss.android.ugc.aweme.app.BaseMediaApplication
    // android.app.Application;
    List<String> VERSIONS = Arrays.asList("9.6.0");
    String CREATE_DIR = "Micro/Backup/init";

    Logger rootLogger = Logger.getLogger("tremoloLog", "RootLog");
    Logger monitorLogger = Logger.getLogger("tremoloLog", "MonitorLog");
    Logger controlLogger = Logger.getLogger("tremoloLog", "ControlLog");
    Logger taskLogger = Logger.getLogger("tremoloLog", "TaskLog");

    int collectType = 2;//采集类型 0-未知;1-控制布局UI;2-广域获取数据;3-狭域监控数据

    boolean isAutoUI = collectType == 1;
    boolean isWideArea = collectType == 2;
    boolean isNarrowArea = collectType == 3;
    int fansCount = 10000;

    static void tremoloApp(Context context) {
        if (!InspectApply.checkApkExist(context, PACKAGE_NAME)) {
            InspectApply.installAPK(context, InspectApply.apkPath(context, "douyin_v9.6.0.apk"), "9.6.0");
        }
    }

    static int isAppOnForeground(Context context) {
        return InspectApply.isAppOnForeground(context, Const.PACKAGE_NAME);
    }

    static void openApply(Context context) {
        InspectApply.openApply(context, Const.PACKAGE_NAME);
    }

    static void setTopApply(Context context) {
        InspectApply.setBackstageToFrontDesk(context, Const.PACKAGE_NAME);
    }
}