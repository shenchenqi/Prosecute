package com.micro.tremolo.sqlite.table;

import java.io.Serializable;
import java.util.List;

/**
 * @Author KiLin
 * @Time 2020/4/2 17:36
 */
public class VideoListModelTable implements Serializable {

    private List<VideoModelTable> data;

    public List<VideoModelTable> getVideoModelTableList() {
        return data;
    }

    public void setVideoModelTableList(List<VideoModelTable> data) {
        this.data = data;
    }
}