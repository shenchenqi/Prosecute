package com.micro.hook;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.HandlerThread;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.EventCallback;
import com.micro.hook.config.Hook;
import com.micro.hook.config.HookParam;
import com.micro.root.Logger;
import com.micro.root.utils.Lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by kilin on 20-3-17 下午2:04
 */
public abstract class ExecuteMonitor implements EventCallback {

    private static final String TAG = "ExecuteMonitor";
    private static final Logger logger = Logger.getLogger("hook", "InitLog");

    private final static Map<String, Class> mHookRegisterMap = new HashMap<>();

    public static void loadRegisterHook(HookParam hookParam) {
        logger.i(TAG, "加载注册Hook 数量有：" + mHookRegisterMap.size());
        for (Class clazz : mHookRegisterMap.values()) {
            Hook.method(clazz, "init", hookParam);
        }
    }

    public static void setHookRegister(String key, Class clazz) {
        logger.i(TAG, "注册Hook");
        if (mHookRegisterMap.containsKey(key)) {
            mHookRegisterMap.remove(key);
            mHookRegisterMap.put(key, clazz);
        } else {
            mHookRegisterMap.put(key, clazz);
        }
    }

    protected final HookParam hookParam;
    private final Handler handler;

    protected ExecuteMonitor(HookParam hookParam) {
        this.hookParam = hookParam;
        this.handler = getHandler();
        initHook();
    }

    private Handler getHandler() {
        HandlerThread thread = new HandlerThread("load_app");
        thread.start();
        return new Handler(thread.getLooper());
    }

    protected abstract String getPackageName();

    protected abstract String applicationPath();

    protected abstract List<String> versions();

    private boolean isCurrentVersion(int versionCode, String versionName) {
        logger.i(TAG, String.format("版本信息: [%s]-[%s]", versionName, versionCode));
        return versions().contains(versionName);
    }

    private void initHook() {
        try {
            if (!hookParam.isFirstApplication()) {
                logger.e(TAG, "当前包不是第一次开启");
                return;
            }

            hookParam.setHook();
            if (hookParam.getContext() != null) {
                loadVersion(hookParam.getHook());
            }
            hookApplication(this.hookParam, new HookParamCallback() {
                @Override
                public void appObject(Application application, ClassLoader classLoader) throws Throwable {
                    hookParam.setApplication(application);
                    hookParam.setClassLoader(application.getClassLoader());
                    hookParam.setHook();
                    if (loadVersion(hookParam.getHook())) {
                        hookEvent(hookParam);
                    }
                }
            });
        } catch (Throwable e) {
            logger.e(e, TAG, "获取当前app信息报错");
        }
    }

    private boolean loadVersion(Hook hook) throws Throwable {
        if (Lang.isEmpty(hookParam.getPackageName())) {
            String currentProcessName = hook.getCurrentProcessName();
            String currentPackageName = hook.getCurrentPackageName();
            logger.i(TAG, String.format("当前包信息: [%s]-[%s]", currentPackageName, currentProcessName));

            if (Lang.isEmpty(getPackageName()) || Lang.isNotEquals(currentProcessName, getPackageName())) {
                logger.e(TAG, "不是需求的包，过滤");
                return false;
            }
        } else if (Lang.isEmpty(getPackageName()) || Lang.isNotEquals(hookParam.getPackageName(), getPackageName())) {
            logger.e(TAG, "不是需求的包，过滤");
            return false;
        }

        Context context;
        if (hookParam.getContext() == null) {
            context = hookParam.getApplication();
        } else {
            context = hookParam.getContext();
        }
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
        if (packageInfo == null) {
            logger.e(TAG, "获取当前app信息为空");
            return false;
        }
        if (Lang.isEmpty(packageInfo.versionName) || !isCurrentVersion(packageInfo.versionCode, packageInfo.versionName)) {
            logger.e(TAG, "当前版本号：" + packageInfo.versionCode + " - " + packageInfo.versionName + " 不匹配");
            return false;
        }
        hookParam.setVersion(packageInfo.versionName);
        return true;
    }

    private void hookApplication(final HookParam hookParam, final HookParamCallback callback) throws Exception {
        if (!Lang.isEmpty(applicationPath())) {
            hookParam.getHook().methodMonitor(applicationPath(), "onCreate", new ForeignHook() {
                @Override
                public void beforeHookedMethod(final ForeignHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    logger.i(TAG, "执行hook-App");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.appObject((Application) param.getThisObject(), Thread.currentThread().getContextClassLoader());
                            } catch (Throwable throwable) {
                                logger.e(throwable, TAG, "加载App报错");
                            }
                        }
                    });
                }
            });
        }
    }

    private interface HookParamCallback {
        void appObject(Application application, ClassLoader classLoader) throws Throwable;
    }
}