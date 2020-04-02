package com.micro.network.available;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.micro.network.available.interceptor.CommonParamInterceptor;
import com.micro.network.available.interceptor.HeaderInterceptor;
import com.micro.network.available.interceptor.HttpLoggingInterceptor;
import com.micro.network.available.interceptor.SignInterceptor;
import com.micro.network.available.interfaces.RPCParam;
import com.micro.network.available.util.RPCUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:15
 */
public class RPCApiFactory {

    private static final Logger logger = LoggerFactory.getLogger("RPCApiFactory");
    private static OkHttpClient client;
    private static Map<Class<?>, Object> serviceCache = new ConcurrentHashMap<Class<?>, Object>();
    private static Map<Class<?>, ApiConfig> apiConfigMap = new ConcurrentHashMap<Class<?>, ApiConfig>();
    private static String environment = ApiConfig.ENVIRONMENT_ONLINE;
    private static HeaderInterceptor headerInterceptor = new HeaderInterceptor();

    private static void useDebugApi(boolean isDebug){
        if(isDebug){
            //测试，读取文件，设置rpc环境（默认使用测试环境）
            setRpcEnviroment(RPCUtil.getRpcEnviroment(RPCApiFactory.mParam.getApp()));
        }else{
            //生产，默认使用正式环境，不用设置
        }
    }

    public static void setRpcEnviroment(String env) {
        if(serviceCache != null && !serviceCache.isEmpty()) {
            serviceCache.clear();
        }
        if(TextUtils.isEmpty(env)){
            environment = ApiConfig.ENVIRONMENT_TEST;
        }else{
            switch (env){
                case ApiConfig.ENVIRONMENT_TEST:
                case ApiConfig.ENVIRONMENT_PRE_ONLINE:
                case ApiConfig.ENVIRONMENT_ONLINE:
                case ApiConfig.ENVIRONMENT_DEV:
                case ApiConfig.ENVIRONMENT_CUSTOM:
                    environment = env;
                    break;
                default:
                    environment = ApiConfig.ENVIRONMENT_ONLINE;
                    break;
            }
        }
    }



    /**
     * 初始化Application
     * @param app
     */
    public static RPCParam mParam;
    public static void init(RPCParam param){
        mParam = param;
        useDebugApi(mParam.isDebug());
    }
    public static <T> T create(Class<T> service) {
        if(mParam==null){
            logger.error("mApp is null please use RpcApiFactory.init(app) to init RPCApiFactory");
            return null;
        }
        T target = (T) serviceCache.get(service);
        ApiConfig config = apiConfigMap.get(service);
        if(target == null) {
            Retrofit retrofit;
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(getBaseUrl(service))
                    .client(getOkHttpClient(config));

            List<Converter.Factory> factorys = getConverterFactory(service);
            if(factorys != null && !factorys.isEmpty()) {
                for (Converter.Factory f: factorys) {
                    builder.addConverterFactory(f);
                }
            }

            retrofit = builder.build();
            target = retrofit.create(service);
            serviceCache.put(service, target);
        }
        return target;
    }

    public static void addConfig(ApiConfig apiConfig) {
        if(apiConfig == null) {
            logger.warn("addConfig, but apiConfig is null");
            return ;
        }
        List<Class<?>> list = apiConfig.getClazzList();
        if(list != null && !list.isEmpty()) {
            for (Class<?> clazz : list) {
                apiConfigMap.put(clazz, apiConfig);
                logger.info("addConfig, clazz:{}, apiConfig:{}", clazz, apiConfig);
            }
        }
    }

    public static <T> String getBaseUrl(Class<T> service) {
        ApiConfig config = apiConfigMap.get(service);
        assert config != null : "service must be init config";
        return config.getBaseUrl(environment);
    }

    public static <T> List<Converter.Factory> getConverterFactory(Class<T> service) {
        ApiConfig config = apiConfigMap.get(service);
        assert config != null : "service must be init config";
        return config.getConverterFactorys();
    }

