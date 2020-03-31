package com.micro.hook.setup;

import com.micro.root.mvp.BaseInterface;

/**
 * created by kilin on 20-3-18 上午9:31
 */
public interface SetupInter extends BaseInterface {
    void initParam(String version);

    void hide();
}