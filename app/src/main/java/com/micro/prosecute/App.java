package com.micro.prosecute;

import com.micro.root.application.BaseApplication;
import com.micro.root.utils.InspectApply;
import com.micro.tremolo.Const;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:34
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        xposedApp();
        Const.initTremoloApp(this);
    }

    private void xposedApp() {
        if (!InspectApply.checkApkExist(this, "de.robv.android.xposed.installer")) {
            InspectApply.installAPK(this, InspectApply.apkPath(this, "xposed.apk"), "3.1.5");
        }
    }
}