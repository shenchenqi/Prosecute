package com.micro.tremolo.inflood.inner.execute.monitor.main_fragment;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.tremolo.UploadNet;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:20
 */
public class MainFragmentPresenter extends PluginPresenter<MainFragmentInter> {
    @Override
    public void onAttached() {

    }

    public void obtainVideo(Aweme aweme) {
        VideoModelTable videoTable = loadVideoTable(aweme);
        UploadNet.uploadVideo(videoTable);
    }

    private synchronized VideoModelTable loadVideoTable(Aweme aweme) {
        VideoModelTable videoTable = new VideoModelTable();
        videoTable.setId(aweme.getAid());
        videoTable.setTitle(aweme.getDesc());
        videoTable.setCreateTime(aweme.getCreateTime());
        videoTable.setShareUrl(aweme.getShareUrl());
        AwemeStatistics statistics = aweme.getStatistics();
        if (statistics != null) {
            videoTable.setCommentCount(statistics.getCommentCount());
            videoTable.setDiggCount(statistics.getDiggCount());
            videoTable.setDownloadCount(statistics.getDownloadCount());
            videoTable.setShareCount(statistics.getShareCount());
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