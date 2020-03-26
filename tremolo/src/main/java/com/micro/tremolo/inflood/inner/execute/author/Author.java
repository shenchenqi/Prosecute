package com.micro.tremolo.inflood.inner.execute.author;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * @Author Kilin
 * @Date 2020/3/24 9:25
 */
public class Author extends Plugin<AuthorPresenter, AuthorInter> implements AuthorInter {

    public Author(Hook hook, Context context) throws Throwable {
        super(hook, context);
        logger.i("博主初始化");
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