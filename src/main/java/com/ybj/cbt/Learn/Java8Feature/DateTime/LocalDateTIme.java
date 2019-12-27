package com.ybj.cbt.Learn.Java8Feature.DateTime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author LocalDateTIme
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class LocalDateTIme {

    @Test
    public void LocalDateDemo(){
        LocalDate localDate= LocalDate.now();
        System.out.println("localDate = " + localDate);
//        localDate = 2019-12-23


        LocalDate localDate2=LocalDate.of(2015, 02, 20);
        System.out.println("localDate2 = " + localDate2);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay = " + previousMonthSameDay);

    }

    @Test
    public void localTimeDemo(){
        LocalTime localTime= LocalTime.now();
        System.out.println("localTime = " + localTime);
//localTime = 22:17:53.792

        LocalTime localTime1=LocalTime.parse("23:33");
        System.out.println("localTime1 = " + localTime1);
//        localTime1 = 23:33
    }

    @Test
    public void localDateTimeDemo(){
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
//        localDateTime = 2019-12-23T22:22:32.358

        String localDate=localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("localDate = " + localDate);
//        localDate = 2019/12/23

        // new还是  直接调用
        /**
         *  new : 实例化对象,然后调用对象里的方法或属性
         *  直接调用,  方法是static类型, 且 方法会返回一个new ***() ,相当于给你实例化对象
         */
        testReturn object = testReturn.getObject();
        System.out.println("object.number = " + testReturn.number);
    }

}
