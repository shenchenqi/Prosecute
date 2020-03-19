package com.micro.network.http3.converter;

import com.google.gson.Gson;
import com.micro.network.http3.filter.BaseBean;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author liwei on 2019/4/25.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type){
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        BaseBean httpResult = gson.fromJson(response, BaseBean.class);
        if (httpResult.getCode() == 200){
            return gson.fromJson(response,type);
        } else {
            throw new IOException(String.format("网络请求报错：　[%s][%s]", httpResult.getCode(), httpResult.getMessage()));
        }
    }
}
