package com.ybj.cbt.Learn.Java8Feature.Lamdba;

import org.junit.Test;
import org.junit.runners.parameterized.TestWithParameters;

import java.util.LinkedList;
import java.util.List;

public abstract class Lamdba {

    @Test
    public void test(){
        List list=new LinkedList<String>();
        list.add("hello ");
        list.add("world ");
        list.add("!!! ");
        list.forEach(word -> System.out.println("word = " + word ));
    }



//    抽象类不能在@Test里 实例化， 所以放在main中测试
  public static void main(String[] args) {
      List list=new LinkedList<String>();
      list.add("hello ");
      list.add("world ");
      list.add("!!! ");

      Traverse traverse= new Traverse(){
          @Override
          public void traverseList(List list) {
              for (Object o : list) {
                System.out.println("o = " + o);
              }
          }
      };
      traverse.traverseList(list);
  }
}

interface  Traverse{
    public abstract void traverseList(List list);
}
