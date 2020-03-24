package com.micro.tremolo.inflood.execute.item.author;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:25
 */
public class Author extends Plugin<AuthorPresenter, AuthorInter> implements AuthorInter {

    public Author(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    protected AuthorPresenter getPresenter() {
        return new AuthorPresenter();
    }

    @Override
    public void monitor(Hook hook) {

    }

    @Override
    protected void execute() {
        monitor(getHook());
    }
}