package com.micro.worker.inflood.inner.execute.api;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.worker.inflood.inner.replace.User;
import com.micro.worker.inflood.version.WorkerParam;

import static com.micro.worker.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/14 15:28
 */
public class UserProfileApi extends BaseApi {

    private static UserProfileApi mUserProfileApi;

    public static void initApi(Hook hook, Context context) {
        if (mUserProfileApi == null) {
            mUserProfileApi = new UserProfileApi(hook, context);
        }
    }

    public static void loadApi(User user, final Callback callback) {
        taskLogger.d(user.toString());
        mUserProfileApi.loadProfileUserApi(user.getId(), callback);
    }

    private UserProfileApi(Hook hook, Context context) {
        super(hook, context);
    }

    private void loadProfileUserApi(String userId, final Callback callback) {
        loadProfileUserApi(userId, new BaseCallback() {
            @Override
            public void success(String data) {
                taskLogger.d(data);
            }

            @Override
            public void fail(Throwable e, String msg) {
                taskLogger.e(e, msg);
                //callback.finish();
            }
        });
    }

    private void loadProfileUserApi(final String userId, final BaseCallback callback) {
        post(second * 5, () -> startThread(() -> {
            try {
                Object object = profileUserApi(userId);
                callback.success(getJsonString(object));
            } catch (Throwable e) {
                callback.fail(e, "用户信息 报错: ");
            }
        }));
    }

    private Object profileUserApi(String userId) {
        return hook.callMethod(getApiService(), WorkerParam.API_SERVICE_USER_PROFILE_METHOD, userId, true, getValue(0));
    }

    public interface Callback {
        void complete();

        void finish();
    }
}