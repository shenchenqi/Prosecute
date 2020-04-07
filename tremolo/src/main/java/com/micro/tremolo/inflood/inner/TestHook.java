package com.micro.tremolo.inflood.inner;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;

import java.util.Map;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:34
 */
public class TestHook {

    private static final Logger logger = Logger.getLogger("tremoloLog", "TestLog");

    public static void test(Hook hook) {
        mainActivity(hook);
        mainFragment(hook);
        profileFragment(hook);
        api(hook);
    }

    private static void mainActivity(final Hook hook) {
        String MainActivity = "com.ss.android.ugc.aweme.main.MainActivity";
        hook.methodMonitor(MainActivity, "onCreate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onCreate ");
            }
        }, Bundle.class);
        hook.methodMonitor(MainActivity, "tryShowGuideView", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity tryShowGuideView >> " + param.getResult());
                Object stateManager = hook.getField(param.getThisObject(), "stateManager");
                logger.d("Main Activity tryShowGuideView >>>> " + JSON.toJSONString(stateManager));
                Object fragment = hook.callMethod(stateManager, "d", "page_feed");
                logger.d("Main Activity tryShowGuideView >>>>>> " + JSON.toJSONString(fragment));
            }
        });
        hook.methodMonitor(MainActivity, "isInDiscoveryPage", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isInDiscoveryPage >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderFamiliarTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderFamiliarTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderMainTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderMainTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderNearbyTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderNearbyTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderProfileTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderProfileTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderSecondTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderSecondTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isUnderThirdTab", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isUnderThirdTab >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "performHomeTabClick", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity performHomeTabClick ");
            }
        });
        hook.methodMonitor(MainActivity, "tryShowLiveTakeBubble", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity tryShowLiveTakeBubble ");
            }
        });
        hook.methodMonitor(MainActivity, "fakeJumpToOpenUrl", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity fakeJumpToOpenUrl >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "hasDialogShowing", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity hasDialogShowing >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "onStartUp", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onStartUp ");
            }
        });
        hook.methodMonitor(MainActivity, "postPreinstallChannelEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity postPreinstallChannelEvent ");
            }
        });
        hook.methodMonitor(MainActivity, "resetWindowBackgroundFromFakeSplash", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity resetWindowBackgroundFromFakeSplash ");
            }
        });
        hook.methodMonitor(MainActivity, "setCanScrollToProfile", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity setCanScrollToProfile ");
            }
        });
        hook.methodMonitor(MainActivity, "showUpdateUserDialog", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity showUpdateUserDialog ");
            }
        });
        hook.methodMonitor(MainActivity, "tryShowSuperEntranceTabDirect", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity tryShowSuperEntranceTabDirect ");
            }
        });
        hook.methodMonitor(MainActivity, "getEnterFromPage", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity getEnterFromPage >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isMainTabVisible", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isMainTabVisible >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "isPaused", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity isPaused >> " + param.getResult());
            }
        });
        hook.methodMonitor(MainActivity, "onBackPressed", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onBackPressed");
            }
        });
        hook.methodMonitor(MainActivity, "uploadChannel", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity uploadChannel");
            }
        });
        hook.methodMonitor(MainActivity, "performSplashSkipClick", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity performSplashSkipClick");
            }
        });
        hook.methodMonitor(MainActivity, "setAdScrollRightControl", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity setAdScrollRightControl");
            }
        });
        hook.methodMonitor(MainActivity, "suitRouter", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity suitRouter");
            }
        });
        hook.methodMonitor(MainActivity, "backRefreshStrategy", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity backRefreshStrategy");
            }
        });
        hook.methodMonitor(MainActivity, "fitAwesomeSplash", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity fitAwesomeSplash");
            }
        });
        hook.methodMonitor(MainActivity, "refreshSlideSwitchCanScrollRight", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity refreshSlideSwitchCanScrollRight");
            }
        });
        hook.methodMonitor(MainActivity, "onMobEnterFromEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onMobEnterFromEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.y"));
        hook.methodMonitor(MainActivity, "onUserLoginStateChange", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onUserLoginStateChange");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.a.g"));
        hook.methodMonitor(MainActivity, "unRegisterActivityOnKeyDownListener", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity unRegisterActivityOnKeyDownListener");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.activity.a"));
        hook.methodMonitor(MainActivity, "changeTabToFollowAfterPublish", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity changeTabToFollowAfterPublish");
            }
        }, boolean.class);
        hook.methodMonitor(MainActivity, "enterRecordReal", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity enterRecordReal");
            }
        }, Intent.class);
        hook.methodMonitor(MainActivity, "showOpenSdkShareDialog", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity showOpenSdkShareDialog");
            }
        }, Intent.class);
        hook.methodMonitor(MainActivity, "cancelRestoreDialog", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity cancelRestoreDialog");
            }
        }, DialogInterface.class);
        hook.methodMonitor(MainActivity, "dispatchTouchEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity dispatchTouchEvent");
            }
        }, MotionEvent.class);
        hook.methodMonitor(MainActivity, "onConfigurationChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onConfigurationChanged");
            }
        }, Configuration.class);
        hook.methodMonitor(MainActivity, "onNewIntent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onNewIntent");
            }
        }, Intent.class);
        hook.methodMonitor(MainActivity, "onNextVideo", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onNextVideo");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.shortvideo.f.h"));
        hook.methodMonitor(MainActivity, "onScrollToDetailEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onScrollToDetailEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.aj"));
        hook.methodMonitor(MainActivity, "setTabBackground", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity setTabBackground");
            }
        }, boolean.class);
        hook.methodMonitor(MainActivity, "showUploadItemInNewFollowFeed", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity showUploadItemInNewFollowFeed");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.shortvideo.publish.d"));
        hook.methodMonitor(MainActivity, "onRestoreInstanceState", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onRestoreInstanceState");
            }
        }, Bundle.class);
        hook.methodMonitor(MainActivity, "onSaveInstanceState", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onSaveInstanceState");
            }
        }, Bundle.class);
        hook.methodMonitor(MainActivity, "onWebSocketEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onWebSocketEvent");
            }
        }, hook.findClass("com.ss.android.websocket.b.a.f"));
        hook.methodMonitor(MainActivity, "onWebSocketEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onWebSocketEvent");
            }
        }, hook.findClass("com.ss.android.websocket.b.a.f"));
        hook.methodMonitor(MainActivity, "onAwesomeSplashEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onAwesomeSplashEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.commercialize.splash.AwesomeSplashEvent"));
        hook.methodMonitor(MainActivity, "onScrollToProfileEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onScrollToProfileEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.ak"));
        hook.methodMonitor(MainActivity, "onUserBannedEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onUserBannedEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.a.e"));
        hook.methodMonitor(MainActivity, "onVideoPageChangeEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onVideoPageChangeEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.ae"));
        hook.methodMonitor(MainActivity, "onWindowFocusChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onWindowFocusChanged");
            }
        }, boolean.class);
        hook.methodMonitor(MainActivity, "onBroadCastEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onBroadCastEvent");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.fe.method.n"));
        hook.methodMonitor(MainActivity, "onPublishMessage", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity onPublishMessage");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.story.model.b"));
        hook.methodMonitor(MainActivity, "enterRecordFrom3rdPlatform", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity enterRecordFrom3rdPlatform");
            }
        }, Intent.class, boolean.class);
        hook.methodMonitor(MainActivity, "tryProcessPublish", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity tryProcessPublish >> " + param.getResult());
            }
        }, Intent.class, boolean.class);
        hook.methodMonitor(MainActivity, "tryProcessPublish", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Activity tryProcessPublish >> " + param.getResult());
            }
        }, Intent.class, boolean.class);
    }

    private static void mainFragment(final Hook hook) {
        String MainFragment = "com.ss.android.ugc.aweme.main.MainFragment";
        hook.methodMonitor(MainFragment, "onViewCreated", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onViewCreated ");
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(int) >>> " + param.getResult());
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(int, String) >>> " + param.getResult());
            }
        }, int.class, String.class);
        hook.methodMonitor(MainFragment, "x", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment x >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "A", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment A ");
            }
        });
        hook.methodMonitor(MainFragment, "B", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment B >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "E", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment E ");
            }
        });
        hook.methodMonitor(MainFragment, "q", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment q >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "z", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment z >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment b >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "c", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment c >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "e", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment e >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment h ");
            }
        });
        hook.methodMonitor(MainFragment, "i", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment i >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "j", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment j >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "k", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment k >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment n >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "o", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment o >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "s", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment s ");
            }
        });
        hook.methodMonitor(MainFragment, "D", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment D ");
            }
        });
        hook.methodMonitor(MainFragment, "w", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment w ");
            }
        });
        hook.methodMonitor(MainFragment, "y", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment y ");
            }
        });
        hook.methodMonitor(MainFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment d ");
            }
        });
        hook.methodMonitor(MainFragment, "p", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment p ");
            }
        });
        hook.methodMonitor(MainFragment, "r", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment r ");
            }
        });
        hook.methodMonitor(MainFragment, "t", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment t >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment f ");
            }
        });
        hook.methodMonitor(MainFragment, "u", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment u ");
            }
        });
        hook.methodMonitor(MainFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment g >>> " + param.getResult());
            }
        });
        hook.methodMonitor(MainFragment, "onSearchClick", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onSearchClick ");
            }
        });
        hook.methodMonitor(MainFragment, "v", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment v ");
            }
        });
        hook.methodMonitor(MainFragment, "m", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment m ");
            }
        });
        hook.methodMonitor(MainFragment, "onAdVideoEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onAdVideoEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.a"));
        hook.methodMonitor(MainFragment, "onCommerceDialogEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onCommerceDialogEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.ap"));
        hook.methodMonitor(MainFragment, "onFeedsPageChangedEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onFeedsPageChangedEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.t"));
        hook.methodMonitor(MainFragment, "onShowHotSearchGuideEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onShowHotSearchGuideEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.aq"));
        hook.methodMonitor(MainFragment, "onSwipeRefreshInMainFragmentEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onSwipeRefreshInMainFragmentEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.main.d.c"));
        hook.methodMonitor(MainFragment, "onVideoPageChangeEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onVideoPageChangeEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.ae"));
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(View) ");
            }
        }, View.class);
        hook.methodMonitor(MainFragment, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment h(int) ");
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment b(int) ");
            }
        }, int.class);
        /*hook.methodMonitor(MainFragment, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment n(int) ");
            }
        }, int.class);*/
        hook.methodMonitor(MainFragment, "e", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment e(int) ");
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment f(int) ");
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment g(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "h_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment h_(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "onAfterLoginInEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onAfterLoginInEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.login.a.a"));
        hook.methodMonitor(MainFragment, "onCaptchaConsumerEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onCaptchaConsumerEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.captcha.a"));
        hook.methodMonitor(MainFragment, "onClickBottomTabEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onClickBottomTabEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.e"));
        hook.methodMonitor(MainFragment, "onDislikeAwemeEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onDislikeAwemeEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.i"));
        hook.methodMonitor(MainFragment, "onDismissSearchGuideEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onDismissSearchGuideEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.m"));
        hook.methodMonitor(MainFragment, "onHiddenChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onHiddenChanged ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "onPrivacyDialogShowStateChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onPrivacyDialogShowStateChanged ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.au.c.b"));
        hook.methodMonitor(MainFragment, "onPublishStatusUpdate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onPublishStatusUpdate ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.shortvideo.f.f"));
        hook.methodMonitor(MainFragment, "setUserVisibleHint", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment setUserVisibleHint ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "c", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment c(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment d(int) ");
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "e", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment e(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment g(int) ");
            }
        }, int.class);
        hook.methodMonitor(MainFragment, "onAwesomeSplashEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onAwesomeSplashEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.commercialize.splash.AwesomeSplashEvent"));
        hook.methodMonitor(MainFragment, "onHideUploadRecoverEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onHideUploadRecoverEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.shortvideo.f.e"));
        hook.methodMonitor(MainFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment b(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment d(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment f(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(MainFragment, "onDismissTitleTabEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onDismissTitleTabEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.n"));
        hook.methodMonitor(MainFragment, "onScrollToFeedFollowGuideEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onScrollToFeedFollowGuideEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.main.guide.f"));
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(com.ss.android.ugc.aweme.poi.a.m) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.poi.a.m"));
        hook.methodMonitor(MainFragment, "onLiveSquareGuideEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onLiveSquareGuideEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.x"));
        hook.methodMonitor(MainFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment b(int, int) ");
            }
        }, int.class, int.class);
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(int, int) ");
            }
        }, int.class, int.class);
        hook.methodMonitor(MainFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment a(boolean, int) ");
            }
        }, boolean.class, int.class);
        hook.methodMonitor(MainFragment, "onCreateView", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Main Fragment onCreateView ");
            }
        }, LayoutInflater.class, ViewGroup.class, Bundle.class);
    }

    private static void profileFragment(final Hook hook) {
        String profileFragment = "com.ss.android.ugc.aweme.profile.ui.UserProfileFragment";
        hook.methodMonitor(profileFragment, "getUserId", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment getUserId >>> " + param.getResult());
            }
        });
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(View) ");
            }
        }, View.class);
        hook.methodMonitor(profileFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment b(Aweme) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.model.Aweme"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(String, Map) ");
            }
        }, String.class, Map.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(String, String) ");
            }
        }, String.class, String.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(Aweme) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.model.Aweme"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(User) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.User"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(View, bn) ");
            }
        }, View.class, hook.findClass("com.ss.android.ugc.aweme.app.bn"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(boolean, boolean) ");
            }
        }, boolean.class, boolean.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(UrlModel) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.model.UrlModel"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(String, int, BlueVBrandInfo, User) ");
            }
        }, String.class, int.class, hook.findClass("com.ss.android.ugc.aweme.profile.model.BlueVBrandInfo"), hook.findClass("com.ss.android.ugc.aweme.profile.model.User"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(DialogInterface, int) ");
            }
        }, DialogInterface.class, int.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(BaseResponse) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.api.BaseResponse"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(DmtTabLayout.f) ");
            }
        }, hook.findClass("com.bytedance.ies.dmt.ui.widget.tablayout.DmtTabLayout.f"));
        hook.methodMonitor(profileFragment, "J", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment J ");
            }
        });
        hook.methodMonitor(profileFragment, "O", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment O ");
            }
        });
        hook.methodMonitor(profileFragment, "R", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment R ");
            }
        });
        hook.methodMonitor(profileFragment, "S", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment S ");
            }
        });
        hook.methodMonitor(profileFragment, "U", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment U ");
            }
        });
        hook.methodMonitor(profileFragment, "F", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment F ");
            }
        });
        hook.methodMonitor(profileFragment, "ba_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment ba_ ");
            }
        });
        hook.methodMonitor(profileFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment d ");
            }
        });
        hook.methodMonitor(profileFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment f ");
            }
        });
        hook.methodMonitor(profileFragment, "getAnalysis", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment getAnalysis ");
            }
        });
        hook.methodMonitor(profileFragment, "z", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment z ");
            }
        });
        hook.methodMonitor(profileFragment, "D", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment D ");
            }
        });
        hook.methodMonitor(profileFragment, "G", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment G ");
            }
        });
        hook.methodMonitor(profileFragment, "K", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment K ");
            }
        });
        hook.methodMonitor(profileFragment, "L", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment L ");
            }
        });
        hook.methodMonitor(profileFragment, "Q", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment Q ");
            }
        });
        hook.methodMonitor(profileFragment, "P", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment P ");
            }
        });
        hook.methodMonitor(profileFragment, "T", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment T ");
            }
        });
        hook.methodMonitor(profileFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment b ");
            }
        });
        hook.methodMonitor(profileFragment, "t_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment t_ >>> " + param.getResult());
            }
        });
        hook.methodMonitor(profileFragment, "v", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment v >>> " + param.getResult());
            }
        });
        hook.methodMonitor(profileFragment, "E", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment E ");
            }
        });
        hook.methodMonitor(profileFragment, "H", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment H ");
            }
        });
        hook.methodMonitor(profileFragment, "M", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment M >>> " + param.getResult());
            }
        });
        hook.methodMonitor(profileFragment, "N", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment N ");
            }
        });
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a ");
            }
        });
        hook.methodMonitor(profileFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment g ");
            }
        });
        hook.methodMonitor(profileFragment, "I", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment I ");
            }
        });
        hook.methodMonitor(profileFragment, "m", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment m ");
            }
        });
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(bh) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.ui.bh"));
        hook.methodMonitor(profileFragment, "i", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment i(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(RoomResponse) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.RoomResponse"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(eg) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.ui.eg"));
        hook.methodMonitor(profileFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment f(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment h(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "m", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment m(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "onEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.a.c"));
        hook.methodMonitor(profileFragment, "onMobRequestIdEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onMobRequestIdEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.e.z"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(RoomStruct) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.follow.presenter.RoomStruct"));
        hook.methodMonitor(profileFragment, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment h(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "i", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment i(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "k", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment k(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "l", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment l(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "r", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment r(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "s", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment s(int) >>> " + param.getResult());
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment g(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "RefreshProfileAfterBlockEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment RefreshProfileAfterBlockEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.a.g"));
        hook.methodMonitor(profileFragment, "j", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment j(String) ");
            }
        }, String.class);
        hook.methodMonitor(profileFragment, "onBack", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onBack ");
            }
        }, View.class);
        hook.methodMonitor(profileFragment, "onChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onChanged ");
            }
        }, Object.class);
        hook.methodMonitor(profileFragment, "onCloseWebViewLoadingJsEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onCloseWebViewLoadingJsEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.commercialize.b.e"));
        hook.methodMonitor(profileFragment, "onEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.im.service.model.g"));
        hook.methodMonitor(profileFragment, "onFeedAdClickFormEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onFeedAdClickFormEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.commercialize.b.g"));
        hook.methodMonitor(profileFragment, "onHiddenChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onHiddenChanged ");
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "onLiveStatusEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onLiveStatusEvent ");
            }
        }, hook.findClass("com.bytedance.android.live.base.b.a"));
        hook.methodMonitor(profileFragment, "onPageScrollStateChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onPageScrollStateChanged ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "onPageSelected", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onPageSelected ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "p", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment p(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "q", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment q(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "c", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment c(Aweme) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.feed.model.Aweme"));
        hook.methodMonitor(profileFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment d(View) ");
            }
        }, View.class);
        hook.methodMonitor(profileFragment, "f", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment f(boolean) ");
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "h", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment h(User) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.User"));
        hook.methodMonitor(profileFragment, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment n(User) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.User"));
        hook.methodMonitor(profileFragment, "onAdBottomClick", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onAdBottomClick(View) ");
            }
        }, View.class);
        hook.methodMonitor(profileFragment, "onAntiCrawlerEvent", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onAntiCrawlerEvent ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.base.a.a"));
        hook.methodMonitor(profileFragment, "onMsgFromRnAndH5", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onMsgFromRnAndH5 ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.fe.method.n"));
        hook.methodMonitor(profileFragment, "t", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment t(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(float) ");
            }
        }, float.class);
        hook.methodMonitor(profileFragment, "a_", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a_(User) ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.User"));
        hook.methodMonitor(profileFragment, "g", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment g(boolean) >>> " + param.getArgs()[0]);
            }
        }, boolean.class);
        hook.methodMonitor(profileFragment, "onFakeCoverAction", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onFakeCoverAction ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.commercialize.b.m"));
        hook.methodMonitor(profileFragment, "onFollowStatusUpdate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onFollowStatusUpdate ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.FollowStatus"));
        hook.methodMonitor(profileFragment, "onFollowStatusUpdate", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onFollowStatusUpdate ");
            }
        }, hook.findClass("com.ss.android.ugc.aweme.profile.model.FollowStatus"));
        hook.methodMonitor(profileFragment, "n", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment n(int) ");
            }
        }, int.class);
        hook.methodMonitor(profileFragment, "onConfigurationChanged", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onConfigurationChanged ");
            }
        }, Configuration.class);
        hook.methodMonitor(profileFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment b(DmtTabLayout.f) ");
            }
        }, hook.findClass("com.bytedance.ies.dmt.ui.widget.tablayout.DmtTabLayout.f"));
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(String, ar) ");
            }
        }, String.class, hook.findClass("com.ss.android.ugc.aweme.aj.ar"));
        hook.methodMonitor(profileFragment, "c", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment c(String, String) ");
            }
        }, String.class, String.class);
        hook.methodMonitor(profileFragment, "d", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment d(String, String) ");
            }
        }, String.class, String.class);
        hook.methodMonitor(profileFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment b(int, int) ");
            }
        }, int.class, int.class);
        hook.methodMonitor(profileFragment, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment b(String, String) ");
            }
        }, String.class, String.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(float, float) ");
            }
        }, float.class, float.class);
        hook.methodMonitor(profileFragment, "onViewCreated", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment onViewCreated ");
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(profileFragment, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("Profile Fragment a(int, int) ");
            }
        }, int.class, int.class);
    }

    private static void api(final Hook hook)  {
        String profileVideoCall = "com.ss.android.ugc.aweme.profile.presenter.b";
        hook.methodMonitor(profileVideoCall, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("presenter.b a(boolean, String, int, long, int, String) 参数[%s, %s, %s, %s, %s, %s]",
                        param.getArgs()[0], param.getArgs()[1], param.getArgs()[2], param.getArgs()[3], param.getArgs()[4], param.getArgs()[5]));
            }
        }, boolean.class, String.class, int.class, long.class, int.class, String.class);

        String profileVideoApi = "com.ss.android.ugc.aweme.profile.api.AwemeApi";
        hook.methodMonitor(profileVideoApi, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("AwemeApi a(String, int) 参数[%s, %s], 返回数据[%s]",
                        param.getArgs()[0], param.getArgs()[1],
                        JSON.toJSONString(param.getResult())));
            }
        }, String.class, int.class);
        hook.methodMonitor(profileVideoApi, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("AwemeApi a(boolean, String, String, int, long, int, String) 参数[%s, %s, %s, %s, %s, %s, %s], 返回数据[%s]",
                        param.getArgs()[0], param.getArgs()[1], param.getArgs()[2], param.getArgs()[3], param.getArgs()[4], param.getArgs()[5], param.getArgs()[6],
                        JSON.toJSONString(param.getResult())));
            }
        }, boolean.class, String.class, String.class, int.class, long.class, int.class, String.class);
        hook.methodMonitor(profileVideoApi, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("AwemeApi a(boolean, String, String, int, long, int, String, int, int) 参数[%s, %s, %s, %s, %s, %s, %s, %s, %s]， 返回数据[%s]",
                        param.getArgs()[0], param.getArgs()[1], param.getArgs()[2], param.getArgs()[3], param.getArgs()[4], param.getArgs()[5], param.getArgs()[6], param.getArgs()[7], param.getArgs()[8],
                        JSON.toJSONString(param.getResult())));
            }
        }, boolean.class, String.class, String.class, int.class, long.class, int.class, String.class, int.class, int.class);

        String profileCall = "com.ss.android.ugc.aweme.profile.presenter.ai";
        hook.methodMonitor(profileCall, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d("presenter.ai b()");
                Object b = hook.getField(param.getThisObject(), "b");
                Object d = hook.getField(param.getThisObject(), "d");
                Object e = hook.getField(param.getThisObject(), "e");
                int f = hook.getIntegerField(param.getThisObject(), "f");
                logger.d(String.format("presenter.ai b() 准备的数据： %s, %s, %s, %s", b, d, e, f));
                Object h = hook.getField(param.getThisObject(), "h");
                Object data = hook.callMethod(h, "getData");
                Object getUser = JSON.toJSONString(hook.callMethod(data, "getUser"));
                logger.d("presenter.ai b() " + JSON.toJSONString(getUser));
            }
        });

        String profileApi = "com.ss.android.ugc.aweme.profile.api.ProfileApi";
        hook.methodMonitor(profileApi, "b", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("ProfileApi b(String, boolean, String) 参数[%s, %s, %s], 返回数据[%s]",
                        param.getArgs()[0], param.getArgs()[1], param.getArgs()[2],
                        JSON.toJSONString(param.getResult())));
            }
        }, String.class, boolean.class, String.class);

        String secUidManager = "com.ss.android.ugc.aweme.utils.ej";
        hook.methodMonitor(secUidManager, "a", new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                logger.d(String.format("ProfileApi a(String, String) 参数[%s, %s]",
                        param.getArgs()[0], param.getArgs()[1]));
            }
        }, String.class, String.class);
    }
}