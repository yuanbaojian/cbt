package com.ybj.cbt.Learn.CleanCode;

import org.junit.Test;

public class Assert {

    /***  断言测试，   assert condition
     *     true  万事大吉
     *     false  抛出AssertError异常，并输出错误信息
     * @param numberA
     * @param numberB
     * @return double
     * @author baojian.yuan
     * @date 2019/11/5
     */
    public static double numberAddition(Double numberA,Double numberB){
        assert numberA!=null :" numberA should not be null";
        assert numberB!=null :" numberB should not be null";
        return numberA+numberB;
    }

    @Test
    public void numberAdditionTest(){
        double sum = numberAddition(null, 2.0);
    System.out.println("sum = " + sum);
    }
    
}
