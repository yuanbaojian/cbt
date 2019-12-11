package com.ybj.cbt.utils.BusInfo;

import com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload.BusInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author getJson
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class GetBusInfo {

    public static void main(String[] args) throws Exception {
        String path = "https://shanghaicity.openservice.kankanews.com/public/bus/Getstop?stoptype=0&stopid=10.&sid=7019f275eae92b302744ade1ac88763a";
        BusInfo busInfo = HttpURLConnection_GET(path);
        System.out.println("亲，759还有" + Long.valueOf(busInfo.getTime()) / 60 + "分钟到达顾戴路秀波路 ,距离："
                + busInfo.getDistance() + "m");
    }


    public static BusInfo HttpURLConnection_GET(String path) throws Exception {
        BusInfo busInfo = new BusInfo();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == 200) {
            String jsonString = IOUtils.toString(conn.getInputStream(), "utf-8");
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                busInfo.setDistance(jsonObject.getString("distance"));
                busInfo.setTime(jsonObject.getString("time"));
            }
        }
        return busInfo;
    }


}
