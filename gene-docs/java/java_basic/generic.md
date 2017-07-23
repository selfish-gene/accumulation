# 泛型

[TOC]

概念：Java的参数化类型（parameterized type）被称为泛型（generic）。

设计原则：只要代码在编译时没有出现警告，就不会遇到ClassCastException异常。

## 深入泛型

### 定义泛型接口、类

下面是Java 5改写后List接曰、Iterator接口、Map的代码片段：

／／定义接口时指定J-个类型形参，该形参名为E

```java
public interface List<E>
{
//在该接口里，E可作为类型使用
//下面方法可以使用E作为参数类型
void add(E x)；
Iterator<E> iterator();
)
//定义接口时指定了一个类型形参，该形参名为E
public interface Iterator<E>
{
//在该接口里E完全可以作为类型使用
E  next()；
boolean hasNext()；
．．．
}
//定义该接口时指定了两个类型形参，其形参名为K、v
public interface Map<K,  V>
{
//在该接口里K、V完全可以作为类型使用
Set<K> keySet();
V put(K key，V value)
… 
)
```
上面三个接口声明是比较简单的，除了尖括号中的内容——这就是泛型的实质：允许在定义接口、
类时声明类型形参，类型形参在整个接口、类体内可当成类型使用，几乎所有可使用普通类型的地方都
可以使用这种类型形参。

可以为任何类、接口增加泛型声明（并不是只有集合类才可以使用泛型声明，虽然集合类是泛型的
重要使用场所）。下面自定义一个Apple类，这个Apple类就可以包含一个泛型声明。

```java
public class Apple<T> {
    private T info;

    public Apple(T info) {
        this.info = info;
    }

    public Apple() {
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public static void main(String[] args) {
        Apple<String> stringApple = new Apple<>("apple");
        System.out.println(stringApple.getInfo());
        Apple<Double> doubleApple = new Apple<>(1.2);
        System.out.println(doubleApple.getInfo());
    }
}
```

### 泛型派生子类

当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，或从该父类派生子类，需要指
出的是，当使用这些接口、父类时不能再包含类型形参。例如，下面代码就是错误的。

```java
//定义类A继承Apple类，Apple类不能跟类型形参
public class A extends Apple<T>{  }
```

如果想从Apple类派生一个子类，则可以改为如下代码：

```java
//使用Apple类时为T形参传入String类型
public class A extends Apple<String>{ }

//使用Apple类时没有为T形参传入实际的类型
public class A extends Apple { }
```

### 并不存在泛型类

前面提到可以把ArrayList<String>类当成ArrayList的子类，事实上，ArrayList<String>类也确实像一种特殊的ArrayList类：该ArrayList<String>对象只能添加String对象作为集合元素。但实际上，系统并没有为ArrayList<String>生成新的class文件，而且也不会把ArrayList<String>当成新类来处理。看下面代码的打印结果是什么？

```java
List<String> list_1=new ArrayList<>();
List<Integer> list_2=new ArrayList<>();
／／调用getClass()方法来比较list_1和list_2的类是否相等
System.out.println(list_1.getClass()  ==list_2.getClass());
```
实际输出：true

不管为泛型的类型形参传入哪一种类型实参，对于Java来说，它们依然被当成同一个类处理，在内存中也只占用一块内存空间，因此在静态方法、静态初始化块或者静态变量的声明和初始化中不允许使用类型形参。下面程序演示了这种错误。

```java
public class R<T>
{
//下面代码错误，不能在静态变量声明中使用类型形参
static T info;    
T age;    
public void  foo(T msg){}
//下面代码错误，不能在静态方法声明中使用类型形参
public static void bar(T msg){}
}
```
由于系统中并不会真正生成泛型类，所以instanceof运算符后不能使用泛型类。例如，下面代码是错误的:

```java
java.util.Collection<String> cs = new java.util.ArrayList<>();
// 下面代码编译时引起错误：instanceof运算符后不能使用泛型类
if(cs instanceof java.util.ArrayList<String>){...}
```

