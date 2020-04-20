package com.micro.wechat.inflood.inner.execute.control;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.control.BaseControl;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 13:47
 */
public abstract class BaseWeChatControl extends BaseControl {

    protected BaseWeChatControl(Context context, Hook hook) {
        super(context, hook);
    }
}