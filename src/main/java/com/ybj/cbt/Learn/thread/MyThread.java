package com.ybj.cbt.Learn.thread;

public class MyThread extends Thread {

    public MyThread(String name) {
        //给Thread类的name赋值
        super(name);
    }

    @Override
    public void run() {
        System.out.println("MyThread - START "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static void main(String[] args) {
        MyThread test=new MyThread("ybj");
        test.run();
    }

}