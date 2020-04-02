package com.so1spms.module.rpc.converter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseJSONArrayConverter implements Converter<ResponseBody, JSONArray> {
    static final ResponseJSONArrayConverter INSTANCE = new ResponseJSONArrayConverter();

    @Override
    public JSONArray convert(ResponseBody value) throws IOException {
      try {
        return new JSONArray(value.string());
      } catch (JSONException e) {
        throw new IOException(e);
      }
    }
  }