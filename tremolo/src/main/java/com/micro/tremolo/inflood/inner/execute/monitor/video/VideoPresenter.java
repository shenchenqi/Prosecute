package com.micro.tremolo.inflood.inner.execute.monitor.video;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.tremolo.inflood.DataBroadcast;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:41
 */
public class VideoPresenter extends PluginPresenter<VideoInter> {

    @Override
    public void onAttached() {
    }

    private Object itemVideoInfo;

    public Object getItemVideoInfo() {
        return itemVideoInfo;
    }

    public void setItemVideoInfo(Object itemVideoInfo) {
        this.itemVideoInfo = itemVideoInfo;
    }

    public void obtainVideoItem(Aweme aweme) {
        getClazz().loadVideoItem(aweme);
    }

    public void obtainVideoList(Hook hook, List<Object> list) {
        if (list != null) {
            List<Aweme> awemeList = new ArrayList<>();
            for (Object object : list) {
                Aweme aweme = new Aweme(hook, object);
                awemeList.add(aweme);
            }
            getClazz().loadVideoList(awemeList);
        }
    }

    public synchronized void saveVideoTableList(List<Aweme> awemeList) {
        for (Aweme aweme : awemeList) {
            saveVideoTableItem(aweme);
        }
    }

    public synchronized void saveVideoTableItem(Aweme aweme) {
        VideoModelTable videoTable = loadVideoTable(aweme);
        DataBroadcast.sendVideo(getContext(), videoTable);
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
        return videoTable;
    }
}