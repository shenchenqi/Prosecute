package com.micro.worker.inflood.inner.execute.api;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.worker.inflood.inner.replace.User;
import com.micro.worker.inflood.version.WorkerParam;

import static com.micro.worker.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/14 15:28
 */
public class UserProfileApiWorker extends BaseWorkerApi {

    private static UserProfileApiWorker mUserProfileApi;

    public static void initApi(Hook hook, Context context) {
        if (mUserProfileApi == null) {
            mUserProfileApi = new UserProfileApiWorker(hook, context);
        }
    }

    public static void loadApi(User user, final Callback callback) {
        taskLogger.d(user.toString());
        mUserProfileApi.loadProfileUserApi(user.getId(), callback);
    }

    private UserProfileApiWorker(Hook hook, Context context) {
        super(hook, context);
    }

    private void loadProfileUserApi(String userId, final Callback callback) {
        loadProfileUserApi(userId, new BaseCallback() {

            @Override
            public void success(boolean isFirst, String data) {
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
        run(second * 5, () -> {
            try {
                Object object = profileUserApi(userId);
                Object mapObject = hook.callMethod(object, "map", hook.newInstance("com.yxcorp.retrofit.consumer.e"));
            /*Object callbackObject = hook.newInstance("io.reactivex.c.g", getIContext());
            hook.methodMonitor(callbackObject.getClass(), "accept", new ForeignHook() {
                @Override
                public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                    callback.success(getJsonString(param.getArgs()[0]));
                }
            }, Object.class);
            Object throwableObject = hook.newInstance("com.yxcorp.gifshow.retrofit.a.c", getIContext());
            hook.methodMonitor(throwableObject.getClass(), "accept", new ForeignHook() {
                @Override
                public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                    callback.fail((Throwable) param.getArgs()[0], "用户信息 报错: ");
                }
            }, Throwable.class);*/
                Object subscribeObject = hook.callMethod(mapObject, "subscribe", null, null);
                hook.callMethod(hook.newInstance("io.reactivex.disposables.a"), "a", subscribeObject);
            } catch (Throwable e) {
                callback.fail(e, "用户信息 报错: ");
            }
        });
    }

    private Object profileUserApi(String userId) {
        return hook.callMethod(getApiService(), WorkerParam.API_SERVICE_USER_PROFILE_METHOD, userId, true, getValue(0));
    }

    public interface Callback {
        void complete();

        void finish();
    }
}