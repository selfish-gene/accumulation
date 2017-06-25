package com.selfish.gene.annotation.customize_annotation.demo_apt;

/**
 * Created by Administrator on 2017/6/25.
 */
@Persistent(table = "p_info")
public class Person {
    @Id(column = "id",type = "integer",generator = "identity")
    private int id;

    @Property(column = "p_name", type = "string")
    private String name;

    @Property(column = "p_age", type = "integer")
    private int age;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
