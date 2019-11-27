package com.ybj.cbt;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class test {

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    @Test
    public void testJavaBeanObjToJSONStr() {
        Student student = new Student("lily", 12);
        String jsonString = JSONObject.toJSONString(student);
        System.out.println(jsonString);
    }

    @Test
    public void testCopy() throws IOException {
        File desc=new File("C:\\Users\\baojian.yuan\\Desktop\\desc\\1.jpg");
        URL url=new URL("https://img.alicdn.com/tfs/TB1TXIEl1H2gK0jSZFEXXcqMpXa-1880-640.jpg");
        Date date=new Date();
        long millsecond=date.getTime();
        int number=240000;
        for(int i=0;i<number;i++){
            System.out.println("正在下载第 " + i + "项");
//            FileUtils.forceMkdir(new File("C:\\Users\\baojian.yuan\\Desktop\\desc\\" +i));
            FileUtils.copyURLToFile(url, new File("C:\\Users\\baojian.yuan\\Desktop\\desc\\"+i+ ".jpg") );
        }
        Date date2=new Date();
        long millsecond2=date2.getTime();
        Long usedTime=(millsecond2-millsecond)/1000;
        System.out.println("下载" +number*64/1000+ "MB  " + "用时 " + usedTime);
        System.out.println("速度约为 " + number*64/1000/usedTime + "M/s");
    }


    @Test
    public void testDownloadPhoto() throws IOException {
        String urlString="https://img.alicdn.com/tfs/TB1TXIEl1H2gK0jSZFEXXcqMpXa-1880-640.jpg";
        URL url =new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        OutputStream outputStream = new FileOutputStream("C:\\Users\\baojian.yuan\\Desktop\\3.png");
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, bytesRead);
        }
    }

}

class  Student{
    String name;
    int age;

    public Student(String name, int age) {
        this.name=name;
        this.age=age;
    }
}


