package com.micro.tremolo.inflood.inner.execute.monitor.video;

import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.inflood.inner.replace.Aweme;

import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/26 13:40
 */
public interface VideoInter extends PluginInter {

    void loadVideoItem(Aweme aweme);

    void loadVideoList(List<Aweme> awemeList);

    void pushVoid(boolean isFirst, String userId, int type, long time, int limit, String key);
}