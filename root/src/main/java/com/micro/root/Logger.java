package com.micro.root;

import android.util.Log;

import com.micro.root.logcat.ILog;

/**
 * created by kilin on 20-3-17 上午10:25
 */
public abstract class Logger implements ILog {
    private static int logLevel = Log.VERBOSE;
    protected final String tag;

    public static synchronized Report getLogger(String tab, String tag) {
        return new Report(tab, tag);
    }

    private Logger(String tab, String tag) {
        this.tag = String.format("{%s [%s]}", tab, tag);
    }

    private synchronized static String getMsg(String methodName, String msg) {
        return String.format("(%s): {%s}", methodName, msg);
    }

    private synchronized static String getMsg(String className, String methodName, String msg) {
        return String.format("[%s(%s)]: {%s}", className, methodName, msg);
    }

    private synchronized static String getMsg(String packName, String className, String methodName, String msg) {
        return String.format("{%s[%s(%s)]}: {%s}", packName, className, methodName, msg);
    }

    public static class Report extends Logger {

        private Report(String tab, String tag) {
            super(tab, tag);
        }

        @Override
        public void i(String packName, String className, String methodName, String msg) {
            this.I(Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void i(String className, String methodName, String msg) {
            this.I(Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void i(String methodName, String msg) {
            this.I(Logger.getMsg(methodName, msg));
        }

        @Override
        public void i(String msg) {
            this.I(msg);
        }

        private void I(Object object) {
            if (logLevel <= Log.INFO) {
                Log.i(tag, object.toString());
            }
        }

        @Override
        public void d(String packName, String className, String methodName, String msg) {
            this.D(null, Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void d(String className, String methodName, String msg) {
            this.D(null, Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void d(String methodName, String msg) {
            this.D(null, Logger.getMsg(methodName, msg));
        }

        @Override
        public void d(String msg) {
            this.D(null, msg);
        }

        @Override
        public void d(Throwable throwable, String packName, String className, String methodName, String msg) {
            this.D(throwable, Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void d(Throwable throwable, String className, String methodName, String msg) {
            this.D(throwable, Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void d(Throwable throwable, String methodName, String msg) {
            this.D(throwable, Logger.getMsg(methodName, msg));
        }

        @Override
        public void d(Throwable throwable, String msg) {
            this.D(throwable, msg);
        }

        private void D(Throwable throwable, Object object) {
            if (logLevel <= Log.DEBUG) {
                if (throwable == null) {
                    Log.d(tag, object.toString());
                } else {
                    Log.d(tag, object.toString(), throwable);
                }
            }
        }

        @Override
        public void e(String packName, String className, String methodName, String msg) {
            this.E(null, Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void e(String className, String methodName, String msg) {
            this.E(null, Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void e(String methodName, String msg) {
            this.E(null, Logger.getMsg(methodName, msg));
        }

        @Override
        public void e(String msg) {
            this.E(null, msg);
        }

        @Override
        public void e(Throwable throwable, String packName, String className, String methodName, String msg) {
            this.E(throwable, Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void e(Throwable throwable, String className, String methodName, String msg) {
            this.E(throwable, Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void e(Throwable throwable, String methodName, String msg) {
            this.E(throwable, Logger.getMsg(methodName, msg));
        }

        @Override
        public void e(Throwable throwable, String msg) {
            this.E(throwable, msg);
        }

        private void E(Throwable throwable, Object object) {
            if (logLevel <= Log.ERROR) {
                if (throwable == null) {
                    Log.e(tag, object.toString());
                } else {
                    Log.e(tag, object.toString(), throwable);
                }
            }
        }

        @Override
        public void v(String packName, String className, String methodName, String msg) {
            this.V(Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void v(String className, String methodName, String msg) {
            this.V(Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void v(String methodName, String msg) {
            this.V(Logger.getMsg(methodName, msg));
        }

        @Override
        public void v(String msg) {
            this.V(msg);
        }

        private void V(Object object) {
            if (logLevel <= Log.VERBOSE) {
                Log.v(tag, object.toString());
            }
        }

        @Override
        public void w(String packName, String className, String methodName, String msg) {
            this.W(Logger.getMsg(packName, className, methodName, msg));
        }

        @Override
        public void w(String className, String methodName, String msg) {
            this.W(Logger.getMsg(className, methodName, msg));
        }

        @Override
        public void w(String methodName, String msg) {
            this.W(Logger.getMsg(methodName, msg));
        }

        @Override
        public void w(String msg) {
            this.W(msg);
        }

        private void W(Object object) {
            if (logLevel <= Log.VERBOSE) {
                Log.w(tag, object.toString());
            }
        }
    }
}