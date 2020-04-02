package com.micro.network.available.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class ResponseStringConverter implements Converter<ResponseBody, String> {
    static final ResponseStringConverter INSTANCE = new ResponseStringConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
      return value.string();
    }

  }