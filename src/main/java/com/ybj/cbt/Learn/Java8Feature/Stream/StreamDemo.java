package com.ybj.cbt.Learn.Java8Feature.Stream;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {


    @Test
    public void test(){
        //  1.获得数据源
        List<Integer>  numberList= new LinkedList<>();
        numberList.add(1);
        numberList.add(6);
        numberList.add(3);
        numberList.add(3);
        numberList.add(66);
        numberList.add(4);

        //  2.创建流
        Stream stream=numberList.stream();

        //  3.对流进行操作
        stream=stream.distinct().sorted((number1, number2)-> ((Integer) number1> (Integer) number2 ? -1:1));

        //  4.得到结果
        List numberList2 = (List) stream.collect(Collectors.toList());
        for (Object o : numberList2) {
          System.out.println("o = " + o);
        }

    }


    @Test
    public void test2(){
        List<String> stringList=new LinkedList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("cat");
        stringList.add("eat");
        stringList.add("dog");


        Stream stream=stringList.stream();
        Stream sortedStream2 = stream.sorted((string1 ,string2) -> -((String)string1).compareTo((String)string2) );

        sortedStream2.forEach(item -> System.out.println("item = " + item));
    }

    @Test
    public void testFilter(){
        List<String> stringList=new LinkedList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("cat");
        stringList.add("eat");
        stringList.add("dog");

        Stream stream=stringList.stream();
        Stream filteredStream=stream.filter(string -> ((String) string).startsWith("e"));
        filteredStream.forEach(item -> System.out.println("item = " + item));

    }


    @Test
    public void testMap(){
        List<String> stringList=new LinkedList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("cat");
        stringList.add("eat");
        stringList.add("dog");

        Stream stream=stringList.stream();
        Stream mapedStream = stream.map(item -> "test " + (String) item);
        mapedStream.forEach(item -> System.out.println("item = " + item));
    }

    @Test
    public void testCollect(){
        List<String> stringList=new LinkedList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("cat");
        stringList.add("eat");
        stringList.add("dog");
        Stream stream=stringList.stream();
        //      收集器， 得到结果
        List list = (List) stream.map(item -> "test" + item)
                .collect(Collectors.toList());
        for (Object o : list) {
          System.out.println("o = " + o);
        }
    }


    @Test
    public void testStream(){
        List<Person> personList=new ArrayList<>();
        personList.add(new Person("张三", 10) );
        personList.add(new Person("李四", 23) );
        personList.add(new Person("李1", 20) );
        personList.add(new Person("李2", 33) );
        personList.add(new Person("李3", 21) );
        personList.add(new Person("王五", 29) );
        personList.add(new Person("王二麻子", 28) );

        List<Person>  list=personList.stream()
                //1.过滤， 留下满足条件的元素
                .filter(person -> person.getAge() > 18)
                //2.排序， 根据条件排序
                .sorted((person1, person2) -> person1.getAge() > person2.getAge() ? 1 : -1)
                //3.处理， 对每个元素进行处理
                .map(person -> new Person("他是" + person.getName(), person.getAge()))
                //4.收集， 把流转换成外部实体
                .collect(Collectors.toList());
        for (Person person : list) {
            System.out.println( person.toString());
        }
    }

    @Test
    public void testStreamWithoutLamdba(){
        List<Person> personList=new LinkedList<>();
        personList.add(new Person("张三", 20) );
        personList.add(new Person("张三", 20) );
        personList.add(new Person("李四", 13) );
        personList.add(new Person("王五", 29) );
        personList.add(new Person("王二麻子", 28) );
        List<Person> personList1 = personList.stream()
                .distinct()
                // 1.过滤， 留下满足条件的元素
                .filter(person -> person.getAge() > 18)
                .sorted((o1, o2) -> o1.age > o2.age ? 1 : -1)
                .collect(Collectors.toList());
        for (Person person : personList1) {
          System.out.println("person.toString() = " + person.toString());
        }
    }



}

@Getter
@Setter
class Person{

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
