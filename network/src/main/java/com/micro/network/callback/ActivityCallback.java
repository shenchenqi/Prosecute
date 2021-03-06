package com.micro.network.callback;

import android.app.Activity;

import com.mirco.network.BuildConfig;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:30
 */
public abstract class ActivityCallback<T> extends BaseCallback<T> {

    private static Logger logger = LoggerFactory.getLogger("ModelCallback");

    private WeakReference<Activity> weakReference = null;

    public ActivityCallback(Activity activity) {
        weakReference = new WeakReference<Activity>(activity);
        logger.debug("ActivityCallback create, => {}", activity);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        super.onResponse(call, response);
        logger.debug("ActivityCallback onResponse, call => {}, response => {}", call, response);
        if (call.isCanceled()) {
            logger.info("call isCanceled, stop do more,url={}", call.request().url());
            return;
        }
        Activity activity = weakReference == null ? null : weakReference.get();
        if (!canContinue(activity)) {
            logger.info("Activity can not Continue, stop do more,url={}", call.request().url());
            return;
        }
        if (response == null || !response.isSuccessful()) {
            logger.info("url=>{}, showServerErrorMsg=>{}", getErrorMsg(activity, 1001), call.request().url().toString());
            onFinish(call, true);
            return;
        }
        if (response.body() == null) {
            logger.info("url=>{}, showJsonParseErrorMsg=>{}", getErrorMsg(activity, 1002), call.request().url().toString());
            onFinish(call, true);
            return;
        }
        try {
            onResponse(response.body());
        } catch (Throwable throwable) {
            if (BuildConfig.DEBUG) {
                // 测试环境直接崩溃
                throw throwable;
            } else {
                //生产环境把异常吞掉,然后记一条日志,这样能防止出现崩溃,降级处理
                logger.error("onResponse has Exception", throwable);
            }
        } finally {
            onFinish(call, false);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        logger.warn("ActivityCallback onFailure", t);
        if (call.isCanceled()) {
            logger.info("call isCanceled, stop do more,url={}", call.request().url());
            return;
        }
        Activity activity = weakReference == null ? null : weakReference.get();
        if (!canContinue(activity)) {
            return;
        }
        onFinish(call, true);
        if (t != null) {
            if (t.getCause() instanceof JSONException) {
                logger.info("url=>{}, showJsonParseErrorMsg=>{}", getErrorMsg(activity, 1002), call.request().url().toString());
                return;
            }
        }
        logger.debug("url=>{}, showNetworkErrorMsg=>{}", getErrorMsg(activity, 1003), call.request().url().toString());
    }

    /**
     * 在这里处理正确的情况
     *
     * @param response 一个非空的JSON转换过来的结果Object
     */
    public void onResponse(T response) {
    }

    /**
     * 在网络返回后都会调用<br/>
     * 但是如果这个Activity被销毁了,不会被调用<br/>
     * 如果onResponse被调用,则在onResponse方法之后被调用
     *
     * @param call
     * @param hasError 包括网络错误、转换错误、数据错误均是true,只有在onResponse被调用的时候是false
     */
    public void onFinish(Call<T> call, boolean hasError) {
    }
}
