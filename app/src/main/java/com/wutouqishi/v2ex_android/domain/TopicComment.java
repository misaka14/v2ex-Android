package com.wutouqishi.v2ex_android.domain;

/**
 * Created by gengjie on 16/9/14.
 */
public class TopicComment
{
    /** 评论内容 */
    String reply_content;

    /** 楼层 */
    String floor;

    /** 头像*/
    String avatar;

    /** 作者*/
    String author;

    /** 回复时间 */
    String replyTime;

    public TopicComment()
    {

    }

    public TopicComment(String reply_content, String floor, String avatar, String author, String replyTime) {
        this.reply_content = reply_content;
        this.floor = floor;
        this.avatar = avatar;
        this.author = author;
        this.replyTime = replyTime;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
