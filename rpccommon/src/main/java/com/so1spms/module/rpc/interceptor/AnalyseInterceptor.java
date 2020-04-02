//package com.etransfar.module.rpc.interceptor;
//
//import com.google.gson.JsonObject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public final class AnalyseInterceptor implements Interceptor {
//
//    private static final Logger logger = LoggerFactory.getLogger("AnalyseInterceptor");
//
//    public Response intercept(Chain chain) throws IOException {
//
//        Request request = chain.request();
//
//        Response response = null;
//        IOException ioexception = null;
//        try {
//            response = chain.proceed(request);
//            return response;
//        } catch (IOException ioe) {
//            ioexception = ioe;
//            throw ioe;
//        } finally {
//            try {
//                recordRpc(request, response, ioexception);
//            } catch (Exception e) {
//                logger.warn("recordRpc has Exception", e);
//            }
//        }
//    }
//
//    private void recordRpc(Request request, Response response, IOException ioexception) {
//        if(request == null) {
//            return ;
//        }
//
//        //网络错误
//        if(response == null) {
//            MobclickAgent.onEvent(BaseApplication.getInstance(), "rpc_net_error");
//
//            JsonObject eventPoint = getLogForEventPoint(request, response, ioexception, "DEV_NETERROR_00000001");
//            if(eventPoint != null) {
//                LoggerFactory.getLogger(Constants.EVENT_LOG).info(eventPoint.toString());
//            }
//            return ;
//        }
//
//        //服务端http状态错误
//        if(response != null && response.code() != 200 && response.code() != 304) {
//            MobclickAgent.onEvent(BaseApplication.getInstance(), "rpc_service_status_error");
//
//            JsonObject eventPoint = getLogForEventPoint(request, response, ioexception, "DEV_NETERROR_00000002");
//            if(eventPoint != null) {
//                LoggerFactory.getLogger(Constants.EVENT_LOG).info(eventPoint.toString());
//            }
//            return ;
//        }
//
//        //请求成功了，状态都对了，但是就是读取过程中失败了，或者读取后解析成对象失败了
//        if(response != null && (response.code() == 200 || response.code() == 304) && ioexception != null) {
//            MobclickAgent.onEvent(BaseApplication.getInstance(), "rpc_service_json_error");
//
//            JsonObject eventPoint = getLogForEventPoint(request, response, ioexception, "DEV_NETERROR_00000003");
//            if(eventPoint != null) {
//                LoggerFactory.getLogger(Constants.EVENT_LOG).info(eventPoint.toString());
//            }
//            return ;
//        }
//
//        //请求成功了，状态也对，也没有错误了
//        if(response != null && (response.code() == 200 || response.code() == 304) && ioexception == null) {
//            MobclickAgent.onEvent(BaseApplication.getInstance(), "rpc_success_all");
//
//            JsonObject eventPoint = getLogForEventPoint(request, response, ioexception, "DEV_NETERROR_00000004");
//            if(eventPoint != null) {
//                LoggerFactory.getLogger(Constants.EVENT_LOG).info(eventPoint.toString());
//            }
//            return ;
//        }
//
//    }
//
//    private JsonObject getLogForEventPoint(Request request, Response response, IOException ioexception, String caseid) {
//        if(request == null) {
//            return null;
//        }
//        String url = request.url().toString();
//
//        JsonObject eventPoint = new JsonObject();
//        eventPoint.addProperty("caseid", caseid);
//        eventPoint.addProperty("type", "hand");
//        eventPoint.addProperty("process", AppHelper.getPackageName(BaseApplication.getInstance()));
//        eventPoint.addProperty("partyid", SaveData.getString(SaveData.partyid, ""));
//        eventPoint.addProperty("packagename", AppHelper.getPackageName(BaseApplication.getInstance()));
//        eventPoint.addProperty("versionname", AppHelper.getVersionName(BaseApplication.getInstance()));
//        eventPoint.addProperty("versioncode", AppHelper.getVersionCode(BaseApplication.getInstance()));
//        eventPoint.addProperty("uuid", AppHelper.getUUID(BaseApplication.getInstance()));
//
//        JsonObject obj = new JsonObject();
//        if (response != null) {
//            int code = response.code();
//            obj.addProperty("code", code);
//        }
//        if (ioexception != null) {
//            obj.addProperty("exception", ioexception.getMessage());
//        }
//        obj.addProperty("url", url);
//        obj.addProperty("deviceNet", AppHelper.getNetworkType(BaseApplication.getInstance()));
//        String otherInfo = obj.toString();
//        eventPoint.addProperty("otherinfo", otherInfo);
//
//        return eventPoint;
//    }
//
//}
