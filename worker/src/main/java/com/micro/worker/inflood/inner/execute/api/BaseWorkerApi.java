package com.micro.worker.inflood.inner.execute.api;

import android.content.Context;

import com.micro.hook.api.BaseApi;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
import com.micro.worker.inflood.version.WorkerParam;

/**
 * @Author KiLin
 * @Time 2020/4/11 10:50
 */
public abstract class BaseWorkerApi extends BaseApi implements BaseInterface {


    protected BaseWorkerApi(Hook hook, Context context) {
        super(hook, context);
    }

    protected synchronized Object getApiService() {
        return hook.callStaticMethod(WorkerParam.API_APP_CLASS, WorkerParam.API_APP_SERVICE_METHOD);
    }

    protected synchronized Object getValue(int type) {
        if (Lang.isEquals(1, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_COLD_START);
        } else if (Lang.isEquals(2, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_ON_HOME_PAGE_CREATED);
        } else if (Lang.isEquals(3, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_ON_FOREGROUND);
        } else if (Lang.isEquals(4, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_ON_BACKGROUND);
        } else if (Lang.isEquals(5, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_LOGIN);
        } else if (Lang.isEquals(6, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_LOGOUT);
        } else if (Lang.isEquals(7, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_AFTER_STARTUP);
        } else if (Lang.isEquals(8, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_AFTER_ABTEST);
        } else if (Lang.isEquals(9, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_NETWORK_CHANGED);
        } else if (Lang.isEquals(10, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_BUSY_TIME);
        } else if (Lang.isEquals(11, type)) {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_BUSY_TIME_NON_DEFAULT);
        } else {
            return hook.getStaticField(WorkerParam.API_ENUM_CONFIG_CLASS, WorkerParam.API_ENUM_CONFIG_DEFAULT);
        }
    }
}