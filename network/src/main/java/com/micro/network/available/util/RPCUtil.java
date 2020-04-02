package com.micro.network.available.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.micro.network.available.ApiConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:13
 */
public class RPCUtil {
    static Logger logger = LoggerFactory.getLogger("RPCUtil");

    public static boolean isNetWorkAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
            return (netWorkInfo != null && netWorkInfo.isAvailable());
        } catch (Exception e) {
            logger.error("isNetWorkAvailable", e);
        }
        return false;
    }


    static String packageName;

    public static String getPackageName(Context context) {
        if (!TextUtils.isEmpty(packageName)) {
            return packageName;
        }
        try {
            packageName = context.getPackageName();
            return packageName;
        } catch (Exception e) {
            logger.error("get package name fail", e);
        }
        return "";
    }

    static String versionNameSimple;
    static String versionNameFull;

    public static String getVersionName(Context context, boolean fullVersion) {
        if (!TextUtils.isEmpty(versionNameSimple) && !fullVersion) {
            return versionNameSimple;
        }
        if (!TextUtils.isEmpty(versionNameFull) && fullVersion) {
            return versionNameFull;
        }
        try {
            String packageName = context.getPackageName();
            String vn = null;
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            vn = pi.versionName;
            if (fullVersion) {
                versionNameFull = vn;
                return versionNameFull;
            }
            String[] strArr = vn.split("\\.");
            if (strArr != null && strArr.length >= 4) {
                vn = String.format("%s.%s.%s", strArr[0], strArr[1], strArr[2]);
            }
            versionNameSimple = vn;
            return versionNameSimple;

        } catch (Exception e) {
            logger.error("get package name fail", e);
        }
        return "";
    }


    private static final String RPC_ENVIROMENT_FILE = "rpc_enviroment";
    private static final String RPC_ENVIROMENT_KEY = "rpc_enviroment_key";

    public static String getRpcEnviroment(Context context) {
        SharedPreferences sp = context.getSharedPreferences(RPC_ENVIROMENT_FILE, Context.MODE_MULTI_PROCESS);
        String env = sp.getString(RPC_ENVIROMENT_KEY, ApiConfig.ENVIRONMENT_TEST);
        return env;
    }

    public static void setRpcEnviroment(Context context, String evn) {
        SharedPreferences sp = context.getSharedPreferences(RPC_ENVIROMENT_FILE, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(RPC_ENVIROMENT_KEY, evn).apply();
    }

    public static String parse(int httpCode) {
        String errorMsg = "未知错误";
        switch (httpCode) {
            case 400:
                errorMsg = "400:Bad Request,请求参数不合法";
                break;
            case 401:
                errorMsg = "401:Unauthorized,未授权,要求身份验证";
                break;
            case 403:
                errorMsg = "403:服务器拒绝请求";
                break;
            case 404:
                errorMsg = "404:您请求的资源去火星了,囧";
                break;
            case 408:
                errorMsg = "408:请求超时";
                break;
            case 413:
                errorMsg = "413:请求实体过大";
                break;
            case 414:
                errorMsg = "413:请求的 URI 过长";
                break;
            case 415:
                errorMsg = "415:目标资源不支持该媒体类型";
                break;
            case 500:
                errorMsg = "500:服务器内部错误";
                break;
            case 501:
                errorMsg = "501:服务器内部错误";
                break;
            case 502:
                errorMsg = "502:网关异常";
                break;
            case 503:
                errorMsg = "503:服务器正忙或正在维护,目前不可用";
                break;
            case 504:
                errorMsg = "504:网关超时";
                break;
            case 505:
                errorMsg = "505:HTTP 版本不受支持";
        }

        return errorMsg;
    }
}