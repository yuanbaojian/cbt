package com.ybj.cbt.Learn.thread.day1;

public class DemoLock2 {

    public static void main(String[] args) {

        Runnable runnable1=() ->{
            synchronized("A"){
                System.out.println("有了A, 等待B");
                try {
//                    释放A锁, 进入等待队列( 阻塞态)
                    "A".wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
//                    唤醒所有需要"A"的锁
                    "A".notifyAll();
                }
            }
        };

        Thread t1=new Thread(runnable1);
        Thread t2=new Thread(runnable2);

        t1.start();
        t2.start();


    }

}
