package com.sim.service.impl;

import com.sim.enums.WebsiteChannel;
import com.sim.model.CommonModel;
import com.sim.service.BookService;
import com.sim.utils.ChangeIP;
import com.sim.utils.ResponseObj;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hyj on 2018年08月13日
 */
public class GetBookServiceImpl implements BookService {


    public static Document readUrl(String url) {
        //创建httpclient工具对象
        HttpClient client = new HttpClient();
        //创建post请求方法
        PostMethod myPost = new PostMethod(url);
        //设置请求超时时间
        client.setConnectionTimeout(5000);
        String responseString = null;
        try {
            //设置请求头部类型
            myPost.setRequestHeader("Content-Type", "application/json");
            myPost.setRequestHeader("charset", "utf-8");
            int statusCode = client.executeMethod(myPost);
            //只有请求成功200了，才做处理
            if (statusCode == HttpStatus.OK.value()) {
                InputStream inputStream = myPost.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                responseString = stringBuffer.toString();
                Document document = Jsoup.parse(responseString);
                return document;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myPost.releaseConnection();
        }
        return null;
    }


    public static String readListHtml(WebsiteChannel websiteChannel, String url) {
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
            e.printStackTrace();
            ChangeIP.changeMyIp();
            readListHtml(websiteChannel, url);
        }
        return "";
    }

    public static void readParentHtml(WebsiteChannel websiteChannel, String url) {
        String tUrl = "";
        if (websiteChannel == WebsiteChannel.BOOKBAO) {
            tUrl = "https://www.bookbao8.com";
        }
        url = tUrl + url;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:5.0)").cookie("auth", "token").timeout(3000).get();
            String text = doc.select("text").text();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String html = readUrl("https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");
        readListHtml(WebsiteChannel.BOOKBAO, "https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");

    }

    @Override
    public <E extends CommonModel> ResponseObj process(E req) {
        return null;
    }
}