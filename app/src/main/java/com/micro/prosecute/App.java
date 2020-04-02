package com.micro.prosecute;

import com.micro.root.application.BaseApplication;
import com.micro.tremolo.Const;

/**
 * @Author Kilin
 * @Date 2020/3/23 14:34
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Const.setContext(this);
    }
}