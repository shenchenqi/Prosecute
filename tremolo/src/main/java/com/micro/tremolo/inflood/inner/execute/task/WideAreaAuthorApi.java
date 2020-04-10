package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.Logger;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.TremoloApi;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.Oversee;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserIdModelTable;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author KiLin
 * @Time 2020/4/10 13:10
 * 根据视频作者的ID，secID进行接口访问拿取数据
 */
public class WideAreaAuthorApi {

    private final static Logger logger = Logger.getLogger("tremoloLog", "WideAreaLog");

    public final static Map<String, String> userMap = new HashMap<>();

    private static WideAreaAuthorApi mWideAreaAuthorApi;

    public static WideAreaAuthorApi getInstance(Hook hook, Context context) {
        if (mWideAreaAuthorApi == null) {
            mWideAreaAuthorApi = new WideAreaAuthorApi(hook, context);
        }
        return mWideAreaAuthorApi;
    }

    private static Oversee mOversee;

    public static void setOversee(Oversee mOversee) {
        WideAreaAuthorApi.mOversee = mOversee;
    }

    public static void canTremoloData(Context context) {
        if (!Const.isWideArea) {
            logger.i("当前不是广域采集模式");
            return;
        }
        Handler handler = new Handler(context.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                logger.i("检测扫描");
                WideAreaAuthorApi.requestData();
                handler.postDelayed(this::run, BaseInterface.second * 20);
            }
        }, BaseInterface.second * 20);
    }

    private static void requestData() {
        requestData(new Callback() {
            @Override
            public void over(String authorID) {
                userMap.remove(authorID);
                logger.d("请求完成");
                isRun = false;
                if (mOversee != null) {
                    logger.d("请求完成, 视频切换");
                    mOversee.nextVideo();
                }
            }

            @Override
            public void fail(String authorID, int type, String msg) {
                userMap.remove(authorID);
                logger.e(String.format("出错[%s][%s][%s]", authorID, type, msg));
                isRun = false;
                if (mOversee != null) {
                    logger.d("请求出错, 视频切换");
                    mOversee.nextVideo();
                }
            }
        });
    }

    private static boolean isRun = false;
    private static int number;

    private static void requestData(Callback callback) {
        if (isRun) {
            logger.i("正在运行");
            number++;
            if (number == 6) {
                if (mOversee != null) {
                    logger.d("运行太久, 先来个视频切换");
                    mOversee.nextVideo();
                }
            }
            return;
        }
        final WideCallback wideCallback = new WideCallback() {
            @Override
            public void userSuccess(String userId, String sceUserId) {
                logger.i(userId + " 用户请求 成功 ");
                setVideoListApi(userId, sceUserId, true, 0, this);
            }

            @Override
            public void userFail(String userId, String msg) {
                logger.i(userId + " 用户请求 报错 " + msg);
                callback.fail(userId, 1, msg);
            }

            @Override
            public void videosSuccess(boolean hasMore, String userId, String secUserId, Video video) {
                logger.i(userId + " 用户视频列表请求 成功 " + hasMore);
                if (!hasMore) {
                    callback.over(userId);
                } else if (Lang.isNotNull(video)) {
                    setVideoListApi(userId, secUserId, false, Long.parseLong(video.getCreateTime() + "000"), this);
                } else {
                    callback.fail(userId, 3, "当前拉去的视频列表未空");
                }
            }

            @Override
            public void videosFail(String userId, String msg) {
                logger.i(userId + " 用户视频列表请求 报错 " + msg);
                callback.fail(userId, 2, msg);
            }
        };
        Map<String, String> map = new HashMap<>(userMap);
        if (map.isEmpty()) {
            logger.i(" 无用户数据 ");
            callback.fail("userId", 4, "无数据");
            return;
        }
        isRun = true;
        number = 0;
        logger.i("开始进行数据采集");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String authorID = entry.getKey();
            String secAuthorID = entry.getValue();
            if (Lang.isNotEmpty(authorID) && Lang.isNotEmpty(secAuthorID)) {
                logger.i(String.format("作者[%s]开始请求", authorID));
                UserIdModelTable userIdModelTable = new UserIdModelTable();
                userIdModelTable.setUserId(authorID);
                userIdModelTable.setSceUserId(secAuthorID);
                UploadNet.isUserExist(userIdModelTable, (userId, sceUserId, isExist) -> {
                    logger.i(String.format("作者[%s]是否已存在服务器[%s]", authorID, isExist));
                    if (isExist) {
                        setVideoListApi(userId, sceUserId, true, 0, wideCallback);
                    } else {
                        setUserApi(userId, sceUserId, wideCallback);
                    }
                });
                return;
            }
        }
    }

    private static void setUserApi(final String authorID, String secAuthorID, final WideCallback callback) {
        mWideAreaAuthorApi.requestUserApi(authorID, secAuthorID, new UserCallback() {
            @Override
            public Context getIContext() {
                return null;
            }

            @Override
            public void loadUserInfo(Author author) {
                UploadNet.uploadUser(loadUserTable(author));
                callback.userSuccess(author.getUserId(), author.getSceUserId());
            }

            @Override
            public void fail(String msg) {
                callback.userFail(authorID, msg);
            }
        });
    }

    private static void setVideoListApi(final String authorID, final String secAuthorID, boolean isFirst, long time, final WideCallback callback) {
        mWideAreaAuthorApi.requestVideoListApi(isFirst, authorID, secAuthorID, time, new VideosCallback() {
            @Override
            public Context getIContext() {
                return null;
            }

            @Override
            public void loadVideoList(boolean hasMore, String userId, String secUserId, List<Video> videos) {
                List<VideoModelTable> videoTableList = new ArrayList<>();
                for (Video video : videos) {
                    videoTableList.add(loadVideoTable(video));
                }
                if (!videoTableList.isEmpty()) {
                    VideoListModelTable videoListModelTable = new VideoListModelTable();
                    videoListModelTable.setVideoModelTableList(videoTableList);
                    UploadNet.uploadVideoList(videoListModelTable);
                }
                callback.videosSuccess(hasMore, userId, secUserId, videos.isEmpty() ? null : videos.get(videos.size() - 1));
            }

            @Override
            public void fail(String msg) {
                callback.videosFail(authorID, msg);
            }
        });
    }

    private static UserModelTable loadUserTable(Author author) {
        UserModelTable userTable = new UserModelTable();
        userTable.setUserId(author.getUserId());
        userTable.setSceUserId(author.getSceUserId());
        userTable.setNickname(author.getNickname());
        userTable.setTremoloId(author.getTremoloId());
        userTable.setTremoloNumberId(author.getTremoloNumberId());
        userTable.setBirthday(author.getBirthday());
        userTable.setCity(author.getCity());
        userTable.setCountry(author.getCountry());
        userTable.setDistrict(author.getDistrict());
        userTable.setSchoolName(author.getSchoolName());
        userTable.setSignature(author.getSignature());
        userTable.setCustomVerify(author.getCustomVerify());
        userTable.setEnterpriseVerify(author.getEnterpriseVerify());
        userTable.setRequestId(author.getRequestId());
        userTable.setFollowingCount(String.valueOf(author.getFollowingCount()));
        userTable.setAwemeCount(String.valueOf(author.getAwemeCount()));
        userTable.setMovingCount(String.valueOf(author.getMovingCount()));
        userTable.setFansCount(String.valueOf(author.getFansCount()));
        userTable.setFavoritingCount(String.valueOf(author.getFavoritingCount()));
        userTable.setAvatarList(author.getAvatarList());
        userTable.setUri(author.getUri());
        userTable.setUrlKey(author.getUrlKey());
        userTable.setAvatarMediumList(author.getAvatarMediumList());
        userTable.setAvatarThumbList(author.getAvatarThumbList());
        return userTable;
    }

    private static VideoModelTable loadVideoTable(Video video) {
        VideoModelTable videoTable = new VideoModelTable();
        videoTable.setId(video.getId());
        videoTable.setTitle(video.getTitle());
        videoTable.setCreateTime(String.valueOf(video.getCreateTime()));
        videoTable.setShareUrl(video.getShareUrl());
        videoTable.setCommentCount(String.valueOf(video.getCommentCount()));
        videoTable.setDiggCount(String.valueOf(video.getDiggCount()));
        videoTable.setDownloadCount(String.valueOf(video.getDownloadCount()));
        videoTable.setShareCount(String.valueOf(video.getShareCount()));
        videoTable.setUrlList(video.getUrlList());
        videoTable.setUserId(video.getUserId());
        videoTable.setNickname(video.getNickname());
        return videoTable;
    }

    private final Hook hook;
    private final Handler handler;

    private WideAreaAuthorApi(Hook hook, Context context) {
        this.hook = hook;
        this.handler = new Handler(context.getMainLooper());
    }

    private synchronized void post(long time, Runnable runnable) {
        if (time <= 0) {
            handler.post(runnable);
        } else {
            handler.postDelayed(runnable, time);
        }
    }

    private synchronized JSONObject getJsonObject(String text) {
        return JSON.parseObject(text);
    }

    private void requestUserApi(final String userId, final String secUserId, final UserCallback callback) {
        logger.i(String.format("作者[%s]信息请求接口", userId));
        post(callback.second * 5, () -> {
            TremoloApi.profileApi(hook, secUserId, new TremoloApi.Callback() {
                @Override
                public void success(String data) {
                    if (Lang.isEmpty(data)) {
                        callback.fail(userId + " 的抖音用户接口返回数据为空");
                    } else {
                        JSONObject dataObject = getJsonObject(data);
                        if (Lang.isNull(dataObject)) {
                            callback.fail(userId + " 的抖音用户接口返回数据解析出错");
                        } else {
                            callback.loadUserInfo(new Author(dataObject.getString("user")));
                        }
                    }
                }

                @Override
                public void fail(Throwable e, String msg) {
                    logger.e(e, msg);
                    callback.fail(msg);
                }
            });
        });
    }

    private void requestVideoListApi(final boolean isFirst, final String userId, final String secUserId, final long time, final VideosCallback callback) {
        logger.i(String.format("作者[%s]视频列表请求接口", userId));
        post(callback.second * 5, () -> {
            if (isFirst) {
                TremoloApi.feedVideoApi(hook, isFirst, userId, secUserId, 0, 20, new TremoloApi.Callback() {
                    @Override
                    public void success(String data) {
                        if (Lang.isEmpty(data)) {
                            callback.fail(userId + " 的抖音视频列表接口返回数据为空");
                        } else {
                            JSONObject dataObject = getJsonObject(data);
                            if (Lang.isNull(dataObject)) {
                                callback.fail(userId + " 的抖音用户接口返回数据解析出错");
                            } else {
                                boolean hasMore = dataObject.getBooleanValue("hasMore");
                                List<Object> videoList = JSON.parseArray(dataObject.getString("items"), Object.class);
                                List<Video> videos = new ArrayList<>();
                                if (videoList != null && !videoList.isEmpty()) {
                                    for (Object video : videoList) {
                                        videos.add(new Video(JSON.toJSONString(video)));
                                    }
                                }
                                callback.loadVideoList(hasMore, userId, secUserId, videos);
                            }
                        }
                    }

                    @Override
                    public void fail(Throwable e, String msg) {
                        logger.e(e, msg);
                        callback.fail(msg);
                    }
                });
            } else {
                TremoloApi.feedVideoApi(hook, isFirst, userId, secUserId, time, 10, new TremoloApi.Callback() {
                    @Override
                    public void success(String data) {
                        if (Lang.isEmpty(data)) {
                            callback.fail(userId + " 的抖音视频列表接口返回数据为空");
                        } else {
                            JSONObject dataObject = getJsonObject(data);
                            if (Lang.isNull(dataObject)) {
                                callback.fail(userId + " 的抖音用户接口返回数据解析出错");
                            } else {
                                boolean hasMore = dataObject.getBooleanValue("hasMore");
                                List<Object> videoList = JSON.parseArray(dataObject.getString("items"), Object.class);
                                List<Video> videos = new ArrayList<>();
                                if (videoList != null && !videoList.isEmpty()) {
                                    for (Object video : videoList) {
                                        videos.add(new Video(JSON.toJSONString(video)));
                                    }
                                }
                                callback.loadVideoList(hasMore, userId, secUserId, videos);
                            }
                        }
                    }

                    @Override
                    public void fail(Throwable e, String msg) {
                        logger.e(e, msg);
                        callback.fail(msg);
                    }
                });
            }
        });
    }

    public interface Callback {
        void over(String authorID);

        void fail(String authorID, int type, String msg);
    }

    private interface WideCallback {
        void userSuccess(String userId, String sceUserId);

        void userFail(String userId, String msg);

        void videosSuccess(boolean hasMore, String userId, String secUserId, Video video);

        void videosFail(String userId, String msg);
    }

    public interface NetUserCallback {
        void profileExist(String userId, String sceUserId, boolean isExist);
    }

    private interface UserCallback extends BaseInterface {
        void loadUserInfo(Author author);

        void fail(String msg);
    }

    private interface VideosCallback extends BaseInterface {

        void loadVideoList(boolean hasMore, String userId, String secUserId, List<Video> videos);

        void fail(String msg);
    }
}