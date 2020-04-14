package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;

import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApi;
import com.micro.tremolo.inflood.inner.execute.api.SearchUserApi;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApi;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.notice.CollectNotice;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserIdModelTable;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/10 17:34
 * 根据内容进行搜索接口访问拿取数据
 */
public class NarrowAreaTask {

    private static NarrowAreaTask mNarrowAreaTask;

    public static NarrowAreaTask getInstance(Context context) {
        NarrowAreaTask.context = context;
        if (mNarrowAreaTask == null) {
            mNarrowAreaTask = new NarrowAreaTask();
        }
        return mNarrowAreaTask;
    }

    private static Context context;

    private static void createShowNotice(String content) {
        CollectNotice.createShowNotice(context, "抖音助手-狭域指定模式", content);
    }

    public static void requestData(String search) {
        if (!Const.isNarrowArea) {
            taskLogger.d("狭域指定模式 未开启");
            return;
        }
        createShowNotice("搜索用户");
        requestData(search, new Callback() {
            @Override
            public void author(Author author) {

            }

            @Override
            public void exist(String userId) {

            }

            @Override
            public void end(String userId) {
                taskLogger.d("狭域指定模式 运行结束");
                isRun = false;
            }
        });
    }

    private static boolean isRun = false;

    private static void requestData(final String search, final Callback callback) {
        if (isRun) {
            taskLogger.i("狭域指定模式 正在运行");
            return;
        }
        isRun = true;
        loadSearch(search, new Callback() {
            @Override
            public void author(final Author author) {
                createShowNotice("搜索用户 已匹配");
                loadUser(author.getUserId(), author.getSceUserId(), new Callback() {
                    @Override
                    public void author(Author author) {

                    }

                    @Override
                    public void exist(String userId) {
                        createShowNotice("搜索用户信息 已上传");
                        loadVideo(userId, author.getSceUserId(), new Callback() {
                            @Override
                            public void author(Author author) {

                            }

                            @Override
                            public void exist(String userId) {

                            }

                            @Override
                            public void end(String userId) {
                                createShowNotice("搜索用户 视频列表 结束");
                                callback.end(userId);
                            }
                        });
                    }

                    @Override
                    public void end(String userId) {
                        createShowNotice("搜索用户信息 上传失败");
                        callback.end(userId);
                    }
                });
            }

            @Override
            public void exist(String userId) {

            }

            @Override
            public void end(String userId) {
                createShowNotice("搜索用户 未匹配");
                callback.end(userId);
            }
        });
    }

    private static void loadSearch(String search, final Callback callback) {
        SearchUserApi.loadApi(search, new SearchUserApi.Callback() {
            @Override
            public void complete(Author author) {
                callback.author(author);
            }

            @Override
            public void finish() {
                callback.end("");
            }
        });
    }

    private static void loadUser(final String authorID, String secAuthorID, final Callback callback) {
        taskLogger.d(String.format("作者[%s]开始请求 接口", authorID));
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

    public interface Callback {
        void author(Author author);

        void exist(String userId);

        void end(String userId);
    }
}