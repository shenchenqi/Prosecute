package com.micro.tremolo.inflood.inner.execute.mvp;

import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;

import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:08
 */
public interface ExecuteInter extends PluginInter {

    void profileInfo(Author author);

    void videoListInfo(List<Video> videos);
}