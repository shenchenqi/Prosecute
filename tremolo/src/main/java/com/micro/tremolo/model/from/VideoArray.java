package com.micro.tremolo.model.from;

import com.micro.tremolo.model.table.VideoTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/15 11:41
 */
public class VideoArray extends BaseFrom {
    private List<VideoTable> videoTables = new ArrayList<>();

    public void setVideos(List<Video> videos) {
        for (Video video : videos) {
            videoTables.add(video.getVideoTable());
        }
    }

    public List<VideoTable> getVideoTables() {
        return videoTables;
    }
}