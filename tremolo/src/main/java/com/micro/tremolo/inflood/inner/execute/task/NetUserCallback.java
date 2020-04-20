package com.micro.tremolo.inflood.inner.execute.task;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 15:59
 */
public interface NetUserCallback {
    void profileExist(String userId, String sceUserId, boolean isExist);
}