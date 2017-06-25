# Annotation

[TOC]

概念：

Annotation提供了一种**为程序元素设置元数据**的方法，从某些方面来看，Annotation就像修饰符一样，可用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声明，这些信息被存储在Annotation的“name=value”对中。

## 基本Annotation

Java提供的5个基本Annotation，使用Annotation时要在其前面增加@符号，并把该Annotation当成一个修饰符使用，用于修饰它支持的程序元素。

- **@Override**

  @Override就是用来指定方法覆载的，它可以强制一个子类必须覆盖父类的方法。

- **@Deprecated**

   @Deprecated用于表示某个程序元素（类、方法等）已过时，当其他程序使用已过时的类、方法时，编译器将会给出警告。

- **@SuppressWarnings**

  @SuppressWarnings指示被该Armotation修饰的程序元素（以及该程序元素中的所有子元素）取消显示指定的编译器警告。

- **@SafeVarargs**

- **@FunctionalInterface**

  Java 8规定：如果接口中只有一个抽象方法（可以包含多个默认方法或多个static方法），该接口就是函数式接口。@ Functionallnterface就是用来指定某个接口必须是函数式接口。

## JDK的Meta Annotation

JDK除了在java.lang下提供了5个基本的Annotation之外，还在java.lang.annotation包下提供了6个Meta Annotation（元Annotation），其中有5个元Annotation都用于修饰其他的Annotation定义。其中@Repeatable专门用于定义Java 8新增的重复注解。

- **@Retention**

  @Retention只能用于修饰Annotation定义，用于指定被修饰的Annotation可以保留多长时间，@Retention包含一个RetentionPolicy类型的value成员变量，所以使用@Retention时必须为该value成员变量指定值。

  value成员变量的值只能是如下三个：

  - RetentionPolicy.CLASS：编译器将把Annotation记录在class文件中。当运行Java程序时，JVM不可获取Annotation信息。这是默认值。
  - RetentionPolicy.RUNTIME：编译器将把Annotation记录在class文件中。当运行Java程序时，JVM也可获取Annotation信息，程序可以通过反射获取该Annotation信息。
  - RetentionPolicy.SOURCE：Annotation只保留在源代码中，编译器直接丢弃这种Annotation。

- **@Target**

  @Target也只能修饰一个Annotation定义，它用于指定被修饰的Annotation能用于修饰哪些程序单元。@Target元Annotation也包含一个名为value的成员变量，该成员变量的值只能是如下几个：

  - ElementType.ANNOTATION_TYPE:指定该策略的Annotation只能修饰Annotation。
  - ElementType．CONSTRUCTOR:指定该策略的Annotation只能修饰构造器。
  - ElementType.FIELD:指定该策略的Annotation只能修饰成员变量。
  - ElementType.LOCAL_VARIABLE:指定该策略的Annotation只能修饰局部变量。
  - ElementType.METHOD:指定该策略的Annotation只能修饰方法定义。
  - ElementType.PACKAGE:指定该策略的Annotation只能修饰包定义。
  - ElementType.PARAMETER:指定该策略的Annotation可以修饰参数。
  - ElementType.TYPE:指定该策略的Annotation可以修饰类、接口（包括注解类型）或枚举定义。

- **@Documented**

  @Documented用于指定被该元Annotation修饰的Annotation类将被javadoc工具提取成文档，如果定义Annotation类时使用了@Documented修饰，则所有使用该Annotation修饰的程序元素的API文档中将会包含该Annotation说明。

