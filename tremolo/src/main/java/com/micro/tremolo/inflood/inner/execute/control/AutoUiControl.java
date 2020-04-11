package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.micro.hook.ControlLayout;
import com.micro.hook.config.Hook;
import com.micro.root.mvp.BaseInterface;
import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.taskLogger;

/**
 * 自动控制布局
 */
public class AutoUiControl extends ControlLayout implements BaseInterface {

    private final Context context;

    private final MainActivityControl mainActivityControl;
    private final MainFragmentControl mainFragmentControl;
    private final ProfileFragmentControl profileFragmentControl;

    public AutoUiControl(Hook hook, Context context) {
        this.context = context;
        this.mainActivityControl = MainActivityControl.getInstance(context, hook);
        this.mainFragmentControl = MainFragmentControl.getInstance(context, new MainFragmentControl.MainCallback() {
            @Override
            public void success(String value, int type) {
                taskLogger.d(String.format("MainFragment[%s][%s]", type, value));
            }

            @Override
            public void fail(String value, int type) {
                taskLogger.e(String.format("MainFragment[%s][%s]", type, value));
            }
        });
        this.profileFragmentControl = ProfileFragmentControl.getInstance(context, new ProfileFragmentControl.ProfileCallback() {
            @Override
            public void success(String value, int type) {
                taskLogger.d(String.format("ProfileFragment[%s][%s]", type, value));
                if (type == ProfileFragmentControl.uiLoadMoreVideo) {
                    setCount++;
                    taskLogger.d(String.format("加载更多抖音号视频： [%s][%s], {[%s][%s][%s][%s]}", type, value, isRead, videoSize, count, setCount));
                    if (isRead && videoSize < count && setCount < (count / 10)) {
                        profileFragmentControl.loadMoreVideo();
                    } else {
                        profileFragmentControl.moveVideo();
                        setStatus(4);
                    }
                } else if (type == ProfileFragmentControl.uiMoveVideo) {
                    mainFragmentControl.uiChangeVideoTop();
                    setStatus(5);
                }
            }

            @Override
            public void fail(String value, int type) {
                taskLogger.e(String.format("ProfileFragment[%s][%s]", type, value));
            }
        });
    }

