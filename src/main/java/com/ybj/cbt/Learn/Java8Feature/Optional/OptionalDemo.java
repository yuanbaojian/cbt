package com.ybj.cbt.Learn.Java8Feature.Optional;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;

/**
 *  为了避免nullPointException的错误
 *  可选对象
 *  使用时， 使用Optional的静态方法最好， 不用声明一个对象，没有必要
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class OptionalDemo {

    @Test
    public void test(){
        Optional optional=Optional.of(1);
        Optional<soundCard> soundCardOptional;
        optional.isPresent();
    }

    /**
    *  创建 可选对象
    * */
    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        assertFalse("空的",empty.isPresent());
    }


    @Test
    public void testOf(){
        Optional<String> stringOptional=Optional.ofNullable(null);
        // 1.匿名类
        stringOptional.ifPresent(new Consumer(){
            @Override
            public void accept(Object o) {
                System.out.println("长度为"+((String)o).length());
            }
        });
        // 2.lamdba语法  能够实现自动对象转换？？？？
        stringOptional.ifPresent(item->System.out.println("lamdba表达式中的长度 = " + item.length()));
        System.out.println("stringOptional.isPresent() = " + stringOptional.isPresent());

        // 3.如果不存在， 用自定义值代替
        String value=stringOptional.orElse("eee");
        System.out.println("value = " + value);
    }

}



@Getter
@Setter
class soundCard{
    String brand;
    Long serialNumber;
}
