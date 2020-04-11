package com.micro.tremolo.inflood.inner.execute.api;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.sqlite.from.Video;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 13:06
 */
public class VideoListApi extends BaseApi {

    private static VideoListApi mVideoListApi;

    public static void setInstance(Hook hook, Context context) {
        if (mVideoListApi == null) {
            mVideoListApi = new VideoListApi(hook, context);
        }
    }

    public static void loadApi(String userId, String secUserId, Callback callback) {
        mVideoListApi.loadVideoListApi(userId, secUserId, callback);
    }

    private VideoListApi(Hook hook, Context context) {
        super(hook, context);
    }

    private void loadVideoListApi(final String userId, final String secUserId, final Callback callback) {
        loadVideoListApi(true, userId, secUserId, 0, new BaseCallback() {
            @Override
            public void success(boolean isFirst, String data) {
                if (Lang.isEmpty(data)) {
                    taskLogger.e("抖音视频列表接口 返回数据 为空");
                    callback.finish();
                } else {
                    JSONObject dataObject = getJsonObject(data);
                    if (Lang.isNull(dataObject)) {
                        taskLogger.e("抖音视频列表接口 返回数据 解析出错");
                        callback.finish();
                    } else {
                        List<Video> videos = getVideos((List<Object>) dataObject.get("items"));
                        callback.videoList(videos);
                        if (dataObject.getBooleanValue("hasMore")) {
                            long maxCursor = dataObject.getLongValue("maxCursor");
                            loadVideoListApi(false, userId, secUserId, maxCursor, this);
                        } else {
                            callback.complete();
                        }
                    }
                }
            }

            @Override
            public void fail(Throwable e, String msg) {
                taskLogger.e(e, msg);
                callback.finish();
            }
        });
    }

    private void loadVideoListApi(final boolean isFirst, final String userId, final String secUserId, final long time, final BaseCallback callback) {
        post(second * 5, () -> startThread(() -> {
            try {
                Object object;
                if (isFirst) {
                    object = videoListApi(isFirst, userId, secUserId, 0, 20);
                } else {
                    object = videoListApi(isFirst, userId, secUserId, time, 10);
                }
                callback.success(isFirst, getJsonString(object));
            } catch (Throwable e) {
                callback.fail(e, "视频列表 报错: ");
            }
        }));
    }

    private Object videoListApi(boolean isFirst, String userId, String secUserId, long time, int limit) {
        taskLogger.d(String.format("视频列表接口,猜数[%s, %s, %s, %s, %s]", isFirst, userId, secUserId, time, limit));
        if (Lang.isEmpty(userId) || Lang.isEmpty(secUserId)) {
            return "";
        }
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_AWEME_API_LIST_METHOD, isFirst, userId, secUserId, 0, time, limit, null);
    }

    private synchronized List<Video> getVideos(List<Object> videoList) {
        List<Video> videos = new ArrayList<>();
        if (videoList != null && !videoList.isEmpty()) {
            for (Object video : videoList) {
                videos.add(new Video(getJsonString(video)));
            }
        }
        return videos;
    }

    public interface Callback {

        void videoList(List<Video> videos);

        void complete();

        void finish();
    }
}