# Inner Class

[TOC]

大部分时候，类被定义成一个独立的程序单元。在某些情况下，也会把一个类放在另一个类的内部定义，这个定义在其他类内部的类就被称为内部类。内部类主要有如下作用：

- 内部类提供了更好的封装，可以把内部类隐藏在外部类之内，不允许同一个包中的其他类访问该类。
- 内部类成员可以直接访问外部类的私有数据，因为内部类被当成其外部类成员，同一个类的成员之间可以互相访问。但外部类不能访问内部类的实现细节，例如内部类的成员变量。
- 匿名内部类适合用于创建那些仅需要一次使用的类。

从语法角度来看，定义内部类与定义外部类的语法大致相同，内部类除了需要定义在其他类里面之外，还存在如下两点区别：

- 内部类比外部类可以多使用三个修饰符：private、protected、static——外部类不可以使用这三个修饰符。
- 非静态内部类不能拥有静态成员。

## 非静态内部类

非静态内部类在类内部定义，且不用static修饰，如下所示：

```java
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
		// 省略getter、setter方法
        public void info(){
            System.out.println("当前牛腿颜色：" + color + "，高" + length);
            System.out.println("本牛腿所在奶牛的体重为：" + weight);
        }
    }
}
```

编译后生成了两个class文件：Cow.class和Cow$CowLeg.class，反编译查看Cow$CowLeg.class文件的info方法如下：

```java
public void info()
{
  System.out.println("当前牛腿颜色：" + this.color + "，高" + this.length);
  // 访问外部类的成员变量，this指向当前外部类的实例
  System.out.println("本牛腿所在奶牛的体重为：" + Cow.access$000(this.this$0));
}

```
 如果**外部类成员变量**、**内部类成员变量**与**内部类里方法的局部变量**同名，则可通过使用this、外部类类名.this作为限定来区分。如下程序所示：

```java
public class DiscernVariable {
    private String prop = "Var of Outer";
    private class Inner{
        private String prop = "Var of Inner";
        public void info(){
            String prop = "Var of Local";
            //通过OuterClass.this.propName的形式访问外部类的实例变量
            System.out.println(DiscernVariable.this.prop);
            //通过this.propName的形式访问非静态内部类的实例变量
            System.out.println(this.prop);
            System.out.println(prop);
        }
    }
    public void test(){
        Inner inner = new Inner();
        inner.info();
    }
    public static void main(String[] args) throws Exception {
        new DiscernVariable().test();
    }
}
```

## 静态内部类

如果使用static来修饰一个内部类，则这个内部类就属于外部类本身，而不属于外部类的某个对象。

- **静态内部类不能访问外部类的实例成员，只能访问外部类的类成员。**


- **外部类不能直接访问静态内部类的成员，但可以使用静态内部类的类名作为调用者来访问静态内部类的类成员，也可以使用静态内部类对象作为调用者来访问静态内部类的实例成员。**

下面程序示范了这两条规则：

```java
public class StaticInnerClassDemo {
    private int prop1 = 5;
    private static int prop2 = 9;
    static class StaticInnerClass{
        private static int in1 = 5;
        private int in2 = 5;
        //静态内部类里可以包含静态成员
        private static int age;
        public void accessOuterProp(){
            // 静态内部类无法访问外部类的实例变量
//            System.out.println(prop1);
            System.out.println(prop2);
        }
    }
    public void accessInnerProp(){
        System.out.println(StaticInnerClass.in1);
        System.out.println(new StaticInnerClass().in2);
    }
}
```

## 使用内部类

### 在外部类内部使用内部类

在外部类内部定义内部类的子类与平常定义子类也没有太大的区别。唯一存在的一个区别是：**不要在外部类的静态成员（包括静态方法和静态初始化块）中使用非静态内部类，因为静态成员不能访问非静态成员**。

### 在外部类以外使用非静态内部类

变量命名格式：

```java
OuterClass.InnerClass varName
```

**由于非静态内部类的对象必须寄生在外部类的对象里，因此创建非静态内部类对象之前，必须先创建其外部类对象。**

初始化方式：

```java
OuterInstance.new InnerConstuctor()
```

案例如下：

```java
public class CreateInnerInstance {
    public static void main(String[] args) throws Exception {
        Out.In in = new Out().new In("Just for test");
    }
}
class Out {
    class In{
        public In(String msg){
            System.out.println(msg);
        }
    }
}
```

**当创建一个子类时，子类构造器总会调用父类的构造器，因此在创建非静态内部类的子类时，必须保证让子类构造器可以调用非静态内部类的构造器，调用非静态内部类的构造器时，必须存在一个外部类对象。**案例如下：

