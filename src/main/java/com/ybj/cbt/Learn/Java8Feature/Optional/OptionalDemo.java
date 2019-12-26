package com.ybj.cbt.Learn.Java8Feature.Optional;

import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @Author OptionalDemo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class OptionalDemo {

    @Test
    public void test(){
        Optional optional=Optional.of(1);

        optional.isPresent();
    }
}
