package com.so1spms.module.rpc.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by qiaobo on 16/6/27.
 */
public class ToastCompat {

    private static WeakReference<Toast> weakReference;

    public static void show(Context context, final CharSequence text, final int duration) {
        if(context == null) {
            return ;
        }
        final Toast toast;
        if (weakReference == null || weakReference.get() == null) {
            toast = Toast.makeText(context, text, duration);
            weakReference = new WeakReference<Toast>(toast);
        } else {
            toast = weakReference.get();
        }

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

    public static void show(Context context, final int stringResId, final int duration) {
        if(context == null) {
            return ;
        }
        final Toast toast;
        if (weakReference == null || weakReference.get() == null) {
            toast = Toast.makeText(context, stringResId, duration);
            weakReference = new WeakReference<Toast>(toast);
        } else {
            toast = weakReference.get();
        }

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                toast.setText(stringResId);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

}
