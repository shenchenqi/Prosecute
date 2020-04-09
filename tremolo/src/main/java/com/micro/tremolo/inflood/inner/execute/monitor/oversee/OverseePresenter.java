package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import android.view.View;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.RequestApi;
import com.micro.tremolo.inflood.inner.execute.control.MainActivityControl;
import com.micro.tremolo.inflood.inner.execute.control.MainFragmentControl;
import com.micro.tremolo.inflood.inner.execute.control.ProfileFragmentControl;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:08
 */
public class OverseePresenter extends PluginPresenter<OverseeInter> {
    private static OverseePresenter mOverseePresenter;

    public static OverseePresenter getInstance() {
        if (mOverseePresenter == null) {
            mOverseePresenter = new OverseePresenter();
        }
        return mOverseePresenter;
    }

    @Override
    public void onAttached() {

    }

    private Hook hook;

    public void setHook(Hook hook) {
        this.hook = hook;
    }

    private RequestApi requestApi;
    private AutoUiControl autoUiControl;
    private MainActivityControl mainActivityControl;
    private MainFragmentControl mainFragmentControl;
    private ProfileFragmentControl profileFragmentControl;

    public void initCreate() {
        if (requestApi == null) {
            requestApi = new RequestApi(hook, getContext());
        }
        if (Const.isAutoUI) {
            if (autoUiControl == null) {
                autoUiControl = new AutoUiControl(hook, getContext());
            }
        } else if (Const.isWideArea) {
            if (mainActivityControl == null) {
                mainActivityControl = MainActivityControl.getInstance(getContext(), hook);
            }
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext(), new MainFragmentControl.MainCallback() {
                    @Override
                    public void success(String value, int type) {
                        monitorLogger.d(String.format("MainFragment[%s][%s]", type, value));
                    }

                    @Override
                    public void fail(String value, int type) {
                        monitorLogger.e(String.format("MainFragment[%s][%s]", type, value));
                    }
                });
            }
            if (profileFragmentControl == null) {
                profileFragmentControl = ProfileFragmentControl.getInstance(getContext(), new ProfileFragmentControl.ProfileCallback() {
                    @Override
                    public void success(String value, int type) {
                        monitorLogger.d(String.format("MainFragment[%s][%s]", type, value));
                    }

                    @Override
                    public void fail(String value, int type) {
                        monitorLogger.e(String.format("MainFragment[%s][%s]", type, value));
                    }
                });
            }
        } else if (Const.isNarrowArea) {
            if (mainFragmentControl == null) {
                mainFragmentControl = MainFragmentControl.getInstance(getContext(), new MainFragmentControl.MainCallback() {
                    @Override
                    public void success(String value, int type) {
                        monitorLogger.d(String.format("MainFragment[%s][%s]", type, value));
                    }

                    @Override
                    public void fail(String value, int type) {
                        monitorLogger.e(String.format("MainFragment[%s][%s]", type, value));
                    }
                });
            }
        } else {
            monitorLogger.d("不控制任何");
        }
    }

    public void setMainActivity(Object mainActivity) {
        if (Const.isAutoUI) {
            autoUiControl.setMainActivity(mainActivity);
        } else if (mainActivityControl != null) {
            mainActivityControl.setMainActivity(mainActivity);
        }
    }

    public void setMainFragmentView(View mainFragmentView) {
        if (Const.isAutoUI) {
            autoUiControl.setMainFragmentView(mainFragmentView);
        } else if (mainFragmentControl != null) {
            mainFragmentControl.setMainFragmentView(mainFragmentView);
        }
    }

    public void setProfileFragmentView(View profileFragmentView) {
        if (Const.isAutoUI) {
            autoUiControl.setProfileFragmentView(profileFragmentView);
        } else if (profileFragmentControl != null) {
            profileFragmentControl.setProfileFragmentView(profileFragmentView);
        }
    }

    private String videoId;
    public String authorId;
    private String secAuthorId;

    public void setData(String videoId, String authorId, String secAuthorId) {
        this.videoId = videoId;
        this.authorId = authorId;
        this.secAuthorId = secAuthorId;
        monitorLogger.d(String.format("设置信息[%s, %s, %s]", this.videoId, this.authorId, this.secAuthorId));
    }

    public void apiLoadProfile() {
        if (Const.isAutoUI) {
            monitorLogger.e("当前自动UI控制");
            return;
        }
        if (!Const.isWideArea) {
            monitorLogger.e("当前不是广域采集数据");
            return;
        }
        if (Lang.isEmpty(secAuthorId)) {
            monitorLogger.e("当前用户请求ID为空");
        }
        monitorLogger.d("是否是视频主： " + isProfile());
        setHandlerPost(OverseeInter.second * 10, () -> {
            if (isProfile()) {
                requestApi.requestProfileApi(secAuthorId, getClazz());
            } else {
                nextVideo();
            }
        });
    }

    public void apiLoadVideoList(boolean isFirst, long time) {
        if (Const.isAutoUI) {
            monitorLogger.e("当前自动UI控制");
            return;
        }
        if (!Const.isWideArea) {
            monitorLogger.e("当前不是广域采集数据");
            return;
        }
        if (Lang.isEmpty(authorId)) {
            monitorLogger.e("当前用户ID为空");
        }
        if (Lang.isEmpty(secAuthorId)) {
            monitorLogger.e("当前用户请求ID为空");
        }
        requestApi.requestFeedVideoApi(isFirst, authorId, secAuthorId, time, getClazz());
    }

    public void apiSearchUser(String search, long size, String requestId) {
        if (!Const.isNarrowArea) {
            return;
        }
        setHandlerPost(OverseeInter.second * 10, () -> {
            requestApi.requestSearchUserApi(search, size, requestId, getClazz());
        });
    }

    public void nextVideo() {
        if (Const.isAutoUI) {
            autoUiControl.autoChangeVideo();
        } else if (Const.isWideArea) {
            mainFragmentControl.clickRecommend();
        }
    }

    private void setHandlerPost(long time, Runnable runnable) {
        setHandlerPost(time, getContext().getMainLooper(), runnable);
    }

    private boolean isProfile;

    private boolean isProfile() {
        return isProfile;
    }

    public void setProfile(boolean profile) {
        isProfile = profile;
    }

    private int fansCount;

    public void setRead(int fansCount) {
        this.fansCount = fansCount;
        if (Const.isAutoUI) {
            autoUiControl.setRead(fansCount);
        }
    }

    public boolean isRead() {
        return fansCount > Const.fansCount;
    }

    private int videoCount;

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public void autoMoveUser() {
        if (Const.isAutoUI) {
            autoUiControl.autoMoveUser();
        }
    }

    public void setVideosSize(int size) {
        if (Const.isAutoUI) {
            autoUiControl.setVideosSize(size);
        }
    }

    public void userMoreVideo() {
        if (Const.isAutoUI) {
            autoUiControl.autoLoadMoreVideo(videoCount);
        }
    }

    public synchronized VideoModelTable loadVideoTable(Video video) {
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
        monitorLogger.d(String.format("视频列表里的 视频信息 [%s, %s]", videoTable.getId(), videoTable.getTitle()));
        return videoTable;
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

    public synchronized VideoModelTable loadVideoTable(Aweme aweme) {
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
        monitorLogger.d(String.format("单个视频信息 [%s, %s]", videoTable.getId(), videoTable.getTitle()));
        return videoTable;
    }

    public synchronized void obtainUser(User user) {
        if (isRead() && Const.isAutoUI) {
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
        monitorLogger.d(String.format("视频用户 [%s, %s]", userTable.getUserId(), userTable.getNickname()));
        return userTable;
    }

    public synchronized UserModelTable loadUserTable(Author author) {
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
        monitorLogger.d(String.format("接口返回的视频主信息 [%s, %s, %s]", userTable.getUserId(), userTable.getNickname(), userTable.getAwemeCount()));
        return userTable;
    }
}