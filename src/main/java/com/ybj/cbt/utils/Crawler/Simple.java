package com.ybj.cbt.utils.Crawler;

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

/**
 * @Author Simple
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Simple {

    public static String fileType=".gif";

    public static  String pbudejieURL="http://www.budejie.com/hot/2";
    public static  String budejieSelector="img[class=lazy]";
    public static  String budejieSelectorAttr="data-original";
    public static  String budejieDescPrefix="C:\\Users\\yuanbaojian\\Desktop\\desc\\budejie\\hot\\";

    public static void main(String[] args) throws IOException {
        Website website= new Website() {
            @Override
            public String getPureSrc(String rawSrc) {
                return rawSrc;
            }

        };
        website.setURL("https://www.google.com/");
        website.setSelector("img[class=lazy]");
        website.setAttr("data-original");
        website.setDescFolder("G:/crawler/budejie/");
        startCrawl(website);
    }


    public static void startCrawl(Website website) throws IOException {
        URL url=new URL(website.getURL());
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc= Jsoup.parse(urlSource);
        Elements elements = doc.select(website.getSelector());
        for (int i = 0; i <elements.size(); i++) {
            String rawUrl = elements.get(i).attr(website.getAttr());
            String fileSrc=website.getPureSrc(rawUrl);
            startDownload(fileSrc,website.getDescFolder(),i);
            System.out.println("第 "+ i+ "项----" + fileSrc);
        }
    }




    public static void startDownload(String urlString, String descPrefix, int serialNumber) throws IOException {
        String filePath=descPrefix+getFileName(urlString);
        URL url=new URL(urlString);
        FileUtils.copyURLToFile(url,new File(filePath) );
    }

    public static String getFileName(String urlString){
        return urlString.substring(urlString.lastIndexOf("/"), urlString.length());
    }





}
