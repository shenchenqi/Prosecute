package com.micro.hook;

import com.micro.Const;
import com.micro.hook.config.HookParam;
import com.micro.root.utils.Lang;

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
            if (Lang.isEquals(1, Const.moduleStatus)) {
                ExecuteMonitor.setHookRegister("TremoloModule", Class.forName("com.micro.tremolo.inflood.TremoloModule"));
            } else if (Lang.isEquals(2, Const.moduleStatus)) {
                ExecuteMonitor.setHookRegister("WorkerModule", Class.forName("com.micro.worker.inflood.WorkerModule"));
            } else {
                ExecuteMonitor.setHookRegister("TremoloModule", Class.forName("com.micro.tremolo.inflood.TremoloModule"));
                ExecuteMonitor.setHookRegister("WorkerModule", Class.forName("com.micro.worker.inflood.WorkerModule"));
            }
        } catch (ClassNotFoundException e) {
            Const.hookLog.e(e, "hook 注册不成功");
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