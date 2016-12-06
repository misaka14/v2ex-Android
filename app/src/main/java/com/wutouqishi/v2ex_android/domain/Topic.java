package com.wutouqishi.v2ex_android.domain;

import java.io.Serializable;

public class Topic implements Serializable
{
    String avatarURL;

    String author;

    String title;

    String node;

    String content;

    String detailUrl;

    String commentCount;

    String lastReplyTime;

    String lastReplyPeople;

    public Topic() {

    }

    public Topic(String avatarURL, String author, String title, String node, String content, String detailUrl, String commentCount, String lastReplyTime, String lastReplyPeople) {
        this.avatarURL = avatarURL;
        this.author = author;
        this.title = title;
        this.node = node;
        this.content = content;
        this.detailUrl = detailUrl;
        this.commentCount = commentCount;
        this.lastReplyTime = lastReplyTime;
        this.lastReplyPeople = lastReplyPeople;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "avatarURL='" + avatarURL + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", node='" + node + '\'' +
                ", content='" + content + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", lastReplyTime='" + lastReplyTime + '\'' +
                ", lastReplyPeople='" + lastReplyPeople + '\'' +
                '}';
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(String lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getLastReplyPeople() {
        return lastReplyPeople;
    }

    public void setLastReplyPeople(String lastReplyPeople) {
        this.lastReplyPeople = lastReplyPeople;
    }
}
