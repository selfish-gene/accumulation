package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/16.
 */
public class People {

    private final Name name;

    public People(Name name) {
        this.name = new Name(name.getFristName(), name.getLastName());
//        this.name = name;
    }

    public Name getName() {
        return new Name(name.getFristName(), name.getLastName());
//        return name;
    }

    public static void main(String[] args) throws Exception {
        Name n = new Name("Allen", "al");
        People p = new People(n);
        System.out.println(p.getName().getFristName()); // Allen
        n.setFristName("abc");
        System.out.println(p.getName().getFristName()); // Allen
    }
}
