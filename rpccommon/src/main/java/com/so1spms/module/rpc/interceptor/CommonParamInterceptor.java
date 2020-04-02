package com.so1spms.module.rpc.interceptor;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class CommonParamInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger("SignInterceptor");
    ParamAdder mParamAdder;

    public CommonParamInterceptor(ParamAdder singer) {
        this.mParamAdder = singer;
    }

    private CommonParamInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request;
        if (mParamAdder != null&&mParamAdder.getCommonParam()!=null&&mParamAdder.getCommonParam().size()>0) {
            if ("GET".equalsIgnoreCase(request.method())) {
                newRequest = rebuildGet(request);
            } else if ("POST".equalsIgnoreCase(request.method())) {
                newRequest = rebuildPost(request);
            }
        }
        Response response = chain.proceed(newRequest);
        return response;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Gson mGson = new Gson();

    private Request rebuildPost(Request request) {
        Map<String,String> commonParam = mParamAdder.getCommonParam();
        RequestBody oldBody = request.body();
        Request newRequest = request;
        if(oldBody==null){
            oldBody = new FormBody.Builder().build();
        }
        if (oldBody instanceof FormBody) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) oldBody;
            //old param
            for (int i = 0; i < oldFormBody.size(); i++) {
                bodyBuilder.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
            }
            //new param
            for(String key:commonParam.keySet()){
                bodyBuilder.addEncoded(key, commonParam.get(key));
            }
            oldFormBody = bodyBuilder.build();

            newRequest = request.newBuilder()
                        .post(oldFormBody)
                        .build();
        }else if(oldBody instanceof MultipartBody){
            MultipartBody oldBodyMultipart = (MultipartBody)oldBody;
            List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            //old part
            for (MultipartBody.Part part:oldPartList) {
                builder.addPart(part);
            }
            FormBody.Builder commonBodyBuilder = new FormBody.Builder();
            //new param
            for(String key : commonParam.keySet()){
                commonBodyBuilder.addEncoded(key,commonParam.get(key));
            }
            builder.addPart(commonBodyBuilder.build());
            newRequest = request.newBuilder()
                        .post(builder.build())
                        .build();

        }


        return newRequest;
    }

    private Request rebuildGet(Request request) {
        Request newRequest = request;
        HttpUrl httpUrl = request.url();
        Map<String, String> param = mParamAdder.getCommonParam();
        if(param!=null&&param.size()>0){
            HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
            for (String key : param.keySet()) {
                if (httpUrl.queryParameter(key) == null) {
                    urlBuilder.addQueryParameter(key, param.get(key));
                }

            }
            HttpUrl newUrl = urlBuilder.build();
            newRequest = new Request.Builder()
                    .url(newUrl)
                    .build();
        }
        return newRequest;
    }



    public interface ParamAdder {
        Map<String, String> getCommonParam();
    }
}