package com.ybj.cbt.utils.Crawler.Register;

import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author register
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class register {

    public static void main(String[] args) throws Exception {
        String path="";
        URL url=new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        conn.setConnectTimeout(5000);
        if(conn.getResponseCode()==200){
            String jsonString = IOUtils.toString(conn.getInputStream(), "utf-8");
           System.out.println("jsonString = " + jsonString);
        }
    }




}
