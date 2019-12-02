package com.ybj.cbt.utils.Crawler.Proxy;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class Proxy {

    @Test
    public void Test() {
        java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("49.70.17.235", 9999));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            System.out.println("code = " + code);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
