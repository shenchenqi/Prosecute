package com.micro.tremolo.folder;

import android.content.Context;

import com.micro.tremolo.Const;
import com.micro.tremolo.folder.mvp.InitInter;
import com.micro.tremolo.folder.mvp.InitPresenter;
import com.micro.rom.RootRom;

import static com.micro.tremolo.Const.rootLogger;

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
        rootLogger.i(TAG, "初始化");
    }

    @Override
    protected InitPresenter getPresenter() {
        return new InitPresenter(getContext(), Const.PACKAGE_NAME, Const.CREATE_DIR);
    }

    @Override
    protected void bindSetup(InitPresenter presenter) {
        rootLogger.i(TAG, "循环扫描");
    }
}