package com.sim.utils;

import com.closeli.utils.SigUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyj on 2018年08月21日
 */
public class ChangeIP {

    private static List<String[]> ipAndPorts = new ArrayList<String[]>();

    private static Integer ipPageNum = 1;

    /**
     * 更换动态ip
     */
    public static void changeMyIp() {
        String[] ipAndPort = getDynamicIpAndPort();
        String ip = ipAndPort[0];
        String port = ipAndPort[1];
        System.setProperty("http.maxRedirects", "50");
        System.setProperty("https.maxRedirects", "50");
        System.getProperties().setProperty("proxySet", "true");
        System.getProperties().setProperty("http.proxyHost", ip);
        System.getProperties().setProperty("http.proxyPort", port);
        System.getProperties().setProperty("https.proxyHost", ip);
        System.getProperties().setProperty("https.proxyPort", port);
    }

    /**
     * 获取ip信息
     */
    public void getMyIpInfo() {
        try {
            Document ipDoc = Jsoup.connect("http://www.ip.cn")
                    .userAgent("Mozilla")
                    .timeout(3000)
                    .get();
            if (ipDoc != null) {
                String ipInfo = ipDoc.select(".well").first().text();
                System.out.println("更换ip 成功" + ipInfo);
            }
        } catch (Exception e) {
            System.out.println("暂不能获取ip 信息");
        }
    }

    /**
     * 获取动态ip
     *
     * @return 动态ip
     */
    private static String[] getDynamicIpAndPort() {
        String[] ipAndPort = null;
        if (ipAndPorts != null && ipAndPorts.size() > 0) {
            ipAndPort = ipAndPorts.get(0);
            ipAndPorts.remove(0);
        } else {
            try {
                Document pageDoc = Jsoup.connect("http://www.xicidaili.com/wn/" + ipPageNum)
                        .userAgent("Mozilla")
                        .timeout(5000)
                        .get();
                Elements elements = pageDoc.select("tr.odd");
                ipPageNum++;
                if (ipPageNum > 400) {
                    ipPageNum = 1;
                }
                for (Element element : elements) {
                    String[] ipPort = new String[2];
                    String ip = element.child(1).text();
                    String port = element.child(2).text();
                    String noName = element.child(4).text();
//                    if(!noName.equals("高匿")){
//                        continue;
//                    }
                    String speedStr = element.child(6).select(".bar").first().attr("title");
                    double speed = Double.valueOf(speedStr.substring(0, speedStr.indexOf("秒")));
                    String timeStr = element.child(7).select(".bar").first().attr("title");
                    double time = Double.valueOf(timeStr.substring(0, timeStr.indexOf("秒")));
                    if (speed <= 1 && time <= 1) {
                        ipPort[0] = ip;
                        ipPort[1] = port;
                        ipAndPorts.add(ipPort);
                    }
                }
                return getDynamicIpAndPort();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ipAndPort;
    }

    public static void main(String[] args) {
        try {
            String a = SigUtils.signWithESDType("abc");
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}