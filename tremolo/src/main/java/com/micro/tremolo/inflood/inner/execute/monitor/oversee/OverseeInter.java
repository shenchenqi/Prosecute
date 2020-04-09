package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:08
 */
public interface OverseeInter extends PluginInter {

    void profileExist(boolean isExist);

    void videoInfo(Aweme aweme);

    void profileInfo(Author author);

    void videoListInfo(List<Video> videos);
}