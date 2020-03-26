package com.micro.tremolo.inflood.inner.execute.author;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.tremolo.inflood.inner.replace.User;

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

    public void obtainAuthor(User user){
        getClazz().loadUser(user);
    }
}