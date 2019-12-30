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
 * 调用现有的方法
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
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


}


class Utils {

    public static int countElements(Object[] array) {
        return array.length;
    }
}
