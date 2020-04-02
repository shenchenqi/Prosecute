package com.so1spms.module.rpc.eventbus;

import android.content.Context;


import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Response;

import com.etransfar.module.rpccommon.BuildConfig;
import com.etransfar.module.rpccommon.R;

/**
 * Created by qiaobo on 16/8/12.
 */
public class BaseResponseEvent<T> {
    protected static Logger logger = LoggerFactory.getLogger("BaseResponseEvent");

    private Call<T> call;
    private Response<T> response;
    private Throwable throwable;
    private boolean fromCache = false;

    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }

    public Call<T> getCall() {
        return call;
    }

    public void setCall(Call<T> call) {
        this.call = call;
    }

    public Response<T> getResponse() {
        return response;
    }

    public void setResponse(Response<T> response) {
        this.response = response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return response != null && throwable == null && response.isSuccessful() && response.body() != null;
    }

    public T getBody() {
        if(response != null && response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    public String getErrorMsg(@NonNull Context context) {
        String errorMsg;
        if(!BuildConfig.DEBUG) {
            errorMsg = context.getString(R.string.rpc_error_no_network);
        } else {
            if(response == null || !response.isSuccessful()) {
                errorMsg = context.getString(R.string.rpc_error_server);
            } else if (response != null && response.isSuccessful() && response.body() == null) {
                errorMsg = context.getString(R.string.rpc_error_jsonparse);
            } else {
                errorMsg = context.getString(R.string.rpc_error_network);
            }
        }
        logger.info("errorMsg=>{}", errorMsg);
        return errorMsg;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseResponseEvent{");
        builder.append("call=").append(call).append(",");
        builder.append("response=").append(response).append(",");
        builder.append("throwable=").append(throwable).append("}");
        builder.append(">>>>").append(getClass());
        return builder.toString();
    }
}
