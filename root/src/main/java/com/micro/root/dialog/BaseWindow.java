package com.micro.root.dialog;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * created by kilin on 19-12-17 下午5:24
 */
public abstract class BaseWindow {

    public final Context context;
    private final WindowManager.LayoutParams params;
    private final WindowManager windowManager;
    private final View view;

    public BaseWindow(Context context) {
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.params = new WindowManager.LayoutParams();
        this.params.type = getType();
        this.params.flags = getFlags();
        this.params.format = getFormat();
        this.params.width = getWidth();
        this.params.height = getHeight();
        this.params.gravity = getGravity();
        this.view = getView();
        loadView(this.view);
    }

    protected int getType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
    }

    protected int getFlags() {
        return WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
    }

    protected int getFormat() {
        // 不设置这个弹出框的透明遮罩显示为黑色
        return PixelFormat.TRANSLUCENT;
    }

    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    protected int getHeight() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected View getView() {
        return LayoutInflater.from(context).inflate(getLayout(), null);
    }

    protected abstract int getLayout();

    protected abstract void loadView(View view);

    private boolean isShown;

    private boolean isShown() {
        return isShown;
    }

    private void setShown(boolean shown) {
        isShown = shown;
    }

    public void show() {
        if (isShown()) {
            return;
        }
        setShown(true);
        this.windowManager.addView(view, params);
    }

    public void hide() {
        if (isShown()) {
            this.windowManager.removeView(view);
            setShown(false);
        }
    }
}