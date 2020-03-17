package com.micro.foreign;

import java.lang.reflect.Member;
import java.util.Set;

/**
 * created by kilin on 20-3-9 下午1:00
 */
public interface IBridgeChange {
    ForeignUnHook hookMethod(Member hookMethod, ForeignHook callback);

    void unhookMethod(Member hookMethod, ForeignHook callback);

    Set<ForeignUnHook> hookAllMethods(Class<?> hookClass, String methodName, ForeignHook callback);

    Set<ForeignUnHook> hookAllConstructors(Class<?> hookClass, ForeignHook callback);
}