package com.micro.root.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * @Description: BaseToast
 * @Author: ALin
 * @CreateDate: 19-9-12
 */
public abstract class BaseToast<M> extends Toast {

    public static void showLong(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String msg) {
        makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public BaseToast(Context context, M data) {
        super(context);
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setGravity(getToastGravity(), getToastX(), getToastY());
            setDuration(getToastDuration());
            View view = LayoutInflater.from(context).inflate(layoutId, null);
            initView(view);
            initData(data);
            setView(view);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData(M data);

    protected int getToastGravity() {
        return Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    }

    protected int getToastX(){
        return 0;
    }

    protected int getToastY() {
        return 0;
    }

    protected int getToastDuration() {
        return LENGTH_SHORT;
    }

    public void showToast() {
        if (getLayoutId() != 0) {
            show();
        }
    }
}