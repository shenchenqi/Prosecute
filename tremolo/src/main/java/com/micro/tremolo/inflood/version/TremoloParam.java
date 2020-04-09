package com.micro.tremolo.inflood.version;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:05
 */
public abstract class TremoloParam {
    public static void init(String version) {
        String impl = "com.micro.tremolo.inflood.version.impl.V" + version.replaceAll("\\.", "");
        try {
            logger.i("PARAM", String.format("初始化参数：%s", impl));
            Class implClazz = Class.forName(impl);
            implClazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected static String PACKAGE;

    protected abstract void loadR();

    protected static String PACKAGE_COLON;
    public static String BIND_VIEW;

    public static String MAIN_FRAGMENT_TOUCH_EVENT_STRING;
    public static Integer MAIN_FRAGMENT_TOUCH_EVENT_INTEGER;
    public static String MAIN_FRAGMENT_ATTENTION_STRING;
    public static Integer MAIN_FRAGMENT_ATTENTION_INTEGER;
    public static String MAIN_FRAGMENT_LIVE_LAYOUT_STRING;
    public static Integer MAIN_FRAGMENT_LIVE_LAYOUT_INTEGER;
    public static String MAIN_FRAGMENT_LIVE_TEXT_STRING;
    public static Integer MAIN_FRAGMENT_LIVE_TEXT_INTEGER;
    public static String MAIN_FRAGMENT_LIVE_BT_STRING;
    public static Integer MAIN_FRAGMENT_LIVE_BT_INTEGER;
    public static String MAIN_FRAGMENT_SEARCH_STRING;
    public static Integer MAIN_FRAGMENT_SEARCH_INTEGER;
    public static String MAIN_FRAGMENT_RECOMMEND_STRING;
    public static Integer MAIN_FRAGMENT_RECOMMEND_INTEGER;
    public static String MAIN_FRAGMENT_USER_STRING;
    public static Integer MAIN_FRAGMENT_USER_INTEGER;
    public static String USER_BACK_STRING;
    public static Integer USER_BACK_INTEGER;
    public static String UPDATE_VERSION_CANCEL_STRING;
    public static Integer UPDATE_VERSION_CANCEL_INTEGER;
    public static String INFO_CANCEL_STRING;
    public static Integer INFO_CANCEL_INTEGER;
    public static String UNDERAGE_CANCEL_STRING;
    public static Integer UNDERAGE_CANCEL_INTEGER;

    protected abstract void logClass();

    public static String LOG_CLASS;
    public static String LOG_SET_METHOD;
    public static String LOG_MSG_CLASS;

    protected abstract void mainClass();

    protected static String PACKAGE_MAIN;

    public static String AWEME_MAIN_ACTIVITY_CLASS;
    public static String AWEME_MAIN_ACTIVITY_CHANGE_METHOD;
    public static String AWEME_MAIN_ACTIVITY_TOUCH_EVENT_METHOD;
    public static String AWEME_MAIN_ACTIVITY_VIDEO_CHANGE_METHOD;
    public static String AWEME_MAIN_FRAGMENT_CLASS;
    public static String AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD;
    public static String AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD;
    public static String AWEME_MAIN_PAGE_FRAGMENT_CLASS;
    public static String AWEME_MAIN_PAGE_FRAGMENT_VIEW_CREATE_METHOD;

    protected abstract void feedClass();

    protected static String PACKAGE_FEED;

    public static String AWEME_FEED_VIDEO_CLASS;
    public static String AWEME_FEED_MODEL_AWEME_FIELD;
    public static String AWEME_FEED_MODEL_AWEME_CLASS;
    public static String AWEME_APPEND_MOB_PARAM_METHOD;
    public static String AWEME_UPDATE_METHOD;
    public static String AWEME_CLONE_METHOD;
    public static String AWEME_TO_STRING_METHOD;

    protected abstract void profileClass();

    protected static String PACKAGE_PROFILE;

    public static String AWEME_PROFILE_USER_FRAGMENT_CLASS;
    public static String AWEME_PROFILE_USER_FRAGMENT_SHOW_USER_METHOD;
    public static String AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD;
    public static String AWEME_PROFILE_USER_FRAGMENT_TO_USER_METHOD;
    public static String AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD;
    public static String AWEME_PROFILE_USER_CLASS;
    public static String AWEME_PROFILE_VIDEO_CALL_CLASS;
    public static String AWEME_PROFILE_VIDEO_CALL_REQUEST_METHOD;
    public static String AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD;
    public static String AWEME_PROFILE_VIDEO_PROFILE_API_CLASS;
    public static String AWEME_PROFILE_VIDEO_PROFILE_API_USER_METHOD;
    public static String AWEME_PROFILE_VIDEO_AWEME_API_CLASS;
    public static String AWEME_PROFILE_VIDEO_AWEME_API_LIST_METHOD;

    protected abstract void utilsClass();

    public static String SEC_UID_MANAGER_CLASS;
    public static String SEC_UID_MANAGER_GET_INSTANCE_METHOD;
    public static String SEC_UID_MANAGER_GET_SEC_USER_ID_METHOD;

    protected abstract void dialogClass();

    public static String DIALOG_CLASS;
    public static String DIALOG_HIDE_METHOD;
    public static String DIALOG_CREATE_CLASS;
    public static String DIALOG_VIEW_METHOD;
    public static String DIALOG_ONE_METHOD;
    public static String DIALOG_TWO_METHOD;
    public static String DIALOG_UPDATE_CLASS;
    public static String DIALOG_UPDATE_CREATE_METHOD;
    public static String DIALOG_INFO_CLASS;
    public static String DIALOG_INFO_CREATE_METHOD;
}