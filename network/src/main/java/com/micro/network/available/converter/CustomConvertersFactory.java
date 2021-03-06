package com.micro.network.available.converter;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:18
 */
public class CustomConvertersFactory extends Converter.Factory {

    private static Logger logger = LoggerFactory.getLogger("CustomConvertersFactory");

    @Retention(RUNTIME)
    public @interface CompatJsonpAndDataEmptyNotObject {
    }

    @Retention(RUNTIME)
    public @interface RequestBodyUseJson {
    }

    private final Converter.Factory jsonFactory;
    private final Gson gson = new Gson();

    public static CustomConvertersFactory create(@NonNull Converter.Factory jsonFactory) {
        return new CustomConvertersFactory(jsonFactory);
    }

    private CustomConvertersFactory(Converter.Factory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        if (type == String.class) {
            return ResponseStringConverter.INSTANCE;
        } else if (type == ResponseBody.class) {
            return ResponseBodyConverter.INSTANCE;
        } else if (type == Void.class) {
            return ResponseVoidConverter.INSTANCE;
        } else if (type == JSONArray.class) {
            return ResponseJSONArrayConverter.INSTANCE;
        } else if (type == JSONObject.class) {
            return ResponseJSONObjectConverter.INSTANCE;
        }

        for (Annotation annotation : annotations) {
            if (annotation instanceof CompatJsonpAndDataEmptyNotObject) {
                return new ResponseCompatJsonpAndDataEmptyConverter(jsonFactory.responseBodyConverter(type, annotations, retrofit));
            }
        }
        return jsonFactory.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, okhttp3.RequestBody> requestBodyConverter(Type type,
                                                                  Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof RequestBodyUseJson) {
                return new RequestWrapBodyConverter(jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit));
            }
        }
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new RequestGsonBodyConverter<>(gson, adapter);
    }

}