## 类型通配符

为了表示各种泛型List的父类，可以使用类型通配符，类型通配符是一个问号(?)，将一个问号作为类型实参传给List集合，写作：List<?>（意思是元素类型未知的List）。这个问号(?)被称为通配符，它的元素类型可以匹配任何类型：

```java
public void test (List<?>c){
	for  (int i=O; i < c.size(); i++){
		System.out.println (c.get (i));
	}
)
```

**但这种带通配符的List仅表示它是各种泛型List的父类，并不能把元素加入到其中。例如，如下代码将会引起编译错误:**

```java
List<?>c=new ArrayList<String>(),
//下面程序引起编译错误   
c.add (new Object())；
```

### 设定类型通配符上限

当直接使用List<?>这种形式时，即表明这个List集合可以是任何汪型List的父类。但还有一种特殊的情形，程序不希望这个List<?>是任何泛型List的父类，只希望它代表某一类泛型List的父类。考虑一个简单的绘图程序，下面先定义三个形状类:

```java
public abstract class Shape {
    public abstract void draw(Canvas canvas);
}
```

```java
public class Circle extends Shape{
    @Override
    public void draw(Canvas canvas) {
        System.out.println("draw a circle on " + canvas );
    }
}
```

```java
public class Rectangle extends Shape {
    @Override
    public void draw(Canvas canvas) {
        System.out.println("draw a rectangle on " + canvas );
    }
}
```

上面定义了三个形状类，其中Shape是一个抽象父类，该抽象父类有两个子类：Circle和Rectangle。接下来定义一个Canvas类，该画布类可以画数量不等的形状（Shape子类的对象），那应该如何定义这个Canvas类呢？考虑如下的Canvas实现类:

```java
public class Canvas {
    public void drawAll(List<Shape> shapes){
        for (Shape shape : shapes){
            Shape s = shape;
            s.draw(this);
        }
    }
}
```

注意上面的drawAll()方法的形参类型是List<Shape>，而**List<Circle>并不是List<Shape>昀子类型**，因此，下面代码将引起编译错误:

```java
List<Circle> list = new ArrayList<>();
Canvas c = new Canvas();
c.drawAll(list);
```

考虑如下修改：

```java
public class Canvas {  
  	public void drawAll(List<?> shapes){
      	for (Object o : shapes){
          	Shape s = (Shape)o;
          	s.draw(this);
      	}
  	}
}
```
上面程序使用了通配符来表示所有的类型。上面的drawAll()方法可以接受List<Circle>对象作为参数，问题是上面的方法实现体显得极为臃肿而烦琐：使用了泛型还需要进行强制类型转换。

使用范型下限，代码如下：

```java
public class Canvas {
    public void drawAll(List<? extends Shape> shapes){
        for (Shape shape : shapes){
            Shape s = shape;
            s.draw(this);
        }
    }
}
```

将Canvas改为如上形式，就可以把List<Circle>对象当成List<? extends Shape>使用。即List<?extends Shape>可以表示List<Circle>、List<Rectangle>的父类——只要List后尖括号里的类型是Shape的子类型即可。

List<? extends Shape>是受限制通配符的例子，此处的问号(?)代表一个未知的类型，就像前面看到的通配符一样。但是此处的这个未知类型一定是Shape的子类型（也可以是Shape本身），因此可以把Shape称为这个**通配符的上限(upper bound)**。

类似地，由于程序无法确定这个受限制的通配符的具体类型，所以不能把Shape对象或其子类的对象加入这个泛型集合中。

设定多个上限：

```java
public class Apple< T extends Number & java_io.Serializable>{...}
```
与类同时继承父类、实现接口类似的是，为类型形参指定多个上限时，**所有的接口上限必须位于类上限之后**。也就是说，如果需要为类型形参指定类上限，**类上限必须位于第一位**。

### 设定类型通配符下限

