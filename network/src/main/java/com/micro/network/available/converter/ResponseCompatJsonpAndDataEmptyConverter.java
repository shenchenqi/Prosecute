package com.micro.network.available.converter;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseCompatJsonpAndDataEmptyConverter<T> implements Converter<ResponseBody, T> {

  private static Logger logger = LoggerFactory.getLogger("ResponseCompatJsonpAndDataEmptyConverter");
  private final Converter<ResponseBody, T> converter;

  ResponseCompatJsonpAndDataEmptyConverter(Converter<ResponseBody, T> converter) {
    this.converter = converter;
  }

  @Override public T convert(ResponseBody value) throws IOException {
    String jsonp = value.string();
    logger.debug("jsonp=>{}\n", jsonp);
    if(TextUtils.isEmpty(jsonp)) {
      return null;
    }

    //先去除掉外面的JSONP的包装
    int start = jsonp.indexOf("{");
    int end = jsonp.lastIndexOf("}");
    if(start < 0 || end < 0) {
      throw new IOException("is not a good json");
    }
    String json = jsonp.substring(start, end + 1);

    //检测data数据是否为空字符,如果是的话修正为null,也就是空对象
    try {
      JSONObject jsonObject = new JSONObject(json);
      if(jsonObject.has("data")
              && TextUtils.isEmpty(jsonObject.getString("data"))) {
        jsonObject.put("data", null);
      }
      json = jsonObject.toString();
    } catch (JSONException e) {
      throw new IOException(e);
    }

    return converter.convert(ResponseBody.create(value.contentType(), json));
  }
}
