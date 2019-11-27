package com.ybj.cbt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;


public class JacksonUtilsTest {


    /**
     *  对象转String
     */
    @Test
    public void objectToString(){
        Teacher teacher=new Teacher("张三", 11);
//        teacher.setName("张三");
//        teacher.setAge(11);
        String s = JacksonUtils.objectToJsonString(teacher);
        System.out.println("s = " + s);
    }

    /**
     *  String  转  object
     */
    @Test
    public void stringToObject() {
        String jsonString = "{'name':'张三' , 'age':26}";
        ObjectMapper objectMapper = new ObjectMapper();
        Teacher teacher=JacksonUtils.stringToObject(jsonString,Teacher.class);
        System.out.println("teacher = " + teacher);
    }

    /**
     *  jsonString 数组  转   object数组
     */
    @Test
    public void StringArrayToObjectArray() throws JsonProcessingException {
        String jsonString = "[{'name':'张三' , 'age':26},{'name':'李四' , 'age':29}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Teacher[] teacherList = JacksonUtils.stringToObject(jsonString, Teacher[].class);
        for (Teacher teacher : teacherList) {
          System.out.println(teacher.toString());
        }
    }

//    @Test
//    public void StringToJsonObject(){
//        String jsonString = "{'name':'张三' , 'age':26}";
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject jsonObject=objectMapper.
//    }

}


@Getter
@Setter
class  Teacher{
    String name;
    int age;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
       return  name + " "  + age ;
    }
}
