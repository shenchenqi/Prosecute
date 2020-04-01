package com.micro.network;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.network.http3.BaseManager;
import com.micro.network.http3.NetWorkConfig;
import com.micro.network.http3.logging.Level;
import com.micro.network.http3.logging.LoggingInterceptor;
import com.mirco.network.BuildConfig;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.internal.platform.Platform;

/**
 * created by kilin on 20-3-17 上午10:12
 */
public class OkHttp3 extends BaseManager {

    private static final String TAG = "OkHttp3";

    public static OkHttp3 getInstance(Context context) {
        return new OkHttp3(context);
    }

    private OkHttp3(Context context) {
        super(context);
    }

    @Override
    protected Interceptor getNetWorkConfig() {
        Log.d(TAG, "NetWorkConfig", "配置");
        return new NetWorkConfig(context);
    }

    @Override
    protected Interceptor getLoggingConfig() {
        Log.d(TAG, "LoggingConfig", "配置");
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build();
    }

    @Override
    public  <T> T create(Class<T> service) {
        return super.create(service);
    }

    @Override
    public void post(String url, RequestCallback callback) {
        Log.d(TAG, "post", String.format("POST请求 [链接： %s]", url));
        super.post(url, callback);
    }

    @Override
    public void post(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        Log.d(TAG, "post", String.format("POST请求 [链接： %s] [headerMap: %s] [bodyMap: %s]", url, JSON.toJSONString(headerMap), JSON.toJSONString(bodyMap)));
        super.post(url, headerMap, bodyMap, callback);
    }

    @Override
    public void get(String url, RequestCallback callback) {
        Log.d(TAG, "get", String.format("GET请求 [链接： %s]", url));
        super.get(url, callback);
    }

    @Override
    public void get(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        Log.d(TAG, "get", String.format("GET请求 [链接： %s] [headerMap: %s] [bodyMap: %s]", url, JSON.toJSONString(headerMap), JSON.toJSONString(bodyMap)));
        super.get(url, headerMap, bodyMap, callback);
    }
}