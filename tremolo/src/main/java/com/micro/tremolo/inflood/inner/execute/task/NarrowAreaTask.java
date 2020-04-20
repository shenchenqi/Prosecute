package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;

import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApiTremolo;
import com.micro.tremolo.inflood.inner.execute.api.SearchUserApiTremolo;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApiTremolo;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.notice.CollectNotice;
import com.micro.tremolo.model.from.Author;
import com.micro.tremolo.model.from.Video;
import com.micro.tremolo.model.params.UserIdParam;
import com.micro.tremolo.model.params.VideoArrayParam;
import com.micro.tremolo.model.params.VideoParam;

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
        CollectNotice.createShowNotice(context, "狭域指定模式", content);
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
        SearchUserApiTremolo.loadApi(search, new SearchUserApiTremolo.Callback() {
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
        ProfileOtherApiTremolo.loadApi(secAuthorID, new ProfileOtherApiTremolo.Callback() {
            @Override
            public void complete(final Author author) {
                if (author.getFansCount() > Const.fansCount) {
                    taskLogger.d(String.format("作者[%s]粉丝数超过1万", author.getUserId()));
                    UploadNet.uploadUser(author.getUserParam());
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

    private static void loadVideo(String authorID, String secAuthorID, final Callback callback) {
        UserIdParam userIdParam = new UserIdParam();
        userIdParam.setUserId(authorID);
        userIdParam.setSceUserId(secAuthorID);
        UploadNet.isUserExist(userIdParam, (userId, sceUserId, isExist) -> {
            taskLogger.d(String.format("作者[%s]是否已存在服务器[%s]", userId, isExist));
            if (!isExist) {
                VideoListApiTremolo.loadApi(userId, sceUserId, new VideoListApiTremolo.Callback() {
                    @Override
                    public void videoList(List<Video> videos) {
                        if (Lang.isEmpty(videos)) {
                            return;
                        }
                        List<VideoParam> videoParams = new ArrayList<>();
                        for (Video video : videos) {
                            videoParams.add(video.getVideoParam());
                        }
                        VideoArrayParam videoArrayParam = new VideoArrayParam();
                        videoArrayParam.setData(videoParams);
                        UploadNet.uploadVideoList(videoArrayParam);
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

    public interface Callback {
        void author(Author author);

        void exist(String userId);

        void end(String userId);
    }
}