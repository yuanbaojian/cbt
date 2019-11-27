package com.ybj.cbt.Learn.thread.day1;

import java.util.concurrent.locks.ReentrantLock;

import static com.ybj.cbt.Learn.thread.day1.TicketCenter.restCount;

public class LockDemo {


    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();

        Runnable r=() ->{
          while(restCount>0){
              lock.lock();
              if(restCount>0){
                  System.out.println(Thread.currentThread().getName() + restCount--);
              }
              lock.unlock();
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
