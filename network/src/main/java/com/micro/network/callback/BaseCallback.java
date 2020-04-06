package com.micro.network.callback;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.fragment.app.Fragment;

import com.micro.network.available.RPCApiFactory;
import com.micro.network.available.response.RpcResponse;
import com.micro.network.available.util.RPCUtil;
import com.mirco.network.R;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:08
 */
public class BaseCallback<T> implements Callback<T> {

    private Logger logger = LoggerFactory.getLogger("BaseCallback");
    public final String AUTHORITY_FAILURE_MSG = "authorityFailure";
    public static final String AUTHORITY_FAILURE_CODE = "99000099";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            checkAuthorityFailure(response);
        }
    }

    public void checkAuthorityFailure(Response<T> response) {
        if (response.body() != null && response.body() instanceof RpcResponse) {
            checkRpcResponse(response);
        } else {
            checkStringResponse(response);
        }
    }

    private void checkRpcResponse(Response<T> response) {
        try {
            RpcResponse rpcResponse = (RpcResponse) response.body();
            String code = rpcResponse.getCode();
            String msg = rpcResponse.getMessage();
            logger.info("checkRpcResponse code " + code + ", msg " + msg);
            if (rpcResponse != null) {
                if ("401".equalsIgnoreCase(code) ||
                        "403".equalsIgnoreCase(code) ||
                        AUTHORITY_FAILURE_CODE.equals(code) ||
                        AUTHORITY_FAILURE_MSG.equals(msg) ||
                        ((("5".equals(code) || "CB_E0008".equals(code))) && "session超时".equals(msg))) {
                    onTokenInvalid();
                }
            }
        } catch (Exception e) {
            logger.info("checkAuthorityFailure Exception " + e + ", body " + response.body());
        }
    }

    private void onTokenInvalid() {
        if (RPCApiFactory.mParam != null && RPCApiFactory.mParam.getTokenCheck() != null) {
            RPCApiFactory.mParam.getTokenCheck().onTokenInvalid();
        }
    }

    private void checkStringResponse(Response<T> response) {
        try {
            JSONObject jsonObject = new JSONObject(response.body().toString());
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            logger.info("checkStringResponse code " + code + ", msg " + msg);
            if ("401".equalsIgnoreCase(code) ||
                    "403".equalsIgnoreCase(code) ||
                    AUTHORITY_FAILURE_CODE.equals(code) ||
                    AUTHORITY_FAILURE_MSG.equals(msg) ||
                    ((("5".equals(code) || "CB_E0008".equals(code))) && "session超时".equals(msg))) {
                onTokenInvalid();
            }
        } catch (Exception e) {
            logger.info("checkAuthorityFailure Exception " + e + ", body " + response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(call.isCanceled()) {
            logger.info("call isCanceled, stop do more,url={}", call.request().url());
            return ;
        }
    }

    protected String getErrorMsg(Context context, int type) {
        String errorMsg;
        if (!RPCUtil.isNetWorkAvailable(context)) {
            errorMsg = "网络不可用,请检查网络"/*context.getString(R.string.rpc_error_no_network)*/;
        } else if (type == 1001) {
            errorMsg = "服务器繁忙,请稍后再试"/*context.getString(R.string.rpc_error_server)*/;
        } else if (type == 1002) {
            errorMsg = "服务器出错啦,请稍后再试"/*context.getString(R.string.rpc_error_jsonparse)*/;
        } else {
            errorMsg = "网络出错,请检查网络"/*context.getString(R.string.rpc_error_network)*/;
        }
        return errorMsg;
    }

    public boolean canContinue(Activity activity) {
        if (activity == null) {
            logger.warn("canContinue return false: activity is null");
            return false;
        }
        if (activity.isFinishing()) {
            logger.warn("canContinue return false: activity.isFinishing");
            return false;
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && activity.isDestroyed()) {
            logger.warn("canContinue return false, SDK_INT={}", Build.VERSION.SDK_INT);
            return false;
        }
        return true;
    }

    public boolean canContinue(Fragment fragment) {
        if (fragment == null) {
            logger.warn("canContinue return false: fragment is null");
            return false;
        }
        if (fragment.isDetached() || fragment.isRemoving()) {
            logger.warn("canContinue return false, Fragment isDetached OR isRemoving.");
            return false;
        }
        Activity activity = fragment.getActivity();
        if (activity == null || activity.isFinishing()) {
            logger.warn("canContinue return false, activity is null Or isFinishing.");
            return false;
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && activity.isDestroyed()) {
            logger.warn("canContinue return false, SDK_INT={}, activity is isDestroyed.", Build.VERSION.SDK_INT);
            return false;
        }
        return true;
    }
}
