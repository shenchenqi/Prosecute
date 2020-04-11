package com.micro.tremolo.inflood.inner.replace;

import com.micro.hook.config.Hook;

import java.util.List;

/**
 * @Author Kilin
 * @Date 2020/3/25 11:03
 * @Source 抖音版本960 com.ss.android.ugc.aweme.base.model.UrlModel
 */
public class UrlModel {
    private String fileHash;
    private int height;
    private long size;
    private String uri;
    private String urlKey;
    private List<String> urlList;
    private int width;

    public UrlModel(Hook hook, Object urlModel) {
        loadUrlModel(hook, urlModel);
    }

    public void loadUrlModel(Hook hook, Object urlModel) {
        if (urlModel == null) {
            return;
        }
        this.fileHash = getFileHash(hook, urlModel);
        //logger.d("UrlModel", String.format("fileHash[%s]", fileHash));
        this.height = getHeight(hook, urlModel);
        //logger.d("UrlModel", String.format("height[%s]", height));
        this.size = getSize(hook, urlModel);
        //logger.d("UrlModel", String.format("size[%s]", size));
        this.uri = getUri(hook, urlModel);
        //logger.d("UrlModel", String.format("uri[%s]", uri));
        this.urlKey = getUrlKey(hook, urlModel);
        //logger.d("UrlModel", String.format("urlKey[%s]", urlKey));
        this.urlList = getUrlList(hook, urlModel);
        //logger.d("UrlModel", String.format("urlList[%s]", JSON.toJSONString(urlList)));
        this.width = getWidth(hook, urlModel);
        //logger.d("UrlModel", String.format("width[%s]", width));
    }

    public String getFileHash() {
        return fileHash;
    }

    public int getHeight() {
        return height;
    }

    public long getSize() {
        return size;
    }

    public String getUri() {
        return uri;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public int getWidth() {
        return width;
    }

    private String getFileHash(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "fileHash");
    }

    private int getHeight(Hook hook, Object urlModel) {
        return hook.getIntegerField(urlModel, "height");
    }

    private long getSize(Hook hook, Object urlModel) {
        return hook.getLongField(urlModel, "size");
    }

    private String getUri(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "uri");
    }

    private String getUrlKey(Hook hook, Object urlModel) {
        return (String) hook.getField(urlModel, "urlKey");
    }

    private List<String> getUrlList(Hook hook, Object urlModel) {
        return (List<String>) hook.getField(urlModel, "urlList");
    }

    private int getWidth(Hook hook, Object urlModel) {
        return hook.getIntegerField(urlModel, "width");
    }
}