/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.so1spms.module.rpc.interceptor;

import android.util.Log;

import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class HttpLoggingInterceptor implements Interceptor {
  private static final Charset UTF8 = Charset.forName("UTF-8");

  public enum Level {
    /** No logs. */
    NONE,
    /**
     * Logs request and response lines.
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1 (3-byte body)
     *
     * <-- 200 OK (22ms, 6-byte body)
     * }</pre>
     */
    BASIC,
    /**
     * Logs request and response lines and their respective headers.
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1
     * Host: example.com
     * Content-Type: plain/text
     * Content-Length: 3
     * --> END POST
     *
     * <-- 200 OK (22ms)
     * Content-Type: plain/text
     * Content-Length: 6
     * <-- END HTTP
     * }</pre>
     */
    HEADERS,
    /**
     * Logs request and response lines and their respective headers and bodies (if present).
     *
     * <p>Example:
     * <pre>{@code
     * --> POST /greeting http/1.1
     * Host: example.com
     * Content-Type: plain/text
     * Content-Length: 3
     *
     * Hi?
     * --> END POST
     *
     * <-- 200 OK (22ms)
     * Content-Type: plain/text
     * Content-Length: 6
     *
     * Hello!
     * <-- END HTTP
     * }</pre>
     */
    BODY
  }

  public interface Logger {

    void log(String message);

    void end();

  }

  private volatile Level level = Level.NONE;
  private volatile String tag;
  private volatile org.slf4j.Logger slf4jLogger;

  public Logger newLoggerForHttp() {
    return new Logger() {

      StringBuilder builder = new StringBuilder();

      public void log(String message) {
        builder.append(message).append("\n");
      }

      public void end() {
        slf4jLogger.info(builder.toString());
        Log.i(tag,builder.toString());
      }
    };
  }

  public HttpLoggingInterceptor() {
    this("http", Level.NONE);
  }

  public HttpLoggingInterceptor(String tag, Level level) {
    this.tag = tag;
    this.level = level;
    this.slf4jLogger = LoggerFactory.getLogger(tag);
  }

  /** Change the level at which this interceptor logs. */
  public HttpLoggingInterceptor setLevel(Level level) {
    if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
    this.level = level;
    return this;
  }

  public Level getLevel() {
    return level;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Level level = this.level;
    Logger logger = newLoggerForHttp();
    logger.log("");

    Request request = chain.request();
    if (level == Level.NONE) {
      return chain.proceed(request);
    }

    boolean logBody = level == Level.BODY;
    boolean logHeaders = logBody || level == Level.HEADERS;

    RequestBody requestBody = request.body();
    boolean hasRequestBody = requestBody != null;

    Connection connection = chain.connection();
    Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
    String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
    if (!logHeaders && hasRequestBody) {
      requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
    }
    logger.log(requestStartMessage);
    if (logHeaders) {
      boolean requestBodyLog = false;
      if (hasRequestBody) {
        // Request body headers are only present when installed as a network interceptor. Force
        // them to be included (when available) so there values are known.
        if (requestBody.contentType() != null) {
          String contentType = requestBody.contentType()+"";
          logger.log("Content-Type: " + contentType);
          if(contentType.startsWith("application/json")||contentType.startsWith("application/x-www-form-urlencoded")||contentType.startsWith("text")){
            requestBodyLog = true;
          }else{
            requestBodyLog = false;
          }
        }
        if (requestBody.contentLength() != -1) {
          long contentLength =requestBody.contentLength();
          logger.log("Content-Length: " + contentLength);
          if(contentLength>1024*3){
            requestBodyLog = false;
          }
        }
      }

      Headers headers = request.headers();
      for (int i = 0, count = headers.size(); i < count; i++) {
        String name = headers.name(i);
        // Skip headers from the request body as they are explicitly logged above.
        if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
          logger.log(name + ": " + headers.value(i));
        }
      }

      if (!logBody || !hasRequestBody) {
        logger.log("--> END " + request.method());
      } else if (bodyEncoded(request.headers())) {
        logger.log("--> END " + request.method() + " (encoded body omitted)");
      } else {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);

        Charset charset = UTF8;
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
          charset = contentType.charset(UTF8);
        }

        logger.log("");
        if (isPlaintext(buffer)&&requestBodyLog) {
          logger.log("--> Body:");
          logger.log(buffer.readString(charset));
          logger.log("--> END " + request.method()
              + " (" + requestBody.contentLength() + "-byte body)");
        } else {
          logger.log("--> END " + request.method() + " (binary "
              + requestBody.contentLength() + "-byte body omitted)");
        }
      }
    }

    long startNs = System.nanoTime();
    Response response;
    try {
      response = chain.proceed(request);
    } catch (Exception e) {
      logger.log("<-- HTTP FAILED: " + e);
      logger.end();
      throw e;
    }
    boolean responseBodyLog = false;
    long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

    ResponseBody responseBody = response.body();
    long contentLength = responseBody.contentLength();
    String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
    logger.log("<-- " + response.code() + ' ' + response.message() + ' '
        + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
        + bodySize + " body" : "") + ')');


    if (logHeaders) {
      Headers headers = response.headers();
      for (int i = 0, count = headers.size(); i < count; i++) {
        logger.log(headers.name(i) + ": " + headers.value(i));
      }

      if (!logBody || !HttpHeaders.hasBody(response)) {
        logger.log("<-- END HTTP");
      } else if (bodyEncoded(response.headers())) {
        logger.log("<-- END HTTP (encoded body omitted)");
      } else {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
          if(contentType.toString().startsWith("application/json")||contentType.toString().startsWith("text")){
            responseBodyLog = true;
          }else{
            responseBodyLog = false;
          }
          try {
            charset = contentType.charset(UTF8);
          } catch (UnsupportedCharsetException e) {
            logger.log("");
            logger.log("Couldn't decode the response body; charset is likely malformed.");
            logger.log("<-- END HTTP");
            logger.end();
            return response;
          }
        }

        if (!isPlaintext(buffer)) {
          logger.log("");
          logger.log("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
          logger.end();
          return response;
        }
        if(contentLength>10*1024){
          responseBodyLog = false;
        }
        if (contentLength != 0&&responseBodyLog) {
          logger.log("<-- Body:");
          logger.log(buffer.clone().readString(charset));
        }else{
          logger.log("<-- END HTTP (binary " + buffer.size() + "-contentType or contentLength body omitted)");
        }

        logger.log("<-- END HTTP (" + buffer.size() + "-byte body)");
      }
    }
    logger.end();
    return response;
  }

  /**
   * Returns true if the body in question probably contains human readable text. Uses a small sample
   * of code points to detect unicode control characters commonly used in binary file signatures.
   */
  static boolean isPlaintext(Buffer buffer) {
    try {
      Buffer prefix = new Buffer();
      long byteCount = buffer.size() < 64 ? buffer.size() : 64;
      buffer.copyTo(prefix, 0, byteCount);
      for (int i = 0; i < 16; i++) {
        if (prefix.exhausted()) {
          break;
        }
        int codePoint = prefix.readUtf8CodePoint();
        if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
          return false;
        }
      }
      return true;
    } catch (EOFException e) {
      return false; // Truncated UTF-8 sequence.
    }
  }

  private boolean bodyEncoded(Headers headers) {
    String contentEncoding = headers.get("Content-Encoding");
    return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
  }
}
