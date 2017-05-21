# Lambda表达式

Lambda表达式支持将代码块作为方法参数，Lambda表达式允许使用更简洁的代码来创建只有一个抽象方法的接口（这种接口被称为函数式借口）的实例。

[TOC]

## 入门

Lambda表达式由三部分组成：

**形参列表：**

**箭头（->）：**

**代码块：**

如下：

```java
public interface Addable {
    int add(int a, int b);
}

public interface Eatable {
    void taste();
}

public interface Flyable {
    void fly(String weather);
}

public class LambdaQs {

    // 调用该方法需要Eatable对象
    public void eat(Eatable e){
        System.out.println(e);
        e.taste();
    }
    // 调用该方法需要Flyable对象
    public void drive(Flyable f)
    {
        System.out.println("我正在驾驶：" + f);
        f.fly("【碧空如洗的晴日】");
    }
    // 调用该方法需要Addable对象
    public void test(Addable add)
    {
        System.out.println("5与3的和为：" + add.add(5, 3));
    }

    public static void main(String[] args) {
        LambdaQs lq = new LambdaQs();
        // Lambda表达式的代码块只有一条语句，可以省略花括号。
        lq.eat(() -> System.out.println("The apple taste well"));
        // Lambda表达式的形参列表只有一个形参，省略圆括号
        lq.drive(weather -> {
            System.out.println("今天天气是：" + weather);
            System.out.println("直升机飞行平稳");
        });
        // Lambda表达式的代码块只有一条语句，省略花括号
        // 代码块中只有一条语句，即使该表达式需要返回值，也可以省略return关键字。
        lq.test((a, b) -> a + b);
    }
}
```

## 函数式接口

**Lambda表达式的类型，也被称为目标类型（target type），Lambda表达式目标类型必须是函数式接口（functional interface）。函数式接口代表只包含一个抽象方法的接口，但可以包含多个默认方法、类方法。Java8专门为函数式接口提供了@FunctionalInterface注解，用于严格检查。**

Lambda表达式的结果被当成函数式接口的对象，可以为对象直接赋值，但该对象必须是以函数式接口声明：

```java
	// Runnable接口中只包含一个无参数的方法
	// Lambda表达式代表的匿名方法实现了Runnable接口中唯一的、无参数的方法
	// 因此下面的Lambda表达式创建了一个Runnable对象
	Runnable r = () -> {
		for(int i = 0 ; i < 100 ; i ++){
			System.out.println();
		}
	};

	// 下面代码报错: 不兼容的类型: Object不是函数接口
	Object obj = () -> {
		for(int i = 0 ; i < 100 ; i ++){
			System.out.println();
		}
	};
		
	// 将代码如下修改即可
    Object obj1 = (Runnable)() -> {
      for(int i = 0 ; i < 100 ; i ++){
        System.out.println();
      }
    };
```
为了保证Lambda表达式的目标类型是一个明确的函数式接口，可以有如下三种常见方式：

- **将Lambda表达式赋值给函数式接口类型的变量**


- **将Lambda表达式作为函数式接口类型的参数传给某个方法（此种方式最为常见）**


- **使用函数式接口对Lambda表达式进行强制类型转换**

### 函数式接口类型

Java 8在java.util.function包下预定义了大量函数式接口，典型地包含知下4类接口

- **XxxFunction：这类接口中通常包含一个apply()抽象方法，该方法对参数进行处理、转换（apply()方法的处理逻辑由Lambda表达式来实现），然后返回一个新的值。该函数式接口通常用于对指定数据进行转换处理。**

```java
public interface Function<T, R> {
  
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}
```

- **XxxConsumer：这类接口中通常包含一个accept()抽象方法，该方法与XxxFunction接口中的apply()方法基本相似，也负责对参数进行处理，只是该方法不会返回处理结果。**

```java
@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);
}
```

