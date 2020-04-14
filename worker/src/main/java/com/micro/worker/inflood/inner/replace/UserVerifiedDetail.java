package com.micro.worker.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

/**
 * @Author KiLin
 * @Time 2020/4/14 17:32
 */
public class UserVerifiedDetail {
    private String mDescription;
    private int mIconType = 1;
    private boolean mIsMusician;
    private int mType = 0;

    public UserVerifiedDetail(Hook hook, Object userVerified) {
        loadUserVerifiedDetail(hook, userVerified);
    }

    public void loadUserVerifiedDetail(Hook hook, Object userVerified) {
        if (Lang.isNull(userVerified)) {
            return;
        }
        this.mDescription = getDescription(hook, userVerified);
        this.mIconType = getIconType(hook, userVerified);
        this.mIsMusician = isIsMusician(hook, userVerified);
        this.mType = getType(hook, userVerified);
    }

    public String getDescription() {
        return mDescription;
    }

    public int getIconType() {
        return mIconType;
    }

    public boolean isIsMusician() {
        return mIsMusician;
    }

    public int getType() {
        return mType;
    }

    private String getDescription(Hook hook, Object userVerified) {
        return (String) hook.getField(userVerified, "mDescription");
    }

    private int getIconType(Hook hook, Object userVerified) {
        return hook.getIntegerField(userVerified, "mIconType");
    }

    private boolean isIsMusician(Hook hook, Object userVerified) {
        return hook.getBooleanField(userVerified, "mIsMusician");
    }

    private int getType(Hook hook, Object userVerified) {
        return hook.getIntegerField(userVerified, "mType");
    }
}