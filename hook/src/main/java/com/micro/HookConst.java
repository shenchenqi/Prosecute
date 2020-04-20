package com.micro;

import android.content.Context;

import com.micro.root.Logger;
import com.micro.root.utils.InspectApply;

/**
 * created by kilin on 20-3-17 上午11:23
 */
public interface HookConst {

    Logger hookLog = Logger.getLogger("XpHook","PluginLog");

    int moduleStatus = 0;//0-多模块；1-抖音模块；2-快手模块

    static void installApp(Context context, String packageName, String pakName, String version) {
        if (!InspectApply.checkApkExist(context, packageName)) {
            InspectApply.installAPK(context, InspectApply.apkPath(context, pakName), version);
        }
    }

    static int isAppOnForeground(Context context, String packageName) {
        return InspectApply.isAppOnForeground(context, packageName);
    }

    static void openApply(Context context, String packageName) {
        InspectApply.openApply(context, packageName);
    }

    static void setTopApply(Context context, String packageName) {
        InspectApply.setBackstageToFrontDesk(context, packageName);
    }
}