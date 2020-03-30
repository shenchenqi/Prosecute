package com.micro.tremolo.inflood.version.impl;

import com.micro.tremolo.inflood.version.TremoloParam;

/**
 * created by kilin on 20-3-17 下午5:07
 */
public class V960 extends TremoloParam {

    public V960() {
        mainClass();
        feedClass();
        awemeClass();
        userClass();
        callClass();
    }

    @Override
    protected void mainClass() {
        AWEME_MAIN_ACTIVITY_CLASS = "com.ss.android.ugc.aweme.main.MainActivity";
        AWEME_MAIN_ACTIVITY_VIDEO_CHANGE_METHOD = "onVideoPageChangeEvent";
        AWEME_MAIN_FRAGMENT_CLASS = "com.ss.android.ugc.aweme.main.MainFragment";
        AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD = "onViewCreated";
        AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD = "onVideoPageChangeEvent";
    }

    @Override
    protected void feedClass() {
        AWEME_FEED_VIDEO_CLASS = "com.ss.android.ugc.aweme.feed.e.ae";
        AWEME_FEED_MODEL_AWEME_FIELD = "a";
    }

    @Override
    protected void awemeClass() {
        AWEME_FEED_MODEL_AWEME_CLASS = "com.ss.android.ugc.aweme.feed.model.Aweme";
        AWEME_APPEND_MOB_PARAM_METHOD = "appendMobParam";
        AWEME_UPDATE_METHOD = "update";
        AWEME_CLONE_METHOD = "clone";
        AWEME_TO_STRING_METHOD = "toString";
    }

    @Override
    protected void userClass() {
        AWEME_PROFILE_USER_FRAGMENT_CLASS = "com.ss.android.ugc.aweme.profile.ui.UserProfileFragment";
        AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD = "a";
        AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD = "onViewCreated";
        AWEME_PROFILE_USER_CLASS = "com.ss.android.ugc.aweme.profile.model.User";
    }

    @Override
    protected void callClass() {
        AWEME_PROFILE_VIDEO_CALL_CLASS = "com.ss.android.ugc.aweme.profile.presenter.b";
        AWEME_PROFILE_VIDEO_CALL_REQUEST_METHOD = "a";
        AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD = "getItems";
    }
}