package com.micro.root.mvp;

import android.content.Context;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:43
 */
public interface BaseInterface {
    long second = new AtomicLong(1000).get();
    long minute = new AtomicLong(60 * 1000).get();
    long hour = new AtomicLong(60 * 60 * 1000).get();
    long day = new AtomicLong(24 * 60 * 60 * 1000).get();
    long week = new AtomicLong(7 * 24 * 60 * 60 * 1000).get();

    Context getIContext();
}