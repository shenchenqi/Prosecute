package com.micro.tremolo.inflood.inner.execute.task;

import android.content.Context;
import android.os.Handler;

import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
import com.micro.task.PluginTask;
import com.micro.tremolo.Const;
import com.micro.tremolo.broad.DataBroadcast;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApi;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApi;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.Oversee;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.notice.CollectNotice;
import com.micro.tremolo.model.from.Author;
import com.micro.tremolo.model.from.Video;
import com.micro.tremolo.model.from.VideoArray;
import com.micro.tremolo.model.params.UserIdParam;
import com.micro.tremolo.model.params.VideoArrayParam;
import com.micro.tremolo.model.params.VideoParam;

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

    public static WideAreaTask getInstance(Context context) {
        WideAreaTask.context = context;
        if (mWideAreaTask == null) {
            mWideAreaTask = new WideAreaTask();
        }
        return mWideAreaTask;
    }

    private static Context context;

    private static void createShowNotice(String content) {
        CollectNotice.createShowNotice(context, "抖音助手-广域采集模式", content);
    }

    private static Oversee mOversee;

    public static void setOversee(Oversee mOversee) {
        WideAreaTask.mOversee = mOversee;
    }

    private static Handler handler;

    public static void canTremoloData(Context context) {
        createShowNotice("抖音开启扫描");
        if (!Const.isWideArea) {
            taskLogger.i("广域采集模式 未开启");
            return;
        }
        if (handler == null) {
            handler = new Handler(context.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    taskLogger.i("广域采集模式 检测扫描");
                    WideAreaTask.requestData();
                    handler.postDelayed(this::run, BaseInterface.second * 20);
                }
            }, BaseInterface.second * 20);
        }
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
                isTaskRun = false;
                if (number < 6 && mOversee != null) {
                    taskLogger.d("广域采集模式 运行结束 视频切换");
                    createShowNotice(String.format("用户[%s] 采集结束 视频准备切换", authorID));
                    mOversee.nextVideo();
                } else {
                    createShowNotice(String.format("用户[%s] 采集结束 未准备切换视频", authorID));
                }
            }
        });
    }

    private static boolean isTaskRun = false;
    private static int number;

    private static void requestData(final Callback callback) {
        if (isTaskRun) {
            taskLogger.i("广域采集模式 正在运行");
            number++;
            if (number == 6 && mOversee != null) {
                taskLogger.d("广域采集模式 运行太久 先进行视频切换");
                createShowNotice("采集花费太久时间 视频准备切换");
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
        isTaskRun = true;
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
        taskLogger.d(String.format("作者[%s]开始请求 接口", authorID));
        ProfileOtherApi.loadApi(secAuthorID, new ProfileOtherApi.Callback() {
            @Override
            public void complete(final Author author) {
                if (author.getFansCount() > Const.fansCount) {
                    taskLogger.d(String.format("作者[%s]粉丝数超过1万", author.getUserId()));
                    UploadNet.uploadUser(author.getUserParam());
                    DataBroadcast.sendUser(context, author.getUserTable());
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

    private static void loadVideo(final String authorID, final String secAuthorID, final Callback callback) {
        handler.postDelayed(() -> {
            UserIdParam userIdParam = new UserIdParam();
            userIdParam.setUserId(authorID);
            userIdParam.setSceUserId(secAuthorID);
            UploadNet.isUserExist(userIdParam, (userId, sceUserId, isExist) -> {
                taskLogger.d(String.format("作者[%s]是否已存在服务器[%s]", userId, isExist));
                if (isExist) {
                    VideoListApi.loadApi(userId, sceUserId, new VideoListApi.Callback() {
                        @Override
                        public void videoList(List<Video> videos) {
                            if (Lang.isEmpty(videos)) {
                                return;
                            }
                            VideoArray videoArray = new VideoArray();
                            videoArray.setVideos(videos);
                            List<VideoParam> videoParams = new ArrayList<>();
                            for (Video video : videos) {
                                videoParams.add(video.getVideoParam());
                            }
                            VideoArrayParam videoArrayParam = new VideoArrayParam();
                            videoArrayParam.setData(videoParams);
                            DataBroadcast.sendVideoList(context, videoArray);
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
        }, BaseInterface.second * 5);
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