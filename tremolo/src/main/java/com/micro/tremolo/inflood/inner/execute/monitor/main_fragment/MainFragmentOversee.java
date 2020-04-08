package com.micro.tremolo.inflood.inner.execute.monitor.main_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.AutoUiControl;
import com.micro.tremolo.inflood.inner.execute.RequestApi;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.network.UploadNet;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:18
 */
public class MainFragmentOversee extends Plugin<MainFragmentPresenter, MainFragmentInter> implements MainFragmentInter {

    private final AutoUiControl autoUiControl;
    private final RequestApi requestApi;

    public MainFragmentOversee(Hook hook, Context context, AutoUiControl autoUiControl, RequestApi requestApi) throws Throwable {
        super(hook, context);
        this.autoUiControl = autoUiControl;
        this.requestApi = requestApi;
    }

    @Override
    protected MainFragmentPresenter getPresenter() {
        return new MainFragmentPresenter();
    }

    @Override
    public void monitor() {
        String MainFragment = TremoloParam.AWEME_MAIN_FRAGMENT_CLASS;
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onViewCreated");
                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                monitorLogger.i("Load Main Fragment View");
                View view = (View) param.getArgs()[0];
                autoUiControl.setMainFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onVideoPageChangeEvent");
                Object videoInfo = hook.getField(param.getArgs()[0], TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                final Aweme aweme = new Aweme(hook, videoInfo);
                presenter.getClazz().videoInfo(aweme);
                monitorLogger.i("视频类型: " + aweme.getAwemeType() + " , " + aweme.getAdAwemeSource() + " , " + aweme.getFeedCount() + " , " + aweme.getAdLinkType());
                autoUiControl.setUserProfile(false);
                setHandlerPost(second * 5, () -> {
                    monitorLogger.i("是否是视频主： " + autoUiControl.isUserProfile());
                    if (autoUiControl.isUserProfile()) {
                        requestApi.requestProfileApi(aweme.getAuthor().getSecUid(), presenter.getClazz());
                    } else {
                        autoUiControl.autoChangeVideo();
                    }
                });
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("User Profile Fragment a_(User)");
                autoUiControl.setUserProfile(true);
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
    }

    private void setHandlerPost(long time, Runnable runnable) {
        presenter.setHandlerPost(time, getIContext().getMainLooper(), runnable);
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }

    @Override
    public void profileInfo(Author author) {
        UserModelTable userTable = presenter.loadUserTable(author);
        if (presenter.isRead(Integer.parseInt(userTable.getFansCount()))) {
            monitorLogger.i("视频主 粉丝大于1万 上传信息");
            UploadNet.uploadUser(userTable);
            requestApi.requestFeedVideoApi(true, author.getUserId(), author.getSceUserId(), 0, presenter.getClazz());
        } else {
            monitorLogger.i("视频主 粉丝小于1万 刷新视频");
            autoUiControl.autoChangeVideo();
        }
    }

    @Override
    public void videoListInfo(List<Video> videos) {
        monitorLogger.i("视频列表： " + videos.size());
        if (videos.isEmpty()) {
            autoUiControl.autoChangeVideo();
        } else {
            List<VideoModelTable> videoTableList = new ArrayList<>();
            for (Video video : videos) {
                videoTableList.add(presenter.loadVideoTable(video));
            }
            if (!videoTableList.isEmpty()) {
                VideoListModelTable videoListModelTable = new VideoListModelTable();
                videoListModelTable.setVideoModelTableList(videoTableList);
                UploadNet.uploadVideoList(videoListModelTable);
                Video video = videos.get(videos.size() - 1);
                long time = video.getCreateTime().length() == 10 ? Long.parseLong(video.getCreateTime() + "000") : Long.parseLong(video.getCreateTime());
                requestApi.requestFeedVideoApi(false, video.getUserId(), video.getSceUserId(), time, presenter.getClazz());
            }
        }
    }

    @Override
    public void videoInfo(Aweme aweme) {
        VideoModelTable videoTable = presenter.loadVideoTable(aweme);
        UploadNet.uploadVideo(videoTable);
    }
}