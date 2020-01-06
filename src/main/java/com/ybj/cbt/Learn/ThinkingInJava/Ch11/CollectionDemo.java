package com.ybj.cbt.Learn.ThinkingInJava.Ch11;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author CollectionDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class CollectionDemo {

    @Test
    public void test(){
        List collection=new ArrayList();
    }

    @Test
    public void queueDemo(){
        Queue queue=new ConcurrentLinkedQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        System.out.println("queue = " + queue);
    }
    
    @Test
    public void ListDemo(){
        List list=new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");

        List list2=new ArrayList();
        list2.add("a");
        System.out.println("list = " + list);
    }
    
}
