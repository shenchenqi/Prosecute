package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;
import android.os.Handler;

import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
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

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/10 13:10
 * 根据视频作者的ID，secID进行接口访问拿取数据
 */
public class WideAreaTask {

    public final static Map<String, String> userMap = new HashMap<>();

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
        requestData(authorID -> {
            userMap.remove(authorID);
            taskLogger.d("广域采集模式 运行结束");
            isRun = false;
            if (number < 6 && mOversee != null) {
                taskLogger.d("广域采集模式 运行结束 视频切换");
                mOversee.nextVideo();
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
            String authorID = entry.getKey();
            String secAuthorID = entry.getValue();
            if (Lang.isNotEmpty(authorID) && Lang.isNotEmpty(secAuthorID)) {
                taskLogger.d(String.format("作者[%s]开始请求", authorID));
                UserIdModelTable userIdModelTable = new UserIdModelTable();
                userIdModelTable.setUserId(authorID);
                userIdModelTable.setSceUserId(secAuthorID);
                UploadNet.isUserExist(userIdModelTable, (userId, sceUserId, isExist) -> {
                    taskLogger.d(String.format("作者[%s]是否已存在服务器[%s]", userId, isExist));
                    if (isExist) {
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
                        ProfileOtherApi.loadApi(sceUserId, new ProfileOtherApi.Callback() {
                            @Override
                            public void complete(final Author author) {
                                if (Lang.toLong(author.getFansCount()) > Const.fansCount) {
                                    taskLogger.d(String.format("作者[%s]粉丝数超过1万", author.getUserId()));
                                    UploadNet.uploadUser(loadUserTable(author));
                                    VideoListApi.loadApi(author.getUserId(), author.getSceUserId(), new VideoListApi.Callback() {
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
                                        }

                                        @Override
                                        public void complete() {
                                            callback.end(author.getUserId());
                                        }

                                        @Override
                                        public void finish() {
                                            callback.end(author.getUserId());
                                        }
                                    });
                                } else {
                                    taskLogger.e(String.format("作者[%s]粉丝数未超过1万", author.getUserId()));
                                    callback.end(author.getUserId());
                                }
                            }

                            @Override
                            public void finish() {
                                callback.end(userId);
                            }
                        });
                    }
                });
                return;
            }
        }
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

    public interface NetUserCallback {
        void profileExist(String userId, String sceUserId, boolean isExist);
    }

    public interface Callback {
        void end(String authorID);
    }
}