package com.micro.tremolo.inflood.version.impl;

import com.micro.tremolo.inflood.version.TremoloParam;

/**
 * created by kilin on 20-3-17 下午5:07
 */
public class V960 extends TremoloParam {

    public V960() {
        PACKAGE = "com.ss.android.ugc.aweme";
        loadR();
        logClass();
        mainClass();
        feedClass();
        profileClass();
        utilsClass();
        dialogClass();
    }

    @Override
    protected void loadR() {
        PACKAGE_COLON = PACKAGE + ":";
        BIND_VIEW = "findViewById";
        MAIN_FRAGMENT_TOUCH_EVENT_STRING = PACKAGE_COLON + "id/av5";
        MAIN_FRAGMENT_TOUCH_EVENT_INTEGER = 2131167364;
        MAIN_FRAGMENT_ATTENTION_STRING = PACKAGE_COLON + "id/ewo";
        MAIN_FRAGMENT_ATTENTION_INTEGER = 2131172893;
        MAIN_FRAGMENT_LIVE_LAYOUT_STRING = PACKAGE_COLON + "id/c_u";
        MAIN_FRAGMENT_LIVE_LAYOUT_INTEGER = 2131169314;
        MAIN_FRAGMENT_LIVE_TEXT_STRING = PACKAGE_COLON + "id/ez_";
        MAIN_FRAGMENT_LIVE_TEXT_INTEGER = 2131172989;
        MAIN_FRAGMENT_LIVE_BT_STRING = PACKAGE_COLON + "id/c6b";
        MAIN_FRAGMENT_LIVE_BT_INTEGER = 2131169147;
        MAIN_FRAGMENT_SEARCH_STRING = PACKAGE_COLON + "id/avg";
        MAIN_FRAGMENT_SEARCH_INTEGER = 2131167376;
        MAIN_FRAGMENT_RECOMMEND_STRING = PACKAGE_COLON + "id/ey2";
        MAIN_FRAGMENT_RECOMMEND_INTEGER = 2131172944;
        MAIN_FRAGMENT_USER_STRING = PACKAGE_COLON + "id/fbr";
        MAIN_FRAGMENT_USER_INTEGER = 2131173488;
        USER_BACK_STRING = PACKAGE_COLON + "id/ko";
        USER_BACK_INTEGER = 2131165614;
        UPDATE_VERSION_CANCEL_STRING = PACKAGE_COLON + "id/fap";
        UPDATE_VERSION_CANCEL_INTEGER = 2131173449;
        INFO_CANCEL_STRING = PACKAGE_COLON + "id/a7h";
        INFO_CANCEL_INTEGER = 2131166455;
        UNDERAGE_CANCEL_STRING = PACKAGE_COLON + "id/a3";
        UNDERAGE_CANCEL_INTEGER = 2131165213;
    }

    @Override
    protected void logClass() {
        LOG_CLASS = "com.ss.android.agilelogger.ALog";
        LOG_SET_METHOD = "println";
        LOG_MSG_CLASS = "com.ss.android.agilelogger.d";
    }

    @Override
    protected void mainClass() {
        PACKAGE_MAIN = PACKAGE + ".main";
        AWEME_MAIN_ACTIVITY_CLASS = PACKAGE_MAIN + ".MainActivity";
        AWEME_MAIN_ACTIVITY_CHANGE_METHOD = "onCreate";
        AWEME_MAIN_ACTIVITY_TOUCH_EVENT_METHOD = "dispatchTouchEvent";
        AWEME_MAIN_ACTIVITY_VIDEO_CHANGE_METHOD = "onVideoPageChangeEvent";
        AWEME_MAIN_FRAGMENT_CLASS = PACKAGE_MAIN + ".MainFragment";
        AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD = "onViewCreated";
        AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD = "onVideoPageChangeEvent";
        AWEME_MAIN_PAGE_FRAGMENT_CLASS = PACKAGE_MAIN + ".MainPageFragment";
        AWEME_MAIN_PAGE_FRAGMENT_VIEW_CREATE_METHOD = "onViewCreated";
    }

    @Override
    protected void feedClass() {
        PACKAGE_FEED = PACKAGE + ".feed";
        AWEME_FEED_VIDEO_CLASS = PACKAGE_FEED + ".e.ae";
        AWEME_FEED_MODEL_AWEME_FIELD = "a";
        AWEME_FEED_MODEL_AWEME_CLASS = PACKAGE_FEED + ".model.Aweme";
        AWEME_APPEND_MOB_PARAM_METHOD = "appendMobParam";
        AWEME_UPDATE_METHOD = "update";
        AWEME_CLONE_METHOD = "clone";
        AWEME_TO_STRING_METHOD = "toString";
    }

    @Override
    protected void profileClass() {
        PACKAGE_PROFILE = PACKAGE + ".profile";
        AWEME_PROFILE_USER_FRAGMENT_CLASS = PACKAGE_PROFILE + ".ui.UserProfileFragment";
        AWEME_PROFILE_USER_FRAGMENT_SHOW_USER_METHOD = "h";
        AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD = "a_";
        AWEME_PROFILE_USER_FRAGMENT_TO_USER_METHOD = "g";
        AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD = "onViewCreated";
        AWEME_PROFILE_USER_CLASS = PACKAGE_PROFILE + ".model.User";
        AWEME_PROFILE_VIDEO_CALL_CLASS = PACKAGE_PROFILE + ".presenter.b";
        AWEME_PROFILE_VIDEO_CALL_REQUEST_METHOD = "a";
        AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD = "getItems";
    }

    @Override
    protected void utilsClass() {
        SEC_UID_MANAGER_CLASS = PACKAGE + ".utils.ej";
        SEC_UID_MANAGER_GET_INSTANCE_METHOD = "a";
        SEC_UID_MANAGER_GET_SEC_USER_ID_METHOD = "b";
    }

    @Override
    protected void dialogClass() {
        DIALOG = "com.bytedance.ies.dmt.ui.c.a";
        DIALOG_CLASS = DIALOG + ".a";
        DIALOG_VIEW_METHOD = "a";
        DIALOG_UPDATE_CLASS = PACKAGE + ".update.m";
        DIALOG_UPDATE_CREATE_METHOD = "onCreate";
        DIALOG_INFO_CLASS = PACKAGE + ".main.cq";
        DIALOG_INFO_CREATE_METHOD = "onCreate";
    }
}