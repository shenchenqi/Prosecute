package com.so1spms.module.rpc.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.so1spms.module.rpc.RPCApiFactory;
import com.so1spms.module.rpc.util.RPCUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Version;

/**
 * Created by qiaobo on 16/7/5.
 */
public class HeaderInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger("HeaderInterceptor");
    private static final String HEADER_NAME_UUID = "X-UUID";
    private static final String HEADER_NAME_Device = "X-Device";
    private static final String HEADER_NAME_UserAgent = "User-Agent";
    private static final String HEADER_NAME_Date = "Date";

    private String deviceValue = null;
    private String userAgent = null;
    private boolean initUserAgent = false;
    private SimpleDateFormat serverTimeFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    private Date serverTime;
    private Date localTime;

    public HeaderInterceptor() {
        serverTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Context context = RPCApiFactory.mParam.getApp();
        if(TextUtils.isEmpty(deviceValue)) {
            deviceValue = RPCApiFactory.mParam.getUUID();
        }
        String uuid = UUID.randomUUID().toString();
        Request request = chain.request();
        if(!initUserAgent) {
            StringBuilder builder = new StringBuilder();
            builder.append(Version.userAgent());
            builder.append(",").append(RPCUtil.getPackageName(context));
            builder.append(",").append(RPCUtil.getVersionName(context, true));
            userAgent = builder.toString();
            initUserAgent = true;
        }

        logger.info("intercept, replace userAgnet, and Add X-Ehuodi-UUID, X-Ehuodi-Device");
        logger.info("deviceValue:{}, uuid:{}, userAgent:{}", deviceValue, uuid, userAgent);

        Request.Builder builder = request.newBuilder();
        builder.addHeader(HEADER_NAME_UUID, uuid);
        builder.addHeader(HEADER_NAME_Device, deviceValue);
        builder.removeHeader(HEADER_NAME_UserAgent);
        builder.addHeader(HEADER_NAME_UserAgent, userAgent);

        Response response = chain.proceed(builder.build());

        String headerDate = response.header(HEADER_NAME_Date, "");
        if(!TextUtils.isEmpty(headerDate)) {
            //默认时间格式：Mon, 26 Dec 2016 07:48:08 GMT
            try {
                serverTime = serverTimeFormat.parse(headerDate);
                localTime = new Date();
                logger.info("headerDate:{}, serverTime:{}, localTime:{}", headerDate, serverTime, localTime);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return response;
    }

    public Date getServerTime() {
        if(serverTime == null || localTime == null) {
            return new Date();
        }
        return new Date(serverTime.getTime() - localTime.getTime() + System.currentTimeMillis());
    }
}