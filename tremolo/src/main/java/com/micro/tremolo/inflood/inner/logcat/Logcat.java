package com.micro.tremolo.inflood.inner.logcat;

import com.micro.hook.config.Hook;
import com.micro.root.Logger;

/**
 * @Author Kilin
 * @Date 2020/3/25 19:08
 */
public class Logcat {
    private static final Logger logger = Logger.getLogger("DY-Log");

    private int status;
    private int type;
    private String tag;
    private String content;
    private long threadId;
    private boolean isLooperMain;
    private Object object;

    public Logcat(Hook hook, Object msg) {
        this.status = getStatus(hook, msg);
        this.type = getType(hook, msg);
        this.tag = getTag(hook, msg);
        this.content = getContent(hook, msg);
        this.threadId = getThreadId(hook, msg);
        this.isLooperMain = isLooperMain(hook, msg);
        this.object = getMsg(hook, msg);
        logger.i(String.format("tag[%s], status[%s], type[%s], threadId[%s], isLooperMain[%s] \n content[%s] \n msg[%s]", tag, status, type, threadId, isLooperMain, content, object == null ? "" : object.toString()));
    }

    private int getStatus(Hook hook, Object msg) {
        return hook.getIntegerField(msg, "a");
    }

    private int getType(Hook hook, Object msg) {
        return hook.getIntegerField(msg, "b");
    }

    private String getTag(Hook hook, Object msg) {
        return (String) hook.getField(msg, "c");
    }

    private String getContent(Hook hook, Object msg) {
        return (String) hook.getField(msg, "d");
    }

    private long getThreadId(Hook hook, Object msg) {
        return hook.getLongField(msg, "e");
    }

    private boolean isLooperMain(Hook hook, Object msg) {
        return hook.getBooleanField(msg, "f");
    }

    private Object getMsg(Hook hook, Object msg) {
        return hook.getField(msg, "h");
    }
}