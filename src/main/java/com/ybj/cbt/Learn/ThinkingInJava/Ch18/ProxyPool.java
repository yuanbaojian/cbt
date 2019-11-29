package com.ybj.cbt.Learn.ThinkingInJava.Ch18;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * 通过网站域名URL获取该网站的源码
 *
 * @author Administrator
 */
public class ProxyPool {
    /** */
    /**
     * @param args
     * @throws
     */
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.91porn.com/view_video.php?viewkey=f49276721007c6f662ed");
        String urlsource = getURLSource(url);
        System.out.println(urlsource);
    }

    /** */
    /**
     * 通过网站域名URL获取该网站的源码
     *
     * @param url
     * @return String
     * @throws Exception
     */
    public static String getURLSource(URL url) throws Exception {
        // 创建代理服务器
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8080");
//        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8080);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // Socket 代理
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        return htmlSource;
    }

    /** */
    /**
     * 把二进制流转化为byte字节数组
     *
     * @param instream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        instream.close();
        return outStream.toByteArray();
    }


    @Test
    public void testProxy() throws IOException {
        URL url = new URL("https://www.hugaola.com/xieegif/2099.html");
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        String html = IOUtils.toString(in, "UTF-8");

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容


    }

}