package com.micro.wechat.inflood.inner.execute.api;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;

/**
 * @Author KiLin
 * @Time 2020/4/11 10:50
 */
public abstract class BaseApi implements BaseInterface {

    protected final Hook hook;
    private final Handler handler;
    private final Context context;

    protected BaseApi(Hook hook, Context context) {
        this.hook = hook;
        this.handler = new Handler(context.getMainLooper());
        this.context = context;
    }

    protected synchronized void startThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    protected synchronized void post(long time, Runnable runnable) {
        if (time <= 0) {
            handler.post(runnable);
        } else {
            handler.postDelayed(runnable, time);
        }
    }

    protected synchronized String getJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    protected synchronized JSONObject getJsonObject(String text) {
        return JSON.parseObject(text);
    }

    @Override
    public Context getIContext() {
        return context;
    }

    protected interface BaseCallback {
        void success(boolean isFirst, String data);

        void fail(Throwable e, String msg);
    }
}