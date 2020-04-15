package com.micro.worker.inflood.inner.replace;

import com.alibaba.fastjson.JSON;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;

/**
 * @Author KiLin
 * @Time 2020/4/15 9:25
 */
public class UserProfileResponse {
    private ProfileCaution mProfileCaution;//ProfileCaution
    private UserProfile mUserProfile;//UserProfile

    public UserProfileResponse(Hook hook, Object profileResponse) {
        loadUserProfileResponse(hook, profileResponse);
    }

    public void loadUserProfileResponse(Hook hook, Object profileResponse) {
        if (Lang.isNull(profileResponse)) {
            return;
        }
        this.mProfileCaution = getProfileCaution(hook, profileResponse);
        this.mUserProfile = getUserProfile(hook, profileResponse);
    }

    public ProfileCaution getProfileCaution() {
        return mProfileCaution;
    }

    public UserProfile getUserProfile() {
        return mUserProfile;
    }

    private ProfileCaution getProfileCaution(Hook hook, Object profileResponse) {
        return new ProfileCaution(hook, hook.getField(profileResponse, "mProfileCaution"));
    }

    private UserProfile getUserProfile(Hook hook, Object profileResponse) {
        return new UserProfile(hook, hook.getField(profileResponse, "mUserProfile"));
    }

    @Override
    public String toString() {
        return "UserProfileResponse{" +
                "mProfileCaution=" + mProfileCaution.toString() +
                ", mUserProfile=" + mUserProfile.toString() +
                '}';
    }
}