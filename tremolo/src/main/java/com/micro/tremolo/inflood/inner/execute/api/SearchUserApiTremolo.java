package com.micro.tremolo.inflood.inner.execute.api;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.hook.config.Hook;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.model.from.Author;

import java.util.List;

import static com.micro.tremolo.Const.taskLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 10:54
 */
public class SearchUserApiTremolo extends TremoloBaseApi {

    private static SearchUserApiTremolo mSearchUserApi;

    public static void setInstance(Hook hook, Context context) {
        if (mSearchUserApi == null) {
            mSearchUserApi = new SearchUserApiTremolo(hook, context);
        }
    }

    public static void loadApi(String search, Callback callback) {
        mSearchUserApi.loadSearchUserApi(search, callback);
    }

    private SearchUserApiTremolo(Hook hook, Context context) {
        super(hook, context);
    }

    private void loadSearchUserApi(final String search, final Callback callback) {
        loadSearchUserApi(search, 0, "", new BaseCallback() {
            @Override
            public void success(boolean isFirst, String data) {
                if (Lang.isEmpty(data)) {
                    taskLogger.e("抖音搜索用户接口 返回数据 为空");
                    callback.finish();
                } else {
                    JSONObject dataObject = getJsonObject(data);
                    if (Lang.isNull(dataObject)) {
                        taskLogger.e("抖音搜索用户接口 返回数据 解析出错");
                        callback.finish();
                    } else {
                        Author author = getAuthor(search, (List<Object>) dataObject.get("userList"));
                        if (Lang.isNotNull(author)) {
                            taskLogger.d("抖音搜索用户接口 已成功匹配");
                            callback.complete(author);
                            return;
                        }
                        if (dataObject.getBooleanValue("hasMore")) {
                            if (isFirst) {
                                setRequestId(dataObject.getString("requestId"));
                            }
                            loadSearchUserApi(search, dataObject.getLongValue("cursor"), getRequestId(), this);
                        } else {
                            taskLogger.e("抖音搜索用户接口 未成功匹配");
                            callback.finish();
                        }
                    }
                }
            }

            @Override
            public void fail(Throwable e, String msg) {
                taskLogger.e(e, msg);
                callback.finish();
            }
        });
    }

    private void loadSearchUserApi(final String search, final long cursor, final String requestId, final BaseCallback callback) {
        run(second * 5, () -> {
            try {
                Object object;
                boolean isFirst;
                if (Lang.isEmpty(requestId)) {
                    object = getSearchUserApi(search, 0, 10, 0, "");
                    isFirst = true;
                } else {
                    object = getSearchUserApi(search, cursor, 10, 1, requestId);
                    isFirst = false;
                }
                callback.success(isFirst, getJsonString(object));
            } catch (Throwable e) {
                callback.fail(e, "调用搜索用户接口 报错: ");
            }
        });
    }

    private Object getSearchUserApi(String search, long size, int limit, int count, String requestId) {
        taskLogger.d(String.format("调用搜索用户接口,猜数[%s, %s, %s, %s, %s]", search, size, limit, count, requestId));
        if (Lang.isEmpty(search)) {
            return "";
        }
        return hook.callStaticMethod(TremoloParam.AWEME_PROFILE_SEARCH_USER_API_CLASS, TremoloParam.AWEME_PROFILE_SEARCH_USER_USER_METHOD, search, size, limit, count, 0, "", requestId, 1);
    }

    private synchronized Author getAuthor(String search, List<Object> userList) {
        for (Object user : userList) {
            JSONObject userObject = JSON.parseObject(JSON.toJSONString(user));
            Author author = new Author(userObject.getString("user"));
            if (Lang.isEquals(search, author.getUserId())) {
                return author;
            }
            if (Lang.isEquals(search, author.getTremoloId())) {
                return author;
            }
            if (Lang.isEquals(search, author.getTremoloNumberId())) {
                return author;
            }
            if (Lang.isEquals(search, author.getNickname())) {
                return author;
            }
        }
        return null;
    }

    private String requestId;

    private String getRequestId() {
        return requestId;
    }

    private void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public interface Callback {

        void complete(Author author);

        void finish();
    }
}