package com.ybj.cbt.utils.Crawler.IPCrawler;

import com.ybj.cbt.model.IPBean;

import java.io.IOException;
import java.net.*;

public class IPUtils {

    public static boolean isValid(IPBean ipBean) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIPAddress(), ipBean.getIpPosrt()));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            System.out.println(code);
            return code == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
