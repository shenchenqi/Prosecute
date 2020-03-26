package com.micro.tremolo.inflood.inner.replace;

import com.micro.hook.config.Hook;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * @Author Kilin
 * @Date 2020/3/26 15:42
 * @Source 抖音版本960 com.ss.android.ugc.aweme.profile.model.FollowerDetail
 */
public class FollowerDetail {
    private String appName;
    private String appleId;
    private String downloadUrl;
    private int fansCount;
    private String icon;
    private String name;
    private String openUrl;
    private String packageName;

    public FollowerDetail(Hook hook, Object followerDetail) {
        loadFollowerDetail(hook, followerDetail);
    }

    public void loadFollowerDetail(Hook hook, Object followerDetail) {
        logger.d("FollowerDetail", String.format("当前抓取的数据对象是否存在[%s]", followerDetail != null));
        if (followerDetail == null) {
            return;
        }
        this.appName = getAppName(hook, followerDetail);
        this.appleId = getAppleId(hook, followerDetail);
        this.downloadUrl = getDownloadUrl(hook, followerDetail);
        this.fansCount = getFansCount(hook, followerDetail);
        this.icon = getIcon(hook, followerDetail);
        this.name = getName(hook, followerDetail);
        this.openUrl = getOpenUrl(hook, followerDetail);
        this.packageName = getPackageName(hook, followerDetail);
    }

    public String getAppName() {
        return appName;
    }

    public String getAppleId() {
        return appleId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public int getFansCount() {
        return fansCount;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    private String getAppName(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "appName");
    }

    private String getAppleId(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "appleId");
    }

    private String getDownloadUrl(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "downloadUrl");
    }

    private int getFansCount(Hook hook, Object followerDetail) {
        return hook.getIntegerField(followerDetail, "fansCount");
    }

    private String getIcon(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "icon");
    }

    private String getName(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "name");
    }

    private String getOpenUrl(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "openUrl");
    }

    private String getPackageName(Hook hook, Object followerDetail) {
        return (String) hook.getField(followerDetail, "packageName");
    }
}