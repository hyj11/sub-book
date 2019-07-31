package com.sim.service.impl;

import com.sim.enums.WebsiteChannel;
import com.sim.model.CommonModel;
import com.sim.service.BookService;
import com.sim.service.SendMailService;
import com.sim.utils.ChangeIP;
import com.sim.utils.ResponseObj;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static com.sim.common.GetBookCommon.readParentHtml;

/**
 * Created by hyj on 2018年08月13日
 */
public class GetBookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(GetBookServiceImpl.class);
    @Autowired
    private SendMailService sendMailService;

    public String readListHtml(WebsiteChannel websiteChannel, String url) {
        logger.info("读取html信息：channel:{}-url:{}", websiteChannel.toString(), url);
        sendMailService.mail();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36").cookie("auth", "token").timeout(5000).get();

            String imgUrl = doc.select("div[id=fmimg]").select("img").attr("src").toString();
            String bookName = doc.select("div[id=info]").select("h1").text();
            String author = doc.select("div[id=info]").select("p:contains(作者)").text();//作者
            String category = doc.select("div[id=info]").select("p:contains(类别)").text();//类别
            String status = doc.select("div[id=info]").select("p:contains(状态)").text();//状态
            String turnTime = doc.select("div[id=info]").select("p:contains(更新时间)").text();//更新时间
            String hits = doc.select("div[id=info]").select("p:contains(点击数)").text();//点击数
            String collec = doc.select("div[id=info]").select("p:contains(收藏量)").text();//收藏量
            //获取html标签中的内容
            Elements elements = doc.select(".info_chapterlist:has(li)").select("li");
            for (Element ele : elements) {
                String eleUrl = ele.select("li").select("a").attr("href");
                System.out.println(eleUrl);
                readParentHtml(websiteChannel, eleUrl);
            }
        } catch (Exception e) {
            ChangeIP.changeMyIp();
            readListHtml(websiteChannel, url);
            logger.error("读取html信息失败",e);
        }
        return "";
    }

    public static void main(String[] args) {
//        String html = readUrl("https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");
        GetBookServiceImpl getBookService = new GetBookServiceImpl();
        getBookService.readListHtml(WebsiteChannel.BOOKBAO, "https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");
    }

    @Override
    public <E extends CommonModel> ResponseObj process(E req) {
        return null;
    }

}