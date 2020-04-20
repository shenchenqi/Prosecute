package com.micro.wechat.inflood.inner.execute.api;

import android.content.Context;

import com.micro.hook.api.BaseApi;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;

/**
 * @Author KiLin
 * @Time 2020/4/11 10:50
 */
public abstract class WeChatBaseApi extends BaseApi implements BaseInterface {

    protected WeChatBaseApi(Hook hook, Context context) {
        super(hook, context);
    }
}