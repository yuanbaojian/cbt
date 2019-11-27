package com.ybj.cbt;

public class Reference {
      public static void main(String[] args) {
//          Person p1 = new Person();
//          System.out.println("p1 = " + p1);
//          p1.age=2;
//          change(p1);
//          System.out.println("p1.age = " + p1.age);

          String a="123";
          a="456";
          System.out.println("a = " + a);
      }
      public static void change(Person p2) {
//          p2=new Person();
//          System.out.println("p2 = " + p2);
//          p2.age++;
     }
 }

 class Person {
     int age=0;
 }
/**
 * p1 = com.ybj.cbt.Person@22d8cfe0
 * p2 = com.ybj.cbt.Person@579bb367
 * p1.age = 2
 */
