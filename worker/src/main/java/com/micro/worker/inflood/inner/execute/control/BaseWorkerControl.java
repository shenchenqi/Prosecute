package com.micro.worker.inflood.inner.execute.control;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.control.BaseControl;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 13:48
 */
public abstract class BaseWorkerControl extends BaseControl {

    protected BaseWorkerControl(Context context, Hook hook) {
        super(context, hook);
    }
}