package com.ybj.cbt;

public class stack {


    public static void main(String[] args) {
//        String a="test";
//        String b="test";
//
//        System.out.println("a==b" +  (a==b));
//        System.out.println("a.equals(b)" +  (a.equals(b)));

//        包装类 是要分配内存的
//        Integer a=new Integer(1);
//        Integer b=new Integer(1);
//        System.out.println("a==b" +  (a==b));
//        System.out.println("a.equals(b)" +  (a.equals(b)));
//
//        int a=1;
//        int b=1;
//        System.out.println("a==b" +  (a==b));
        PersonTest person=new PersonTest("a", 2);
        PersonTest person1=new PersonTest("a", 2);
        System.out.println("person.equals(person1)" + person.equals(person1));
        System.out.println("person==(person1)" + (person==person1));
        System.out.println("person.name.equals(person1.name)"+person.name.equals(person1.name));
        System.out.println("person.name==(person1.name)"+ (person.name==(person1.name)));
        System.out.println("person.age==(person1.age)"+ (person.age==(person1.age)));

        System.out.println("person.age==(person1.age)"+ (person.age==(person1.age)));
        System.out.println("person.name==(person1.name)"+ (person.name==(person1.name)));

    }


}

class PersonTest{
    String name;
    int age;

    public PersonTest(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
