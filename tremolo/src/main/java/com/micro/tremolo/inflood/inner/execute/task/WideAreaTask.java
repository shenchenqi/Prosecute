package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;
import android.os.Handler;

import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
import com.micro.task.PluginTask;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApi;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApi;
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

import static com.micro.tremolo.Const.monitorLogger;
import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/10 13:10
 * 根据视频作者的ID，secID进行接口访问拿取数据
 */
public class WideAreaTask extends BaseTaskExecutor {

    private final static Map<String, String> userMap = new HashMap<>();

    public static void setData(String authorId, String secAuthorId) {
        if (Const.isWideArea) {
            if (userMap.containsKey(authorId)) {
                monitorLogger.d(String.format("抖音用户已存在 [%s, %s]", authorId, secAuthorId));
            } else {
                userMap.put(authorId, secAuthorId);
            }
        }
    }

    private final static Map<String, String> videoMap = new HashMap<>();

    private static void setVideo(String authorId, String secAuthorId) {
        if (Const.isWideArea) {
            if (videoMap.containsKey(authorId)) {
                monitorLogger.d(String.format("抖音用户已存在 [%s, %s]", authorId, secAuthorId));
            } else {
                videoMap.put(authorId, secAuthorId);
            }
        }
    }

    private static WideAreaTask mWideAreaTask;

    public static WideAreaTask getInstance() {
        if (mWideAreaTask == null) {
            mWideAreaTask = new WideAreaTask();
        }
        return mWideAreaTask;
    }

    private static Oversee mOversee;

    public static void setOversee(Oversee mOversee) {
        WideAreaTask.mOversee = mOversee;
    }

