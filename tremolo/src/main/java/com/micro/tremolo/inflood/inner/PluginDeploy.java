package com.micro.tremolo.inflood.inner;

import com.micro.hook.config.Hook;
import com.micro.hook.config.HookParam;

import java.util.HashMap;
import java.util.Map;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * created by kilin on 20-3-18 上午10:10
 */
public class PluginDeploy {
    private static final Map<String, Class> registerPluginMap = new HashMap<>();

    public static Map<String, Class> getRegisterPluginMap() {
        return registerPluginMap;
    }

    public static void registerPlugin(String key, Class clazz) {
        logger.d("注册项目插件");
        if (registerPluginMap.containsKey(key)) {
            registerPluginMap.remove(key);
            registerPluginMap.put(key, clazz);
        } else {
            registerPluginMap.put(key, clazz);
        }
    }

    public static void executePlugin(HookParam hookParam) {
        if (hookParam == null) {
            logger.e("当前参数值为空");
            return;
        }
        if (hookParam.getHook() == null) {
            logger.e("当前参数Hook值为空");
            return;
        }
        logger.d("执行插件数 :" + registerPluginMap.size());
        for (Class clazz : registerPluginMap.values()) {
            Hook.method(clazz, "start", hookParam.getHook(), hookParam.getContext());
        }
    }

    private static final Map<String, Object> executePluginMap = new HashMap<>();

    public static void setExecutePluginMap(String key, Object object) {
        if (!executePluginMap.containsKey(key)) {
            executePluginMap.put(key, object);
        }
    }

    public static boolean isExist(String key) {
        return executePluginMap.containsKey(key);
    }

    public static Object getExecutePlugin(String key) {
        return executePluginMap.get(key);
    }
}