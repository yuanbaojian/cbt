package com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author getJson
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class getJson {

    public static void main(String[] args) throws Exception {
        String path="https://shanghaicity.openservice.kankanews.com/public/bus/Getstop?stoptype=0&stopid=10&sid=7019f275eae92b302744ade1ac88763a";
        BusInfo busInfo = HttpURLConnection_GET(path);
        System.out.println("亲，759还有"+ Long.valueOf(busInfo.getTime()) /60+"分钟到达顾戴路秀波路 "  );
    }

    @Test
    public void testIOUtils() throws IOException {
        String path="https://www.pexels.com/";
        URL url=new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        String context = IOUtils.toString(conn.getInputStream(), "utf-8");
        System.out.println("context = " + context);
    }


    public static BusInfo HttpURLConnection_GET(String path)throws Exception{
        BusInfo busInfo=new BusInfo();
        URL url=new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        if(conn.getResponseCode()==200){				//200表示请求成功
            String jsonString = IOUtils.toString(conn.getInputStream(), "utf-8");
            JSONArray jsonArray=new JSONArray(jsonString);
            for(int i=0;i<jsonArray.length();i++){		//一个循环代表一个BusInfo对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                busInfo.setDistance(jsonObject.getString("distance"));
                busInfo.setTime(jsonObject.getString("time"));
            }
        }
        return busInfo;
    }



}
