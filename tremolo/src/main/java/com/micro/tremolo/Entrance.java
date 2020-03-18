package com.micro.tremolo;

import com.micro.hook.setup.Setup;
import com.micro.hook.config.HookParam;
import com.micro.tremolo.execute.PluginDeploy;
import com.micro.tremolo.mvp.EntranceInter;
import com.micro.tremolo.mvp.EntrancePresenter;
import com.micro.tremolo.version.TremoloParam;

import static com.micro.tremolo.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:23
 */
public class Entrance extends Setup<EntrancePresenter, EntranceInter> {

    private static final String TAG = "DY-Entrance";

    Entrance(HookParam hookParam) throws Throwable {
        super(hookParam);
    }

    @Override
    protected EntrancePresenter getPresenter() {
        return new EntrancePresenter();
    }

    @Override
    public void initParam(String version) {
        logger.i(TAG, "initParam", "参数初始化");
        TremoloParam.init(version);
    }

    @Override
    public void hide() {
        logger.i(TAG, "hide", "隐藏");
    }

    @Override
    public void executeSQL() {
        logger.i(TAG, "executeSQL", "隐藏");
    }

    @Override
    public void config() {
        logger.i(TAG, "config", "隐藏");
    }

    @Override
    public void execute() {
        PluginDeploy.executePlugin(getHookParam());
    }
}