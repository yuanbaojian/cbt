package com.ybj.cbt.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

/***  使用  顺丰包
 * @param
 * @return
 * @author baojian.yuan
 * @date 2019/11/7
 */
public class SF_Json {


    /**
     *   json字符串组 --->  jsonArray
     */
    @Test
    public void testJsonArray(){
        String json = "[{'name':'张三','age':26},{'name':'李四', 'age':27 }]";
        JSONArray jsonArray=JSONArray.fromObject(json);
        for(int i = 0; i < jsonArray.size(); i++) {
            String name = jsonArray.getJSONObject(i).getString("name");
            int age = jsonArray.getJSONObject(i).getInt("age");
            System.out.println("name = " + name);
            System.out.println("age = " + age);
        }
    /** output *
     *  name = 张三 age = 26
     *  name = 李四 age = 27 */
    }


    /**
     *  jsonString  ---> jsonObject
     */
    @Test
    public void jsonString(){
        String json = "{'name':'张三','age':26}";
        JSONObject obj = JSONObject.fromObject(json);
        String name = obj.getString("name");
        int age = obj.getInt("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    /** output
     * name = 张三
     * age = 26 */
    }

    @Test
    public void jsonStringToBean(){
        String json = "{'name':'张三','age':26}";
        JSONObject obj= JSONObject.fromObject(json);
        Student student= (Student) JSONObject.toBean(obj,Student.class);
        System.out.println("student = " + student);
    }


}

class Student{
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
