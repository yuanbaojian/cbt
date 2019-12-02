package com.ybj.cbt.utils.Crawler.IPCrawler;

import com.ybj.cbt.model.IPBean;
import com.ybj.cbt.model.IPBeanList;
import com.ybj.cbt.model.Website;
import com.ybj.cbt.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class IPCrawler {

    public static void main(String[] args) throws IOException {
        Website website = new Website() {
            @Override
            public String getPureSrc(String rawSrc) {
                return rawSrc;
            }

        };
        website.setURL("https://www.xicidaili.com/wt/1");
        website.setSelector("tr");
        website.setAttr("data-original");
        website.setDescFolder("G:/crawler/budejie/");
        List<IPBean> ipBeans = startCrawl(website);
        List<IPBean> usefulIpList = new LinkedList<>();
        int validNumber = 0;
        int count = 0;
        for (IPBean ipBean : ipBeans) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid == true) {
                        IPBeanList.add(ipBean);
                        System.out.println("有效 = " + ipBean.getIPAddress() + "  " + ipBean.getIpPosrt());
                    }
                    IPBeanList.increase();
                }
            }).start();

        }
        while (true) {
            if (IPBeanList.getCount() == ipBeans.size()) {
                System.out.println("共爬取到  " + ipBeans.size() + " 个IP");
                System.out.println("共爬取到  " + IPBeanList.getSize() + " 个有用的IP");
                break;
            }
        }

    }

    public static List<IPBean> startCrawl(Website website) throws IOException {
        List<IPBean> ipBeanList = new LinkedList<>();
        URL url = new URL(website.getURL());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");

        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc = Jsoup.parse(urlSource);
        Elements elements = doc.select(website.getSelector());
        for (int i = 0; i < elements.size(); i++) {
            if (i == 0) {
                continue;
            }
            IPBean ipBean = new IPBean();
            String ipAddress = elements.get(i).children().get(1).text();
            Integer ipPort = Integer.valueOf(elements.get(i).children().get(2).text().trim());
            ipBean.setIPAddress(ipAddress);
            ipBean.setIpPosrt(ipPort);
            ipBeanList.add(ipBean);
        }
        return ipBeanList;
    }


    public static void startDownload(String urlString, String descPrefix, int serialNumber) throws IOException {
        String filePath = descPrefix + getFileName(urlString);
        URL url = new URL(urlString);
        FileUtils.copyURLToFile(url, new File(filePath));
    }

    public static String getFileName(String urlString) {
        return urlString.substring(urlString.lastIndexOf("/"), urlString.length());
    }


}
