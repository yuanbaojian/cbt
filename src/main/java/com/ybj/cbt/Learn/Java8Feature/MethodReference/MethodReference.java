package com.ybj.cbt.Learn.Java8Feature.MethodReference;

import com.ybj.cbt.utils.FileUtils;
import org.junit.Test;

import java.nio.file.DirectoryStream;

/**
 * @Author MethodReference
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class MethodReference {

    interface Sayable{
        void say();
    }


    public static void saySomething(){
        System.out.println("Hello, this is static method.");
    }

    public static void main(String[] args) {
        // Referring static method
        Sayable sayable = MethodReference::saySomething;
        // Calling interface method
        sayable.say();
    }
}
