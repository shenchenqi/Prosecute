package com.micro.tremolo.execute;

import com.micro.hook.config.Hook;
import com.micro.hook.config.HookParam;

import java.util.HashMap;
import java.util.Map;

import static com.micro.tremolo.TremoloModule.logger;

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
        for (Class clazz : registerPluginMap.values()) {
            Hook.method(clazz, "start", hookParam.getHook());
        }
    }
}