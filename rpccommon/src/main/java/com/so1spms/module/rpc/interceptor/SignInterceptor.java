package com.so1spms.module.rpc.interceptor;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Version;
import okio.Buffer;

public class SignInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger("SignInterceptor");
    ParamSinger mSinger;
    public SignInterceptor(ParamSinger singer){
        this.mSinger = singer;
    }
    private SignInterceptor(){}
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request;
        if(mSinger!=null){
            if ("GET".equalsIgnoreCase(request.method())) {
                newRequest = rebuildGet(request);
            }else if("POST".equalsIgnoreCase(request.method())){
                newRequest = rebuildPost(request);
            }
        }
        Response response = chain.proceed(newRequest);
        return response;
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Gson mGson = new Gson();
    private Request rebuildPost(Request request) {
        Request newRequest = request;
        String oldUrl = request.url().toString();
        if(request.body() instanceof  FormBody){
            FormBody oldBody = (FormBody) request.body();
            FormBody.Builder builder = new FormBody.Builder();
            Map<String,String> oldParam = new HashMap<>();
            Map<String,String>signParam = null;
            for (int i =0 ; i < oldBody.size(); ++i){
                String name = oldBody.name(i);
                String value = oldBody.value(i);
                oldParam.put(name,value);
            }
            if(oldParam.size()>0){
                signParam = mSinger.getDoggyMap(oldParam);
                for(String key: signParam.keySet()){
                    builder.addEncoded(key,signParam.get(key));
                }
                newRequest = new Request.Builder()
                        .url(oldUrl)
                        .post(builder.build())
                        .build();
            }
            //new branch
        }else if(request.body() instanceof MultipartBody){
            MultipartBody oldBodyMultipart = (MultipartBody)request.body();
            List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            HashMap<String,String>oldParam = new HashMap<>();
            //old part
            for (MultipartBody.Part part:oldPartList) {
                builder.addPart(part);
                if(part.body() instanceof FormBody){
                    FormBody oldBody = (FormBody) part.body();
                    for (int i =0 ; i < oldBody.size(); ++i){
                        String name = oldBody.name(i);
                        String value = oldBody.value(i);
                        oldParam.put(name,value);
                    }
                }
            }
            if(oldParam!=null&&oldParam.size()>0){
                Map<String,String> signParam = new HashMap<>(mSinger.getDoggyMap(oldParam));
                if(signParam!=null&&signParam.size()>0){
                    for(String key:oldParam.keySet()){
                        signParam.remove(key);
                    }
                }
                FormBody.Builder signBodyBuilder = new FormBody.Builder();
                //new param
                for(String key : signParam.keySet()){
                    signBodyBuilder.addEncoded(key,signParam.get(key));
                }
                builder.addPart(signBodyBuilder.build());
                newRequest = request.newBuilder()
                        .post(builder.build())
                        .build();
            }
        }
        return newRequest;
    }

    private Request rebuildGet(Request request) {
        Request newRequest = request;
        HttpUrl httpUrl = request.url();
        Map<String,String> param = new HashMap<>();
        if(httpUrl.queryParameterNames()!=null&&httpUrl.queryParameterNames().size()>0){
            Set<String> keySet = httpUrl.queryParameterNames();
            for(String key: keySet){
                param.put(key,httpUrl.queryParameter(key));
            }
            Map<String,String>signParam = mSinger.getDoggyMap(param);
            HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
            for(String key: signParam.keySet()){
                if(httpUrl.queryParameter(key)==null){
                    urlBuilder.addQueryParameter(key,signParam.get(key));
                }

            }
            HttpUrl newUrl = urlBuilder.build();
            newRequest = new Request.Builder()
                    .url(newUrl)
                    .build();
        }
        return newRequest;
    }

    public interface  ParamSinger{
        Map<String, String> getDoggyMap(  Map<String, String> map);
    }
}