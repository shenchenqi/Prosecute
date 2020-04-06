package com.micro.tremolo.inflood.inner.execute.monitor.profile_fragment;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.UploadNet;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 13:41
 */
public class ProfileFragmentPresenter extends PluginPresenter<ProfileFragmentInter> {
    @Override
    public void onAttached() {

    }

    public void obtainUser(User user) {
        UserModelTable userTable = loadUserTable(user);
        UploadNet.uploadUser(userTable);
    }

    private synchronized UserModelTable loadUserTable(User user) {
        UserModelTable userTable = new UserModelTable();
        userTable.setUserId(user.getUid());
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
        /*if (userTable.getUpdate() == 0) {
            userTable.setUpdate(1);
            monitorLogger.d(String.format("视频用户[%s], Map[%s]", userTable.toString(), userTable.getUserMap()));
        }*/
        monitorLogger.d(String.format("视频用户[%s]", userTable.toString()));
        return userTable;
    }

    public void obtainVideoList(List<Aweme> awemeList) {
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
        } else {
            /*videoTable.setUpdate(-1);*/
        }
        com.micro.tremolo.inflood.inner.replace.Video video = aweme.getVideo();
        if (video != null) {
            VideoUrlModel videoUrlModel = video.getPlayAddr();
            if (videoUrlModel != null) {
                videoTable.setUrlList(videoUrlModel.getUrlList());
            } else {
                /*videoTable.setUpdate(-2);*/
            }
        } else {
            /*videoTable.setUpdate(-2);*/
        }
        User user = aweme.getAuthor();
        if (user != null) {
            videoTable.setUserId(user.getUid());
            videoTable.setNickname(user.getNickname());
        } else {
            /*videoTable.setUpdate(-3);*/
        }
        /*if (videoTable.getUpdate() == 0) {
            videoTable.setUpdate(1);
            monitorLogger.d(videoTable.toString());
        }*/
        monitorLogger.d(videoTable.toString());
        return videoTable;
    }
}
