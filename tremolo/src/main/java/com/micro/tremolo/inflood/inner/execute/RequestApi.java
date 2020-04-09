package com.micro.tremolo.inflood.inner.execute;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.OverseeInter;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/7 10:57
 */
public class RequestApi {

    private final Hook hook;
    private final Handler handler;

    public RequestApi(Hook hook, Context context) {
        this.hook = hook;
        this.handler = new Handler(context.getMainLooper());
    }

    private Object searchUserApi(String search, long size, int limit, int count, String requestId) {
        monitorLogger.d("search >>> " + search);
        if (Lang.isEmpty(search)) {
            return "";
        }
        return hook.callStaticMethod("com.ss.android.ugc.aweme.discover.api.SearchApi", "a", search, size, limit, count, 0, "", requestId, 1);
    }

    public void requestSearchUserApi(final String search, final long size, final String requestId, final OverseeInter overseeInter) {
        handler.postDelayed(() -> {
            if (Lang.isEmpty(requestId)) {
                requestSearchUserApi(search, 0, 10, 0, "", overseeInter);
            } else {
                requestSearchUserApi(search, size, 10, 1, requestId, overseeInter);
            }
        }, overseeInter.second * 5);
    }

    private void requestSearchUserApi(final String search, final long size, final int limit, final int count, final String requestId, final OverseeInter overseeInter) {
        new Thread(() -> {
            Object object = searchUserApi(search, size, limit, count, requestId);
            monitorLogger.d(JSON.toJSONString(object));
        }).start();
    }

    private Object profileApi(String secUserId) {
        monitorLogger.d("secUserId >>> " + secUserId);
        if (Lang.isEmpty(secUserId)) {
            return "";
        }
        String url = "https://aweme.snssdk.com/aweme/v1/user/profile/other/?sec_user_id=" + secUserId + "&address_book_access=1&from=0";
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_PROFILE_API_USER_METHOD, url, false, null);
    }

    public void requestProfileApi(final String secUserId, final OverseeInter overseeInter) {
        handler.postDelayed(() -> {
            requestProfileApi(null, secUserId, overseeInter);
        }, overseeInter.second * 5);
    }

    //调用该接口,视频不能往上切换,只能进行随机刷新
    private void requestProfileApi(String userId, final String secUserId, final OverseeInter overseeInter) {
        new Thread(() -> {
            Object object = profileApi(secUserId);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
            overseeInter.profileInfo(new Author(jsonObject.getString("user")));
        }).start();
    }

    private Object feedVideoApi(boolean isFirst, String userId, String secUserId, long time, int limit) {
        monitorLogger.d("userId >>> " + userId + ", secUserId >>> " + secUserId + ", time >>> " + time);
        if (Lang.isEmpty(userId) || Lang.isEmpty(secUserId)) {
            return "";
        }
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_LIST_METHOD, isFirst, userId, secUserId, 0, time, limit, null);
    }

    public void requestFeedVideoApi(final boolean isFirst, final String userId, final String secUserId, final long time, final OverseeInter overseeInter) {
        handler.postDelayed(() -> {
            if (isFirst) {
                requestFeedVideoApi(isFirst, userId, secUserId, 0, 20, overseeInter);
            } else {
                requestFeedVideoApi(isFirst, userId, secUserId, time, 30, overseeInter);
            }
        }, overseeInter.second * 5);
    }

    private void requestFeedVideoApi(final boolean isFirst, final String userId, final String secUserId, final long time, final int limit, final OverseeInter overseeInter) {
        new Thread(() -> {
            Object object = feedVideoApi(isFirst, userId, secUserId, time, limit);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
            List<Object> videoList = JSON.parseArray(jsonObject.getString("items"), Object.class);
            List<Video> videos = new ArrayList<>();
            if (videoList != null && !videoList.isEmpty()) {
                for (Object video : videoList) {
                    videos.add(new Video(JSON.toJSONString(video)));
                }
            }
            overseeInter.videoListInfo(videos);
        }).start();
    }
}