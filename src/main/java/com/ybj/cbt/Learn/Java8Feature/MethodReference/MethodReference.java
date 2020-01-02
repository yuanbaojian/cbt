package com.ybj.cbt.Learn.Java8Feature.MethodReference;

import com.ybj.cbt.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 特殊的lamdba表达式
 * 方法引用符号 ::
 * 引用现有的方法
 **/
public class MethodReference {

    public static void saySomething(){
        System.out.println("Hello, this is static method.");
    }

    public static void main(String[] args) {
        MethodReference methodReference=new MethodReference();
    }

  /**
   * Consumer本身就是个范型类
   * */
    @Test
    public void test() {
      List<String> messages = Arrays.asList("Hello", "baeldung", "readers!");
      messages.forEach(string -> string=StringUtils.capitalize(string));
    }


    // 1.方法引用, 如果是lambda ，参数是一个animal类，函数体是animal.move，  这个参数可以省略，
    // 直接写函数体就行，用方法引用的方式写
    @Test
    public void testMethodReference(){
        animalMove(System.out::print);
    }

    static void animalMove(animal animal){
        animal.move("狗用腿跑");
    }


}


interface animal{
    void move(String s);
 }
