package com.micro.tremolo.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

import java.util.List;

public class Video {
    private UrlModel animatedCover;
    private List<Object> bitRate;//BitRate.class
    private UrlModel captionDownloadAddr;
    private UrlModel cover;
    private String dVideoModel;
    private UrlModel downloadAddr;
    private UrlModel dynamicCover;
    private boolean hasSuffixWaterMark;
    private boolean hasWaterMark;
    private int height;
    private boolean needSetCookie;
    private UrlModel newDownloadAddr;
    private UrlModel originCover;
    private VideoUrlModel playAddr;//VideoUrlModel.class
    private VideoUrlModel playAddrH265;//VideoUrlModel.class
    private String ratio;
    private UrlModel suffixLogoAddr;
    private UrlModel uiAlikeAddr;
    private int videoLength;
    private int width;

    public Video(Hook hook, Object video) {
        loadVideo(hook, video);
    }

    public void loadVideo(Hook hook, Object video) {
        if (Lang.isNull(video)) {
            return;
        }
        this.animatedCover = getAnimatedCover(hook, video);
        this.bitRate = (List<Object>) getBitRate(hook, video);
        this.captionDownloadAddr = getCaptionDownloadAddr(hook, video);
        this.cover = getCover(hook, video);
        this.dVideoModel = getdVideoModel(hook, video);
        this.downloadAddr = getDownloadAddr(hook, video);
        this.dynamicCover = getDynamicCover(hook, video);
        this.hasSuffixWaterMark = isHasSuffixWaterMark(hook, video);
        this.hasWaterMark = isHasWaterMark(hook, video);
        this.height = getHeight(hook, video);
        this.needSetCookie = isNeedSetCookie(hook, video);
        this.newDownloadAddr = getNewDownloadAddr(hook, video);
        this.originCover = getOriginCover(hook, video);
        this.playAddr = getPlayAddr(hook, video);
        this.playAddrH265 = getPlayAddrH265(hook, video);
        this.ratio = getRatio(hook, video);
        this.suffixLogoAddr = getSuffixLogoAddr(hook, video);
        this.uiAlikeAddr = getUiAlikeAddr(hook, video);
        this.videoLength = getVideoLength(hook, video);
        this.width = getWidth(hook, video);
    }

    public UrlModel getAnimatedCover() {
        return animatedCover;
    }

    public List<Object> getBitRate() {
        return bitRate;
    }

    public UrlModel getCaptionDownloadAddr() {
        return captionDownloadAddr;
    }

    public UrlModel getCover() {
        return cover;
    }

    public String getdVideoModel() {
        return dVideoModel;
    }

    public UrlModel getDownloadAddr() {
        return downloadAddr;
    }

    public UrlModel getDynamicCover() {
        return dynamicCover;
    }

    public boolean isHasSuffixWaterMark() {
        return hasSuffixWaterMark;
    }

    public boolean isHasWaterMark() {
        return hasWaterMark;
    }

    public int getHeight() {
        return height;
    }

    public boolean isNeedSetCookie() {
        return needSetCookie;
    }

    public UrlModel getNewDownloadAddr() {
        return newDownloadAddr;
    }

    public UrlModel getOriginCover() {
        return originCover;
    }

    public VideoUrlModel getPlayAddr() {
        return playAddr;
    }

    public VideoUrlModel getPlayAddrH265() {
        return playAddrH265;
    }

    public String getRatio() {
        return ratio;
    }

    public UrlModel getSuffixLogoAddr() {
        return suffixLogoAddr;
    }

    public UrlModel getUiAlikeAddr() {
        return uiAlikeAddr;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public int getWidth() {
        return width;
    }

    private UrlModel getAnimatedCover(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "animatedCover"));
    }

    private List<?> getBitRate(Hook hook, Object video) {
        return (List<?>) hook.getField(video, "bitRate");
    }

    private UrlModel getCaptionDownloadAddr(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "captionDownloadAddr"));
    }

    private UrlModel getCover(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "cover"));
    }

    private String getdVideoModel(Hook hook, Object video) {
        return (String) hook.getField(video, "dVideoModel");
    }

    private UrlModel getDownloadAddr(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "downloadAddr"));
    }

    private UrlModel getDynamicCover(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "dynamicCover"));
    }

    private boolean isHasSuffixWaterMark(Hook hook, Object video) {
        return hook.getBooleanField(video, "hasSuffixWaterMark");
    }

    private boolean isHasWaterMark(Hook hook, Object video) {
        return hook.getBooleanField(video, "hasWaterMark");
    }

    private int getHeight(Hook hook, Object video) {
        return hook.getIntegerField(video, "height");
    }

    private boolean isNeedSetCookie(Hook hook, Object video) {
        return hook.getBooleanField(video, "needSetCookie");
    }

    private UrlModel getNewDownloadAddr(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "newDownloadAddr"));
    }

    private UrlModel getOriginCover(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "originCover"));
    }

    private VideoUrlModel getPlayAddr(Hook hook, Object video) {
        return new VideoUrlModel(hook, hook.getField(video, "playAddr"));
    }

    private VideoUrlModel getPlayAddrH265(Hook hook, Object video) {
        return new VideoUrlModel(hook, hook.getField(video, "playAddrH265"));
    }

    private String getRatio(Hook hook, Object video) {
        return (String) hook.getField(video, "ratio");
    }

    private UrlModel getSuffixLogoAddr(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "suffixLogoAddr"));
    }

    private UrlModel getUiAlikeAddr(Hook hook, Object video) {
        return new UrlModel(hook, hook.getField(video, "uiAlikeAddr"));
    }

    private int getVideoLength(Hook hook, Object video) {
        return hook.getIntegerField(video, "videoLength");
    }

    private int getWidth(Hook hook, Object video) {
        return hook.getIntegerField(video, "width");
    }
}