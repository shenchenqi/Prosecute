package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.replace.Aweme;
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
 * @Time 2020/4/9 11:15
 */
public abstract class Oversee extends Plugin<OverseePresenter, OverseeInter> implements OverseeInter {

    protected Oversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
        presenter.setHook(hook);
        presenter.initCreate();
    }

    @Override
    protected OverseePresenter getPresenter() {
        return OverseePresenter.getInstance();
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }

    @Override
    public void videoInfo(Aweme aweme) {
        VideoModelTable videoTable = presenter.loadVideoTable(aweme);
        UploadNet.uploadVideo(videoTable);
    }

    @Override
    public void profileInfo(Author author) {
        UserModelTable userTable = presenter.loadUserTable(author);
        presenter.setRead(Integer.parseInt(userTable.getFansCount()));
        if (presenter.isRead()) {
            monitorLogger.i("视频主 粉丝大于1万 上传信息");
            UploadNet.uploadUser(userTable);
            presenter.apiLoadVideoList(true, 0);
        } else {
            monitorLogger.i("视频主 粉丝小于1万 刷新视频");
            presenter.nextVideo();
        }
    }

    @Override
    public void videoListInfo(List<Video> videos) {
        monitorLogger.i("视频列表： " + videos.size());
        if (videos.isEmpty()) {
            presenter.nextVideo();
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
                presenter.apiLoadVideoList(false, time);
            }
        }
    }
}