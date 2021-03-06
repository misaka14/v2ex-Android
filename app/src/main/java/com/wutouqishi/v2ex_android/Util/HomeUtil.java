package com.wutouqishi.v2ex_android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;
import com.wutouqishi.v2ex_android.domain.Topic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.global.GlobalConstants;

import java.io.IOException;
import java.util.ArrayList;

import static android.util.Log.INFO;

/**
 * Created by gengjie on 16/9/7.
 */
public class HomeUtil
{

    // 根据html解析出首页话题数据
    public static void parseTopicWithHTML(final String url, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Topic> topics = new ArrayList<Topic>();
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements cellItemEs = doc.select("div.item");
//                System.out.println("doc:" + doc);
//                System.out.println("cellItemEs:" + cellItemEs.size());
                for (int i = 0; i < cellItemEs.size(); i++) {
                    Element cellItemE = cellItemEs.get(i);
                    System.out.println("i:" + i + "--cellItemE:" + cellItemE.text());
                    Element titleE = cellItemE.select("span.item_title").select("a").get(0);

                    Element avatarE = cellItemE.select("img").get(0);

                    Element nodeE = cellItemE.select("a.node").get(0);

                    Elements strongEs = cellItemE.select("strong");

                    Element authorE = strongEs.get(0).select("a").get(0);

                    Elements commentCountEs = cellItemE.select("a.count_livid");


                    String commentCount = "";
                    if (commentCountEs.size() > 0)
                    {
                        commentCount =  commentCountEs.get(0).text();
                    }
//                    System.out.println("title1:" + titleE.attr("href") + "content1:" + titleE.text());


                    String replyTime = "";
//                    if (strongEs.size() == 2) {
//                        System.out.println("strongEs:" + strongEs.get(1).text());
//                        String[] replyTimes = strongEs.get(1).text().split("•");
//
//                        replyTime = replyTimes[1];
//                    }
                    Elements smallEs = cellItemE.select("span.small");
                    if (smallEs.size() > 0)
                    {
                        replyTime = smallEs.get(1).text().split("•")[0];
                    }
                    Topic topic = new Topic();
                    topic.setAvatarURL(GlobalConstants.V2EXHTTP + avatarE.attr("src"));
                    topic.setDetailUrl(GlobalConstants.SERVER_URL + titleE.attr("href"));
                    System.out.println("href"+ titleE.attr("href"));
                    topic.setTitle(titleE.text());
                    topic.setNode(nodeE.text());
                    topic.setAuthor(authorE.text());
                    topic.setCommentCount(commentCountEs.text());
                    topic.setLastReplyTime(replyTime);
                    topics.add(topic);

                }

                Message msg = Message.obtain();
                msg.obj = topics;
                handler.sendMessage(msg);
            }
        }).start();
    }

    public static void parseTopicWithDetailUrl(final String url, final Handler handler, final Context context)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    Document doc = Jsoup.connect(url).get();

                    TopicDetail topicDetail = new TopicDetail();


                    // 正文
                    Element topicContentE = doc.select("div.topic_content").get(0);
                    Element boxE = doc.select("div.box").get(1);


                    Elements cells = boxE.select("div.cell");
                    Element lastCell = cells.get(cells.size() - 1);

                    System.out.println("doc:" + doc.html());
                    String content;
                    String commentHtml;

                    // 判断是否存在分页
                    if (StringUtils.isEmpty(lastCell.attr("id")))  // 不存在分页
                    {
                        commentHtml = boxE.html();
                    }
                    else                                           // 存在分页
                    {
                        // 把分页的cell去除掉
//                        cells.remove(cells.size() - 1);

                        // 如果当前页面是第一页的话
                        Elements inners = boxE.select("div.inner");



                        // 把正文和评论拼接出来
                        StringBuffer sb = new StringBuffer();
                        for (Element cell : cells)
                        {
                            sb.append(cell.outerHtml());
                        }

                        for (Element inner : inners)
                        {
                            if (!StringUtils.isEmpty(inner.attr("id")))
                            {
                                String html = inner.outerHtml();
                                html = html.replace("class=\"inner\"", "class=\"cell\"");
                                sb.append(html);
                            }

                        }
                        commentHtml = sb.toString();


//                        Logger.i("maxPage:", boxE.select("a.page_current").text());
                        // 取出当前页
//                        topicDetail.setCurrentPage(boxE.select("a.page_current").text());


                    }



                    System.out.println("js:" + AssetsUtil.getAssetsFile("v2ex.js", context));
                    content = "<!DOCTYPE HTML><html><meta content='width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0' name='viewport'><head> " + AssetsUtil.getAssetsFile("v2ex.js", context) + AssetsUtil.getAssetsFile("light.css", context) +  "</head><body>" + topicContentE.html() + commentHtml+ "</body></html>";

                    // 把头像变大
                    content = content.replace("width=\"24\"", "width=\"40\"");
                    content = content.replace("max-width: 24px; max-height: 24px;", "max-width: 40px; max-height: 40px");
                    content = content.replace("?s=24", "?s=48");

                    topicDetail.setContent(content);
                    System.out.println("content:123" + content);
                    Logger.i("content123:", content);

                    // 当前的页数


                    // 评论
//                    Elements cellEs = doc.select("div.cell");
//                    for (Element cell : cellEs)
//                    {
//                        Elements reply_contentEs = cell.select("div.reply_content");
//                        if (reply_contentEs.size() == 0)
//                        {
//                            continue;
//                        }
//
//                        Element reply_contentE = reply_contentEs.get(0);
//
//                        Element noE = cell.select("span.no").get(0);
//
//                        Element authorE = cell.select("a.dark").get(0);
//
//                        Element avatarE = cell.select("img.avatar").get(0);
//
//                        Element smallE = cell.select("span.small").get(0);
//
//                        TopicComment topicComment = new TopicComment();
//                        topicComment.setReply_content(reply_contentE.text());
//                        topicComment.setFloor(noE.text());
//                        topicComment.setAuthor(authorE.text());
//                        topicComment.setAvatar(GlobalConstants.V2EXHTTP + avatarE.attr("src"));
//                        topicComment.setReplyTime(smallE.text());
//
//                        topicDetails.add(topicComment);
//                    }

                    Message msg = Message.obtain();
                    msg.obj = topicDetail;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
