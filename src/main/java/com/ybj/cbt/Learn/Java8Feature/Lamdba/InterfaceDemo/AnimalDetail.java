package com.ybj.cbt.Learn.Java8Feature.Lamdba.InterfaceDemo;

import com.ybj.cbt.Learn.Java8Feature.Lamdba.AnonymousClass;
import org.junit.Test;

public class AnimalDetail {


    // 1.声明并实例化变量，传递对象
    @Test
    public void test(){
        Animal dog=new Dog();
        animalMove(dog);
    }

    public static void animalMove(Animal animal){
        animal.move("旺财");
    }

}
