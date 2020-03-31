package com.micro.tremolo.inflood.inner.execute.control;

import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;

public interface ControlInter extends BaseInterface {

    void autoControl(Hook hook);
}