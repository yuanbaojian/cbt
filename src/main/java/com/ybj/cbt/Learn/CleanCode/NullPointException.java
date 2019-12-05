package com.ybj.cbt.Learn.CleanCode;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class NullPointException {


    @Test
    public void testReturnNull() {
        List<Object> objects = Collections.emptyList();
        System.out.println("objects = " + objects);
    }
}
