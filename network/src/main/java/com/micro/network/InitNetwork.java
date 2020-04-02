package com.micro.network;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.micro.network.available.ApiConfig;
import com.micro.network.available.BaseRpcParam;
import com.micro.network.available.RPCApiFactory;
import com.micro.network.available.interfaces.TokenCheck;
import com.mirco.network.BuildConfig;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:56
 */
public class InitNetwork {

    private final static String DOMAIN = "http://skingsoft.cn/";

    public static <T> void loadNetwork(Context context, Class<T> clazz) {
        new InitNetwork(context).init().addConfig(clazz, DOMAIN, DOMAIN, DOMAIN, DOMAIN, DOMAIN).setNetLog();
    }

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        InitNetwork.mContext = mContext;
    }

    private final Context context;

    private InitNetwork(Context context) {
        this.context = context;
        setContext(context);
    }

    public InitNetwork init() {
        RPCApiFactory.init(new BaseRpcParam() {
            @Override
            public Application getApp() {
                return (Application) context;
            }

            @Override
            public boolean isDebug() {
                return TextUtils.equals(BuildConfig.BUILD_TYPE, "debug");
            }

            @Override
            public TokenCheck getTokenCheck() {
                return new TokenCheck() {
                    @Override
                    public void onTokenInvalid() {

                    }
                };
            }
        });
        return this;
    }

    public <T> InitNetwork addConfig(Class<T> clazz, String mainDomain, String devDomain, String testDomain, String preDomain, String onlineDomain) {
        RPCApiFactory.addConfig(ApiConfig.config(clazz)
                .mainBaseUrl(mainDomain)
                .devBaseUrl(devDomain)
                .testBaseUrl(testDomain)
                .preOnlineBaseUrl(preDomain)
                .onlineBaseUrl(onlineDomain)
                .build());
        return this;
    }

    public void setNetLog() {
        try {
            Class.forName("com.facebook.stetho.Stetho");
            com.facebook.stetho.Stetho.initializeWithDefaults(context);
        } catch (Exception e) {
        }
    }
}