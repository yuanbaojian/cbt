package com.ybj.cbt.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

public class DownloadUtils {

    @Autowired
    HttpServletResponse response;

    @Test
    public void testDownload() throws IOException {
        String fileSrc = "https://www.baidu.com/img/bd_logo1.png";
        URL url = new URL(fileSrc);
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.reset();
        response.setContentType("application/x-msdownload; charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + URLEncoder.encode("I'm file", "UTF-8").replaceAll("\\+", "%20") + "\"");
        OutputStream out = response.getOutputStream();
        int i;
        while ((i = in.read()) != -1) {
            response.getOutputStream().write(i);
        }
        in.close();
        response.getOutputStream().close();
    }
}
