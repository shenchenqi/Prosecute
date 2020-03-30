package com.micro.tremolo.inflood.inner.execute.video;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;
import com.micro.root.utils.Lang;
import com.micro.tremolo.inflood.inner.execute.Deploy;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.inner.replace.AwemeStatistics;
import com.micro.tremolo.inflood.inner.replace.User;
import com.micro.tremolo.inflood.inner.replace.VideoUrlModel;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import java.util.ArrayList;
import java.util.List;

import static com.micro.tremolo.inflood.inner.execute.Deploy.logger;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:41
 */
public class VideoPresenter extends PluginPresenter<VideoInter> {

    private Handler handler;

    @Override
    public void onAttached() {
        handler = getHandler(getContext().getMainLooper());
    }

    private Object itemVideoInfo;

    public Object getItemVideoInfo() {
        return itemVideoInfo;
    }

    public void setItemVideoInfo(Object itemVideoInfo) {
        this.itemVideoInfo = itemVideoInfo;
    }

    public void obtainVideoItem(Aweme aweme) {
        getClazz().loadVideoItem(aweme);
    }

    public void obtainVideoList(Hook hook, List<Object> list) {
        if (list != null) {
            List<Aweme> awemeList = new ArrayList<>();
            for (Object object : list) {
                Aweme aweme = new Aweme(hook, object);
                awemeList.add(aweme);
            }
            getClazz().loadVideoList(awemeList);
        }
    }

    public void saveVideoTableList(List<Aweme> awemeList) {
        for (Aweme aweme : awemeList) {
            saveVideoTableItem(aweme);
        }
    }

    public synchronized void saveVideoTableItem(Aweme aweme) {
        VideoModelTable videoTable = loadVideoTable(aweme);
        //Deploy.logger.d(videoTable.toString());
    }

    private synchronized VideoModelTable loadVideoTable(Aweme aweme) {
        VideoModelTable videoTable = new VideoModelTable();
        videoTable.setId(aweme.getAid());
        videoTable.setTitle(aweme.getDesc());
        videoTable.setCreateTime(aweme.getCreateTime());
        videoTable.setShareUrl(aweme.getShareUrl());
        AwemeStatistics statistics = aweme.getStatistics();
        if (statistics != null) {
            videoTable.setCommentCount(statistics.getCommentCount());
            videoTable.setDiggCount(statistics.getDiggCount());
            videoTable.setDownloadCount(statistics.getDownloadCount());
            videoTable.setShareCount(statistics.getShareCount());
        } else {
            videoTable.setUpdate(-1);
        }
        com.micro.tremolo.inflood.inner.replace.Video video = aweme.getVideo();
        if (video != null) {
            VideoUrlModel videoUrlModel = video.getPlayAddr();
            if (videoUrlModel != null) {
                videoTable.setUrlList(videoUrlModel.getUrlList());
            } else {
                videoTable.setUpdate(-2);
            }
        } else {
            videoTable.setUpdate(-2);
        }
        User user = aweme.getAuthor();
        if (user != null) {
            videoTable.setUserId(user.getUid());
            videoTable.setNickname(user.getNickname());
        } else {
            videoTable.setUpdate(-3);
        }
        if (videoTable.getUpdate() == 0) {
            videoTable.setUpdate(1);
        }
        return videoTable;
    }

    public void clickAttentionView(final View rootView) {//com.ss.android.ugc.aweme:id/ewo 0x7f071e1d (2131172893)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                TextView attentionTv = bindView(rootView, 2131172893);
                logger.e("关注按钮是否绑定 : " + (attentionTv != null));
                if (attentionTv != null) {
                    attentionTv.performClick();
                }
            }
        }, VideoInter.second * 10);
    }

    public void clickRecommendView(final View rootView) {//com.ss.android.ugc.aweme:id/c_u 0x7f071022 (2131169314)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                TextView recommendTv = bindView(rootView, 2131169314);
                logger.e("推荐按钮是否绑定 : " + (recommendTv != null));
                if (recommendTv != null) {
                    recommendTv.performClick();
                }
            }
        }, VideoInter.second * 10);
    }

    public void clickSearchView(final View rootView) {//com.ss.android.ugc.aweme:id/avg 0x7f070890 (2131167376)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                LinearLayout searchBt = bindView(rootView, 2131167376);
                logger.e("查询按钮是否绑定 : " + (searchBt != null));
                if (searchBt != null) {
                    searchBt.performClick();
                }
            }
        }, VideoInter.second * 10);
    }

    public void clickLiveView(final View rootView) {//com.ss.android.ugc.aweme:id/ey2  0x7f071e50 (2131172944)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                TextView liveTv = bindView(rootView, 2131172944);
                logger.e("直播按钮是否绑定 : " + (liveTv != null));
                if (liveTv != null) {
                    liveTv.performClick();
                }
            }
        }, VideoInter.second * 10);
    }

    public void clickUserView(final View rootView) {//com.ss.android.ugc.aweme:id/fbr 0x7f072070 (2131173488)
        handlerPost(handler, new Runnable() {
            @Override
            public void run() {
                RelativeLayout userBt = bindView(rootView, 2131173488);
                logger.e("用户按钮是否绑定 : " + (userBt != null));
                if (userBt != null) {
                    userBt.performClick();
                }
            }
        }, VideoInter.second * 10);
    }
}