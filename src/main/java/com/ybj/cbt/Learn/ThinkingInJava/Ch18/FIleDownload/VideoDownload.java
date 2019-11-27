package com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class VideoDownload {


    @Test
    public void testCopy() throws IOException {
    String desc = "H:\\video\\testDownload\\";
        String suffix=".mp4";
        String srcPrefix="http://185.38.13.130//mp43/";
        String videoSrc;
        String videoDesc;
        File descFile;
        int startNumber=344003;
        try{
            for(int i=startNumber; i<1000000000; i++){
                videoSrc=srcPrefix+i+suffix;
                videoDesc=desc+i+suffix;
                URL url=new URL(videoSrc);
                descFile=new File(videoDesc);
                URLConnection connection = url.openConnection();
                HttpURLConnection httpURLConnection=(HttpURLConnection) connection;
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                // 取得输入流，并使用Reader读取
                int responseCode=((HttpURLConnection) connection).getResponseCode();
                if(responseCode==200){
                    FileUtils.copyURLToFile(url,descFile );
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }





}
