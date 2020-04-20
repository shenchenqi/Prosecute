package com.micro.tremolo.inflood.inner.execute.api;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.model.from.Author;

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 11:39
 */
public class ProfileOtherApiTremolo extends BaseTremoloApi {

    private final static String USER_URL;

    static {
        USER_URL = "https://aweme.snssdk.com/aweme/v1/user/profile/other/?sec_user_id={secUserId}&address_book_access=1&from=0";
    }

    private static ProfileOtherApiTremolo mProfileOtherApi;

    public static void setInstance(Hook hook, Context context) {
        if (mProfileOtherApi == null) {
            mProfileOtherApi = new ProfileOtherApiTremolo(hook, context);
        }
    }

    public static void loadApi(String secUserId, Callback callback) {
        mProfileOtherApi.loadProfileOtherApi(secUserId, callback);
    }

    private ProfileOtherApiTremolo(Hook hook, Context context) {
        super(hook, context);
    }

    private void loadProfileOtherApi(String secUserId, final Callback callback) {
        loadProfileOtherApi(secUserId, new BaseCallback() {
            @Override
            public void success(boolean isFirst, String data) {
                if (Lang.isEmpty(data)) {
                    taskLogger.e("抖音用户信息 返回数据 为空");
                    callback.finish();
                } else {
                    JSONObject dataObject = getJsonObject(data);
                    if (Lang.isNull(dataObject)) {
                        taskLogger.e("抖音用户信息 返回数据 解析出错");
                        callback.finish();
                    } else {
                        callback.complete(new Author(dataObject.getString("user")));
                    }
                }
            }

            @Override
            public void fail(Throwable e, String msg) {
                taskLogger.e(e, msg);
                callback.finish();
            }
        });
    }

    private void loadProfileOtherApi(final String secUserId, final BaseCallback callback) {
        run(second * 5, (Runnable) () -> {
            try {
                Object object = profileOtherApi(secUserId);
                callback.success(true, getJsonString(object));
            } catch (Throwable e) {
                callback.fail(e, "用户信息 报错: ");
            }
        });
    }

    private Object profileOtherApi(String secUserId) {
        taskLogger.d(String.format("调用用户信息接口,猜数[%s]", secUserId));
        if (Lang.isEmpty(secUserId)) {
            return "";
        }
        String url = USER_URL.replace("{secUserId}", secUserId);
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_USER_METHOD, url, false, null);
    }

    public interface Callback {

        void complete(Author author);

        void finish();
    }
}