package com.example.scdemo.gateway.demo;

import java.util.function.Predicate;

/**
 * 演示JDK8中的Predicate
 */
public class PredicateDemo {
    public static void main(String[] args) {

        Person p = new Person("zhangsan", 20, "上海");

    }
}

class PersonAgeLarge18Predicate implements Predicate<Person>{
    @Override
    public boolean test(Person person) {
        return person.getAge()>18;
    }
}

class PersonLocationInShanghaiPredicate implements Predicate<Person>{
    @Override
    public boolean test(Person person) {
        return "上海".equals(person.getLocation());
    }
}

class Person {
    private String name;
    private Integer age;
    private String location;

    public Person(String name, Integer age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                '}';
    }
}
