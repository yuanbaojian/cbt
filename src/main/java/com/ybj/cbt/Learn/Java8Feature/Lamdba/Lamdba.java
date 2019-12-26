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
        list.forEach(word -> System.out.println("word = " + word));
    }

    @Test
    public void testWithAnonymousClass(){
        Traverse traverse= new Traverse(){

            @Override
            public void traverseList() {
                System.out.println(" = " );
            }
        };
        traverse.traverseList();

    }

  public static void main(String[] args) {
      Traverse traverse= new Traverse(){

          @Override
          public void traverseList() {
              System.out.println(" = " );
          }
      };
      traverse.traverseList();
  }
}

@FunctionalInterface
interface  Traverse{
    public abstract void traverseList();
}
