package com.micro.foreign;

import android.os.Bundle;

import java.lang.reflect.Member;

/**
 * created by kilin on 20-3-9 上午11:28
 */
public interface ForeignHookParam {

    Object getThisObject();

    Object[] getArgs();

    Member getMethod();

    Object getResult();

    void setResult(Object result);

    Throwable getThrowable();

    void setThrowable(Throwable throwable);

    boolean hasThrowable();

    Object getResultOrThrowable() throws Throwable;

    Bundle getExtra();

    Object getObjectExtra(String key);

    void setObjectExtra(String key, Object value);
}