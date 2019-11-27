package com.ybj.cbt.Learn.ThinkingInJava.Ch18;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest {

    @Test
    public void testFileInputStream() throws IOException {
    String fileSrc = "C:\\Users\\baojian.yuan\\Desktop\\test\\test.txt";
        FileInputStream fileInputStream=new FileInputStream(fileSrc);
        int b=fileInputStream.read();
        while(b!=-1){
            System.out.print( (char)b);
            b=fileInputStream.read();
        }
    }

}
