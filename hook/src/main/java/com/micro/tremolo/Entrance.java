package com.micro.tremolo;

import com.micro.hook.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.version.TremoloParam;

import static com.micro.tremolo.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:23
 */
public class Entrance extends Setup {

    private static final String TAG = "DY-Entrance";

    public Entrance(HookParam hookParam) {
        super(hookParam);
    }

    @Override
    protected void initParam(String version) {
        logger.i(TAG, "initParam", "参数初始化");
        TremoloParam.init(version);
    }

    @Override
    protected void hide() {
        logger.i(TAG, "hide", "隐藏");
    }

    @Override
    protected void executeSQL() {
        logger.i(TAG, "executeSQL", "隐藏");
    }

    @Override
    protected void config() {
        logger.i(TAG, "config", "隐藏");
    }

    @Override
    protected void execute() {
        logger.i(TAG, "execute", "隐藏");
    }
}