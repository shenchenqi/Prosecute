package com.so1spms.module.rpc.callback;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.etransfar.module.rpccommon.R;
import com.so1spms.module.rpc.RPCApiFactory;
import com.so1spms.module.rpc.response.RpcResponse;
import com.so1spms.module.rpc.util.RPCUtil;
import com.so1spms.module.rpc.util.ToastCompat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseCallback<T> implements Callback<T> {

    Logger mLogger = LoggerFactory.getLogger("BaseCallback");
    public final String AUTHORITY_FAILURE_MSG = "authorityFailure";
    public static final String AUTHORITY_FAILURE_CODE = "99000099";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null)
            checkAuthorityFailure(response);
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
            mLogger.info("checkRpcResponse code " + code + ", msg " + msg);
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
            mLogger.info("checkAuthorityFailure Exception " + e + ", body " + response.body());
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
            mLogger.info("checkStringResponse code " + code + ", msg " + msg);
            if ("401".equalsIgnoreCase(code) ||
                    "403".equalsIgnoreCase(code) ||
                    AUTHORITY_FAILURE_CODE.equals(code) ||
                    AUTHORITY_FAILURE_MSG.equals(msg) ||
                    ((("5".equals(code) || "CB_E0008".equals(code))) && "session超时".equals(msg))) {
                onTokenInvalid();
            }
        } catch (Exception e) {
            mLogger.info("checkAuthorityFailure Exception " + e + ", body " + response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }


    public void showJsonParseErrorMsg(Context context) {
        ToastCompat.show(context, R.string.rpc_error_jsonparse, Toast.LENGTH_LONG);
    }

    public void showServerErrorMsg(Context context) {
        if (!RPCUtil.isNetWorkAvailable(context)) {
            ToastCompat.show(context, R.string.rpc_error_no_network, Toast.LENGTH_LONG);
            return;
        }
        ToastCompat.show(context, R.string.rpc_error_server, Toast.LENGTH_LONG);
    }

    public void showNetworkErrorMsg(Context context) {
        if (!RPCUtil.isNetWorkAvailable(context)) {
            ToastCompat.show(context, R.string.rpc_error_no_network, Toast.LENGTH_LONG);
            return;
        }
        ToastCompat.show(context, R.string.rpc_error_network, Toast.LENGTH_LONG);
    }


    public boolean canContinue(Activity activity) {
        if (activity == null) {
            mLogger.warn("canContinue return false: activity is null");
            return false;
        }
        if (activity.isFinishing()) {
            mLogger.warn("canContinue return false: activity.isFinishing");
            return false;
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && activity.isDestroyed()) {
            mLogger.warn("canContinue return false, SDK_INT={}", Build.VERSION.SDK_INT);
            return false;
        }
        return true;
    }

    public boolean canContinue(Fragment fragment) {
        if (fragment == null) {
            mLogger.warn("canContinue return false: fragment is null");
            return false;
        }
        if (fragment.isDetached() || fragment.isRemoving()) {
            mLogger.warn("canContinue return false, Fragment isDetached OR isRemoving.");
            return false;
        }
        Activity activity = fragment.getActivity();
        if (activity == null || activity.isFinishing()) {
            mLogger.warn("canContinue return false, activity is null Or isFinishing.");
            return false;
        }
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
                && activity.isDestroyed()) {
            mLogger.warn("canContinue return false, SDK_INT={}, activity is isDestroyed.", Build.VERSION.SDK_INT);
            return false;
        }
        return true;
    }
}
