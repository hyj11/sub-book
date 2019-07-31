package com.sim.common;

import com.sim.enums.WebsiteChannel;
import com.sim.exception.ServiceException;
import com.sim.service.impl.GetBookServiceImpl;
import com.sim.utils.ResponseObj;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: huangyj
 * Date: 2019/7/31
 */
public class GetBookCommon {

    private static final Logger logger = LoggerFactory.getLogger(GetBookCommon.class);

    public static Document readUrl(String url) {
        logger.info("开始读取url:", url);
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
            logger.error("读取url失败", e);
            throw new ServiceException(ResponseObj.CODE_FAILED,"读取url失败");
        } finally {
            myPost.releaseConnection();
        }
        return null;
    }

    public static void readParentHtml(WebsiteChannel websiteChannel, String url) {
        logger.info("读取子html信息：channel:{}--url:{}",websiteChannel.toString(),url);
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
          logger.error("读取子html失败");
          throw new ServiceException(ResponseObj.CODE_FAILED,"读取子html失败");
        }
    }
}
