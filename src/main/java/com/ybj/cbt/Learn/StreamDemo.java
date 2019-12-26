package com.ybj.cbt.Learn;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author StreamDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class StreamDemo {

    @Test
    public void test(){
        List list=new LinkedList<String>();
        list.add("hello");
        list.add("world");
        Stream stream=Stream.of(list);
        System.out.println("stream = " + stream);
        for (Object o : list) {
          System.out.println("o = " + o);
        }
    }

    @Test
    public void test2(){

    }
    
}
