package com.micro.tremolo.inflood.version.impl;

import com.micro.tremolo.inflood.version.TremoloParam;

/**
 * created by kilin on 20-3-17 下午5:07
 */
public class V960 extends TremoloParam {

    public V960() {
        awemeClass();
    }

    @Override
    protected void awemeClass() {
        AWEME_FEED_MODEL_AWEME_CLASS = "com.ss.android.ugc.aweme.feed.model.Aweme";
        AWEME_APPEND_MOB_PARAM_METHOD = "appendMobParam";
        AWEME_UPDATE_METHOD = "update";
        AWEME_CLONE_METHOD = "clone";
        AWEME_TO_STRING_METHOD = "toString";
    }
}