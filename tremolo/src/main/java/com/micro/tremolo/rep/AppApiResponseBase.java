package com.micro.tremolo.rep;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.so1spms.module.rpc.response.RpcResponse;

/**
 * @author lixiaolei 2018/12/26
 * @Package com.transfar.pratylibrary.rpc.Api
 * Create DateTime: 2018/12/26
 */
public class AppApiResponseBase<T> implements RpcResponse {
    @SerializedName("result")
    private String result;
    @SerializedName("count")
    private String pageCount;
    @SerializedName("code")
    private String code;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("msg")
    private String message;
    @SerializedName("data")

    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getCode() {
        return code;
    }
    public boolean isSuccess(){
        if(TextUtils.equals(code,"1")){
            return true;
        }
        return false;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
