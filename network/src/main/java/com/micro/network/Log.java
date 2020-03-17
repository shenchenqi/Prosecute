package com.micro.network;

import com.micro.root.Logger;

/**
 * created by kilin on 20-3-17 上午10:47
 */
public class Log {
    private static final Logger logger = Logger.getLogger("Network-Log");

    public static void i(String packName, String className, String methodName, String msg) {
        logger.i(packName, className, methodName, msg);
    }

    public static void i(String className, String methodName, String msg) {
        logger.i(className, methodName, msg);
    }

    public static void i(String methodName, String msg) {
        logger.i(methodName, msg);
    }

    public static void i(String msg) {
        logger.i(msg);
    }

    public static void d(String packName, String className, String methodName, String msg) {
        logger.d(packName, className, methodName, msg);
    }

    public static void d(String className, String methodName, String msg) {
        logger.d(className, methodName, msg);
    }

    public static void d(String methodName, String msg) {
        logger.d(methodName, msg);
    }

    public static void d(String msg) {
        logger.d(msg);
    }

    public static void d(Throwable throwable, String packName, String className, String methodName, String msg) {
        logger.d(throwable, packName, className, methodName, msg);
    }

    public static void d(Throwable throwable, String className, String methodName, String msg) {
        logger.d(throwable, className, methodName, msg);
    }

    public static void d(Throwable throwable, String methodName, String msg) {
        logger.d(throwable, methodName, msg);
    }

    public static void d(Throwable throwable, String msg) {
        logger.d(throwable, msg);
    }
}