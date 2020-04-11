package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;

import androidx.core.util.Pair;

import com.micro.hook.ControlLayout;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.controlLogger;

/**
 * @Author KiLin
 * @Time 2020/4/9 9:33
 */
public class MainActivityControl extends ControlLayout {

    public static MainActivityControl getInstance(Context context, Hook hook) {
        return new MainActivityControl(context, hook);
    }

    private final Handler handler;
    private final int[] screenData;
    private final Hook hook;

    private MainActivityControl(Context context, Hook hook) {
        this.screenData = phoneScreen(context);
        this.hook = hook;
        this.handler = new Handler(context.getMainLooper());
    }

    private Object mainActivity;

    public void setMainActivity(Object mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void simulateChangeVideo() {
        float x = screenData[0] / 2;
        float y = screenData[1] / 24;
        controlLogger.d(String.format("滑动 加载更多视频 准备 width [%s], height [%s], 移动 [%s]", screenData[0], screenData[1], y));
        List<Pair<Float, Float>> pairList = new ArrayList<>();
        pairList.add(new Pair<>(x, y * 20));
        pairList.add(new Pair<>(x, y * 19));
        pairList.add(new Pair<>(x, y * 18));
        pairList.add(new Pair<>(x, y * 17));
        pairList.add(new Pair<>(x, y * 16));
        pairList.add(new Pair<>(x, y * 15));
        pairList.add(new Pair<>(x, y * 14));
        pairList.add(new Pair<>(x, y * 13));
        pairList.add(new Pair<>(x, y * 12));
        pairList.add(new Pair<>(x, y * 10));
        pairList.add(new Pair<>(x, y * 4));
        callTouchEvent(getMotionEvents(pairList));
    }

    public void simulateRefreshVideo() {
        float x = screenData[0] / 2;
        float y = screenData[1] / 24;
        controlLogger.d(String.format("滑动 加载更多视频 准备 width [%s], height [%s], 移动 [%s]", screenData[0], screenData[1], y));
        List<Pair<Float, Float>> pairList = new ArrayList<>();
        pairList.add(new Pair<>(x, y * 4));
        pairList.add(new Pair<>(x, y * 6));
        pairList.add(new Pair<>(x, y * 7));
        pairList.add(new Pair<>(x, y * 8));
        pairList.add(new Pair<>(x, y * 9));
        pairList.add(new Pair<>(x, y * 10));
        pairList.add(new Pair<>(x, y * 15));
        pairList.add(new Pair<>(x, y * 16));
        pairList.add(new Pair<>(x, y * 17));
        pairList.add(new Pair<>(x, y * 18));
        pairList.add(new Pair<>(x, y * 20));
        callTouchEvent(getMotionEvents(pairList));
    }

    private void callTouchEvent(final List<MotionEvent> events) {
        if (mainActivity == null) {
            controlLogger.e("Main Activity is null");
            return;
        }
        handlerPost(handler, () -> {
            for (MotionEvent event : events) {
                callDispatchTouchEvent(event);
            }
        }, BaseInterface.second * 5);
    }

    private void callDispatchTouchEvent(MotionEvent event) {
        hook.callMethod(mainActivity, TremoloParam.AWEME_MAIN_ACTIVITY_TOUCH_EVENT_METHOD, event);
    }
}