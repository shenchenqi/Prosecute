package com.micro.wechat.folder;

import android.content.Context;

import com.micro.rom.RootRom;
import com.micro.wechat.Const;
import com.micro.wechat.folder.mvp.InitInter;
import com.micro.wechat.folder.mvp.InitPresenter;

/**
 * created by kilin on 20-3-17 下午5:40
 */
public class InitRoot extends RootRom<InitPresenter, InitInter> {

    private static final String TAG = "InitRoot";
    private static InitRoot INSTANCE;
    private static boolean isInit = false;

    public static InitRoot getInstance() {
        if (!isInit) {
            throw new RuntimeException("please invoke the init method first!");
        }
        return INSTANCE;
    }

    public static void instance(Context context) {
        if (isInit) {
            return;
        }
        isInit = true;
        INSTANCE = new InitRoot(context);
    }

    private InitRoot(Context context) {
        super(context);
    }

    @Override
    protected InitPresenter getPresenter() {
        return new InitPresenter(getContext(), Const.PACKAGE_NAME, Const.CREATE_DIR);
    }

    @Override
    protected void bindSetup(InitPresenter presenter) {

    }
}