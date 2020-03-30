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

    protected abstract void mainClass();

    public static String AWEME_MAIN_ACTIVITY_CLASS;
    public static String AWEME_MAIN_ACTIVITY_VIDEO_CHANGE_METHOD;
    public static String AWEME_MAIN_FRAGMENT_CLASS;
    public static String AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD;
    public static String AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD;

    protected abstract void feedClass();

    public static String AWEME_FEED_VIDEO_CLASS;
    public static String AWEME_FEED_MODEL_AWEME_FIELD;

    protected abstract void awemeClass();

    public static String AWEME_FEED_MODEL_AWEME_CLASS;
    public static String AWEME_APPEND_MOB_PARAM_METHOD;
    public static String AWEME_UPDATE_METHOD;
    public static String AWEME_CLONE_METHOD;
    public static String AWEME_TO_STRING_METHOD;

    protected abstract void userClass();

    public static String AWEME_PROFILE_USER_FRAGMENT_CLASS;
    public static String AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD;
    public static String AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD;
    public static String AWEME_PROFILE_USER_CLASS;

    protected abstract void callClass();

    public static String AWEME_PROFILE_VIDEO_CALL_CLASS;
    public static String AWEME_PROFILE_VIDEO_CALL_REQUEST_METHOD;
    public static String AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD;
}