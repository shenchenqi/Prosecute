package com.micro.tremolo.inflood.inner.execute.task.action;

import com.micro.root.task.BaseRunnable;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.execute.api.ProfileOtherApiTremolo;
import com.micro.tremolo.inflood.inner.execute.api.VideoListApiTremolo;
import com.micro.tremolo.inflood.inner.execute.task.BaseTremoloExecutor;
import com.micro.tremolo.model.from.Author;
import com.micro.tremolo.model.from.Video;
import com.micro.tremolo.model.from.VideoArray;
import com.micro.tremolo.model.params.VideoArrayParam;
import com.micro.tremolo.model.params.VideoParam;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 15:30
 */
public class ProfileAndAwemeAction extends BaseRunnable {

    private static ProfileAndAwemeAction mProfileAndAwemeAction;

    public static ProfileAndAwemeAction getInstance(BaseTremoloExecutor executor) {
        if (mProfileAndAwemeAction == null) {
            mProfileAndAwemeAction = new ProfileAndAwemeAction(executor);
        }
        return mProfileAndAwemeAction;
    }

    private final BaseTremoloExecutor executor;

    private ProfileAndAwemeAction(BaseTremoloExecutor executor) {
        super();
        this.executor = executor;
    }

    private Callback callback;

    public void register(String authorId, String secAuthorId, Callback callback) {
        this.authorId = authorId;
        this.secAuthorId = secAuthorId;
        this.callback = callback;
        executor.addTask(this);
    }

    @Override
    protected String taskName() {
        return "AreaWide";
    }

    @Override
    protected boolean isCycleConfig() {
        return false;
    }

    @Override
    protected boolean isSingleConfig() {
        return false;
    }

    @Override
    protected void process() {
        taskLogger.d("用户与作品采集 运行 ");
        loadUser(secAuthorId, new Callback() {
            @Override
            public void user(Author author) {
                loadVideos(authorId, secAuthorId, this);
                callback.user(author);
            }

            @Override
            public void videos(VideoArray videoArray, VideoArrayParam videoArrayParam) {
                callback.videos(videoArray, videoArrayParam);
            }

            @Override
            public void over(ProfileAndAwemeAction action, String authorId) {
                callback.over(action, authorId);
            }
        });
    }

    @Override
    protected void timeout() {
        taskLogger.d("用户与作品采集 超时 ");
    }

    private String authorId;
    private String secAuthorId;

    private void loadUser(String secAuthorId, final Callback callback) {
        ProfileOtherApiTremolo.loadApi(secAuthorId, new ProfileOtherApiTremolo.Callback() {
            @Override
            public void complete(final Author author) {
                callback.user(author);
            }

            @Override
            public void finish() {
                callback.over(mProfileAndAwemeAction, authorId);
            }
        });
    }

    private void loadVideos(String authorId, String secAuthorId, final Callback callback) {
        VideoListApiTremolo.loadApi(authorId, secAuthorId, new VideoListApiTremolo.Callback() {
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
                callback.videos(videoArray, videoArrayParam);
            }

            @Override
            public void complete() {
                callback.over(mProfileAndAwemeAction, authorId);
            }

            @Override
            public void finish() {
                callback.over(mProfileAndAwemeAction, authorId);
            }
        });
    }

    @Override
    protected void error(Throwable e) {
        taskLogger.e(e, "用户与作品采集 报错 ");
    }

    @Override
    protected void finish() {
        taskLogger.d("用户与作品采集 结束 ");
    }

    public void remove() {
        executor.removeTask(this);
    }

    public interface Callback {
        void user(Author author);

        void videos(VideoArray videoArray, VideoArrayParam videoArrayParam);

        void over(ProfileAndAwemeAction action, String authorId);
    }
}