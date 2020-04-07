package com.micro.tremolo;

import android.content.Context;

import com.micro.root.Logger;
import com.micro.root.utils.InspectApply;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 */
public interface Const {

    Logger monitorLogger = com.micro.root.Logger.getLogger("tremoloLog", "MonitorLog");

    boolean isAuto = true;
    int fansCount = 10000;

    String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    String APPLICATION = "android.app.Application";
    // "com.ss.android.ugc.aweme.app.AwemeApplication"
    // "com.ss.android.ugc.aweme.app.host.HostApplication"
    // "com.ss.android.ugc.aweme.app.BaseMediaApplication";
    List<String> VERSIONS = Arrays.asList("9.6.0");
    String CREATE_DIR = "Micro/Backup/init";

    static void tremoloApp(Context context) {
        if (!InspectApply.checkApkExist(context, PACKAGE_NAME)) {
            InspectApply.installAPK(context, InspectApply.apkPath(context, "douyin_v9.6.0.apk"), "9.6.0");
        }
    }
}