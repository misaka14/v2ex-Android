package com.wutouqishi.v2ex_android.domain;

/**
 * Created by gengjie on 16/9/14.
 *
 */
public class TopicDetail
{
    /** 头像*/
    String avatar;

    /** 作者*/
    String author;

    /** 标题 */
    String title;

    /** 节点名称 */
    String node;

    /** 创建时间 */
    String createTime;

    /** 内容 */
    String content;

    /** 博客被查看次数 */
    int seeCount;

    public TopicDetail() {
    }

    public TopicDetail(String avatar, String author, String title, String node, String createTime, String content, String floor, int seeCount) {
        this.avatar = avatar;
        this.author = author;
        this.title = title;
        this.node = node;
        this.createTime = createTime;
        this.content = content;
        this.seeCount = seeCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSeeCount() {
        return seeCount;
    }

    public void setSeeCount(int seeCount) {
        this.seeCount = seeCount;
    }
}
