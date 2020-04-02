package com.so1spms.module.rpc.interceptor;

import android.util.Log;


import com.so1spms.module.rpc.util.ReflectionUtil;

import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;


public final class LJLoggingInterceptor implements Interceptor {
    static final String TAG = "LJLoggingInterceptor";

    public static class LJlogger {
        String url;
        Map<String, String> urlParams;
        Map<String, String> bodyParams;
        String method;
        long startTime;
        long endTime;
        int httpCode;
        String remark;
        Exception exception;

        public LJlogger(Builder builder) {
            if (builder != null) {
                this.url = builder.url;
                this.urlParams = builder.urlParams;
                this.bodyParams = builder.bodyParams;
                this.method = builder.method;
                this.startTime = builder.startTime;
                this.endTime = builder.endTime;
                this.httpCode = builder.httpCode;
                this.remark = builder.remark;
                this.exception = builder.exception;
            }

        }

        public void log() {
            String className = "com.transfar.statistic.StatisticsApi";
            String method = "trackHttp";
            Class[] methodClass = {String.class, Map.class, Map.class, String.class, long.class, long.class, int.class, String.class, Exception.class};
            Object[] methodObject = {url, urlParams, bodyParams, this.method, startTime, endTime, httpCode, remark, exception};
            try {
                ReflectionUtil.callClassMethod(className, method, methodClass, methodObject);
            } catch (Exception e) {
                Log.e(TAG, "log: call StatisticsApi error", e);
            }
        }

        public static class Builder {
            private String url;
            private Map<String, String> urlParams;
            private Map<String, String> bodyParams;
            private String method;
            private long startTime;
            private long endTime;
            private int httpCode;
            private String remark;
            private Exception exception;

            public LJlogger build() {
                return new LJlogger(this);
            }

            public void url(String u) {
                this.url = u;
            }

            public void urlParam(Map<String, String> param) {
                if (param != null && param.size() > 0) {
                    this.urlParams = new HashMap<>();
                    this.urlParams.putAll(param);
                }
            }

            public void bodyParam(Map<String, String> param) {
                if (param != null && param.size() > 0) {
                    this.bodyParams = new HashMap<>();
                    this.bodyParams.putAll(param);
                }
            }

            public void method(String u) {
                this.method = u;
            }

            public void startTime(long t) {
                this.startTime = t;
            }

            public void endTime(long t) {
                this.endTime = t;
            }

            public void httpCode(int code) {
                this.httpCode = code;
            }

            public void remark(String r) {
                this.remark = r;
            }

            public void exception(Exception e) {
                this.exception = e;
            }
        }
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        LJlogger.Builder logBuilder = new LJlogger.Builder();
        Request request = chain.request();
        //url
        logBuilder.url(request.url().toString());
        //urlParams
        HttpUrl httpUrl = request.url();
        Map<String, String> param = null;
        if (httpUrl.queryParameterNames() != null && httpUrl.queryParameterNames().size() > 0) {
            param = new HashMap<>();
            Set<String> keySet = httpUrl.queryParameterNames();
            for (String key : keySet) {
                param.put(key, httpUrl.queryParameter(key));
            }
        }
        logBuilder.urlParam(param);
        //bodyParams
        Map<String, String> bodyParam = null;
        if (request.body() instanceof FormBody) {
            FormBody oldBody = (FormBody) request.body();
            if (oldBody.size() > 0) {
                bodyParam = new HashMap<>();
                for (int i = 0; i < oldBody.size(); ++i) {
                    String name = oldBody.name(i);
                    String value = oldBody.value(i);
                    bodyParam.put(name, value);
                }
            }
        } else if (request.body() instanceof MultipartBody) {
            MultipartBody oldBodyMultipart = (MultipartBody) request.body();
            List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
            //old part
            for (MultipartBody.Part part : oldPartList) {
                if (part.body() instanceof FormBody) {
                    bodyParam = new HashMap<>();
                    FormBody partyForm = (FormBody) part.body();
                    for (int i = 0; i < partyForm.size(); ++i) {
                        String name = partyForm.name(i);
                        String value = partyForm.value(i);
                        bodyParam.put(name, value);
                    }
                }
            }
        }
        logBuilder.bodyParam(bodyParam);
        //method
        Headers headers = request.headers();
        boolean gzip = false;
        if (headers != null) {
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                String value = headers.value(i);
                // Skip headers from the request body as they are explicitly logged above.
                if ("Content-Encoding".equalsIgnoreCase(name) && "gzip".equalsIgnoreCase(value)) {
                    gzip = true;
                }
            }
        }
        logBuilder.method(request.method() + gzip);
        //startTime
        logBuilder.startTime(System.currentTimeMillis());
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            //Exception
            logBuilder.exception(e);
            throw e;
        }
        //endTime
        logBuilder.endTime(System.currentTimeMillis());
        //httpCode
        logBuilder.httpCode(response.code());
        logBuilder.build().log();
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
