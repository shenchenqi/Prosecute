package com.micro.tremolo.sqlite.table;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class VideoModelTable implements Serializable/*extends DataTable*/ {
    private String id;
    private String title;
    private String createTime;
    private String shareUrl;
    private String commentCount;//评论数
    private String diggCount;//爱心数
    private String downloadCount;//下载数
    private String shareCount;//分享数
    private List<String> urlList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(String diggCount) {
        this.diggCount = diggCount;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
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
     * 用户为空 = -3; 视频链接为空 = -2; 统计数据为空 = -1; 已准备 = 0; 未上传 = 1; 上传 = 2;
     */
    /*private int update;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }*/

    @Override
    public String toString() {
        return "VideoModelTable{" +
                "\nid='" + id + '\'' +
                ", \ntitle='" + title + '\'' +
                ", \ncreateTime=" + createTime +
                ", \nshareUrl='" + shareUrl + '\'' +
                ", \ncommentCount=" + commentCount +
                ", \ndiggCount=" + diggCount +
                ", \ndownloadCount=" + downloadCount +
                ", \nshareCount=" + shareCount +
                ", \nurlList=" + JSON.toJSONString(urlList) +
                ", \nuserId='" + userId + '\'' +
                ", \nnickname='" + nickname + '\'' +
                "\n}";
    }
}