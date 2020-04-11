package com.micro.tremolo.inflood.inner.replace;

import com.micro.hook.config.Hook;

import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/25 11:01
 * @Source 抖音版本960 com.ss.android.ugc.aweme.commerce.model.b
 */
public class CommerceActivityStruct {
    private int actType;
    private String authorId;
    private UrlModel clickTrackUrlList;
    private long endTime;
    private String enterFrom;
    private String groupId;
    private UrlModel image;
    private String jumpOpenUrl;
    private String jumpWebUrl;
    private long startTime;
    private List<Object> timeRange;//a.class
    private String title;
    private UrlModel trackUrlList;

    public CommerceActivityStruct(Hook hook, Object commerce) {
        loadCommerceActivityStruct(hook, commerce);
    }

    public void loadCommerceActivityStruct(Hook hook, Object commerce) {
        if (commerce == null) {
            return;
        }
        this.actType = getActType(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("actType[%s]", actType));
        this.authorId = getAuthorId(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("authorId[%s]", authorId));
        this.clickTrackUrlList = new UrlModel(hook, getClickTrackUrlList(hook, commerce));
        //logger.d("CommerceActivityStruct", String.format("clickTrackUrlList[%s]", clickTrackUrlList));
        this.endTime = getEndTime(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("endTime[%s]", endTime));
        this.enterFrom = getEnterFrom(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("enterFrom[%s]", enterFrom));
        this.groupId = getGroupId(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("groupId[%s]", groupId));
        this.image = new UrlModel(hook, getImage(hook, commerce));
        //logger.d("CommerceActivityStruct", String.format("image[%s]", image));
        this.jumpOpenUrl = getJumpOpenUrl(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("jumpOpenUrl[%s]", jumpOpenUrl));
        this.jumpWebUrl = getJumpWebUrl(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("jumpWebUrl[%s]", jumpWebUrl));
        this.startTime = getStartTime(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("startTime[%s]", startTime));
        this.timeRange = (List<Object>) getTimeRange(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("timeRange[%s]", timeRange));
        this.title = getTitle(hook, commerce);
        //logger.d("CommerceActivityStruct", String.format("title[%s]", title));
        this.trackUrlList = new UrlModel(hook, getTrackUrlList(hook, commerce));
        //logger.d("CommerceActivityStruct", String.format("trackUrlList[%s]", trackUrlList));
    }

    public int getActType() {
        return actType;
    }

    public String getAuthorId() {
        return authorId;
    }

    public UrlModel getClickTrackUrlList() {
        return clickTrackUrlList;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getEnterFrom() {
        return enterFrom;
    }

    public String getGroupId() {
        return groupId;
    }

    public UrlModel getImage() {
        return image;
    }

    public String getJumpOpenUrl() {
        return jumpOpenUrl;
    }

    public String getJumpWebUrl() {
        return jumpWebUrl;
    }

    public long getStartTime() {
        return startTime;
    }

    public List<Object> getTimeRange() {
        return timeRange;
    }

    public String getTitle() {
        return title;
    }

    public UrlModel getTrackUrlList() {
        return trackUrlList;
    }

    private int getActType(Hook hook, Object commerce) {
        return hook.getIntegerField(commerce, "actType");
    }

    private String getAuthorId(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "authorId");
    }

    private Object getClickTrackUrlList(Hook hook, Object commerce) {
        return hook.getField(commerce, "clickTrackUrlList");
    }

    private long getEndTime(Hook hook, Object commerce) {
        return hook.getLongField(commerce, "endTime");
    }

    private String getEnterFrom(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "enterFrom");
    }

    private String getGroupId(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "groupId");
    }

    private Object getImage(Hook hook, Object commerce) {
        return hook.getField(commerce, "image");
    }

    private String getJumpOpenUrl(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "jumpOpenUrl");
    }

    private String getJumpWebUrl(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "jumpWebUrl");
    }

    private long getStartTime(Hook hook, Object commerce) {
        return hook.getLongField(commerce, "startTime");
    }

    private List<?> getTimeRange(Hook hook, Object commerce) {
        return (List<?>) hook.getField(commerce, "timeRange");
    }

    private String getTitle(Hook hook, Object commerce) {
        return (String) hook.getField(commerce, "title");
    }

    private Object getTrackUrlList(Hook hook, Object commerce) {
        return hook.getField(commerce, "trackUrlList");
    }
}