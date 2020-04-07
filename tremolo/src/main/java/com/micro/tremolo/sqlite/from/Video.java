package com.micro.tremolo.sqlite.from;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.root.utils.Lang;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 16:03
 */
public class Video {
    private String id;
    private String title;
    private String createTime;
    private String shareUrl;
    private String commentCount;//评论数
    private String diggCount;//爱心数
    private String downloadCount;//下载数
    private String shareCount;//分享数
    private List<String> urlList;
    private String userId;
    private String sceUserId;
    private String nickname;

    public Video(String content) {
        JSONObject videoObject = JSON.parseObject(content);
        id = videoObject.getString("aid");
        title = videoObject.getString("desc");
        createTime = videoObject.getString("createTime");
        shareUrl = videoObject.getString("shareUrl");
        String statistics = videoObject.getString("statistics");
        if (Lang.isNotEmpty(statistics)) {
            JSONObject statisticsObject = JSON.parseObject(statistics);
            commentCount = statisticsObject.getString("commentCount");
            diggCount = statisticsObject.getString("diggCount");
            downloadCount = statisticsObject.getString("downloadCount");
            shareCount = statisticsObject.getString("shareCount");
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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getDiggCount() {
        return diggCount;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public String getShareCount() {
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
}