- **XxxPredicate：这类接口中通常包含一个test()抽象方法，该方法通常用来对参数进行某种判断( test()方法的判断逻辑由Lambda表达式来实现），然后返回一个boolean值。该接口通常用于判断参数是否满足特定条件，经常用于进行筛滤数据。**

```java
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);
}
```

- **XxxSupplier：这类接口中通常包含一个getAsXxx()抽象方法，该方法不需要输入参数，该方法会按某种逻辑算法( getAsXxx()方法的逻辑算法由Lambda表达式来实现）返回一个数据。**

```java
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```

## 方法引用与构造器引用

### 引用类方法

```java
@FunctionalInterface
public interface Converter {
    Integer convert(String from);
}
```

```java
Converter converter1 = from -> Integer.valueOf(from);
System.out.println(converter1.convert("99"));
// 函数式接口中被实现方法的全部参数传给该类方法作为参数，使用两个英文冒号
Converter converter2 = Integer::valueOf;
System.out.println(converter2.convert("32"));
```

### 引用特定对象的实例方法

```java
@FunctionalInterface
public interface Converter {
    Integer convert(String from);
}
```

```java
Converter converter3 = from -> "Java".indexOf(from);
System.out.println(converter3.convert("v"));
// 函数式接口中被实现方法的全部参数传给该类方法作为参数
Converter converter4 = "Java"::indexOf;
System.out.println(converter4.convert("v"));
```

### 引用某类对象的实例方法

```java
@FunctionalInterface
public interface MyTest {
    String test(String s, int a, int b);
}
```

```java
MyTest mt = (a, b, c) -> a.substring(b ,c);
System.out.println(mt.test("I love Java", 2, 6));
// 函数式接口中被实现方法的第一个参数作为调用者，后面的参数全部传给该方法作为参数
MyTest mt2 = String::substring;
System.out.println(mt2.test("I love Java", 2, 6));
```

### 引用构造器

```java
@FunctionalInterface
public interface YourTest {
    JFrame win(String title);
}
```

```java
YourTest yt = a -> new JFrame(a);
System.out.println(yt.win("My title"));
// 函数式接口中被实现方法的全部参数传给该类构造器作为参数
YourTest yt2 = JFrame::new;
System.out.println(yt2.win("My title"));
```

## 与匿名内部类的联系和区别

### 相同点

从前面介绍可以看出，Lambda表达式是匿名内部类的一种简化，因此它可以部分取代匿名内部类的作用，Lambda表达式与匿名内部类存在如下相同点：

- Lambda表达式与匿名内部类一样，都可以直接访问“effectively final”的局部变量，以及外部类的成员变量（包括实例变量和类变量）。


- Lambda表达式创建的对象与匿名内部类生成的对象一样，都可以直接调用从接口中继承的默认方法。

示例：

```java
@FunctionalInterface
public interface Displayable {
    void display();
    default int add(int a, int b){
        return a + b;
    }
}
```

```java
public class LambdaAndInner {

    private int age = 20;
    private static String name = "selfish_gene";
    public void test(){
        String skill = "Java";
        Displayable dis = () -> {
            System.out.println("skill is :" + skill);
            System.out.println("age is : " + age);
            System.out.println("name is : " + name);
            // Lambda表示代码块中不能调用接口中定义的默认方法
//            System.out.println(dis.add(3, 5));
        };
        dis.display();
        System.out.println(dis.add(3, 5));
    }

    public static void main(String[] args) {
        LambdaAndInner lai = new LambdaAndInner();
        lai.test();
    }
}
```

### 不同点

- 匿名内部类可以为任意接口创建实例——不管接口包含多少个抽象方法，只要匿名内部类实现所有的抽象方法即可；但Lambda表达式只能为函数式接口创建实例。


- 匿名内部类可以为抽象类甚至普通类创建实例；但Lambda表达式只能为函数式接口创建实例。


- 匿名内部类实现的抽象方法的方法体允许调用接口中定义的默认方法；但Lambda表达式的代码块不允许调用接口中定义的默认方法。

## 调用Arrays的类方法

Arrays类的有些方法需要Comparator、XxxOperator、XxxFunction等接口的实例，这些接口都是函数式接口，因此可以使用Lambda表达式来调用Arrays的方法。例如如下程序：

```java
public class LambdaArrays {
    public static void main(String[] args) {
        String[] arr1 = new String[]{"Java", "Maven", "Git", "Linux"};
        Arrays.parallelSort(arr1, ((o1, o2) -> o1.length() - o2.length()));
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = new int[]{3, -4 , 25, 16, 30, 18};
        // left代表数组中前一个所索引处的元素，计算第一个元素时，left为1
        // right代表数组中当前索引处的元素
        Arrays.parallelPrefix(arr2, ((left, right) -> left * right));
        System.out.println(Arrays.toString(arr2));

        long[] arr3 = new long[5];
        // operand代表正在计算的元素索引
        Arrays.parallelSetAll(arr3 , operand -> operand * 5);
        System.out.println(Arrays.toString(arr3));
    }
}
```

