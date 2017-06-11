package com.selfish.gene.innerclass.anonymous_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class AnonymousInner {
    public void test(Device d){
        System.out.println("购买" + d.getName() + "花费了" + d.getPrice() + "元");
    }

    public static void main(String[] args) throws Exception {
        AnonymousInner a = new AnonymousInner();
        // 调用有参数的构造器创建Device匿名实现类的对象
        a.test(new Device("代步车") {
            @Override
            public double getPrice() {
                return 2688;
            }
        });
        // 调用无参数的构造器创建Device匿名实现类的对象
        Device d = new Device() {
            {
                System.out.println("匿名内部类初始化块...");
            }
            @Override
            public double getPrice() {
                return 1235.6;
            }

            @Override
            public String getName() {
                return "耳机";
            }
        };
        a.test(d);
    }
}

abstract class Device {
    private String name;
    public abstract double getPrice();

    public Device() {
    }

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
