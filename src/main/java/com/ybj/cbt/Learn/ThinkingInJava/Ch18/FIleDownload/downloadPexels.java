package com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload;

import com.ybj.cbt.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author downloadPexels
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class downloadPexels {
    public static  String pexeclURL="https://www.pexels.com/";
    public static  String baiduURL="https://www.baidu.com/";
    public static  String pexeclSelector="img[class=photo-item__img]";
    public static  String pexeclSelectorAttr="srcset";
    public static  String pexeclDescPrefix="C:\\Users\\yuanbaojian\\Desktop\\desc\\pexels\\";

    public static  String pbudejieURL="http://www.budejie.com/hot/2";
    public static  String budejieSelector="img[class=lazy]";
    public static  String budejieSelectorAttr="data-original";
    public static  String budejieDescPrefix="C:\\Users\\yuanbaojian\\Desktop\\desc\\budejie\\hot\\";


    public static String nineOnePornURL = "http://198.255.82.91/index.php";
    public static String nineOnePornSelector = "a[target=blank]";
    public static String nineOnePornSelectorAttr = "href";
    public static String nineOnePornDescPrefix = "C:\\Users\\yuanbaojian\\Desktop\\desc\\nineOne\\video\\";

    public static  String googlelURL="https://www.google.com/";


    @Test
    public void testIOUtils() throws IOException {
        String path = nineOnePornURL;
        URL url=new URL(path);

        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);

        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc= Jsoup.parse(urlSource);
        Elements elements = doc.select(budejieSelector);
        for (int i = 0; i <elements.size(); i++) {
            String text = elements.get(i).attr(budejieSelectorAttr);
            text=getBudejiePath(text);
            download(text,budejieDescPrefix);
            System.out.println("第 "+ i+ "项----" + text);
        }
    }

    @Test
    public void testNineOne() throws IOException {
        String path = nineOnePornURL;
        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);

        String urlSource = IOUtils.toString(conn.getInputStream(), "utf-8");
        Document doc = Jsoup.parse(urlSource);
        Elements elements = doc.select(budejieSelector);
        for (int i = 0; i < elements.size(); i++) {
            String text = elements.get(i).attr(budejieSelectorAttr);

            URL url2 = new URL(text);

            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            conn2.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
            conn2.setRequestMethod("POST");
            conn2.setConnectTimeout(5000);

            String urlSource2 = IOUtils.toString(conn2.getInputStream(), "utf-8");
            Document doc2 = Jsoup.parse(urlSource2);
            Elements elements2 = doc2.select("video[id=vid]").select("source");
            for (Element element : elements2) {
                String text2 = elements.get(i).attr("src");
                text2 = getNineonePath(text2);
                download(text2, nineOnePornDescPrefix);
            }
        }
    }

    public static void download(String urlString,String descPrefix) throws IOException {
        String fileName = getNineoneFileName(urlString);
        String filePath=descPrefix+fileName;
        URL url=new URL(urlString);
        FileUtils.copyURLToFile(url,new File(filePath) );
    }

    public static String getPexecFileName(String urlString){
        String fileName=urlString.substring(urlString.lastIndexOf("/") +1,urlString.length());
        return fileName;
    }

    public static String getNineoneFileName(String urlString) {
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1, urlString.indexOf("?"));
        return fileName;
    }

    public static String getNineonePath(String urlString) {
        String fileName = urlString.substring(0, urlString.lastIndexOf("?") + 1);
        return fileName;
    }

    public static String getBudejiePath(String text){
        return text;
    }



}
