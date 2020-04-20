package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.control.BaseControl;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 13:46
 */
public abstract class BaseTremoloControl extends BaseControl {

    protected BaseTremoloControl(Context context, Hook hook) {
        super(context, hook);
    }
}