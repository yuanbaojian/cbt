package com.ybj.cbt.Learn.Java8Feature.DefaultMethod;

public interface Moveable {

    default void move() {
        System.out.println("I 'm  moving");
    }

}
