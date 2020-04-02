package com.so1spms.module.rpc;

import android.app.Application;
import android.text.TextUtils;


import com.so1spms.module.rpc.converter.CustomConvertersFactory;
import com.so1spms.module.rpc.interceptor.CommonParamInterceptor;
import com.so1spms.module.rpc.interceptor.SignInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiaobo on 16/8/12.
 */
public class ApiConfig {
    static final Logger mLogger = LoggerFactory.getLogger("ApiConfig");
    /**开发环境**/
    public static final String ENVIRONMENT_DEV = "dev";
    /**测试环境**/
    public static final String ENVIRONMENT_TEST = "test";
    /**预发布环境**/
    public static final String ENVIRONMENT_PRE_ONLINE = "pre_online";
    /**先上环境**/
    public static final String ENVIRONMENT_ONLINE = "online";
    /**自定义的环境**/
    public static final String ENVIRONMENT_CUSTOM = "custom";

    private final List<Class<?>> clazzList;
    private final String mainBaseUrl;
    private final Map<String, String> environmentBaseUrlMap;
    private final List<Converter.Factory> converterFactorys;
    private final SignInterceptor.ParamSinger mSigner;
    private final CommonParamInterceptor.ParamAdder mCommonParamAdder;


    private ApiConfig(List<Class<?>> clazzList,
                      String mainBaseUrl,
                      Map<String, String> environmentBaseUrlMap,
                      List<Converter.Factory> converterFactorys,
                      SignInterceptor.ParamSinger signer,
                      CommonParamInterceptor.ParamAdder adder) {
        this.clazzList = clazzList;
        this.mainBaseUrl = mainBaseUrl;
        this.environmentBaseUrlMap = environmentBaseUrlMap;
        this.converterFactorys = converterFactorys;
        this.mSigner = signer;
        this.mCommonParamAdder = adder;
    }

    public List<Class<?>> getClazzList() {
        return clazzList;
    }

    public List<Converter.Factory> getConverterFactorys() {
        return converterFactorys;
    }

    /**
     * 根据环境不同,获取该环境下的HOST地址
     * @param environment
     * @return
     */
    public String getBaseUrl(String environment) {
        String baseUrl = null;
        if(environmentBaseUrlMap != null) {
            baseUrl = environmentBaseUrlMap.get(environment);
        }
        if(TextUtils.isEmpty(baseUrl)) {
            baseUrl = mainBaseUrl;
        }
        mLogger.debug("getBaseUrl  env = {},baseUrl = {}",environment,baseUrl);
        return baseUrl;
    }

    public SignInterceptor.ParamSinger getSinger(){
        return  mSigner;
    }
    public CommonParamInterceptor.ParamAdder getCommonParamAdder(){
        return mCommonParamAdder;
    }
    @Override
    public String toString() {
        return "ApiConfig{" +
                "clazzList=" + clazzList +
                ", mainBaseUrl='" + mainBaseUrl + '\'' +
                ", environmentBaseUrlMap=" + environmentBaseUrlMap +
                ", converterFactorys=" + converterFactorys +
                "signer="+mSigner+
                '}';
    }

    public static Builder config(Class<?> rpcInterface) {
        return new Builder().addInterface(rpcInterface);
    }

    public static class Builder {
        private List<Class<?>> clazzList;
        private String mainBaseUrl;
        private Map<String, String> environmentBaseUrlMap;
        private List<Converter.Factory> converterFactorys;
        private SignInterceptor.ParamSinger singer;
        private CommonParamInterceptor.ParamAdder adder;
        public Builder addInterface(Class<?> clazz) {
            if(clazzList == null) {
                clazzList = new ArrayList<Class<?>>();
            }
            clazzList.add(clazz);
            return this;
        }
        public Builder singer(SignInterceptor.ParamSinger s) {
            this.singer = s;
            return this;
        }
        public Builder commonParamAdder(CommonParamInterceptor.ParamAdder ad) {
            this.adder = ad;
            return this;
        }
        public Builder mainBaseUrl(String mainBaseUrl) {
            this.mainBaseUrl = mainBaseUrl;
            return this;
        }

        /**注册开发环境的地址**/
        public Builder devBaseUrl(String devBaseUrl) {
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            environmentBaseUrlMap.put(ENVIRONMENT_DEV, devBaseUrl);
            return this;
        }

        /**注册测试环境的地址**/
        public Builder testBaseUrl(String testBaseUrl) {
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            environmentBaseUrlMap.put(ENVIRONMENT_TEST, testBaseUrl);
            return this;
        }

        /**注册预发布环境的地址**/
        public Builder preOnlineBaseUrl(String preOnlineBaseUrl) {
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            environmentBaseUrlMap.put(ENVIRONMENT_PRE_ONLINE, preOnlineBaseUrl);
            return this;
        }

        /**线上环境的地址**/
        public Builder onlineBaseUrl(String onlineBaseUrl) {
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            environmentBaseUrlMap.put(ENVIRONMENT_ONLINE, onlineBaseUrl);
            return this;
        }

        /**注册自定义环境的地址**/
        public Builder customBaseUrl(String customBaseUrl) {
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            environmentBaseUrlMap.put(ENVIRONMENT_CUSTOM, customBaseUrl);
            return this;
        }

        public Builder converterFactory(Converter.Factory factory) {
            if(converterFactorys == null) {
                converterFactorys = new ArrayList<Converter.Factory>();
            }
            converterFactorys.add(factory);
            return this;
        }

        public ApiConfig build() {
            if(clazzList == null || clazzList.isEmpty()) {
                throw new RuntimeException("addInterface is must be set.");
            }
            if(TextUtils.isEmpty(mainBaseUrl)) {
                throw new RuntimeException("mainBaseUrl is must be set.");
            }
            if(environmentBaseUrlMap == null) {
                environmentBaseUrlMap = new HashMap<String, String>();
            }
            if(converterFactorys == null) {
                converterFactorys = new ArrayList<Converter.Factory>();
                converterFactorys.add(CustomConvertersFactory.create(GsonConverterFactory.create()));
            }
            return new ApiConfig(clazzList, mainBaseUrl, environmentBaseUrlMap, converterFactorys,singer,adder);
        }

    }

}
