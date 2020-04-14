package com.micro.worker.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

/**
 * @Author KiLin
 * @Time 2020/4/14 17:21
 */
public class UserOwnerCount {
    private int mArticlePublic = -1;
    private int mCollection = -1;
    private int mFan = -1;//粉丝
    private String mFansCountText;
    private int mFollow = -1;//关注
    private int mLike = -1;
    private int mMoment = -1;//动态
    private int mPhoto = -1;//作品
    private int mPrivatePhoto = -1;
    private int mPublicPhoto = -1;
    private int mSong = -1;

    public UserOwnerCount(Hook hook, Object userOwner) {
        loadUserOwnerCount(hook, userOwner);
    }

    public void loadUserOwnerCount(Hook hook, Object userOwner) {
        if (Lang.isNull(userOwner)) {
            return;
        }
        this.mArticlePublic = getArticlePublic(hook, userOwner);
        this.mCollection = getCollection(hook, userOwner);
        this.mFan = getFan(hook, userOwner);
        this.mFansCountText = getFansCountText(hook, userOwner);
        this.mFollow = getFollow(hook, userOwner);
        this.mLike = getLike(hook, userOwner);
        this.mMoment = getMoment(hook, userOwner);
        this.mPhoto = getPhoto(hook, userOwner);
        this.mPrivatePhoto = getPrivatePhoto(hook, userOwner);
        this.mPublicPhoto = getPublicPhoto(hook, userOwner);
        this.mSong = getSong(hook, userOwner);
    }

    public int getArticlePublic() {
        return mArticlePublic;
    }

    public int getCollection() {
        return mCollection;
    }

    public int getFan() {
        return mFan;
    }

    public String getFansCountText() {
        return mFansCountText;
    }

    public int getFollow() {
        return mFollow;
    }

    public int getLike() {
        return mLike;
    }

    public int getMoment() {
        return mMoment;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public int getmPrivatePhoto() {
        return mPrivatePhoto;
    }

    public int getPublicPhoto() {
        return mPublicPhoto;
    }

    public int getSong() {
        return mSong;
    }

    private int getArticlePublic(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mArticlePublic");
    }

    private int getCollection(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mCollection");
    }

    private int getFan(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mFan");
    }

    private String getFansCountText(Hook hook, Object userOwner) {
        return (String) hook.getField(userOwner, "mFansCountText");
    }

    private int getFollow(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mFollow");
    }

    private int getLike(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mLike");
    }

    private int getMoment(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mMoment");
    }

    private int getPhoto(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mPhoto");
    }

    private int getPrivatePhoto(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mPrivatePhoto");
    }

    private int getPublicPhoto(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mPublicPhoto");
    }

    private int getSong(Hook hook, Object userOwner) {
        return hook.getIntegerField(userOwner, "mSong");
    }
}