package com.ybj.cbt.Learn.CleanCode.Ch7;

import org.junit.Test;

import javax.lang.model.SourceVersion;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Author TryCatch
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class TryCatch {

    @Test
    public void testTry(){
        try {
            File file=new File("C:\\33\33\\2.txt");
            FileInputStream fileInputStream=new FileInputStream(file);
            System.out.println("读取输入流没报错" );
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("执行 finally 语句" );
        }
        System.out.println("执行完了try catch语句" );
    }

}
