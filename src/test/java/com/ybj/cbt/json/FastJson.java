package com.ybj.cbt.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/***  主要研究fastjosn的使用
 * @param
 * @return
 * @author baojian.yuan
 * @date 2019/11/8
 */
public class FastJson {

  public static void main(String[] args) {
      Person person=new Person("张三", 22);
      String a1Json = JSON.toJSONString(person);
      System.out.println(a1Json);
  }

    /**
     *   jsonString  数组  -->   jsonObject  数组
      */
    @Test
    public void fastJson_StringToJsonObject(){
        String jsonString = "[{'name':'张三','age':26},{'name':'三','age':36}]";
        JSONArray jsonArray=JSONArray.parseArray(jsonString);
        for(int i = 0; i < jsonArray.size(); i++) {
            String name = jsonArray.getJSONObject(i).getString("name");
            System.out.println("name = " + name);
        }
    }


    /**
     *   jsonString转对象
     */
    @Test
    public void jsonStringToBean(){
        String jsonString = "{'name':'张三','age':26}";
        Person person=JSONObject.parseObject(jsonString,Person.class);
        System.out.println("person = " + person);
    }

    /**
     *   java对象  --->  jsonString
     */
    @Test
    public void toJsonString(){
        Person person=new Person("张三", 22);
        String a1Json = JSON.toJSONString(person);
        System.out.println(a1Json);
    }

}


@Getter
@Setter
class Person {

    String name;
    int age;

    public Person(String name, int age) {
        this.name=name;
        this.age=age;
    }

//    没有默认构造器，可能会出错
    public Person() {
    }
}
