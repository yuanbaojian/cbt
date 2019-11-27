//package com.ybj.cbt.json;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ybj.cbt.utils.Teacher;
//import org.junit.Test;
//
///**
// * Jackson的使用
// */
//public class Jackson {
//
//    @Test
//    public void testJsonStringToObject() throws JsonProcessingException {
//        ObjectMapper objectMapper=new ObjectMapper();
//        String jsonString = "{'name':'张三','age':26}";
//        Teacher teacher2=objectMapper.readValue(jsonString, Teacher.class);
//        System.out.println("teacher2 = " + teacher2);
//    }
//
//    @Test
//    public void ObjectToJsonString() throws JsonProcessingException {
//        ObjectMapper objectMapper=new ObjectMapper();
//        Teacher teacher=new Teacher("李四", 33);
//        String s = objectMapper.writeValueAsString(teacher);
//        System.out.println("s = " + s);
//    }
//
//
//}
////