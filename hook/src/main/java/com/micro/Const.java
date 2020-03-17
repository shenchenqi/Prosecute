package com.micro;

import com.micro.hook.BuildConfig;
import com.micro.root.Logger;

/**
 * created by kilin on 20-3-17 上午11:23
 */
public interface Const {

    Logger hookLog = Logger.getLogger("HookPluginLog");
    Logger romLog = Logger.getLogger("RomPluginLog");

    boolean DEBUG = BuildConfig.DEBUG;
}