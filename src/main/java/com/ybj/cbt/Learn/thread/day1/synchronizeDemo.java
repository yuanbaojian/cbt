package com.ybj.cbt.Learn.thread.day1;

import static com.ybj.cbt.Learn.thread.day1.TicketCenter.restCount;

public class synchronizeDemo {

    public static void main(String[] args) {
        Runnable r=() ->{
            while(restCount>0){
                //对象锁
//                类锁
                synchronized (synchronizeDemo.class){
                    if(restCount<=0){
                        return ;
                    }
                    System.out.println(Thread.currentThread().getName() + restCount--);
                }
            }
        };

        Thread t1=new Thread(r, "thread 1 ");
        Thread t2=new Thread(r, "thread 2 ");
        Thread t3=new Thread(r, "thread 3 ");
        Thread t4=new Thread(r, "thread 4 ");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
