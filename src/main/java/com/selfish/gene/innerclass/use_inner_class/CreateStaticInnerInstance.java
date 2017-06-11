package com.selfish.gene.innerclass.use_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class CreateStaticInnerInstance {
    public static void main(String[] args) throws Exception {
        StaticOut.StaticIn staticIn = new StaticOut.StaticIn();
    }
}

class StaticOut {
    static class StaticIn{
        public StaticIn() {
            System.out.println("Static Inner Class Test");
        }
    }
}
