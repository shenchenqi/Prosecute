package com.micro.tremolo.model.table;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 11:17
 */
public class VideoTable extends DataTable {
    private String videoId;
    private String title;
    private long createTime;
    private String shareUrl;
    private int commentCount;//评论数
    private int diggCount;//爱心数
    private int downloadCount;//下载数
    private int shareCount;//分享数
    private String urlList;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        this.diggCount = diggCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = JSON.toJSONString(urlList);
    }

    private String userId;
    private String nickname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 已准备 = 0; 未上传 = 1; 上传 = 2;
     */
    private int update;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}