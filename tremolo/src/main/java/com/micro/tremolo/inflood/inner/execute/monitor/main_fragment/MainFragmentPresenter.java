package com.micro.tremolo.inflood.inner.execute.monitor.main_fragment;

import com.micro.tremolo.Const;
import com.micro.tremolo.inflood.inner.execute.mvp.ExecutePresenter;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:20
 */
public class MainFragmentPresenter extends ExecutePresenter<MainFragmentInter> {
    @Override
    public void onAttached() {

    }

    public boolean isRead(int fansCount) {
        return fansCount > Const.fansCount;
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
        //monitorLogger.d(String.format("视频列表里的 视频信息 [%s]", videoTable.toString()));
        monitorLogger.d(String.format("视频列表里的 视频信息 [%s]", videoTable.getId()));
        return videoTable;
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
        monitorLogger.d(String.format("单个视频信息 [%s]", videoTable.toString()));
        return videoTable;
    }

    public synchronized UserModelTable loadUserTable(Author author) {
        UserModelTable userTable = new UserModelTable();
        userTable.setUserId(author.getUserId());
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
        monitorLogger.d(String.format("接口返回的视频主信息 [%s]", userTable.toString()));
        return userTable;
    }
}