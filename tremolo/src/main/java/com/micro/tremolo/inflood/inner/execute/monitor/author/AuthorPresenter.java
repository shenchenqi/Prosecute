package com.micro.tremolo.inflood.inner.execute.monitor.author;

import com.alibaba.fastjson.JSON;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.network.OkHttp3;
import com.micro.network.http3.filter.BaseBean;
import com.micro.root.utils.Lang;
import com.micro.tremolo.ApiService;
import com.micro.tremolo.inflood.inner.replace.UrlModel;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.sqlite.table.UserModelTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.micro.tremolo.inflood.inner.execute.Deploy.monitorLogger;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:26
 */
public class AuthorPresenter extends PluginPresenter<AuthorInter> {

    private static List<Map<String, Object>> authors = new ArrayList<>();

    @Override
    public void onAttached() {
        setHandlerPost(0, null, new Runnable() {
            @Override
            public void run() {
                monitorLogger.d("视频用户数：" + authors.size());
                for (Map<String, Object> map : authors) {
                    uploadTremolo(map);
                    authors.remove(map);
                }
                handler.postDelayed(this::run, AuthorInter.second * 10);
            }
        });
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
        authors.add(userTable.getUserMap());
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
        //monitorLogger.d(userTable.toString());
        return userTable;
    }

    private void uploadTremolo(final Map<String, Object> data) {
        monitorLogger.d("视频用户上传");
        OkHttp3.getInstance(getContext()).create(ApiService.class)
                .uploadTremolo(data)
                .subscribe(objectBaseBean -> monitorLogger.i(String.format("抖音用户[%s][%s]", objectBaseBean.getCode(), objectBaseBean.getMessage())),
                        throwable -> monitorLogger.e(throwable, "抖音用户上传报错")).dispose();
    }
}