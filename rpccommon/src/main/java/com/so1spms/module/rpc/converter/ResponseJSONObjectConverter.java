package com.so1spms.module.rpc.converter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseJSONObjectConverter implements Converter<ResponseBody, JSONObject> {
    static final ResponseJSONObjectConverter INSTANCE = new ResponseJSONObjectConverter();

    @Override
    public JSONObject convert(ResponseBody value) throws IOException {
      try {
        return new JSONObject(value.string());
      } catch (JSONException e) {
        throw new IOException(e);
      }
    }
  }