package com.micro.hook.control;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.micro.hook.config.Hook;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 12:20
 */
public abstract class BaseControl extends ControlLayout {

    private final Context context;
    private final Handler handler;
    private final int width;
    private final int height;
    private final Hook hook;

    protected BaseControl(Context context, Hook hook) {
        this.context = context;
        this.handler = getHandler(context.getMainLooper());
        int[] screenData = phoneScreen(context);
        this.width = screenData[0];
        this.height = screenData[1];
        this.hook = hook;
    }

    protected Context getContext() {
        return context;
    }

    protected Handler getHandler() {
        return handler;
    }

    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    protected Hook getHook() {
        return hook;
    }

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    protected View getView() {
        return view;
    }

    private Object controlFile;

    public void setControlFile(Object controlFile) {
        this.controlFile = controlFile;
    }

    protected Object getControlFile() {
        return controlFile;
    }

    protected Class getControlClass() {
        return controlFile.getClass();
    }
}