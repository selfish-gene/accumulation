package com.selfish.gene.innerclass.non_static_inner_class;

/**
 * Created by Administrator on 2017/5/26.
 */
public class Cow {
    private double weight;

    public Cow() {
    }

    public Cow(double weight) {
        this.weight = weight;
    }

    public void test(){
        CowLeg cowleg = new CowLeg(1.12, "黑白相间");
        cowleg.info();
    }

    public static void main(String[] args) throws Exception {
        Cow cow = new Cow(378.9);
        cow.test();
    }

    /**
     * 在非静态内部类对象里，保存了一个它所寄生的外部类对象的引用
     * 当调用非静态内部类的实例方法时，必须有一个非静态内部类实例，非静态内部类实例必须寄生在外部类实例里
     */
    private class CowLeg {
        private double length;

        private String color;

        public CowLeg() {
        }

        public CowLeg(double length, String color) {
            this.length = length;
            this.color = color;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
        public void info(){
            System.out.println("当前牛腿颜色：" + color + "，高" + length);
            System.out.println("本牛腿所在奶牛的体重为：" + weight);
        }
    }
}
