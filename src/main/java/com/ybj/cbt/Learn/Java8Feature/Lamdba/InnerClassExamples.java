package com.ybj.cbt.Learn.Java8Feature.Lamdba;

/**
 *  非静态外部类
 * */
class InstanceOuter {
    public InstanceOuter(int xx) { x = xx; }

    private int x;

    class InstanceInner {
        public void printSomething() {
            System.out.println("The value of x in my outer is " + x);
        }
    }
}

/**
 *  静态外部类
 * */
class StaticOuter {
    private static int x = 24;

    static class StaticInner {
        public void printSomething() {
            System.out.println("The value of x in my outer is " + x);
        }
    }
}

/**
 * @author baojian.yuan
 */
public class InnerClassExamples {
    public static void main(String... args) {
        InstanceOuter io = new InstanceOuter(12);

        InstanceOuter.InstanceInner ii = io.new InstanceInner();
        ii.printSomething(); // prints 12

        StaticOuter.StaticInner si = new StaticOuter.StaticInner();
        si.printSomething(); // prints 24
    }
}