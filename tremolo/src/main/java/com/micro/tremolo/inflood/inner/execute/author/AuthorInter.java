package com.micro.tremolo.inflood.inner.execute.author;

import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.inflood.inner.replace.User;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:26
 */
public interface AuthorInter extends PluginInter {

    void loadUser(User user);
}