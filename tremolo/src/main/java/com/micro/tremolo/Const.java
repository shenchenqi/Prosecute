package com.micro.tremolo;

import com.micro.root.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 */
public interface Const {

    Logger monitorLogger = com.micro.root.Logger.getLogger("tremoloLog", "MonitorLog");
    Logger controlLogger = com.micro.root.Logger.getLogger("tremoloLog", "ControlLog");
    Logger netLogger = com.micro.root.Logger.getLogger("tremoloLog", "NetLog");

    boolean isAuto = true;
    int fansCount = 3000;

    String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    String APPLICATION = "android.app.Application";
    // "com.ss.android.ugc.aweme.app.AwemeApplication"
    // "com.ss.android.ugc.aweme.app.host.HostApplication"
    // "com.ss.android.ugc.aweme.app.BaseMediaApplication";
    List<String> VERSIONS = Arrays.asList("9.6.0");
    String CREATE_DIR = "Micro/Backup/init";
}