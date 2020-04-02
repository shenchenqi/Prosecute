package com.so1spms.module.rpc.converter;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by qiaobo on 16/8/12.
 */
final class WrapRequestBodyConverter<T> implements Converter<T, RequestBody> {
  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private static Logger logger = LoggerFactory.getLogger("WrapRequestBodyConverter");
  private final Converter<T, RequestBody> converter;
  private static Gson gson = new Gson();
  WrapRequestBodyConverter(Converter<T, RequestBody> converter) {
    this.converter = converter;
  }

  @Override
  public RequestBody convert(T value) throws IOException {
    if(value instanceof JSONObject || value instanceof JSONArray) {
      logger.debug("RequestBody->>>>>json={}", value.toString());
      return RequestBody.create(MEDIA_TYPE, value.toString());
    }
    logger.debug("json={}", gson.toJson(value));
    return converter.convert(value);
  }
}
