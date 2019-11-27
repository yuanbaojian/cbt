package com.ybj.cbt.ch3;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

public class Equals {
    public static void main(String[] args) {
        String n1=new String("123");
        String n2=new String("123");
        System.out.println(" (n1==n2 = " +  (n1.equals(n2)));
        /**
         *     public boolean equals(Object anObject) {
         *         if (this == anObject) {
         *             return true;
         *         }
         *         if (anObject instanceof String) {
         *             String anotherString = (String)anObject;
         *             int n = value.length;
         *             if (n == anotherString.value.length) {
         *                 char v1[] = value;
         *                 char v2[] = anotherString.value;
         *                 int i = 0;
         *                 while (n-- != 0) {
         *                     if (v1[i] != v2[i])
         *                         return false;
         *                     i++;
         *                 }
         *                 return true;
         *             }
         *         }
         *         return false;
         *     }
         *      output: true
         */
    }
    
    @Test
    public void  TestEquals(){
        Value v1=new Value();
        Value v2=new Value();
        v1.i=100;
        v2.i=100;
        System.out.println("v1.equals(v2) = " + v1.equals(v2));
        /**
         *    public boolean equals(Object obj) {
         *         return (this == obj);
         *     }
         *     output : false
         */
    }

    @Test
    public void LongEquals(){
        Long n1=new Long(111);
        Long n2=new Long(111);
        System.out.println(" (n1==n2 = " +  (n1.equals(n2)));
        /**
         *     public boolean equals(Object obj) {
         *         if (obj instanceof Long) {
         *             return value == ((Long)obj).longValue();
         *         }
         *         return false;
         *     }
         *     output: true
         */
    }

    @Test
    public void DirectAddress(){
        int d2=0x2f;
        System.out.println("Integer.toBinaryString(d2) = " + Integer.toBinaryString(d2));
    }
}

// 注释是非常重要的，减少不必要的注释

class Value{
    int i;
}