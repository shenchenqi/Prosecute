package com.micro;

import com.micro.root.Logger;

/**
 * created by kilin on 20-3-17 上午11:23
 */
public interface Const {

    Logger hookLog = Logger.getLogger("XpHook","PluginLog");

    int moduleStatus = 1;//0-多模块；1-抖音模块；2-快手模块
}