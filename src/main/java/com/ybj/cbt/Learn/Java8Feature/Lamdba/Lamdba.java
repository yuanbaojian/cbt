package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import com.ybj.cbt.Learn.Java8Feature.Lamdba.InterfaceDemo.Animal;

public abstract class Lamdba {

  public static void main(String[] args) {
    AnonymousClass.animalMove(name -> System.out.println(name + "是一只狗," + "用腿跑"));
  }

}