假设自己实现一个工具方法：实现将src集合里的元素复制到dest集合里的功能，因为dest集合可以保存src集合里的所有元素，所以dest集合元素的类型应该是src集合元素类型的父类。为了表示两个参数之间的类型依赖，考虑同时使用通配符、泛型参数来实现该方法，并且该方法需要返回最后一个被复制的元素，代码如下：

```java
static <T> T copy(Collection<T> dest, Collection< ? extends T> src){
    T last = null;
    for (T ele : src){
        last = ele;
        dest.add(ele);
    }
    return last;
}
```

表面上看起来，上面方法实现了这个功能，实际上有一个问题：当遍历src集合的元素时，src元素的类型是不确定的（只可以肯定它是T的子类），程序只能用T来笼统地表示各种src集合的元素类型。例如如下代码：

```java
List<Number> In = new ArrayList<>();
List<Integer> li = new ArrayList<>();
//下面代码引起编译错误,copy方法返回的时Number类型
Integer last = copy(ln, li);
```
通过设定上限，修改代码如下：

```java
public class MyUtils {
    // 下面dest集合元素类型必须与src集合元素类型相同，或是其父类
    static <T> T copy(Collection<? super T> dest, Collection<T> src){
        T last = null;
        for (T ele : src){
            last = ele;
            dest.add(ele);
        }
        return last;
    }

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        // 此处可准确的知道最后一个被复制的元素是Integer类型
        // 与src集合元素的类型相同
        Integer last = copy(numberList, integerList);
        System.out.println(last);
    }
}
```

## 泛型方法

### 定义泛型方法

语法格式：

**修饰符 <T, S> 返回值类型 方法名 (形参列表){**	

​	// 方法体...

**}**

把上面方法的格式和普通方法的格式进行对比，不难发现泛型方法的方法签名比普通方法的方法签名多了类犁形参声明，类型形参声明以尖括号括起来，多个类型形参之间以逗号(，)隔开，所有的类型形参声明放在方法修饰符和方法返回值类型之间。

示例：

```java
public class GenericMethodDemo {
    // 声明一个泛型方法，该泛型方法中带一个T类型形参
    static <T> void fromArrayToCollection(T[] a, Collection<T> c){
        for (T o : a){
            c.add(o);
        }
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        // 下面代码中T代表Object类型
        fromArrayToCollection(oa, co);
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        // 下面代码中T代表String类型
        fromArrayToCollection(sa, cs);
        // 下面代码中T代表Object类型
        fromArrayToCollection(sa, co);
        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<>();
        // 下面代码中T代表Number类型
        fromArrayToCollection(ia, cn);
        // 下面代码中T代表Number类型
        fromArrayToCollection(fa, cn);
        // 下面代码中T代表Number类型
        fromArrayToCollection(na, cn);
        // 下面代码中T代表Object类型
        fromArrayToCollection(na, co);
        // 下面代码中T代表String类型，但na是一个Number数组，
        // 因为Number既不是String类型，
        // 也不是它的子类，所以出现编译错误
//    fromArrayToCollection(na, cs);
    }
}
```

```java
public class ErrorAndRightDemo {
    static <T> void error(Collection<T> from, Collection<T> to){
        for (T t : from){
            to.add(t);
        }
    }

    static <T> void right(Collection<? extends T> from, Collection<T> to){
        for (T t : from){
            to.add(t);
        }
    }

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
//        error(stringList, objectList);
        right(stringList, objectList);
    }
}
```

### 泛型方法和类型通配符的区别

```java
// TODO Some things do not understand
```

### 泛型构造器

正如泛型方法允许在方法签名中声明类型形参一样，Java也允许在构造器签名中声明类型形参，这样就产生了所谓的泛型构造器。一旦定义了泛型构造器，接下来在调用构造器时，就不仅可以让Java裉据数据参数的类型来“推断”类型形参的类型，而且程序员也可以显式地为构造器中的类型形参指定实际的类型。如下程序所示:

