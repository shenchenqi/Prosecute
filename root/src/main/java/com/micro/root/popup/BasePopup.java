package com.micro.root.popup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * @Description: BasePopup
 * @Author: ALin
 * @CreateDate: 19-9-12
 */
public abstract class BasePopup implements PopupWindow.OnDismissListener {

    protected final Context context;
    protected final View parent;
    protected final int[] location;
    protected PopupWindow popupWindow;

    public BasePopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        this.location = new int[2];
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            View contentView = LayoutInflater.from(context).inflate(layoutId, null, false);
            popupWindow = new PopupWindow(contentView, getWidth(), getHeight());
            popupWindow.setBackgroundDrawable(getBackgroundId());
            popupWindow.setTouchable(getTouchable());
            popupWindow.setOutsideTouchable(getOutsideTouchable());
            popupWindow.setFocusable(getFocusable());
            popupWindow.setOnDismissListener(this);
            popupWindow.setAnimationStyle(getAnimationStyle());
            initView(contentView);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected int getWidth() {
        return LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    protected int getHeight() {
        return LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    protected abstract Drawable getBackgroundId();

    protected boolean getTouchable() {
        return true;
    }

    protected boolean getOutsideTouchable() {
        return true;
    }

    protected boolean getFocusable() {
        return true;
    }

    protected abstract int getAnimationStyle();

    protected abstract int getGravity();

    public void showPopupLocation() {
        if (popupWindow != null) {
            if (!popupWindow.isShowing()) {
                parent.getLocationOnScreen(location);
                popupWindow.showAsDropDown(parent);
            } else {
                onDismiss();
            }
        }
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    private float getSystemVersion() {
        float sdkVersion;
        try {
            sdkVersion = Float.valueOf(android.os.Build.VERSION.RELEASE);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }

    @Override
    public void onDismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}