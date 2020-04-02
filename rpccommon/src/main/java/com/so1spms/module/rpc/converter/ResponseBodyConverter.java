package com.so1spms.module.rpc.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {
    static final ResponseBodyConverter INSTANCE = new ResponseBodyConverter();

    @Override
    public ResponseBody convert(ResponseBody value) throws IOException {
      return value;
    }
  }