    private Handler handler;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public void statusMonitor() {
        if (!Const.isAutoUI) {
            taskLogger.d("当前不是控制UI模式");
            return;
        }
        if (handler == null) {
            handler = new Handler(context.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    taskLogger.d("当前控制UI模式 状态为: " + status);
                    if (Lang.isEquals(0, status) || Lang.isEquals(5, status)) {
                        mainFragmentControl.uiChangeVideoTop();
                        setStatus(5);
                    }
                    handler.postDelayed(this::run, second * 5);
                }
            }, second * 5);
        }
    }

    private int count;
    private int setCount;

    private int videoSize;

    public void setVideosSize(int videoSize) {
        this.videoSize = videoSize;
    }

    private boolean isRead;

    public void setRead(int fansCount) {
        isRead = fansCount > Const.fansCount;
    }

    public void setMainActivity(Object mainActivity) {
        mainActivityControl.setMainActivity(mainActivity);
    }

    public void setMainFragmentView(View view) {
        mainFragmentControl.setMainFragmentView(view);
    }

    public void setProfileFragmentView(View view) {
        profileFragmentControl.setProfileFragmentView(view);
    }

    public void autoMoveUser() {
        mainFragmentControl.uiMoveUser();
    }

    public void autoChangeVideo() {
        mainFragmentControl.uiChangeVideoTop();
    }

    public void autoLoadMoreVideo(int count) {
        this.count = count;
        this.setCount = 0;
        profileFragmentControl.loadMoreVideo();
    }

    @Override
    public Context getIContext() {
        return context;
    }

    public synchronized void obtainVideoList(List<Aweme> awemeList) {
        List<VideoModelTable> videoTableList = new ArrayList<>();
        for (Aweme aweme : awemeList) {
            videoTableList.add(loadVideoTable(aweme));
        }
        if (!videoTableList.isEmpty()) {
            VideoListModelTable videoListModelTable = new VideoListModelTable();
            videoListModelTable.setVideoModelTableList(videoTableList);
            UploadNet.uploadVideoList(videoListModelTable);
        }
    }

    private synchronized VideoModelTable loadVideoTable(Aweme aweme) {
        VideoModelTable videoTable = new VideoModelTable();
        videoTable.setId(aweme.getAid());
        videoTable.setTitle(aweme.getDesc());
        videoTable.setCreateTime(String.valueOf(aweme.getCreateTime()));
        videoTable.setShareUrl(aweme.getShareUrl());
        AwemeStatistics statistics = aweme.getStatistics();
        if (statistics != null) {
            videoTable.setCommentCount(String.valueOf(statistics.getCommentCount()));
            videoTable.setDiggCount(String.valueOf(statistics.getDiggCount()));
            videoTable.setDownloadCount(String.valueOf(statistics.getDownloadCount()));
            videoTable.setShareCount(String.valueOf(statistics.getShareCount()));
        }
        com.micro.tremolo.inflood.inner.replace.Video video = aweme.getVideo();
        if (video != null) {
            VideoUrlModel videoUrlModel = video.getPlayAddr();
            if (videoUrlModel != null) {
                videoTable.setUrlList(videoUrlModel.getUrlList());
            }
        }
        User user = aweme.getAuthor();
        if (user != null) {
            videoTable.setUserId(user.getUid());
            videoTable.setNickname(user.getNickname());
        }
        taskLogger.d(String.format("单个视频信息 [%s, %s]", videoTable.getId(), videoTable.getTitle()));
        return videoTable;
    }

    public synchronized void obtainUser(User user) {
        if (isRead) {
            UploadNet.uploadUser(loadUserTable(user));
        }
    }

    private synchronized UserModelTable loadUserTable(User user) {
        UserModelTable userTable = new UserModelTable();
        userTable.setUserId(user.getUid());
        userTable.setSceUserId(user.getSecUid());
        userTable.setNickname(user.getNickname());
        userTable.setTremoloId(user.getUniqueId());
        userTable.setTremoloNumberId(user.getShortId());
        userTable.setBirthday(user.getBirthday());
        userTable.setCity(user.getCity());
        userTable.setCountry(user.getCountry());
        userTable.setDistrict(user.getDistrict());
        userTable.setSchoolName(user.getSchoolName());
        userTable.setSignature(user.getSignature());
        userTable.setCustomVerify(user.getCustomVerify());
        userTable.setEnterpriseVerify(user.getEnterpriseVerifyReason());
        userTable.setRequestId(user.getRequestId());
        userTable.setFollowingCount(String.valueOf(user.getFollowingCount()));
        userTable.setAwemeCount(String.valueOf(user.getAwemeCount()));
        userTable.setMovingCount(String.valueOf(user.getDongtai_count()));
        userTable.setFansCount(String.valueOf(user.getFansCount()));
        userTable.setFavoritingCount(String.valueOf(user.getFavoritingCount()));
        UrlModel avatarLarger = user.getAvatarLarger();
        if (avatarLarger != null) {
            userTable.setAvatarList(avatarLarger.getUrlList());
            userTable.setUri(avatarLarger.getUri());
            userTable.setUrlKey(avatarLarger.getUrlKey());
        }
        UrlModel avatarMedium = user.getAvatarMedium();
        if (avatarMedium != null) {
            userTable.setAvatarMediumList(avatarMedium.getUrlList());
            if (Lang.isEmpty(userTable.getUri())) {
                userTable.setUri(avatarMedium.getUri());
            }
            if (Lang.isEmpty(userTable.getUrlKey())) {
                userTable.setUri(avatarMedium.getUrlKey());
            }
        }
        UrlModel avatarThumb = user.getAvatarThumb();
        if (avatarThumb != null) {
            userTable.setAvatarThumbList(avatarThumb.getUrlList());
            if (Lang.isEmpty(userTable.getUri())) {
                userTable.setUri(avatarThumb.getUri());
            }
            if (Lang.isEmpty(userTable.getUrlKey())) {
                userTable.setUri(avatarThumb.getUrlKey());
            }
        }
        taskLogger.d(String.format("视频用户 [%s, %s]", userTable.getUserId(), userTable.getNickname()));
        return userTable;
    }
}
