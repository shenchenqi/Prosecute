package com.so1spms.module.rpc.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by qiaobo on 16/8/12.
 */
final class RequestGsonBodyConverter<T> implements Converter<T, RequestBody> {
  private static Logger logger = LoggerFactory.getLogger("RequestWrapBodyConverter");
  private static final Charset UTF_8 = Charset.forName("UTF-8");


  private Gson gson;
  private TypeAdapter<T> adapter;

  RequestGsonBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override
  public RequestBody convert(T value) throws IOException {
    Buffer buffer = new Buffer();
    Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
    JsonWriter jsonWriter = gson.newJsonWriter(writer);
    adapter.write(jsonWriter, value);
    jsonWriter.close();
    JSONObject jsonObject = null;

    FormBody.Builder bodyBuilder = new FormBody.Builder();
    try{
      jsonObject = new JSONObject(buffer.readUtf8());
      Iterator<String> keys = jsonObject.keys();
      while (keys != null && keys.hasNext()) {
        String name = keys.next();
        bodyBuilder.addEncoded(name, jsonObject.getString(name));
      }
    } catch (JSONException e) {
      logger.error("解析JSON出错，请检查你的Entity对象", e);
      throw new IOException(e);
    } catch (Exception e) {
      logger.error("在转换中出现了问题", e);
      throw new IOException(e);
    }
    return bodyBuilder.build();
  }
}
