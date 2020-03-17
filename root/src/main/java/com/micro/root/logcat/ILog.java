package com.micro.root.logcat;

/**
 * created by kilin on 20-3-17 上午10:19
 */
public interface ILog {

    void i(String packName, String className, String methodName, String msg);

    void i(String className, String methodName, String msg);

    void i(String methodName, String msg);

    void i(String msg);

    void d(String packName, String className, String methodName, String msg);

    void d(String className, String methodName, String msg);

    void d(String methodName, String msg);

    void d(String msg);

    void d(Throwable throwable, String packName, String className, String methodName, String msg);

    void d(Throwable throwable, String className, String methodName, String msg);

    void d(Throwable throwable, String methodName, String msg);

    void d(Throwable throwable, String msg);

    void e(String packName, String className, String methodName, String msg);

    void e(String className, String methodName, String msg);

    void e(String methodName, String msg);

    void e(String msg);

    void e(Throwable throwable, String packName, String className, String methodName, String msg);

    void e(Throwable throwable, String className, String methodName, String msg);

    void e(Throwable throwable, String methodName, String msg);

    void e(Throwable throwable, String msg);

    void v(String packName, String className, String methodName, String msg);

    void v(String className, String methodName, String msg);

    void v(String methodName, String msg);

    void v(String msg);

    void w(String packName, String className, String methodName, String msg);

    void w(String className, String methodName, String msg);

    void w(String methodName, String msg);

    void w(String msg);
}