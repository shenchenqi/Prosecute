package com.micro.tremolo.inflood.inner.execute.author;

import android.os.Handler;
import android.view.View;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.execute.Deploy;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.sqlite.table.UserModelTable;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:26
 */
public class AuthorPresenter extends PluginPresenter<AuthorInter> {

    private Handler handler;

    @Override
    public void onAttached() {
        handler = getHandler(getContext().getMainLooper());
    }

    private Object authorInfo;

    public Object getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(Object authorInfo) {
        this.authorInfo = authorInfo;
    }

    public void obtainAuthor(User user) {
        getClazz().loadUser(user);
    }

    public synchronized void saveUserTableItem(User user) {
        UserModelTable userTable = loadUserTable(user);
        //Deploy.logger.d(userTable.toString());
    }

    private synchronized UserModelTable loadUserTable(User user) {
        UserModelTable userTable = new UserModelTable();
        userTable.setUserId(user.getUid());
        userTable.setNickname(user.getNickname());
        userTable.setTremoloId(user.getUniqueId());
        userTable.setTremoloNumberId(user.getShortId());
        userTable.setBirthday(user.getBirthday());
        userTable.setCity(user.getCity());
        userTable.setCountry(user.getCountry());
        userTable.setDistrict(user.getDistrict());
        userTable.setSchoolName(user.getSchoolName());
        userTable.setSignature(user.getSignature());
        userTable.setCustomVerify(user.getCustomVerify());
        userTable.setEnterpriseVerify(user.getEnterpriseVerifyReason());
        userTable.setRequestId(user.getRequestId());
        userTable.setFollowingCount(user.getFollowingCount());
        userTable.setAwemeCount(user.getAwemeCount());
        userTable.setMovingCount(user.getDongtai_count());
        userTable.setFansCount(user.getFansCount());
        userTable.setFavoritingCount(user.getFavoritingCount());
        UrlModel avatarLarger = user.getAvatarLarger();
        if (avatarLarger != null) {
            userTable.setAvatarList(avatarLarger.getUrlList());
            userTable.setUri(avatarLarger.getUri());
            userTable.setUrlKey(avatarLarger.getUrlKey());
        }
        UrlModel avatarMedium = user.getAvatarMedium();
        if (avatarMedium != null) {
            userTable.setAvatarMediumList(avatarMedium.getUrlList());
            if (Lang.isEmpty(userTable.getUri())) {
                userTable.setUri(avatarMedium.getUri());
            }
            if (Lang.isEmpty(userTable.getUrlKey())) {
                userTable.setUri(avatarMedium.getUrlKey());
            }
        }
        UrlModel avatarThumb = user.getAvatarThumb();
        if (avatarThumb != null) {
            userTable.setAvatarThumbList(avatarThumb.getUrlList());
            if (Lang.isEmpty(userTable.getUri())) {
                userTable.setUri(avatarThumb.getUri());
            }
            if (Lang.isEmpty(userTable.getUrlKey())) {
                userTable.setUri(avatarThumb.getUrlKey());
            }
        }
        return userTable;
    }

    protected void clickBackView(View rootView) {//com.ss.android.ugc.aweme:id/ko 0x7f0701ae (2131165614)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                Deploy.logger.d(String.format("rootView:[%s]", rootView));
                /*if (isClickText(rootView, "返回")) {

                }*/
            }
        }, AuthorInter.second * 15);
    }
}