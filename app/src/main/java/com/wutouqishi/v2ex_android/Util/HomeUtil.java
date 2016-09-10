package com.wutouqishi.v2ex_android.Util;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.wutouqishi.v2ex_android.domain.Topic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.wutouqishi.v2ex_android.global.GlobalConstants;

import java.io.IOException;
import java.util.ArrayList;

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
                String URL = "https://www.v2ex.com/?tab=creative";
                Document doc = null;
                try {
                    doc = Jsoup.connect(URL).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements cellItemEs = doc.select("div.item");
                System.out.println("doc:" + doc);
                System.out.println("cellItemEs:" + cellItemEs.size());
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
                    topic.setDetailUrl(GlobalConstants.SERVER_URL + avatarE.attr("href"));
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

}
