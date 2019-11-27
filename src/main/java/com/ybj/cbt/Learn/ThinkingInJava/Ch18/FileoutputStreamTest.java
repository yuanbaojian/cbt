package com.ybj.cbt.Learn.ThinkingInJava.Ch18;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileoutputStreamTest {

    /***
     *    write()  方法可写入数据
     * @param
     * @return void
     * @author baojian.yuan 
     * @date 2019/11/25
     */
    @Test

    public void  testOutputStream() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\baojian.yuan\\Desktop\\fileOutputStream.txt");
        String name="hello world";
        fileOutputStream.write(name.getBytes());
        fileOutputStream.close();
    }

}
