package com.ybj.cbt.Learn.ThinkingInJava.Ch3;

import org.junit.Test;

public class CastingNumber {


    @Test
    public void casting(){
        double number=1.7;
        System.out.println("Math.round(number) = " + Math.round(number));
    }

    @Test
    public void MaxInt(){
        int number=Integer.MAX_VALUE;
        int big=number*4;
        System.out.println("big = " + big);
    }

    /*** 只跳出当前循环
     * @param
     * @return void
     * @author baojian.yuan
     * @date 2019/11/6
     */
    @Test
    public void breakFor(){
        int number1=10;
        int number2=10;
        for (int i = 0; i < number1; i++) {
            for(int j = 0; j < number2; j++) {
                if( j>=0 ){
                    break;
                }
            }
            System.out.println("i = " + i);
        }
    }

}