    public static synchronized OkHttpClient getOkHttpClient(ApiConfig config) {
        if (client == null) {
            client = null;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //接入stetho调试器
            Interceptor stethoInterceptor = getStethoInterceptor();
            if(stethoInterceptor != null) {
                logger.warn("find com.facebook.stetho.okhttp3.StethoInterceptor, add it");
                builder.addNetworkInterceptor(stethoInterceptor);
            }

            //设置Mock调试器
            Interceptor mockInterceptor = getMockInterceptor();
            if(mockInterceptor != null) {
                logger.warn("find com.etransfar.module.mockApi.Mockinterceptor, add it");
                builder.addInterceptor(mockInterceptor);
            }


            //设置X-Ehuodi的追踪HEADER,和更新时间
            builder.addInterceptor(headerInterceptor);

            //公共参数
            if(config.getCommonParamAdder()!=null){
                builder.addInterceptor(new CommonParamInterceptor(config.getCommonParamAdder()));
            }

            if(config.getSinger()!=null){
                //参数签名
                builder.addInterceptor(new SignInterceptor(config.getSinger()));
            }
            //陆鲸http统计
            if(mParam.isDebug()){
                Interceptor ljHttpInterceptor = getLJHttpInterceptor();
                if(ljHttpInterceptor!=null){
                    builder.addInterceptor(ljHttpInterceptor);
                }
            }
            //设置HEADERS的追踪日志
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(RPCConstants.NET_LOG_HEADERS, HttpLoggingInterceptor.Level.HEADERS));

            //设置追踪BODYS的日志
            builder.addInterceptor(new HttpLoggingInterceptor(RPCConstants.NET_LOG_BODYS, HttpLoggingInterceptor.Level.BODY));

            //设置RPC请求的统计
//            builder.addInterceptor(new AnalyseInterceptor());

            //设置缓存目录
            File httpCacheDirectory = new File(RPCApiFactory.mParam.getApp().getFilesDir(), "httpcache");
            logger.info("httpCacheDirectory={}", httpCacheDirectory.getAbsolutePath());
            builder.cache(new Cache(httpCacheDirectory, 30 * 1024 * 1024));

            //设置超时策略
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(90, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            //指定DNS服务器
            builder.dns(Dns.SYSTEM);

            client = builder.build();

        }
        return client;
    }

    public static Interceptor getStethoInterceptor() {
        try {
            Class clazz = Class.forName("com.facebook.stetho.okhttp3.StethoInterceptor");
            return (Interceptor)clazz.newInstance();
        } catch (Exception e) {
            logger.info("com.facebook.stetho.okhttp3.StethoInterceptor not find, can not debug use Stetho.");
        }
        return null;
    }

    public static Interceptor getLJHttpInterceptor(){
        try {
            Class clazz = Class.forName("com.etransfar.module.rpc.interceptor.LJLoggingInterceptor");
            return (Interceptor)clazz.newInstance();
        } catch (Exception e) {
            logger.info("com.etransfar.module.rpc.interceptor.LJLoggingInterceptor.");
        }
        return null;
    }

    public static Interceptor getMockInterceptor() {
        try {
            Class clazz = Class.forName("com.etransfar.module.mockApi.Mockinterceptor");
            return (Interceptor)clazz.newInstance();
        } catch (Exception e) {
            logger.info("com.etransfar.module.mockApi.Mockinterceptor not find, can not debug use mock.");
        }
        return null;
    }

    /**
     * 获取服务器时间<br/>
     * 在每一次RPC请求后从HTTP Header中取出服务器时间，来跟本地时间作一个校准<br/>
     * 如果你从启动开始算，你获取该时间的时候，RPC一次都没有成功响应过，那么将直接返回本地时间<br/>
     *
     * 校准规则:
     * 获取服务器时间后，计算服务器时间跟本地时间差，然后再使用本地时间差+本地时间，计算出服务器时间<br/>
     *
     * 异常状况（一般不会发生的）:
     * <ul>
     * <li>也就是说如果本地时间没有主动去调整，这个时间一般是不会出错的</li>
     * <li>但如果调整了本地时间，又发出过一次RPC，这个时间将会被再次校准</li>
     * <il>在请求多个服务器的时候，几个服务器的时间不一致，会导致这个时间会多次变化</il>
     * <il>服务器时间不准，那么就坑了</il>
     * <il>如果服务器没有遵守Http协议，那么有可能取不到这个Header的值</il>
     * </ul>
     *
     * @return
     */
    public static @NonNull
    synchronized Date getServerTime() {
        return headerInterceptor.getServerTime();
    }
}