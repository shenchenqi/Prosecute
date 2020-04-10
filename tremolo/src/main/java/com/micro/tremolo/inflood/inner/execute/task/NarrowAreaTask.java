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
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/10 17:34
 */
public class NarrowAreaTask {

    private final static Logger logger = Logger.getLogger("tremoloLog", "NarrowAreaLog");

    private static NarrowAreaTask mNarrowAreaTask;

    public static NarrowAreaTask getInstance(Hook hook, Context context) {
        if (mNarrowAreaTask == null) {
            mNarrowAreaTask = new NarrowAreaTask(hook, context);
        }
        return mNarrowAreaTask;
    }

    public static void requestData(String search) {
        if (!Const.isNarrowArea) {
            logger.d("当前不是狭域指定模式");
            return;
        }
        requestData(search, new Callback() {
            @Override
            public void over(String authorID) {
                logger.d("请求完成");
                isRun = false;
            }

            @Override
            public void fail(String authorID, int type, String msg) {
                logger.e(String.format("出错[%s][%s][%s]", authorID, type, msg));
                isRun = false;
            }
        });
    }

    private static boolean isRun = false;

    private static void requestData(final String search, Callback callback) {
        if (isRun) {
            logger.i("正在运行");
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
        isRun = true;
        setSearchUserApi(search, 0, "", new NarrowCallback() {
            @Override
            public Author getAuthor(String key, List<Author> authors) {
                for (Author author : authors) {
                    if (Lang.isEquals(key, author.getUserId())) {
                        return author;
                    }
                    if (Lang.isEquals(key, author.getTremoloId())) {
                        return author;
                    }
                    if (Lang.isEquals(key, author.getTremoloNumberId())) {
                        return author;
                    }
                    if (Lang.isEquals(key, author.getNickname())) {
                        return author;
                    }
                }
                return null;
            }

            @Override
            public void againRequest(long cursor, String requestId) {
                setSearchUserApi(search, cursor, requestId, this);
            }

            @Override
            public void exist(String authorId, String sceAuthorId) {
                setUserApi(authorId, sceAuthorId, wideCallback);
            }

            @Override
            public void fail(String msg) {
                callback.fail(search, 0, msg);
            }
        });
    }

    private static String requestId;

    private static void setSearchUserApi(final String search, long cursor, String requestId, final NarrowCallback callback) {
        mNarrowAreaTask.requestSearchUserApi(search, cursor, requestId, new SearchUserCallback() {
            @Override
            public void firstLoadUser(long cursor, String requestId, boolean hasMore, List<Author> authors) {
                NarrowAreaTask.requestId = requestId;
                Author author = callback.getAuthor(search, authors);
                if (Lang.isNotNull(author)) {
                    logger.d("first author: " + JSON.toJSONString(author));
                    callback.exist(author.getUserId(), author.getSceUserId());
                } else if (!hasMore) {
                    callback.fail("搜索不出");
                } else {
                    callback.againRequest(cursor, requestId);
                }
            }

            @Override
            public void loadUser(long cursor, boolean hasMore, List<Author> authors) {
                Author author = callback.getAuthor(search, authors);
                if (Lang.isNotNull(author)) {
                    logger.d("author: " + JSON.toJSONString(author));
                    callback.exist(author.getUserId(), author.getSceUserId());
                } else if (!hasMore) {
                    callback.fail("搜索不出");
                } else {
                    callback.againRequest(cursor, NarrowAreaTask.requestId);
                }
            }

            @Override
            public void fail(String msg) {
                callback.fail(msg);
            }
        });
    }

    private static void setUserApi(final String authorID, String secAuthorID, final WideCallback callback) {
        mNarrowAreaTask.requestUserApi(authorID, secAuthorID, new UserCallback() {
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
        mNarrowAreaTask.requestVideoListApi(isFirst, authorID, secAuthorID, time, new VideosCallback() {
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

    private NarrowAreaTask(Hook hook, Context context) {
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

    private void requestSearchUserApi(final String search, final long cursor, final String requestId, SearchUserCallback callback) {
        logger.i(String.format("查询用户信息请求接口, 内容[%s]", search));
        post(BaseInterface.second * 5, () -> {
            if (Lang.isEmpty(requestId)) {
                TremoloApi.searchUserApi(hook, search, 0, 10, 0, "", new TremoloApi.Callback() {
                    @Override
                    public void success(String data) {
                        if (Lang.isEmpty(data)) {
                            callback.fail(" 抖音查询用户信息返回数据为空");
                        } else {
                            JSONObject dataObject = getJsonObject(data);
                            if (Lang.isNull(dataObject)) {
                                callback.fail(" 抖音查询用户信息返回数据解析出错");
                            } else {
                                String request = dataObject.getString("requestId");
                                long cursor = dataObject.getLongValue("cursor");
                                boolean hasMore = dataObject.getBooleanValue("hasMore");
                                List<Object> userList = (List<Object>) dataObject.get("userList");
                                List<Author> authors = new ArrayList<>();
                                for (Object user : userList) {
                                    JSONObject userObject = JSON.parseObject(JSON.toJSONString(user));
                                    authors.add(new Author(userObject.getString("user")));
                                }
                                callback.firstLoadUser(cursor, request, hasMore, authors);
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
                TremoloApi.searchUserApi(hook, search, cursor, 10, 1, requestId, new TremoloApi.Callback() {
                    @Override
                    public void success(String data) {
                        if (Lang.isEmpty(data)) {
                            callback.fail(" 抖音查询用户信息返回数据为空");
                        } else {
                            JSONObject dataObject = getJsonObject(data);
                            if (Lang.isNull(dataObject)) {
                                callback.fail(" 抖音查询用户信息返回数据解析出错");
                            } else {
                                long cursor = dataObject.getLongValue("cursor");
                                boolean hasMore = dataObject.getBooleanValue("hasMore");
                                List<Object> userList = (List<Object>) dataObject.get("userList");
                                List<Author> authors = new ArrayList<>();
                                for (Object user : userList) {
                                    JSONObject userObject = JSON.parseObject(JSON.toJSONString(user));
                                    authors.add(new Author(userObject.getString("user")));
                                }
                                callback.loadUser(cursor, hasMore, authors);
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

    private interface NarrowCallback {
        Author getAuthor(String key, List<Author> authors);

        void againRequest(long cursor, String requestId);

        void exist(String authorId, String sceAuthorId);

        void fail(String msg);
    }

    private interface SearchUserCallback {

        void firstLoadUser(long cursor, String requestId, boolean hasMore, List<Author> authors);

        void loadUser(long cursor, boolean hasMore, List<Author> authors);

        void fail(String msg);
    }

    private interface WideCallback {
        void userSuccess(String userId, String sceUserId);

        void userFail(String userId, String msg);

        void videosSuccess(boolean hasMore, String userId, String secUserId, Video video);

        void videosFail(String userId, String msg);
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