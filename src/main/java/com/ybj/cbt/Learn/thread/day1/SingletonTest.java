package com.ybj.cbt.Learn.thread.day1;

public class SingletonTest {



    public static void main(String[] args) {
        Runnable runnable=() ->{
            Boss.getBoss();
        };

        for(int i=0; i<100;i++){
           new Thread(runnable).start();
        }
    }
}

class Boss{

    private Boss(){
        System.out.println("一个Boss实例 被实例化了");
    }
    private static Boss Instance=null;

//    使用类锁，解决懒汉式单例在多线程中的问题
    public static synchronized Boss getBoss(){
        if(Instance==null){
            Instance=new Boss();
        }
        return Instance;
    }
}
