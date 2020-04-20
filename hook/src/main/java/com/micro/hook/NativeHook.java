package com.micro.hook;

import com.micro.HookConst;
import com.micro.hook.config.HookParam;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * created by kilin on 20-3-17 下午2:41
 */
public class NativeHook implements IXposedHookLoadPackage, IXposedHookInitPackageResources, IXposedHookZygoteInit {

    static {
        try {
            ExecuteMonitor.setHookRegister("TremoloModule", Class.forName("com.micro.tremolo.inflood.TremoloModule"));
            ExecuteMonitor.setHookRegister("WorkerModule", Class.forName("com.micro.worker.inflood.WorkerModule"));
            ExecuteMonitor.setHookRegister("WeChatModule", Class.forName("com.micro.wechat.inflood.WeChatModule"));
        } catch (ClassNotFoundException e) {
            HookConst.hookLog.e(e, "hook 注册不成功");
        }
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        ExecuteMonitor.loadRegisterHook(new HookParam(loadPackageParam.isFirstApplication, null, loadPackageParam.classLoader, loadPackageParam.packageName));
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam initPackageResourcesParam) throws Throwable {

    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {

    }
}