    public static void canTremoloData(Context context) {
        if (!Const.isWideArea) {
            taskLogger.i("广域采集模式 未开启");
            return;
        }
        Handler handler = new Handler(context.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                taskLogger.i("广域采集模式 检测扫描");
                WideAreaTask.requestData();
                handler.postDelayed(this::run, BaseInterface.second * 20);
            }
        }, BaseInterface.second * 20);
    }

    private static void requestData() {
        requestData(new Callback() {
            @Override
            public void exist(String authorID) {

            }

            @Override
            public void end(String authorID) {
                userMap.remove(authorID);
                taskLogger.d("广域采集模式 运行结束");
                isRun = false;
                if (number < 6 && mOversee != null) {
                    taskLogger.d("广域采集模式 运行结束 视频切换");
                    mOversee.nextVideo();
                }
            }
        });
    }

    private static boolean isRun = false;
    private static int number;

    private static void requestData(final Callback callback) {
        if (isRun) {
            taskLogger.i("广域采集模式 正在运行");
            number++;
            if (number == 6 && mOversee != null) {
                taskLogger.d("广域采集模式 运行太久 先进行视频切换");
                mOversee.nextVideo();
            }
            return;
        }
        Map<String, String> map = new HashMap<>(userMap);
        if (map.isEmpty()) {
            taskLogger.e("广域采集模式 无用户数据");
            callback.end("无用户数据");
            return;
        }
        isRun = true;
        number = 0;
        taskLogger.d("广域采集模式 开始进行数据采集");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            final String authorID = entry.getKey();
            final String secAuthorID = entry.getValue();
            if (Lang.isNotEmpty(authorID) && Lang.isNotEmpty(secAuthorID)) {
                taskLogger.d(String.format("作者[%s]开始请求", authorID));
                loadUser(authorID, secAuthorID, new Callback() {
                    @Override
                    public void exist(String authorID) {
                        loadVideo(authorID, secAuthorID, new Callback() {
                            @Override
                            public void exist(String authorID) {

                            }

                            @Override
                            public void end(String authorID) {
                                callback.end(authorID);
                            }
                        });
                    }

                    @Override
                    public void end(String authorID) {
                        callback.end(authorID);
                    }
                });
                return;
            }
        }
    }

    private static void loadUser(final String authorID, String secAuthorID, final Callback callback) {
        taskLogger.d(String.format("作者[%s]开始请求", authorID));
        ProfileOtherApi.loadApi(secAuthorID, new ProfileOtherApi.Callback() {
            @Override
            public void complete(final Author author) {
                if (Lang.toLong(author.getFansCount()) > Const.fansCount) {
                    taskLogger.d(String.format("作者[%s]粉丝数超过1万", author.getUserId()));
                    UploadNet.uploadUser(loadUserTable(author));
                    callback.exist(author.getUserId());
                } else {
                    taskLogger.e(String.format("作者[%s]粉丝数未超过1万", author.getUserId()));
                    callback.end(author.getUserId());
                }
            }

            @Override
            public void finish() {
                callback.end(authorID);
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

    private static void loadVideo(String authorID, String secAuthorID, final Callback callback) {
        UserIdModelTable userIdModelTable = new UserIdModelTable();
        userIdModelTable.setUserId(authorID);
        userIdModelTable.setSceUserId(secAuthorID);
        UploadNet.isUserExist(userIdModelTable, (userId, sceUserId, isExist) -> {
            taskLogger.d(String.format("作者[%s]是否已存在服务器[%s]", userId, isExist));
            if (!isExist) {
                VideoListApi.loadApi(userId, sceUserId, new VideoListApi.Callback() {
                    @Override
                    public void videoList(List<Video> videos) {
                        if (Lang.isEmpty(videos)) {
                            return;
                        }
                        List<VideoModelTable> videoModelTables = new ArrayList<>();
                        for (Video video : videos) {
                            videoModelTables.add(loadVideoTable(video));
                        }
                        VideoListModelTable videoListModelTable = new VideoListModelTable();
                        videoListModelTable.setVideoModelTableList(videoModelTables);
                        UploadNet.uploadVideoList(videoListModelTable);
                        callback.end(userId);
                    }

                    @Override
                    public void complete() {
                        callback.end(userId);
                    }

                    @Override
                    public void finish() {
                        callback.end(userId);
                    }
                });
            } else {
                callback.end(userId);
            }
        });
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

    public static class UserTask extends PluginTask {
        private boolean isRun = false;

        @Override
        protected String taskName() {
            return "user-task";
        }

        @Override
        protected void process() {
            Map<String, String> map = new HashMap<>(userMap);
            if (map.isEmpty()) {
                taskLogger.e("广域采集模式 用户采集 无数据");
                return;
            }
            if (isRun) {
                taskLogger.e("广域采集模式 用户采集 正在运行");
                return;
            }
            isRun = true;
            taskLogger.d("广域采集模式 用户采集 开始进行");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                final String authorID = entry.getKey();
                final String secAuthorID = entry.getValue();
                if (Lang.isNotEmpty(authorID) && Lang.isNotEmpty(secAuthorID)) {
                    taskLogger.d(String.format("用户[%s] 用户采集 开始请求", authorID));
                    loadUser(authorID, secAuthorID, new Callback() {
                        @Override
                        public void exist(String authorID) {
                            userMap.remove(authorID);
                            setVideo(authorID, secAuthorID);
                            isRun = false;
                        }

                        @Override
                        public void end(String authorID) {
                            userMap.remove(authorID);
                            isRun = false;
                        }
                    });
                    return;
                }
            }
        }

        @Override
        protected void error(Throwable throwable) {
            taskLogger.e(throwable, "广域采集模式 用户采集 报错");
            isRun = false;
        }

        @Override
        public void finish() {
            taskLogger.i("广域采集模式 用户采集 结束");
            isRun = false;
            mWideAreaTask.removeTask(this);
        }

        @Override
        public boolean isCycle() {
            return true;
        }

        @Override
        public boolean isSingle() {
            return false;
        }
    }

    public static class VideoTask extends PluginTask {
        private boolean isRun = false;

        @Override
        protected String taskName() {
            return "video-task";
        }

        @Override
        protected void process() {
            Map<String, String> map = new HashMap<>(videoMap);
            if (map.isEmpty()) {
                taskLogger.e("广域采集模式 视频采集 无数据");
                return;
            }
            if (isRun) {
                taskLogger.e("广域采集模式 视频采集 正在运行");
                return;
            }
            isRun = true;
            taskLogger.d("广域采集模式 视频采集 开始进行");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                final String authorID = entry.getKey();
                final String secAuthorID = entry.getValue();
                if (Lang.isNotEmpty(authorID) && Lang.isNotEmpty(secAuthorID)) {
                    taskLogger.d(String.format("用户[%s] 视频采集 开始请求", authorID));
                    loadVideo(authorID, secAuthorID, new Callback() {
                        @Override
                        public void exist(String authorID) {
                            isRun = false;
                        }

                        @Override
                        public void end(String authorID) {
                            videoMap.remove(authorID);
                            isRun = false;
                        }
                    });
                    return;
                }
            }
        }

        @Override
        protected void error(Throwable throwable) {
            taskLogger.e(throwable, "广域采集模式 视频采集 报错");
            isRun = false;
        }

        @Override
        public void finish() {
            taskLogger.i("广域采集模式 视频采集 结束");
            isRun = false;
            mWideAreaTask.removeTask(this);
        }

        @Override
        public boolean isCycle() {
            return true;
        }

        @Override
        public boolean isSingle() {
            return true;
        }
    }

    public static void setTasks() {
        if (Const.isWideArea) {
            mWideAreaTask.loadScheduled();
            /*mWideAreaTask.setDistribution(new UserTask());
            mWideAreaTask.setDistribution(new VideoTask());*/
        }
    }

    @Override
    protected Runnable changeUI() {
        return () -> {
            if (mOversee != null) {
                taskLogger.d("广域采集模式 运行结束 视频切换");
                mOversee.nextVideo();
            }
        };
    }

    public interface NetUserCallback {
        void profileExist(String userId, String sceUserId, boolean isExist);
    }

    public interface Callback {
        void exist(String authorID);

        void end(String authorID);
    }
}