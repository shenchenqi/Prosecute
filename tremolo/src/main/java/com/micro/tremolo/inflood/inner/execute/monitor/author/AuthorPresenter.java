package com.micro.tremolo.inflood.inner.execute.monitor.author;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.ApiService;
import com.micro.tremolo.inflood.DataBroadcast;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.rep.AppApiResponseBase;
import com.micro.tremolo.rep.entity.EmptyEntity;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.so1spms.module.rpc.RPCApiFactory;
import com.so1spms.module.rpc.callback.BaseCallback;

import retrofit2.Call;
import retrofit2.Response;

import static com.micro.tremolo.inflood.inner.execute.Deploy.monitorLogger;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:26
 */
public class AuthorPresenter extends PluginPresenter<AuthorInter> {

    @Override
    public void onAttached() {
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
        DataBroadcast.sendUser(getContext(), userTable);
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
        /*if (userTable.getUpdate() == 0) {
            userTable.setUpdate(1);
            monitorLogger.d(String.format("视频用户[%s], Map[%s]", userTable.toString(), userTable.getUserMap()));
        }*/
        return userTable;
    }
}