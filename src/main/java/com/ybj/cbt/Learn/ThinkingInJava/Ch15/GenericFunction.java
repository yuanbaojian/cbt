package com.ybj.cbt.Learn.ThinkingInJava.Ch15;

import org.junit.Test;

import java.io.File;

public class GenericFunction {

    /***   就算没有返回值，  也要加上<T>, 表示使用范型
     * @param t
     * @return void
     * @author baojian.yuan
     * @date 2019/11/21
     */
    public static <T> void genericFunction(T t){
        System.out.println("t = " + t);
    }

    @Test
    public void testGenericFunction(){
        genericFunction("String");
        genericFunction(1);
        genericFunction(1.33);
        genericFunction(true);
    }

    public static <T> void multiParameterGenericFunction(T ... t){
        for (T t1 : t) {
//          System.out.println("t1 = " + t1.getClass().getName());
            System.out.println("(t1 instantof String) = " + (t1 instanceof String));
            System.out.println("(t1 instantof String) = " + (t1 instanceof File));
        }
    }

    public static <T> void multiParameterGenericPlusFunction(T ... t){
        String result="";
        for (T t1 : t) {
            result+=t1;
        }
        System.out.println("result = " + result);
    }

    @Test
    public void testMultiParameterGenericFunction(){
        File file=new File("");
        multiParameterGenericFunction(file,1,1,"2");
    }

    @Test
    public void testMultiParameterGenericPlusFunction(){
        multiParameterGenericPlusFunction(1,1,"2");
    }


}
