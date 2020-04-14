package com.micro.worker.inflood.inner;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:22
 */
public class TestHook {

    private static final Logger logger = Logger.getLogger("workerLog", "TestLog");

    public static void test(Hook hook) {
        util(hook);
        gifShowActivity(hook);
        homeActivity(hook);
        userProfileActivity(hook);
        photoDetailActivity(hook);
    }

    private static void util(final Hook hook) {
        hook.methodMonitor("com.yxcorp.gifshow.util.CPU", "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("CPU 参数[%s, %s, %s] 返回数据[%s]",
                        param.getArgs()[0], JSON.toJSONString(param.getArgs()[1]), param.getArgs()[2],
                        param.getResult()));
            }
        }, Context.class, byte[].class, int.class);
    }

    private static void gifShowActivity(final Hook hook) {
        String gifShowActivity = "com.yxcorp.gifshow.activity.GifshowActivity";
        hook.methodMonitor(gifShowActivity, "getUrl", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity getUrl() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(gifShowActivity, "getPage2", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity getPage2() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(gifShowActivity, "getPagePath", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity getPagePath() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(gifShowActivity, "getPreUrl", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity getPreUrl() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(gifShowActivity, "getUrlWithAnchorPoint", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity getUrlWithAnchorPoint() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(gifShowActivity, "setTitle", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity setTitle(int, int, int) 猜数[%s, %s, %s]", param.getArgs()[0], param.getArgs()[1], param.getArgs()[2]));
            }
        }, int.class, int.class, int.class);
        hook.methodMonitor(gifShowActivity, "setTitle", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("gif show activity setTitle(int, int, CharSequence) 猜数[%s, %s, %s]", param.getArgs()[0], param.getArgs()[1], param.getArgs()[2]));
            }
        }, int.class, int.class, CharSequence.class);
    }

    private static void homeActivity(final Hook hook) {
        String homeActivity = "com.yxcorp.gifshow.HomeActivity";
        hook.methodMonitor(homeActivity, "e", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity e() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(homeActivity, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity h() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(homeActivity, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity n() "));
            }
        });
        hook.methodMonitor(homeActivity, "bw_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity bw_ 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(homeActivity, "bx_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity bx_ 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(homeActivity, "by_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity by_ 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(homeActivity, "getCategory", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getCategory 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "getPage", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getPage 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "getPageId", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getPageId 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "getPageParams", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getPageParams 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "getSubPages", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getSubPages 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "getUrl", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getUrl 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "j", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity j() 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "k", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity k() "));
            }
        });
        hook.methodMonitor(homeActivity, "l", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity l() "));
            }
        });
        hook.methodMonitor(homeActivity, "m", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity m() "));
            }
        });
        hook.methodMonitor(homeActivity, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity g() "));
            }
        });
        hook.methodMonitor(homeActivity, "getToastLeftOffset", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity getToastLeftOffset 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(ChildLockDialogEvent) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.childlock.ChildLockSettingActivity.ChildLockDialogEvent");
        hook.methodMonitor(homeActivity, "onMultiWindowModeChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onMultiWindowModeChanged 猜数[%s]", param.getArgs()[0]));
            }
        }, boolean.class);
        hook.methodMonitor(homeActivity, "onConfigurationChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onConfigurationChanged 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, Configuration.class);
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(g) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.childlock.g");
        hook.methodMonitor(homeActivity, "onNewIntent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onNewIntent 猜数[%s]", param.getArgs()[0]));
            }
        }, Intent.class);
        hook.methodMonitor(homeActivity, "onUserScreenShot", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onUserScreenShot 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.al.a.a");
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(com.yxcorp.gifshow.homepage.h.a.a) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.homepage.h.a.a");
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(Intent) 猜数[%s]", param.getArgs()[0]));
            }
        }, Intent.class);
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(m) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.events.m");
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(String) 猜数[%s]", param.getArgs()[0]));
            }
        }, String.class);
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(String) 猜数[%s]", param.getArgs()[0]));
            }
        }, String.class);
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(Uri) 猜数[%s]", param.getArgs()[0]));
            }
        }, Uri.class);
        hook.methodMonitor(homeActivity, "onCreate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onCreate 猜数[%s]", param.getArgs()[0]));
            }
        }, Bundle.class);
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(l) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.events.l");
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(b) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.splash.a.b");
        hook.methodMonitor(homeActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity onEventMainThread(b) 猜数[%s]", JSON.toJSONString(param.getArgs()[0])));
            }
        }, "com.yxcorp.gifshow.splash.a.b");
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(Intent, boolean) 猜数[%s, %s]", param.getArgs()[0], param.getArgs()[1]));
            }
        }, Intent.class, boolean.class);
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(int, int, Intent) 猜数[%s, %s]", param.getArgs()[0], param.getArgs()[1], param.getArgs()[2]));
            }
        }, int.class, int.class, Intent.class);
        hook.methodMonitor(homeActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("home activity a(String, int, Intent) 猜数[%s, %s]", param.getArgs()[0], param.getArgs()[1], param.getArgs()[2], param.getArgs()[3]));
            }
        }, String.class, int.class, int.class, Intent.class);
    }

    private static void userProfileActivity(final Hook hook) {
        String userProfileActivity = "com.yxcorp.gifshow.profile.activity.UserProfileActivity";
        hook.methodMonitor(userProfileActivity, "j", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity j() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "bw_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity bw_() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "bx_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity bx_() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "by_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity by_() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "l", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity l() "));
            }
        });
        hook.methodMonitor(userProfileActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity a() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "getEnterArguments", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity getEnterArguments() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "getLeaveParams", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity getLeaveParams() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "getPageId", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity getPageId() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "getUrl", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity getUrl() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "k", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity k() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(userProfileActivity, "logPageEnter", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity logPageEnter() 猜数[%s]", param.getArgs()[0]));
            }
        }, int.class);
        hook.methodMonitor(userProfileActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onEventMainThread(com.yxcorp.gifshow.init.a.a) 猜数[%s]", param.getArgs()[0]));
            }
        }, "com.yxcorp.gifshow.init.a.a");
        hook.methodMonitor(userProfileActivity, "onNewIntent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onNewIntent(Intent) 猜数[%s]", param.getArgs()[0]));
            }
        }, Intent.class);
        hook.methodMonitor(userProfileActivity, "startActivity", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity startActivity(Intent) 猜数[%s]", param.getArgs()[0]));
            }
        }, Intent.class);
        hook.methodMonitor(userProfileActivity, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity a(UserProfileResponse) 猜数[%s]", param.getArgs()[0]));
                Object object = hook.getField(param.getThisObject(), "e");
                logger.d(String.format(JSON.toJSONString(object)));
            }
        }, "com.yxcorp.gifshow.model.response.UserProfileResponse");
        hook.methodMonitor(userProfileActivity, "onConfigurationChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onConfigurationChanged(Configuration) 猜数[%s]", param.getArgs()[0]));
            }
        }, Configuration.class);
        hook.methodMonitor(userProfileActivity, "onCreate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onCreate(Configuration) 猜数[%s]", param.getArgs()[0]));
            }
        }, Bundle.class);
        hook.methodMonitor(userProfileActivity, "onEventMainThread", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onEventMainThread(com.yxcorp.gifshow.init.a.b) 猜数[%s]", param.getArgs()[0]));
            }
        }, "com.yxcorp.gifshow.init.a.b");
        hook.methodMonitor(userProfileActivity, "onUserScreenShot", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("user profile activity onUserScreenShot(com.yxcorp.gifshow.al.a.a) 猜数[%s]", param.getArgs()[0]));
            }
        }, "com.yxcorp.gifshow.al.a.a");
    }

    private static void photoDetailActivity(final Hook hook) {
        String photoDetailActivity = "com.yxcorp.gifshow.detail.PhotoDetailActivity";
        hook.methodMonitor(photoDetailActivity, "C_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity C_() 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(photoDetailActivity, "aS_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity aS_() 返回数据[%s]", param.getResult()));
            }
        });
        hook.methodMonitor(photoDetailActivity, "by_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity by_() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(photoDetailActivity, "k", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity k() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(photoDetailActivity, "l", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity l() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(photoDetailActivity, "m", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity m() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(photoDetailActivity, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity n() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
        hook.methodMonitor(photoDetailActivity, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("photo detail activity n() 返回数据[%s]", JSON.toJSONString(param.getResult())));
            }
        });
    }
}