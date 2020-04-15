package com.micro.worker.inflood.inner.replace;

import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

/**
 * @Author KiLin
 * @Time 2020/4/15 9:29
 */
public class ProfileCaution {

    private String mDescription;
    private String mPopupTitle;
    private String mTitle;
    private int mType;

    public ProfileCaution(Hook hook, Object profile) {
        loadProfileCaution(hook, profile);
    }

    public void loadProfileCaution(Hook hook, Object profile) {
        if (Lang.isNull(profile)) {
            return;
        }
        this.mDescription = getDescription(hook, profile);
        this.mPopupTitle = getPopupTitle(hook, profile);
        this.mTitle = getTitle(hook, profile);
        this.mType = getType(hook, profile);
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPopupTitle() {
        return mPopupTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getType() {
        return mType;
    }

    private String getDescription(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mDescription");
    }

    private String getPopupTitle(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mPopupTitle");
    }

    private String getTitle(Hook hook, Object profile) {
        return (String) hook.getField(profile, "mTitle");
    }

    private int getType(Hook hook, Object profile) {
        return hook.getIntegerField(profile, "mType");
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "\nmDescription='" + mDescription + '\'' +
                ", \nmPopupTitle='" + mPopupTitle + '\'' +
                ", \nmTitle='" + mTitle + '\'' +
                ", \nmType=" + mType +
                "\n}";
    }
}