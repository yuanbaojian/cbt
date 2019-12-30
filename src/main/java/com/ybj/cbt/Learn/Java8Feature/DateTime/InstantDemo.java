package com.ybj.cbt.Learn.Java8Feature.DateTime;

import org.junit.Test;

import javax.lang.model.SourceVersion;
import java.time.Instant;

public class InstantDemo {

    @Test
    public void test(){
        Instant instant=Instant.now();
        System.out.println("instant = " + instant);
    }
}