```java
public class GenericConstructor {
    public static void main(String[] args) {
        new Foo("Java");
        new Foo(102);

        // 显式指定泛型构造器中的T参数为String，
        // 传给Foo构造器的实参也是String对象，完全正确。
        new <String> Foo("Git");
        // 显式指定泛型构造器中的T参数为String，
        // 但传给Foo构造器的实参是Double对象，下面代码出错
//        new <String> Foo(12.3);
    }

    static class Foo{
        public <T> Foo(T t){
            System.out.println(t);
        }
    }
}
```

```java
public class GenericDiamondDemo {
    public static void main(String[] args) {
        // MyClass类声明中的E形参是String类型。
        // 泛型构造器中声明的T形参是Integer类型
        MyClass<String> mc1 = new MyClass<>(1);
        // 显式指定泛型构造器中声明的T形参是Integer类型
        MyClass<String> mc2 = new <Integer> MyClass<String>(2);

        // 如果显式指定泛型构造器中声明的T形参是Integer类型
        // 此时就不能使用"菱形"语法，下面代码是错的。
//    MyClass<String> mc3 = new <Integer> MyClass<>(3);
        MyClass mc4 = new <Integer> MyClass(4);
    }

    static class MyClass<E> {
        public <T> MyClass(T t) {
            System.out.println("t is : " + t);
        }
    }
}
```

### 泛型方法重载

因为泛型既允许设定通配符的上限，也允许设定通配符的下限，从而允许在一个类里包含如下两个方法定义:

```java
publid c;lass; MyUtils{
    public static <T> void copy(Collection<T> dest,  Collection<? extends T> src){…}
    public static <T> T copy(Collection<? super T>dest,  Collection<T> src){...}
}
```
上面的MyUtils类中包含两个copy()方法，这两个方法的参数列表存在一定的区别，但这种区别不是很明确：这两个方法的两个参数都是Collection对象，前一个集合里的集合元素类型是后一个集合里集合元素类型的父类。如果只是在该类中定义这两个方法不会有任何错误，但只要调用这个方法就会引起编译错误。例如，对于如下代码：

```java
List<Number> In = new ArrayList<>();
List<Integer> li = new ArrayList<>();
copy(ln，li)；
```
## 擦除和转换

在严格的泛型代码里，带泛型声明的类总应该带着类型参数。但为了与老的Java代码保持一致，也允许在使用带泛型声明的类时不指定实际的类型参数。如果没有为这个泛型类指定实际的类型参数，则该类型参数被称作raw type（原始类型），默认是声明该类型参数时指定的第一个上限类型。

当把一个具有泛型信息的对象赋给另一个没有泛型信息的变量时，所有在尖括号之间的类型信息都将被扔掉。比如一个List<String>类型被转换为List，则该List对集合元素的类型检查变成了类型参数的上限（即Object）。下面程序示范了这种擦除:

```java
public class ErasureDemo {

    static class Apple<T extends Number>{
        T size;

        public Apple() {
        }

        public Apple(T size) {
            this.size = size;
        }

        public T getSize() {
            return size;
        }

        public void setSize(T size) {
            this.size = size;
        }
    }

    public static void main(String[] args) {
        Apple<Integer> a = new Apple<>(6);
        // a的getSize方法返回Integer对象
        Integer ai = a.getSize();
        System.out.println(ai.getClass());
        // 把a对象赋给Apple变量，丢失尖括号里的类型信息
        Apple b = a;
        // b只知道size的类型是Number
        Number size = b.getSize();
        System.out.println(size.getClass());

        // 下面代码引起编译错误
//        Integer size2 = b.getSize();

        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        // 丢失泛型信息
        List list = li;
        // 下面代码引起“未经检查的转换”的警告，编译、运行时完全正常
        List<String> ls = list;
        // 但只要访问ls里的元素，如下面代码将引起运行时异常
        System.out.println(ls.get(0));
    }
}
```

## 泛型与数组

```java
// TODO Some things do not understand
```