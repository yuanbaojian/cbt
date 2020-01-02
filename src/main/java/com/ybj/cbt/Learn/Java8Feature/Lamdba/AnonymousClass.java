package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import com.ybj.cbt.Learn.Java8Feature.Lamdba.InterfaceDemo.Animal;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AnonymousClass {

    @Test
    public void testAwt(){
        //      匿名类的最早用法， 图形界面的监听事件： 监听后，调用一个函数
        Button button=new Button();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello");
            }
        });
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



    public static void main(String[] args) {
        animalMove(new Animal() {
            @Override
            public void move(String name) {
                System.out.println(name + "是一只狗," +"用腿跑");
            }
        });
    }


    //     1.形参是 类对象 的静态方法
    static void animalMove(Animal animal){
        // 2.调用形参的 一个方法
        animal.move("旺财");
    }

}
