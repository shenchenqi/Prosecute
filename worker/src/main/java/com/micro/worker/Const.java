package com.micro.worker;

import android.content.Context;

import com.micro.HookConst;
import com.micro.root.Logger;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/11 15:55
 * 快手配置接口
 */
public interface Const extends HookConst {

    String PACKAGE_NAME = "com.smile.gifmaker";
    String APPLICATION = "android.app.Application";
    //com.yxcorp.gifshow.App
    //com.kwai.hotfix.loader.app.TinkerApplication
    //android.app.Application
    List<String> VERSIONS = Arrays.asList("7.2.0.12758");
    String CREATE_DIR = "Micro/Backup/Worker/init";

    Logger monitorLogger = Logger.getLogger("WorkerLog", "MonitorLog");
    Logger taskLogger = Logger.getLogger("WorkerLog", "TaskLog");

    int collectType = 2;//采集类型 0-未知;1-控制布局UI;2-广域获取数据;3-狭域监控数据

    static void initWorkerApply(Context context) {
        LitePal.initialize(context);
        HookConst.installApp(context, PACKAGE_NAME, "kuaishou_v7.2.0.12758.apk", "7.2.0.12758");
    }

    static int isWorkerForegroundApply(Context context) {
        return HookConst.isAppOnForeground(context, Const.PACKAGE_NAME);
    }

    static void openWorkerApply(Context context) {
        HookConst.openApply(context, Const.PACKAGE_NAME);
    }

    static void setWorkerTopApply(Context context) {
        HookConst.setTopApply(context, Const.PACKAGE_NAME);
    }
}