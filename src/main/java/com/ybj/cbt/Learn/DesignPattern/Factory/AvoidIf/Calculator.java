package com.ybj.cbt.Learn.DesignPattern.Factory.AvoidIf;

import org.junit.Test;

import java.util.Calendar;

/**
 * @Author Calculator
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Calculator {

    @Test
    public void test(){
        int result = Calculator.calculate(1, 2, "minus");
        System.out.println("result = " + result);
    }

    public static int calculate(int a, int b ,String operator){
        Operation operation=OperatorFactory.getOperation(operator).orElseThrow(() -> new IllegalArgumentException("无效参数"));
        return operation.apply(a, b);
    }

}
