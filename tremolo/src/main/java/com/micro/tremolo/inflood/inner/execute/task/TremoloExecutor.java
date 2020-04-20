package com.micro.tremolo.inflood.inner.execute.task;

import com.micro.tremolo.inflood.inner.execute.task.action.ProfileAndAwemeAction;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 16:20
 */
public class TremoloExecutor extends BaseTremoloExecutor {

    private ProfileAndAwemeAction profileAndAwemeAction;

    public TremoloExecutor(){
        super();
        profileAndAwemeAction = ProfileAndAwemeAction.getInstance(this);
    }

    @Override
    protected Runnable changeUI() {
        return () -> {

        };
    }

    public ProfileAndAwemeAction getProfileAndAwemeAction() {
        return profileAndAwemeAction;
    }
}