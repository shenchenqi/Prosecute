package com.micro.network.http3.converter;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author liwei on 2019/4/25.
 */

public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private final Gson gson;
    private final Type type;

    public GsonRequestBodyConverter(Gson gson, Type type){
        this.gson = gson;
        this.type = type;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String requestBody = gson.toJson(value);
        return RequestBody.create(MEDIA_TYPE, requestBody);
    }
}
