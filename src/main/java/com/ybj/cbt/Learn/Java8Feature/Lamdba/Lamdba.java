package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import com.ybj.cbt.Learn.Java8Feature.Lamdba.InterfaceDemo.Animal;
import com.ybj.cbt.Learn.Java8Feature.Lamdba.InterfaceDemo.AnimalDetail;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class Lamdba {

  public static void main(String[] args) {
    AnimalDetail.animalMove(name -> System.out.println(name + "是一只狗," + "用腿跑"));
  }

  @Test
  public void testComparator(){
    List list=new LinkedList<Integer>();
    list.add(1);
    list.add(4);
    list.add(7);
    list.add(2);
    list.add(3);
    list.add(9);

    list.sort(new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        return (Integer) o2 - (Integer) o1;
      }
    });
    System.out.println("list = " + list);
    list.forEach( o -> System.out.println("o = " + o));

  }

}


