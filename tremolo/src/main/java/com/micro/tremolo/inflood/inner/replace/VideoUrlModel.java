package com.micro.tremolo.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

public class VideoUrlModel extends UrlModel {
    private double duration;
    private String fileCheckSum;
    private boolean isH265;
    private String mDashVideoId;
    private String mDashVideoModelString;
    private boolean mVr;
    private String ratio;
    private String ratioUri;
    private String sourceId;

    public VideoUrlModel(Hook hook, Object urlModel) {
        super(hook, urlModel);
    }

    public void loadVideoUrlModel(Hook hook, Object urlModel) {
        if (Lang.isNull(urlModel)) {
            return;
        }
        this.duration = getDuration(hook, urlModel);
        this.fileCheckSum = getFileCheckSum(hook, urlModel);
        this.isH265 = isH265(hook, urlModel);
        this.mDashVideoId = getmDashVideoId(hook, urlModel);
        this.mDashVideoModelString = getmDashVideoModelString(hook, urlModel);
        this.mVr = ismVr(hook, urlModel);
        this.ratio = getRatio(hook, urlModel);
        this.ratioUri = getRatioUri(hook, urlModel);
        this.sourceId = getSourceId(hook, urlModel);
    }

    public double getDuration() {
        return duration;
    }

    public String getFileCheckSum() {
        return fileCheckSum;
    }

    public boolean isH265() {
        return isH265;
    }

    public String getmDashVideoId() {
        return mDashVideoId;
    }

    public String getmDashVideoModelString() {
        return mDashVideoModelString;
    }

    public boolean ismVr() {
        return mVr;
    }

    public String getRatio() {
        return ratio;
    }

    public String getRatioUri() {
        return ratioUri;
    }

    public String getSourceId() {
        return sourceId;
    }

    private double getDuration(Hook hook, Object urlModel) {
        return hook.getDoubleField(urlModel, "duration");
    }

    private String getFileCheckSum(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "fileCheckSum");
    }

    private boolean isH265(Hook hook, Object urlModel) {
        return hook.getBooleanField(urlModel, "isH265");
    }

    private String getmDashVideoId(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "mDashVideoId");
    }

    private String getmDashVideoModelString(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "mDashVideoModelString");
    }

    private boolean ismVr(Hook hook, Object urlModel) {
        return hook.getBooleanField(urlModel, "mVr");
    }

    private String getRatio(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "ratio");
    }

    private String getRatioUri(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "ratioUri");
    }

    private String getSourceId(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "sourceId");
    }
}
