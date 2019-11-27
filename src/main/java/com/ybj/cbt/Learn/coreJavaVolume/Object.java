package com.ybj.cbt.Learn.coreJavaVolume;

import org.junit.Test;

import java.util.ArrayList;

public class Object {


    /***     Object是所有对象的父类，
     * @param
     * @return void
     * @author baojian.yuan
     * @date 2019/11/8
     */
    @Test
    public void testObject(){
        ArrayList list=new ArrayList();
        int number=1;
        list.add(number);
        int number2= (int) list.get(0);
        System.out.println("number2 = " + number2);
    }

}
