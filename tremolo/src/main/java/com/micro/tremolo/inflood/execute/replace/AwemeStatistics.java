package com.micro.tremolo.inflood.execute.replace;

import com.micro.hook.config.Hook;

import static com.micro.tremolo.inflood.TremoloModule.logger;

/**
 * @Author Kilin
 * @Date 2020/3/25 10:26
 */
public class AwemeStatistics {
    private String aid;
    private int commentCount;//评论数
    private int diggCount;//爱心数
    private int downloadCount;//下载数
    private int forwardCount;
    private int loseCommentCount;
    private int loseCount;
    private int playCount;
    private int shareCount;//分享数

    public AwemeStatistics(Hook hook, Object awemeStatistics) {
        loadAwemeStatistics(hook, awemeStatistics);
    }

    public void loadAwemeStatistics(Hook hook, Object awemeStatistics) {
        logger.d("AwemeStatistics", String.format("当前抓取的视频统计数据对象是否存在[%s]", awemeStatistics == null));
        if (awemeStatistics == null) {
            return;
        }
        this.aid = getAid(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("aid[%s]", aid));
        this.commentCount = getCommentCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("评论数[%s]", commentCount));
        this.diggCount = getDiggCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("爱心数[%s]", diggCount));
        this.downloadCount = getDownloadCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("下载数[%s]", downloadCount));
        this.forwardCount = getForwardCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("forwardCount[%s]", forwardCount));
        this.loseCommentCount = getLoseCommentCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("loseCommentCount[%s]", loseCommentCount));
        this.loseCount = getLoseCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("loseCount[%s]", loseCount));
        this.playCount = getPlayCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("播放数[%s]", playCount));
        this.shareCount = getShareCount(hook, awemeStatistics);
        //logger.d("AwemeStatistics", String.format("分享数[%s]", shareCount));
    }

    public String getAid() {
        return aid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public int getLoseCommentCount() {
        return loseCommentCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    private String getAid(Hook hook, Object awemeStatistics) {
        return (String) hook.getField(awemeStatistics, "aid");
    }

    private int getCommentCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "commentCount");
    }

    private int getDiggCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "diggCount");
    }

    public int getDownloadCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "downloadCount");
    }

    private int getForwardCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "forwardCount");
    }

    private int getLoseCommentCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "loseCommentCount");
    }

    private int getLoseCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "loseCount");
    }

    private int getPlayCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "playCount");
    }

    private int getShareCount(Hook hook, Object awemeStatistic) {
        return hook.getIntegerField(awemeStatistic, "shareCount");
    }
}