package com.so1spms.module.rpc.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseVoidConverter implements Converter<ResponseBody, Void> {
    static final ResponseVoidConverter INSTANCE = new ResponseVoidConverter();

    @Override
    public Void convert(ResponseBody value) throws IOException {
      value.close();
      return null;
    }
  }