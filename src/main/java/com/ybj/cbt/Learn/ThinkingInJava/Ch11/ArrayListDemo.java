package com.ybj.cbt.Learn.ThinkingInJava.Ch11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.print;


/**
 * @Author ArrayListDemo
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ArrayListDemo {

    @Test
    public void testArrayList(){
        ArrayList arrayList=new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println("arrayList = " + arrayList);
        Collection collection;
        List list;
    }

    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println("arrayList = " + arrayList);
        Collection collection;
        List list;
        print(arrayList);
    }
}
