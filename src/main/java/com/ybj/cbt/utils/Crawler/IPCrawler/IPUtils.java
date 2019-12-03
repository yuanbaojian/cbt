package com.ybj.cbt.utils.Crawler.IPCrawler;

import com.ybj.cbt.model.IPBean;
import org.junit.Test;

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

    @Test
    public void testValid() {
        IPBean ipBean = new IPBean();
        ipBean.setIPAddress("121.226.188.111");
        ipBean.setIpPosrt(9999);
        boolean valid = isValid(ipBean);
        System.out.println("valid = " + valid);
    }

    @Test
    public void testGetServerIp() throws MalformedURLException, UnknownHostException {
        URL url = new URL("https://www.leangoo.com/kanban/board/go/3091585");
        String host = url.getHost();
        InetAddress address = InetAddress.getByName(host);
        String ip = address.getHostAddress();
        System.out.println("ip = " + ip);
    }
}
