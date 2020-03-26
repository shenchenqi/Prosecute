package com.micro.tremolo.inflood.inner.execute.video;

import android.content.Context;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.author.Author;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.inner.execute.Deploy.logger;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:41
 */
public class Video extends Plugin<VideoPresenter, VideoInter> implements VideoInter {

    public Video(Hook hook, Context context) throws Throwable {
        super(hook, context);
        logger.i("视频初始化");
    }

    @Override
    protected VideoPresenter getPresenter() {
        return new VideoPresenter();
    }

    @Override
    public void monitor(final Hook hook) {
        hook.methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    logger.e("当前工厂未实例");
                    return;
                }
                presenter.setItemVideoInfo(param.getArgs()[0]);
                Object videoInfo = hook.getField(presenter.getItemVideoInfo(), TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                presenter.obtainVideo(new Aweme(hook, videoInfo));
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
        Aweme.update(hook, new Aweme.Callback() {
            @Override
            public void loadData(Aweme aweme, String msg) {
                logger.i(String.format("视频信息 [%s]", msg));
                presenter.obtainVideo(aweme);
            }
        });
    }

    @Override
    protected String taskName() {
        return Video.class.getSimpleName();
    }

    @Override
    protected void process() {

    }

    @Override
    protected void error(Throwable throwable) {

    }

    @Override
    public void finish(boolean success) {
        logger.i(String.format("视频任务[%s]", success));
    }

    @Override
    public void loadVideo(Aweme aweme) {
        AwemeStatistics statistics = aweme.getStatistics();
        logger.i(String.format("当前视频信息：{视频Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s]}",
                aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount()));
        Author.loadStaticUser(aweme.getAuthor());
    }
}