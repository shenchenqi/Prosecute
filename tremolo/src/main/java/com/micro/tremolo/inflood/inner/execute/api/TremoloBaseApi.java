package com.micro.tremolo.inflood.inner.execute.api;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.hook.api.BaseApi;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;

/**
 * @Author KiLin
 * @Time 2020/4/11 10:50
 */
public abstract class TremoloBaseApi extends BaseApi implements BaseInterface {

    protected TremoloBaseApi(Hook hook, Context context) {
        super(hook, context);
    }
}