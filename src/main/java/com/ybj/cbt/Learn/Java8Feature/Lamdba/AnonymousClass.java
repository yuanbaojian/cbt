package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AnonymousClass {

    @Test
    public void test(){

        //      匿名类的最早用法， 图形界面的监听事件： 监听后，调用一个函数
        Button button=new Button();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello");
            }
        });

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Howdy, world!");
            }
        };
        r.run();

    }

  @Test
  public void testL() {
        Runnable r2 = () -> System.out.println("Howdy, world!");
        r2.run();
    }

    @Test
    public void testComparator(){
        List list=new LinkedList<Integer>();
        list.add(1);
        list.add(4);
        list.add(7);
        list.add(2);
        list.add(3);
        list.add(9);

        list.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o2 - (Integer) o1;
            }
        });
        System.out.println("list = " + list);
        list.forEach((Consumer<Integer>) o -> System.out.println("o = " + o));

    }




}
