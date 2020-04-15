package com.micro.worker;

import android.content.Context;

import com.micro.root.Logger;
import com.micro.root.utils.InspectApply;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/11 15:55
 * 快手配置接口
 */
public interface Const {

    String PACKAGE_NAME = "com.smile.gifmaker";
    String APPLICATION = "android.app.Application";
    //com.yxcorp.gifshow.App
    //com.kwai.hotfix.loader.app.TinkerApplication
    //android.app.Application
    List<String> VERSIONS = Arrays.asList("7.2.0.12758");
    String CREATE_DIR = "Micro/Backup/init";

    Logger monitorLogger = Logger.getLogger("workerLog", "MonitorLog");
    Logger taskLogger = Logger.getLogger("workerLog", "TaskLog");

    int collectType = 2;//采集类型 0-未知;1-控制布局UI;2-广域获取数据;3-狭域监控数据

    static void workerApp(Context context) {
        LitePal.initialize(context);

        if (!InspectApply.checkApkExist(context, PACKAGE_NAME)) {
            InspectApply.installAPK(context, InspectApply.apkPath(context, "kuaishou_v7.2.0.12758.apk"), "7.2.0.12758");
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