- **@Inherited**

  @Inherited元Annotation指定被它修饰的Annotation将具有继承性——如果某个类使用了@Xxx注解（定义该Annotation时使用了@Inherited修饰）修饰，则其子类将自动被@Xxx修饰。案例如下：

  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Inherited
  public @interface Inheritable {}

  public class InheritableTest extends Base{
      public static void main(String[] args) {
         // 输出结果为true
         System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
      }
  }

  @Inheritable
  class Base{}
  ```

- **@Repeatable**

  在Java 8以前，同一个程序元素前最多只能使用一个相同类型的Annotation；如果需要在同一个元素前使用多个相同类型的Annotation，则必须使用Annotation“容器”。Java 8允许使用多个相同粪型的Annotation来修饰同一个类，案例如下：

  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  public @interface FKTags {
    	// 定义value成员变量，该成员变量可接受多个@FkTag注解
      FKTag[] value();
  }
  ```

  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Repeatable(FKTags.class)//这是关键
  public @interface FKTag {
      String name() default "selfish-gene";
      int age();
  }
  ```

  ```java
  @FKTag(age = 20)
  @FKTag(name = "Edam", age = 20)
  public class FKTagTest {
      public static void main(String[] args) {
          Class<FKTagTest> fkTagTestClass = FKTagTest.class;
          Arrays.stream(fkTagTestClass.getAnnotationsByType(FKTag.class)).forEach(fkTag -> System.out.println(fkTag.name() + "-->" + fkTag.age()));
          FKTags container = fkTagTestClass.getAnnotation(FKTags.class);
          System.out.println(container);
      }
  }
  ```

  ```java
  输出结果：
  selfish-gene-->20
  Edam-->20
  @FKTags(value=[@FKTag(name=selfish-gene, age=20), @FKTag(name=Edam, age=20)])
  ```

  上面程序中尝试获取修饰FKTagTest类的@FkTags注解，虽然上面源代码中并未显式使用@FKTags注解，但由于程序使用了两个@FKTag注解修饰该类，因此系统会自动将两个@FkTag注解作为@FKTags的value成员变量的数组元素处理。因此，代码将可以成功地获取到@FkTags注解。

- **@Native**

## 自定义Annotation

### 定义Annotation

根据Annotation是否可以包含成员变量，可以把Annotation分为如下两类：

- 标记Annotation:没有定义成员变量的Annotation类型被称为标记。这种Annotation仅利用自身的存在与否来提供信息，如@Override、@Test等Annotation。
- 元数据Annotation:包含成员变量的Annotation，因为它们可以接受更多的元数掘，所以也被称为元数据Annotation。

### 提取Annotation信息

使用Annotation修饰了类、方法、成员变量等成员之后，这些Annotation不会自己生效，必须由开发者提供相应的工具来提取并处理Annotation信息。

**Java使用Annotation接口来代表程序元素前面的注解，该接口是所有注解的父接口。**

**Java 5在java.lang.reflect包下新增了AnnotatedElement接口，该接口代表程序中可以接受注解的程序元素。**

### 编译时处理Annotation

APT (Annotation Processing Tool)是一种注解处理工具，它对源代码文件进行检测，并找出源文件所包含的Annotation信息，然后针对Annotation信息进行额外的处理。

Java提供的javac.exe工具有一个-processor选项，该选项可指定一个Annotation处理器，如果在编译Java源文件时通过该选项指定了Annotation处理器，那么这个Annotation处理器将会在编译时提取并处理Java源文件中的Annotation。

每个Annotation处理器都需要实现javax.annotation.processing包下的Processor接口。不过实现该接口必须实现它里面所有的方法，因此**通常会采用继承AbstractProcessor的方式来实现Annotation处理器**。一个Annotation处理器可以处理一种或者多种Annotation类型。

### Java8新增的Type Annotation

 Java 8为ElementType枚举增加了TYPE_PARAMETER、TYPE_USE两个枚举值，这样就允许定义枚举时使用@Target(ElementType.TYPE_USE)修饰，这种注解被称为Type Annotation（类型注解），Type Annotation可用在任何用到类型的地方。

需要指出的是，上面程序虽然大量使用了@NotNull注解，但这些注解暂时不会起任何作用——因为并没有为这些注解提供处理工具。而且Java 8本身并没有提供对Type Annotation执行检查的框架，因此如果需要让这些Type Annotation发挥作用，开发者需要自己实现Type Annotation检查框架。