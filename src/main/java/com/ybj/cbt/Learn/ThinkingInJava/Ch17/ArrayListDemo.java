package com.ybj.cbt.Learn.ThinkingInJava.Ch17;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ArrayList
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> names = new LinkedList<>();

        names.add(1);
        names.add(2);
        names.forEach(Id ->
                        System.out.println("Id = " + Id)
        );
//        names.forEach(System.out::println);
    }
}
