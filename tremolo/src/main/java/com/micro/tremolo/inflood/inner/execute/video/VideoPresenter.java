package com.micro.tremolo.inflood.inner.execute.video;

import com.micro.hook.plugin.PluginPresenter;
import com.micro.tremolo.inflood.inner.replace.Aweme;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:41
 */
public class VideoPresenter extends PluginPresenter<VideoInter> {
    @Override
    public void onAttached() {

    }

    private Object itemVideoInfo;

    public Object getItemVideoInfo() {
        return itemVideoInfo;
    }

    public void setItemVideoInfo(Object itemVideoInfo) {
        this.itemVideoInfo = itemVideoInfo;
    }

    public void obtainVideo(Aweme aweme){
        getClazz().loadVideo(aweme);
    }
}