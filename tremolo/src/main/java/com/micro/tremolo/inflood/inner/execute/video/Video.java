package com.micro.tremolo.inflood.inner.execute.video;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.inflood.version.TremoloParam;

import java.util.List;

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
                presenter.obtainVideoItem(new Aweme(hook, videoInfo));
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_VIDEO_CALL_CLASS, TremoloParam.AWEME_PROFILE_VIDEO_CALL_ITEMS_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    logger.e("当前工厂未实例");
                    return;
                }
                List<Object> list = (List<Object>) param.getResult();
                presenter.obtainVideoList(hook, list);
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

    public static void loadStaticVideo(Aweme aweme){
        AwemeStatistics statistics = aweme.getStatistics();
        logger.i(String.format("当前视频信息：{视频Id[%s], 标题[%s], 创建时间[%s], 分享链接[%s], 评论数[%s], 爱心数[%s], 下载数[%s], 分享数[%s]}",
                aweme.getAid(), aweme.getDesc(), aweme.getCreateTime(), aweme.getShareUrl(),
                statistics.getCommentCount(), statistics.getDiggCount(), statistics.getDownloadCount(), statistics.getShareCount()));
        com.micro.tremolo.inflood.inner.replace.Video video = aweme.getVideo();
        VideoUrlModel videoUrlModel = video.getPlayAddr();
        List<String> urlList = videoUrlModel.getUrlList();
        logger.i("视频链接列表：" + JSON.toJSONString(urlList));
    }

    @Override
    public void loadVideoItem(Aweme aweme) {
        presenter.saveVideoTableItem(aweme);
    }

    @Override
    public void loadVideoList(List<Aweme> awemeList) {
        presenter.saveVideoTableList(awemeList);
    }

    public void callVoidList(boolean isFirst, String userId, long time, int limit, String key) {
        this.pushVoid(isFirst, userId, 0, time, limit, key);
    }

    @Override
    public void pushVoid(boolean isFirst, String userId, int type, long time, int limit, String key) {
        Object apiVideo = getHook().newInstance(TremoloParam.AWEME_PROFILE_VIDEO_CALL_CLASS);
        getHook().callMethod(apiVideo, TremoloParam.AWEME_PROFILE_VIDEO_CALL_REQUEST_METHOD, isFirst, userId, type, time, limit, key);
    }
}