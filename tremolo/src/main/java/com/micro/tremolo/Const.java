package com.micro.tremolo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.micro.tremolo.inflood.DataBroadcast;
import com.micro.tremolo.rep.AppApiConfig;
import com.so1spms.module.rpc.BaseRpcParam;
import com.so1spms.module.rpc.RPCApiFactory;
import com.so1spms.module.rpc.interfaces.TokenCheck;

import org.litepal.LitePal;

import java.util.Arrays;
import java.util.List;

/**
 * created by kilin on 20-3-18 下午1:08
 */
public class Const {

    private static Context context;

    public static void setContext(Context context) {
        Const.context = context;
        init();
    }

    public static Context getContext() {
        return context;
    }

    private static void init() {
        LitePal.initialize(context);
        DataBroadcast.registerReceiver(context);
        initRpc();
    }

    private static void initRpc() {
        RPCApiFactory.init(new BaseRpcParam() {
            @Override
            public Application getApp() {
                return (Application) context;
            }

            @Override
            public boolean isDebug() {
                return TextUtils.equals(BuildConfig.BUILD_TYPE,"debug");
            }

            @Override
            public TokenCheck getTokenCheck() {
                return new TokenCheck(){
                    @Override
                    public void onTokenInvalid() {

                    }
                };
            }
        });
        //登录相关
        AppApiConfig.configApi();
        try {
            Class clazz = Class.forName("com.facebook.stetho.Stetho");
            com.facebook.stetho.Stetho.initializeWithDefaults(context);
        } catch (Exception e) {
        }
    }

    public final static String PACKAGE_NAME = "com.ss.android.ugc.aweme";
    public final static String APPLICATION = "android.app.Application";
    //"com.ss.android.ugc.aweme.app.AwemeApplication" "com.ss.android.ugc.aweme.app.host.HostApplication" "com.ss.android.ugc.aweme.app.BaseMediaApplication";
    public final static List<String> VERSIONS = Arrays.asList("9.6.0");
    public final static String CREATE_DIR = "Micro/Backup/init";
}