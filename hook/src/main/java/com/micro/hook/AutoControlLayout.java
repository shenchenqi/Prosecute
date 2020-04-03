package com.micro.hook;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AutoControlLayout {

    private HandlerThread handlerThread;

    private Looper loadLooper() {
        if (handlerThread == null) {
            handlerThread = new HandlerThread("auto_control_handler");
            handlerThread.start();
        }
        return handlerThread.getLooper();
    }

    protected void startThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    private Handler handler;

    protected Handler getHandler(Looper looper) {
        if (handler == null) {
            handler = new Handler(looper == null ? loadLooper() : looper);
        }
        return handler;
    }

    protected synchronized void handlerPost(Handler handler, Runnable runnable) {
        handler.post(runnable);
    }

    protected synchronized void handlerPost(Handler handler, Runnable runnable, long time) {
        handler.postDelayed(runnable, time);
    }

    protected int[] phoneScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }

    protected void sendPointerSync(List<MotionEvent> events) {
        Instrumentation instrumentation = getInstrumentation();
        for (MotionEvent event : events) {
            instrumentation.sendPointerSync(event);
        }
    }

    protected List<MotionEvent> getMotionEvents(List<Pair<Float, Float>> coordinates) {
        List<MotionEvent> events = new ArrayList<>();
        int size = coordinates.size() - 1;
        for (Pair<Float, Float> coordinate : coordinates) {
            int index = coordinates.indexOf(coordinate);
            if (index == 0) {
                events.add(getObtain(0, MotionEvent.ACTION_DOWN, coordinate.first, coordinate.second));
            }
            int time = (1000 / size) * index;
            events.add(getObtain(time, MotionEvent.ACTION_MOVE, coordinate.first, coordinate.second));
            if (index == size) {
                events.add(getObtain(1000, MotionEvent.ACTION_UP, coordinate.first, coordinate.second));
            }
        }
        return events;
    }

    private Instrumentation getInstrumentation() {
        return new Instrumentation();
    }

    protected synchronized MotionEvent getObtain(int eventTime, int action, float x, float y) {
        return MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + eventTime, action, x, y, 0);
    }

    protected synchronized <T extends View> T bindView(View rootView, int resId) {
        return rootView.findViewById(resId);
    }

    protected boolean isClickText(View rootView, String text) {
        if (null == rootView) {
            return false;
        }
        List<View> views = getAllChildViews(rootView);
        for (View view : views) {
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (text.equals(((TextView) view).getText().toString())) {
                    tv.performClick();
                    return true;
                }
            } else if (view instanceof Button) {
                Button tv = (Button) view;
                if (text.equals(((Button) view).getText().toString())) {
                    tv.performClick();
                    return true;
                }
            }/* else if (text.equals(view.getContentDescription().toString())) {
                view.performClick();
                return true;
            }*/
        }
        return false;
    }

    protected String getText(View rootView, String text) throws Throwable {
        if (null == rootView) {
            throw new Throwable("root view is null");
        }

        List<View> views = getAllChildViews(rootView);
        for (View view : views) {
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                return tv.getText().toString();
            } else if (view instanceof Button) {
                Button tv = (Button) view;
                return tv.getText().toString();
            }
        }
        throw new Throwable("root view not string");
    }

    protected View getAimsView(View rootView, String id) throws Throwable {
        if (null == rootView) {
            throw new Throwable("root view is null");
        }
        List<View> views = getAllChildViews(rootView);
        for (View view : views) {
            if (view instanceof LinearLayout) {
                String viewId = String.valueOf(view.getId());
                if (id.equals(viewId)) {
                    return view;
                }
            }
        }
        throw new Throwable("root view select is null");
    }

    protected boolean enterText(View rootView, String id, String text) {
        if (null == rootView) {
            return false;
        }
        List<View> views = getAllChildViews(rootView);
        for (View view : views) {
            try {
                String viewId = view.getResources().getResourceName(view.getId());
                if (id.equals(viewId)) {
                    if (view instanceof EditText) {
                        EditText ed = (EditText) view;
                        ed.setText(text);
                        return true;
                    }
                }
            } catch (Exception e) {

            }
        }
        return false;
    }

    protected List<View> getAllChildViews(View view) {
        List<View> allChildren = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewChild = vp.getChildAt(i);
                allChildren.add(viewChild);
                allChildren.addAll(getAllChildViews(viewChild));
            }
        }
        return allChildren;
    }

    public interface Callback {
        void success(String value);

        void fail(String msg);

        long sleep();
    }
}