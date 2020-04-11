package com.micro.tremolo.inflood.inner.execute;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/7 10:57
 */
public class TremoloApi {
    private final static String USER_URL;

    static {
        USER_URL = "https://aweme.snssdk.com/aweme/v1/user/profile/other/?sec_user_id={secUserId}&address_book_access=1&from=0";
    }

    private synchronized static void startThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void searchUserApi(final Hook hook, final String search, final long cursor, final int limit, final int count, final String requestId, final Callback callback) {
        startThread(() -> {
            try {
                Object object = searchUserApi(hook, search, cursor, limit, count, requestId);
                callback.success(JSON.toJSONString(object));
            } catch (Throwable e) {
                callback.fail(e,"查询用户接口报错: ");
            }
        });
    }

    public static void profileApi(final Hook hook, final String secUserId, final Callback callback) {
        startThread(() -> {
            try {
                Object object = profileApi(hook, secUserId);
                callback.success(JSON.toJSONString(object));
            } catch (Throwable e) {
                callback.fail(e,"用户信息报错: ");
            }
        });
    }

    public static void feedVideoApi(final Hook hook, final boolean isFirst, final String userId, final String secUserId, final long time, final int limit, final Callback callback) {
        startThread(() -> {
            try {
                Object object = feedVideoApi(hook, isFirst, userId, secUserId, time, limit);
                callback.success(JSON.toJSONString(object));
            } catch (Throwable e) {
                callback.fail(e,"用户视频列表报错: ");
            }
        });
    }

    private static Object searchUserApi(Hook hook, String search, long size, int limit, int count, String requestId) {
        monitorLogger.d("search >>> " + search);
        if (Lang.isEmpty(search)) {
            return "";
        }
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_SEARCH_USER_API_CLASS, TremoloParam.AWEME_PROFILE_SEARCH_USER_USER_METHOD, search, size, limit, count, 0, "", requestId, 1);
    }

    private static Object profileApi(Hook hook, String secUserId) {
        monitorLogger.d("secUserId >>> " + secUserId);
        if (Lang.isEmpty(secUserId)) {
            return "";
        }
        String url = USER_URL.replace("{secUserId}", secUserId);
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_USER_METHOD, url, false, null);
    }

    private static Object feedVideoApi(Hook hook, boolean isFirst, String userId, String secUserId, long time, int limit) {
        monitorLogger.d("userId >>> " + userId + ", secUserId >>> " + secUserId + ", time >>> " + time);
        if (Lang.isEmpty(userId) || Lang.isEmpty(secUserId)) {
            return "";
        }
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_LIST_METHOD, isFirst, userId, secUserId, 0, time, limit, null);
    }

    public interface Callback {
        void success(String data);

        void fail(Throwable e, String msg);
    }
}