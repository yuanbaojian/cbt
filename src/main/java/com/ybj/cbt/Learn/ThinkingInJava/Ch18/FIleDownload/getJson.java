package com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
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


    public static BusInfo HttpURLConnection_GET(String path)throws Exception{
//        String path="http://222.22.254.223:8080/web/BusInfoJson";
        //参数直接加载url后面
        BusInfo busInfo=new BusInfo();
//        path+="?username="+ URLEncoder.encode("我是大帅哥HttpClientGET","utf-8");
        URL url=new URL(path);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        if(conn.getResponseCode()==200){				//200表示请求成功
            InputStream is=conn.getInputStream();		//以输入流的形式返回
            //将输入流转换成字符串
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte [] buffer=new byte[1024];
            int len=0;
            while((len=is.read(buffer))!=-1){
                baos.write(buffer, 0, len);
            }
            String jsonString=baos.toString();
            baos.close();
            is.close();
            //转换成json数据处理
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
