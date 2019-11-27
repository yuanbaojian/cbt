package com.ybj.cbt.Learn.thread.day1;

public class DeadLock {

    public static void main(String[] args) {

        Runnable runnable1=() ->{
            synchronized("A"){
                System.out.println("有了A, 等待B");
                synchronized("B"){
                    System.out.println("A 同时有了A和B");
                }
            }
        };

        Runnable runnable2=() ->{
            synchronized("B"){
                System.out.println("有了B, 等待A");
                synchronized("A"){
                    System.out.println("B 同时有了A和B");
                }
            }
        };

        Thread t1=new Thread(runnable1);
        Thread t2=new Thread(runnable2);

        t1.start();
        t2.start();


    }

}
