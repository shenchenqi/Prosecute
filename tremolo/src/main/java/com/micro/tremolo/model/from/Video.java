package com.micro.tremolo.model.from;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.model.params.VideoParam;
import com.micro.tremolo.model.table.VideoTable;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 16:03
 */
public class Video extends BaseFrom {
    private String id;
    private String title;
    private long createTime;
    private String shareUrl;
    private int commentCount;//评论数
    private int diggCount;//爱心数
    private int downloadCount;//下载数
    private int shareCount;//分享数
    private List<String> urlList;
    private String userId;
    private String sceUserId;
    private String nickname;

    public Video(String content) {
        JSONObject videoObject = JSON.parseObject(content);
        id = videoObject.getString("aid");
        title = videoObject.getString("desc");
        createTime = videoObject.getLongValue("createTime");
        shareUrl = videoObject.getString("shareUrl");
        String statistics = videoObject.getString("statistics");
        if (Lang.isNotEmpty(statistics)) {
            JSONObject statisticsObject = JSON.parseObject(statistics);
            commentCount = statisticsObject.getIntValue("commentCount");
            diggCount = statisticsObject.getIntValue("diggCount");
            downloadCount = statisticsObject.getIntValue("downloadCount");
            shareCount = statisticsObject.getIntValue("shareCount");
        }
        String videoUrlModel = videoObject.getString("video");
        if (Lang.isNotEmpty(videoUrlModel)) {
            String playAddr = JSON.parseObject(videoUrlModel).getString("playAddr");
            if (Lang.isNotEmpty(playAddr)) {
                urlList = (List<String>) JSON.parseObject(playAddr).get("urlList");
            }
        }
        String author = videoObject.getString("author");
        if (Lang.isNotEmpty(author)) {
            JSONObject authorObject = JSON.parseObject(author);
            userId = authorObject.getString("uid");
            sceUserId = authorObject.getString("secUid");
            nickname = authorObject.getString("nickname");
        }
    }

    public Video(Aweme aweme) {
        id = aweme.getAid();
        title = aweme.getDesc();
        createTime = aweme.getCreateTime();
        shareUrl = aweme.getShareUrl();
        AwemeStatistics statistics = aweme.getStatistics();
        if (Lang.isNotNull(statistics)) {
            commentCount = statistics.getCommentCount();
            diggCount = statistics.getDiggCount();
            downloadCount = statistics.getDownloadCount();
            shareCount = statistics.getShareCount();
        }
        com.micro.tremolo.inflood.inner.replace.Video video = aweme.getVideo();
        if (Lang.isNotNull(video)) {
            VideoUrlModel videoUrlModel = video.getPlayAddr();
            if (Lang.isNotNull(videoUrlModel)) {
                urlList = videoUrlModel.getUrlList();
            }
        }
        User user = aweme.getAuthor();
        if (Lang.isNotNull(user)) {
            userId = user.getUid();
            sceUserId = user.getSecUid();
            nickname = user.getNickname();
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public String getUserId() {
        return userId;
    }

    public String getSceUserId() {
        return sceUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public VideoParam getVideoParam() {
        VideoParam videoParam = new VideoParam();
        videoParam.setId(getId());
        videoParam.setTitle(getTitle());
        videoParam.setCreateTime(String.valueOf(getCreateTime()));
        videoParam.setShareUrl(getShareUrl());
        videoParam.setCommentCount(String.valueOf(getCommentCount()));
        videoParam.setDiggCount(String.valueOf(getDiggCount()));
        videoParam.setDownloadCount(String.valueOf(getDownloadCount()));
        videoParam.setShareCount(String.valueOf(getShareCount()));
        videoParam.setUrlList(getUrlList());
        videoParam.setUserId(getUserId());
        videoParam.setNickname(getNickname());
        return videoParam;
    }

    public VideoTable getVideoTable() {
        VideoTable videoTable = new VideoTable();
        videoTable.setVideoId(getId());
        videoTable.setTitle(getTitle());
        videoTable.setCreateTime(getCreateTime());
        videoTable.setShareUrl(getShareUrl());
        videoTable.setCommentCount(getCommentCount());
        videoTable.setDiggCount(getDiggCount());
        videoTable.setDownloadCount(getDownloadCount());
        videoTable.setShareCount(getShareCount());
        videoTable.setUrlList(getUrlList());
        videoTable.setUserId(getUserId());
        videoTable.setNickname(getNickname());
        videoTable.setUpdate(0);
        return videoTable;
    }
}