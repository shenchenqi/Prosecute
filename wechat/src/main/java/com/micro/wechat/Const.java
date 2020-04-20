package com.micro.wechat;

import android.content.Context;

import com.micro.HookConst;
import com.micro.root.Logger;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 9:13
 * 微信配置接口
 */
public interface Const extends HookConst {

    String PACKAGE_NAME = "com.tencent.mm";
    String APPLICATION = "com.tencent.tinker.loader.app.TinkerApplication";
    List<String> VERSIONS = Arrays.asList("7.0.6");
    String CREATE_DIR = "Micro/Backup/WeChat/init";

    Logger monitorLogger = Logger.getLogger("WeChatLog", "MonitorLog");
    Logger taskLogger = Logger.getLogger("WeChatLog", "TaskLog");

    int collectType = 2;//采集类型 0-未知;1-控制布局UI;2-广域获取数据;3-狭域监控数据

    static void initWeChatApply(Context context) {
        LitePal.initialize(context);
        HookConst.installApp(context, PACKAGE_NAME, "kuaishou_v7.2.0.12758.apk", "7.2.0.12758");
    }

    static int isWeChatForegroundApply(Context context) {
        return HookConst.isAppOnForeground(context, Const.PACKAGE_NAME);
    }

    static void openWeChatApply(Context context) {
        HookConst.openApply(context, Const.PACKAGE_NAME);
    }

    static void setWeChatTopApply(Context context) {
        HookConst.setTopApply(context, Const.PACKAGE_NAME);
    }
}