package com.micro.wechat.inflood.inner.execute.task.action;

import com.micro.hook.config.Hook;
import com.micro.root.task.BaseRunnable;
import com.micro.wechat.inflood.inner.execute.task.BaseWeChatExecutor;

import static com.micro.wechat.Const.taskLogger;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 15:13
 */
public class SubscribeAction extends BaseRunnable {

    private final Hook hook;
    private final BaseWeChatExecutor executor;

    public SubscribeAction(Hook hook, BaseWeChatExecutor executor) {
        super();
        this.hook = hook;
        this.executor = executor;
    }

    public void register() {
        executor.addTask(this);
    }

    @Override
    protected String taskName() {
        return "SubscribeAction";
    }

    @Override
    protected boolean isCycleConfig() {
        return false;
    }

    @Override
    protected boolean isSingleConfig() {
        return false;
    }

    @Override
    protected void process() {
    }

    @Override
    protected void timeout() {

    }

    @Override
    protected void error(Throwable e) {
        taskLogger.e(e, "公众号");
    }

    @Override
    protected void finish() {
        executor.removeTask(this);
    }
}