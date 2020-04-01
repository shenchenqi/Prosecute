package com.micro.network.http3;

import android.content.Context;

import com.micro.network.Api;
import com.micro.network.http3.converter.ResponseConverterFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * created by kilin on 20-3-17 上午9:42
 */
public abstract class BaseManager {
    private enum Type {
        POST,
        GET
    }

    protected final Context context;
    private final OkHttpClient okHttpClient;

    protected BaseManager(Context context) {
        this.context = context;
        this.okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(getNetWorkConfig())
                .addInterceptor(getLoggingConfig())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(10, 3, TimeUnit.SECONDS))
                .build();
    }

    protected abstract Interceptor getNetWorkConfig();

    protected abstract Interceptor getLoggingConfig();

    protected <T> T create(Class<T> service) {
        return new Retrofit.Builder()
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(Api.BASE_URL)
                .build()
                .create(service);
    }

    protected void post(String url, RequestCallback callback) {
        post(url, null, null, callback);
    }

    protected void post(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        asyncAskPost(url, headerMap, bodyMap, callback);
    }

    protected void get(String url, RequestCallback callback) {
        get(url, null, null, callback);
    }

    protected void get(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        asyncAskGet(url, headerMap, bodyMap, callback);
    }

    private void asyncAskPost(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        asyncAsk(url, Type.POST, headerMap, bodyMap, callback);
    }

    private void asyncAskGet(String url, Map<String, Object> headerMap, Map<String, Object> bodyMap, RequestCallback callback) {
        asyncAsk(url, Type.GET, headerMap, bodyMap, callback);
    }

    private synchronized void asyncAsk(String url, Type type, Map<String, Object> headerMap, Map<String, Object> bodyMap, final RequestCallback callback) {
        Call call = getCall(url, type, headerMap, bodyMap);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.success(response.code(), response.message());
            }
        });
    }

    private Call getCall(String url, Type type, Map<String, Object> headerMap, Map<String, Object> bodyMap) {
        return okHttpClient.newCall(getRequest(url, type, headerMap, bodyMap));
    }

    private Request getRequest(String url, Type type, Map<String, Object> headerMap, Map<String, Object> bodyMap) {
        return getRequestBuilder(url, type, headerMap, bodyMap).build();
    }

    private RequestBody getBody(Map<String, Object> bodyMap) {
        FormBody.Builder builder = new FormBody.Builder();
        if (bodyMap != null && !bodyMap.isEmpty()) {
            for (String key : bodyMap.keySet()) {
                builder.add(key, bodyMap.get(key).toString());
            }
        }
        return builder.build();
    }

    private Headers getHeader(Map<String, Object> headerMap) {
        Headers.Builder builder = new Headers.Builder();
        if (headerMap != null && !headerMap.isEmpty()) {
            for (String key : headerMap.keySet()) {
                builder.add(key, headerMap.get(key).toString());
            }
        }
        return builder.build();
    }

    private Request.Builder getRequestBuilder(String url, Type type, Map<String, Object> headerMap, Map<String, Object> bodyMap) {
        Request.Builder builder = new Request.Builder();
        builder.headers(getHeader(headerMap));
        builder.method(type.name(), getBody(bodyMap));
        builder.url(url);
        return builder;
    }

    public interface RequestCallback {
        void success(int code, String message);

        void error(Exception e);
    }
}