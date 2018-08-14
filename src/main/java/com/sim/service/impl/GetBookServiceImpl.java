package com.sim.service.impl;

import com.sim.service.GetBookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by hyj on 2018年08月13日
 */
public class GetBookServiceImpl implements GetBookService {

    public static String readUrl(String url) {
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            inputStream = new URL(url).openConnection().getInputStream();//爬取的网址、这里爬取的是一个生物网站
            bis = new BufferedInputStream(inputStream);
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            return bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                bis.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String readHtml(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:5.0)").cookie("auth", "token").timeout(3000).get();

            String imgUrl = doc.select("div[id=fmimg]").select("img").attr("src").toString();
            String bookName = doc.select("div[id=info]").select("h1").val();
            //获取html标签中的内容
            Elements elements = doc.select("div[id=fmimg]").select("li[class=gl-item]");
            for (Element ele : elements) {
                String bookID = ele.attr("data-sku");
                String bookPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
//        String html = readUrl("https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");
        readHtml("https://www.bookbao8.com/book/201805/29/id_XNTk5NTMz.html");
    }
}