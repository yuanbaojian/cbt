package com.ybj.cbt;

import org.apache.commons.math3.analysis.function.Tan;

public class Assignment {

    public static void main(String[] args) {
        Tank tank1=new Tank();
        Tank tank2=new Tank();
//        tank1.a=1;
//        tank2.a=2;
//        tank1.a=tank2.a;
//        System.out.println("t1 " + tank1.a);
//        System.out.println("t2 " + tank2.a);

        tank1.a=2;
        changeValue(tank1);
        System.out.println("tank2 = " + tank1.a);

    }

    public static void changeValue(Tank tank){
        tank.a++;
    }

}

class Tank{

    int a;

}
