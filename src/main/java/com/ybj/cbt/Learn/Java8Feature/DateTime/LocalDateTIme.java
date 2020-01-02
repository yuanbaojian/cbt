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
        //localDate = 2019-12-23

        LocalDate localDate2=LocalDate.of(2019, 7, 31);
        System.out.println("localDate2 = " + localDate2);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("previousMonthSameDay = " + previousMonthSameDay);
    }

    @Test
    public void localTimeDemo(){
        LocalTime localTime= LocalTime.now();
        System.out.println("localTime = " + localTime);
        //localTime = 22:17:53.792

        // 这个构造器是私有的，所以不能使用
        //  LocalTime localTime1=new LocalTime(23,4,33,0);
        LocalTime localTime1=LocalTime.of(23,3,4);
        System.out.println("localTime1 = " + localTime1);
        //localTime1 = 23:33
    }

    @Test
    public void localDateTimeDemo(){
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
        //localDateTime = 2019-12-23T22:22:32.358
//        LocalDateTime localDateTime2=LocalDateTime.parse("2019-12-23T22:22:32.358", DateTimeFormatter.of)

        LocalDateTime localDateTime1=LocalDateTime.of(2011,1,1,1,1,1);
        System.out.println("localDateTime1 = " + localDateTime1);
        String localDate=localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("localDate = " + localDate);
        //localDate = 2019/12/23
    }

}
