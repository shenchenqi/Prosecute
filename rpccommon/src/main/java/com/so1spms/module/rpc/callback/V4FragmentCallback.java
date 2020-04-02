package com.so1spms.module.rpc.callback;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.etransfar.module.rpccommon.BuildConfig;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by qiaobo on 16/7/4.
 */
public abstract class V4FragmentCallback<T> extends BaseCallback<T> {

    private static Logger logger = LoggerFactory.getLogger("FragmentCallback");

    private WeakReference<Fragment> weakReference = null;

    public V4FragmentCallback(Fragment fragment) {
        weakReference = new WeakReference<Fragment>(fragment);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        super.onResponse(call, response);
        Fragment fragment = weakReference == null ? null : weakReference.get();
        if (!canContinue(fragment)) {
            return ;
        }
        Context context = fragment.getContext();
        if(response == null || !response.isSuccessful()) {
            showServerErrorMsg(context);
            onFinish(call, true);
            return ;
        }
        if(response.body() == null) {
            showJsonParseErrorMsg(context);
            onFinish(call, true);
            return ;
        }
        try {
            onResponse(response.body());
        } catch (Throwable throwable) {
            if(BuildConfig.DEBUG) {
                // 测试环境直接崩溃
                throw throwable;
            } else {
                //生产环境把异常吞掉,然后记一条日志
                logger.error("onResponse has Exception", throwable);
            }
        } finally {
            onFinish(call, false);
        }
    }

    /**
     * 在这里处理正确的情况
     * @param response 一个非空的JSON转换过来的结果Object
     */
    public void onResponse(@NonNull T response) {

    }

    /**
     * 在网络返回后都会调用<br/>
     * 但是如果这个Activity被销毁了,不会被调用<br/>
     * 如果onResponse被调用,则在onResponse方法之后被调用
     * @param call
     * @param hasError 包括网络错误、转换错误、数据错误均是true,只有在onResponse被调用的时候是false
     */
    public void onFinish(Call<T> call, boolean hasError) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        super.onFailure(call,t);
        Fragment fragment = weakReference == null ? null : weakReference.get();
        if (!canContinue(fragment)) {
            return ;
        }
        onFinish(call, true);
        Context context = fragment.getActivity();
        if(t != null) {
            if(t.getCause() instanceof JSONException) {
                showJsonParseErrorMsg(context);
                return;
            }
        }

        if(context != null) {
            showNetworkErrorMsg(context);
        }
    }

}
