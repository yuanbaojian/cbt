package com.ybj.cbt.Learn.CleanCode.Ch9;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/***  断言
 * @param
 * @return
 * @author baojian.yuan
 * @date 2019/12/6
 * @time 13:02
 */
public class Assert {

    /**
     * * 如果报错， 则会抛出异常
     * java.lang.AssertionError:
     * Expected :1
     * Actual :2
     *
     * @param
     * @return void
     * @author baojian.yuan
     * @date 2019/12/6
     * @time 13:22
     */
    @Test
    public void testAssert() {
        int a = 1;
        int b = 2;
        int c = 1;
        String d = "1";
//        assertEquals(a, b);
        assertEquals(a, d);
    }
}
