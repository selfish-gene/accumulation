# final关键字

[TOC]

## 修饰变量

**final修饰的变量不可被改变，一旦获得了初始值，该final变量的值就不能被重新赋值。**

### 成员变量

**final修饰的成员变量必须由程序员显式地指定初始值。**

**与普通成员变量不同的是，final成员变量（包括实例变量和类变量）必须由程序员式初始化，系统不会对final成员进行隐式初始化。**

类变量

- 声明时初始化
- 静态初始化块

实例变量

- 声明时初始化
- 普通初始化块
- 构造器中

### 局部变量

**系统不会对局部变量进行初始化，局部变量必须由程序员显式初始化。因此使用final修饰局部变量时，既可以在定义时指定默认值，也可以不指定默认值。**

### 基本类型与引用类型

共同点：不能被重新赋值。

不同点：但对于引用类型而言，final只能保证这个引用类型变量所引用的地址不变，该对象是可以改变的。

如下例子：

```java
public class Person {
    private int age;
    public Person() {
    }
    public Person(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

public class FinalReferenceTest {
    public static void main(String[] args) throws Exception {
        final Person p = new Person();
        p.setAge(10);
        System.out.println(p.getAge());
        //改变Person对象的age实例变量，合法
        p.setAge(20);
        System.out.println(p.getAge());
        //重新赋值，非法
//        p = new Person();
    }
}
```

## 宏替换与宏变量

**对一个final变量来说，不管它是类变量、实例变量，还是局部变量，只要该变量满足三个条件，这个final变量就不再是一个变量，而是相当于一个直接量。**

- 使用final修饰符修饰
- 在定义该final变量时指定了初始值
- 该初始值可以在编译时就被确定下来

宏变量的情况：

为final变量赋值**直接量**与**基本的算式表达式**或**字符串连接运算符**

非宏变量的情况：

为final变量赋值时**访问普通变量**或**调用方法**

```java
public class FinalReplaceTest {
    public static void main(String[] args) throws Exception {
        final int a = 4;
        final double d = 1.2/3;
        final String str = "love" + "Java";
        final String book = "Java" + 50;
        // 无法在编译时确定book2的值，book2不会被当成宏变量
        final String book2 = "Java" + String.valueOf(50);
        System.out.println(book == "Java50");	//true
        System.out.println(book2 == "Java50");	//false
      
        String s1 = "loveJava";
        String s2 = "love" + "Java";
        System.out.println(s1 == s2);	//true

        // str1与str2只是2个普通变量，编译器不会执行宏替换
        String str1 = "love";
        String str2 = "Java";
        String s3 = str1 + str2;
        System.out.println(s1 == s3);	//false
    }
}
```

## 修饰类

**final修饰的类不可以有子类**

## 修饰方法

**final修饰的方法不可被重写**

## 不可变类

**不可变( immutable)类的意思是创建该类的实例后，该实例的实例变量是不可改变的。**

如果需要创建自定义的不可变类，可遵守如下规则：

1. **使用private和final修饰符来修饰该类的成员变量。**
2. **提供带参数构造器，用于根据传入参数来初始化类里的成员变量。**
3. **仅为该类的成员变量提供getter方法，不要为该类的成员交量提供setter方法，因为普通方法无法修改final修饰的成员变量。**
4. **如果有必要，重写Object类的hashCode0和equals()方法。equals()方法根据关键成员变量来作为两个对象是否相等的标准，除此之外，还应该保证两个用equals()方法判断为相等的对象的hashCode()也相等。**

例子如下：

```java
public class Name {
    private String fristName;
    private String lastName;
    public Name() {
    }
  	//第2点
    public Name(String fristName, String lastName) {
        this.fristName = fristName;
        this.lastName = lastName;
    }
  	//省略getter和setter方法
}

public class People {
    private final Name name;//第1点
    public People(Name name) {
        this.name = new Name(name.getFristName(), name.getLastName());
//        this.name = name;
    }
  	//第3点
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
```

## 缓存实例的不可变类

**不可变类的实例状态不可改变，可以很方便地被多个对象所共享**。如果程序经常需要使用相同的不可变类实例，则应该考虑缓存这种不可变类的实例。毕竟重复创建相同的对象没有太大的意义，而且加大系统开销。如果可能，应该将已经创建的不可变类的实例进行缓存。

以下使用一个数组来作为缓存池，从而实现一个缓存实例的不可变类。

```java
public class CacheImmutableTest {
    private static int MAX_SIZE = 16;
    // 使用数组来缓存已有的实例
    private static CacheImmutableTest[] cache = new CacheImmutableTest[MAX_SIZE];
    // 记录缓存实例在缓存中的位置,cache[pos-1]是最新缓存的实例
    private static int pos = 0;
    private final String name;//不可变类设计规则第1点
    private CacheImmutableTest(String name){//不可变类设计规则第2点
        this.name = name;
    }
    public String getName() {//不可变类设计规则第3点
        return name;
    }
    public static CacheImmutableTest valueOf(String name)
    {
        // 遍历已缓存的对象，
        for(int i = 0; i < MAX_SIZE; i++) {
            // 如果已有相同实例，直接返回该缓存的实例
            if(cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }
        // 如果缓存池已满
        if(pos == MAX_SIZE) {
            // 把缓存的第一个对象覆盖，即把刚刚生成的对象放在缓存池的最开始位置。
            cache[0] = new CacheImmutableTest(name);
            // 把pos设为1
            pos = 1;
        }
        else {
            // 把新创建的对象缓存起来，pos加1
            cache[pos++] = new CacheImmutableTest(name);
        }
        return cache[pos - 1];
    }
    //不可变类设计规则第4点
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj != null && obj.getClass() == CacheImmutableTest.class) {
            CacheImmutableTest ci = (CacheImmutableTest) obj;
            return name.equals(ci.getName());
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        CacheImmutableTest c1 = CacheImmutableTest.valueOf("hello");
        CacheImmutableTest c2 = CacheImmutableTest.valueOf("hello");
        System.out.println(c1 == c2);   //true
    }
}
```

Java提供的java.lang.lnteger类，它就采用了与Cachelmmutale类相同的处理策略，下面程序示范了Integer类构造器和valueOf()方法存在的差异。

```java
public class IntegerCacheTest {
    public static void main(String[] args) throws Exception {
        Integer in1 = new Integer(10);
        Integer in2 = Integer.valueOf(10);
        Integer in3 = Integer.valueOf(10);
        System.out.println(in1 == in2); // false
        System.out.println(in2 == in3); // true

        // 由于Integer只缓存-128~127之间的值，因此200对应的Integer对象没有被缓存。
        Integer in4 = Integer.valueOf(200);
        Integer in5 = Integer.valueOf(200);
        System.out.println(in4 == in5); // false
    }
}
```