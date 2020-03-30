package com.micro.hook.plugin;

import com.micro.hook.config.Hook;

import java.util.concurrent.atomic.AtomicLong;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public interface PluginInter {
    long second = new AtomicLong(1000).get();
    long minute = new AtomicLong(60 * 1000).get();
    long hour = new AtomicLong(60 * 60 * 1000).get();
    long day = new AtomicLong(24 * 60 * 60 * 1000).get();
    long week = new AtomicLong(7 * 24 * 60 * 60 * 1000).get();

    void monitor(Hook hook);

    void autoControl();
}