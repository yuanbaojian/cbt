package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import org.junit.Test;

import javax.lang.model.SourceVersion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
//       相当于 构造函数， 没有传参？？？
        Runnable r2 = () -> System.out.println("Howdy, world!");
        r2.run();
    }




}
