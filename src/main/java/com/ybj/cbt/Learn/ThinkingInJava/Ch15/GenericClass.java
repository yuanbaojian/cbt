package com.ybj.cbt.Learn.ThinkingInJava.Ch15;

import org.junit.Test;

/***   范型类， 在类名后加<T> 表示是泛型类
 * @param null
 * @return
 * @author baojian.yuan
 * @date 2019/11/21
 */
public class GenericClass<T> {

    private T t;

    public <T> void sout(){
        System.out.println("t = " + t);
    }

    @Test
    public void testGenericClass(){
        GenericClass<String> Class1=new GenericClass<String>();
        Class1.t="I am String";
        System.out.println("Class1.t.getClass().getName() = " + Class1.t.getClass().getName());

        GenericClass<Integer> Class2=new GenericClass<Integer>();
        Class2.t=11;
        System.out.println("Class2.t.getClass().getName() = " + Class2.t.getClass().getName());
    }

}