```java
public class SubClass extends Out.In {
    /**
     * 当创建一个子类时，子类构造器总会调用父类的构造器，因此在创建非静态内部类的子类时，
     * 必须保证让子类构造器可以调用非静态内部类的构造器，调用非静态内部类的构造器时，
     * 必须存在一个外部类对象。
     * @param out 外部类对象
     */
    public SubClass(Out out) {
        out.super("hello");
    }
}
```

### 在外部类以外使用静态内部类

变量命名格式（与非静态内部类一样）：

```
OuterClass.InnerClass varName
```

**因为静态内部类是外部类类相关的，因此创建静态内部类对象时无须创建外部类对象。**

初始化方式：

```java
new OuterClass.InnerConstructor()
```

案例：

```java
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
```

创建子类：

```java
public class StaticSubClass extends StaticOut.StaticIn {}
```

## 匿名内部类

匿名内部类适合创建那种只需要一次使用的类，创建匿名内部类时会立即创建一个该类的实例，这个类定义立即消失，匿
名内部类不能重复使用。定义匿名内部类的格式如下：

```java
new InterfaceToImpl() | Constructs(Vargs...)
{
  // body
}
```

从上面定义可以看出，匿名内部类必须继承一个父类，或实现一个接口，但最多只能继承一个父类，或实现一个接口。

关于匿名内部类还有如下两条规则：

- 匿名内部类不能是抽象类，因为系统在创建匿名内部类时，会立即创建匿名内部类的对象。因此不允许将匿名内部类定义成抽象类。
- 匿名内部类不能定义构造器。由于匿名内部类没有类名，所以无法定义构造器，但匿名内部类可以定义初始化块，可以通过实例初始化块来完成构造器需要完成的事情。

案例：

```java
public class AnonymousDemo {
    public void test(Product p){
        System.out.println("购买" + p.getName() + "花费了" + p.getPrice() + "元");
    }
    public static void main(String[] args) throws Exception {
        AnonymousDemo a = new AnonymousDemo();
        a.test(new Product() {
            @Override
            public double getPrice() {
                return 15567.2;
            }
            @Override
            public String getName() {
                return "苹果电脑";
            }
        });
    }
}
interface Product {
    double getPrice();
    String getName();
}
```

通过实现接口来创建匿名内部类时，匿名内部类也不能显式创建构造器，因此匿名内部类只有一个隐式的无参数构造器，故new接口名后的括号里不能传入参数值。但**通过继承父类来创建匿名内部类时，匿名内部类将拥有和父类相似的构造器，此处的相似指的是拥有相同的形参列表**。案例：

```java
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
```

在Java 8之前，Java要求被局部内部类、匿名内部类访问的局部变量必须使用final修饰，从Java 8开始这个限制被取消了，Java 8更加智能：**如果局部变量被匿名内部类访问，那么该局部变量相当于自动使用了final修饰。**例如如下程序。

```java
public class ADemo {
    public static void main(String[] args) throws Exception {
        int age = 5;
        A a = new A() {
            @Override
            public void test() {
                // 被匿名内部类或局部内部类访问的局部变量默认使用final修饰，
                // 因此该变量初始化之后不能被重新赋值
                System.out.println(age);
            }
        };
    }
}
interface A{
    void test();
}
```

## 局部内部类

如果把一个内部类放在方法里定义，则这个内部类就是一个局部内部类，局部内部类仅在该方法里有效。由于局部内部类不能在外部类的方法以外的地方使用，因此局部内部类也不能使用访问控制符和static修饰符修饰。案例如下：

```java
public class LocalInnerClass {
    public static void main(String[] args) throws Exception {
        class InnerBase {
            int a;
        }
        class InnerSub extends InnerBase {
            int b;
        }
        InnerSub sub = new InnerSub();
        sub.a = 5;
        sub.b = 8;
        System.out.println();
    }
}
```

编译上面程序，看到生成了三个class文件：LocallnnerClass.class、LocallnnerClass$1InnerBase.class
和LocallnnerClass$1InnerSub.class，这表明局部内部类的class文件总是遵循如下命名格式：

**OuterClass$NInnerClass.class。**

**注意到局部内部类的class文件的文件名比成员内部类的class文件的文件名多了一个数字，这是因为同一个类里不可能有两个同名的成员内部类，而同一个类里则可能有两个以上同名的局部内部类（处于不同方法中），所以Java为局部内部类的class文件名中增加了一个数字，用于区分。**