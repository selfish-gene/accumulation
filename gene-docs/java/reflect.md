# Reflect

[TOC]

## 反射查看类信息

### 获得Class对象

在Java程序中获得Class对象通常有如下三种方式:

- 使用Class类的forName(String clazzName)静态方法。该方法需要传入字符串参数，该字符串参数的值是某个类的全限定类名（必须添加完整包名）。


- 调用某个类的class属性来获取该类对应的Class对象。例如，Person.class将会返回Person类对应的Class对象。
- 调用某个对象的getClass()方法。该方法是java.lang.Object类中的一个方法，所以所有的Java对象都可以调用该方法，该方法将会返回该对象所属类对应的Class对象。

### 从Class中获取信息

构造器

包含的方法

成员变量

Annotation

内部类

外部类

接口

继承的父类

修饰符、所在包、类名等基本信息

判断该类是否为接口、枚举、注解类型等

例子：

```java
@Repeatable(Annos.class)
public @interface Anno {
}
```

```java
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Annos {
    Anno[] value();
}
```

```java
@SuppressWarnings(value = "unchecked")
@Deprecated
@Anno
@Anno
public class ClassDemo {
    // 为该类定义一个私有的构造器
    private ClassDemo()
    {
    }
    // 定义一个有参数的构造器
    public ClassDemo(String name)
    {
        System.out.println("执行有参数的构造器");
    }
    // 定义一个无参数的info方法
    public void info()
    {
        System.out.println("执行无参数的info方法");
    }
    // 定义一个有参数的info方法
    public void info(String str)
    {
        System.out.println("执行有参数的info方法"
                + "，其str参数值：" + str);
    }
    // 定义一个测试用的内部类
    class Inner
    {
    }

    public static void main(String[] args) throws Exception{
        // 下面代码可以获取ClassTest对应的Class
        Class<ClassDemo> clazz = ClassDemo.class;
        // 获取该Class对象所对应类的全部构造器
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(constructor ->  System.out.println(constructor));
        // 获取该Class对象所对应类的全部public构造器
        Constructor[] publicConstructors = clazz.getConstructors();
        Arrays.stream(publicConstructors).forEach(constructor ->  System.out.println(constructor));
        // 获取该Class对象所对应类的全部public方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> System.out.println(method));
        // 获取该Class对象所对应类的指定方法
        Method info = clazz.getMethod("info", String.class);
        System.out.println(info);
        // 获取该Class对象所对应类的上的全部注解
        Annotation[] annotations = clazz.getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> System.out.println(annotation));
        // 获取该Class对象所对应类的全部内部类
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        Arrays.stream(declaredClasses).forEach(classInner -> System.out.println(classInner));

        System.out.println("ClassTest的包为：" + clazz.getPackage());
        System.out.println("ClassTest的父类为：" + clazz.getSuperclass());

    }
}
```

### Java8新增的方法参数反射

- getModifiers()：获取修饰该形参的修饰符。
- StringgetName()：获取形参名。
- TypegetParameterizedType()：获取带泛型的形参类型。
- Class<?> getType()：获取形参类型。
- boolean isNamePresent()：该方法返回该类的class文件中是否包含了方法的形参名信息。
- boolean isVarArgs()：该方法用于判断该参数是否为个数可变的形参。

```java
public class Demo {
    public void replace(String str, List<String> list){}
}
```

```java
public class MethodParameterDemo {
    public static void main(String[] args) throws Exception {
        Class<Demo> clazz = Demo.class;
        Method replace = clazz.getMethod("replace", String.class, List.class);

        System.out.println(replace.getParameterCount());
        Parameter[] replaceParameters = replace.getParameters();
        Arrays.stream(replaceParameters).forEach(parameter -> {
            //需要指出的是，使用javac命令编译Java源文件时，默认生成的class文件并不包含方法的形参名信息，因此调用isNamePresent()方法将会返回false，
            if (parameter.isNamePresent()) {
                System.out.println("参数名：" + parameter.getName());
                System.out.println("形参类型：" + parameter.getType());
                System.out.println("泛型类型：" + parameter.getParameterizedType());
            }
        });
    }
}
```

## 使用反射生成对象并操作

### 创建对象

通过反射来生成对象有如下两种方式。

- 使用**Class对象的newlnstance()方法**来创建该Class对象对应类的实例，这种方式要求该Class对象的对应类有默认构造器，而执行newlnstance()方法时实际上是利用默认构造器来创建该类的实例。
- 先使用**Class对象获取指定的Constructor对象**，再调用Constructor对象的newlnstance()方法来创建该Class对象对应类的实例。通过这种方式可以选择使用指定的构造器来创建实例。

```java
public class ObjectPoolFactory {
    // 定义一个对象池,前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    // 定义一个创建对象的方法，
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }
    // 该方法根据指定文件来初始化对象池，
    // 它会根据配置文件来创建对象
    public void initPool(String fileName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File(ObjectPoolFactory.class.getResource("/").getPath() + File.separator + fileName);
        try (FileInputStream fis = new FileInputStream(file)){
            Properties props = new Properties();
            props.load(fis);
//            props.stringPropertyNames().forEach(name -> objectPool.put(name, creatObject(props.getProperty(name))));

            for (String name : props.stringPropertyNames())
            {
                // 每取出一对key-value对，就根据value创建一个对象
                // 调用createObject()创建对象，并将对象添加到对象池中
                objectPool.put(name , createObject(props.getProperty(name)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found class");
        } catch (IOException e) {
            System.out.println("读取" + fileName + "异常");
        }
    }
    // 从objectPool中取出指定name对应的对象
    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        ObjectPoolFactory pf  = new ObjectPoolFactory();
        pf.initPool("obj.properties");
        System.out.println(pf.getObject("date"));
        System.out.println(pf.getObject("user"));

    }

}
```

### 调用方法

每个Method对象对应一个方法，获得Method对象后，程序兢可通过该Method来调用它对应的方法。在Method里包含一个invoke()方法，该方法的签名如下:

Object invoke(Object obj，Object... args)：该方法中的obj是执行该方法的主调，后面的args是执行该方法时传入该方法的实参。

```java
public class ExtendedObjectPoolFactory {
    /*
     * Get the log object
     */
     private static final Logger LOGGER = LogManager.getLogger();
    // 定义一个对象池,前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    private Properties props = new Properties();
    // 从指定属性文件中初始化Properties对象
    public void init(String fileName){
        try (FileInputStream fis = new FileInputStream(ExtendedObjectPoolFactory.class.getResource("/").getPath() + File.separator + fileName)) {
            props.load(fis);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        } catch (IOException e) {
            LOGGER.error("IO error");
        }
    }
    // 定义一个创建对象的方法，
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }
    // 该方法根据指定文件来初始化对象池，
    // 它会根据配置文件来创建对象
    public void initPool() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String name : props.stringPropertyNames())
        {
            // 每取出一对key-value对，如果key中不包含百分号（%）,每取出一对key-value对，就根据value创建一个对象
            // 调用createObject()创建对象，并将对象添加到对象池中
            if (!name.contains("%")) {
                objectPool.put(name , createObject(props.getProperty(name)));
            }
        }
    }
    // 该方法将会根据属性文件来调用指定对象的setter方法
    public void initProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for(String name : props.stringPropertyNames()){
            if (name.contains("%")){
                String[] strs = name.split("%");
                // 取出调用setter方法的对象
                Object target = getObject(strs[0]);
                // 获取setter方法名:set + "首字母大写" + 剩下部分
                String methodName = "set" + strs[1].substring(0,1).toUpperCase() + strs[1].substring(1);
                Class<?> targetClass = target.getClass();
                Method method = targetClass.getMethod(methodName, String.class);
                // 通过Method的invoke方法执行setter方法，
                // 将config.getProperty(name)的值作为调用setter的方法的参数
                method.invoke(target, props.getProperty(name));
            }
        }
    }

    // 从objectPool中取出指定name对应的对象
    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        User user = (User) Class.forName("com.selfish.gene.modles.User").newInstance();
        Method method = user.getClass().getMethod("setName", String.class);
        method.invoke(user, "selfish_gene");
        System.out.println(user);

        ExtendedObjectPoolFactory epf  = new ExtendedObjectPoolFactory();
        epf.init("obj.properties");
        epf.initPool();
        epf.initProperty();
        System.out.println(epf.getObject("user"));
    }
}
```

当通过Method的invoke()方法来调用对应的方法时，Java会要求程序必须有调用该方法的权限。如果程序确实需要调用某个对象的private方法，则可以先调用Method对象的如下方法:

setAccessible(boolean flag):将Method对象的accessible设置为指定的布尔值。值为true，指示该Method在使用时应该取消Java语言的访问权限检查：值为false，则指示该Method在使用时要实施Java语言的访问权限检查。

### 访问成员变量

通过Class对象的getFields()或getField()方法可以获取该粪所包括的全部成员变量或指定成员变量。
Field提供了如下两组方法来读取或设置成员变量值。

- getXxx(Object obj)：获取obj对象的该成员变量的值。此处的Xxx对应8种基本类型，如果该成员变量的类型是引用类型，则取消get后面的Xxx。
- setXxx(Objectobj，Xxx val)：将obj对象的该成员变量设置成val值。此处的Xxx对应8种基本类型，如果该成员变量的类型是引用类型，则取消set后面的Xxx。

```java
public class Person {

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

```java
public class FieldDemo {
    public static void main(String[] args) throws Exception{
        Person p = new Person();
        Class<Person> personClass = Person.class;
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p, "selfish_gene");
        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(p, 25);
        System.out.println(p);
    }
}
```

```java
Person{name='selfish_gene', age=25}
```

### 操作数组

在j ava.lang.reflect包下还提供了一个Array类，Array对象可以代表所有的数组。程序可以通过使
用Array采动态地创建数组，操作数组元素等。

Array提供了如下几类方法:

- static Object newInstance(Class<?> componentType，int... length):创建一个具有指定的元素类型、指定维度的新数组。
- static xxx getXxx(Object array, int index):返回array数组中第index个元素。其中xxx是各种基本数据类型，如果数组元素是引用类型，则该方法变为get(Object array, int index)。
- static void setXxx(Object array, int index，xxx val):将array数组中第index个元素的值设为val。其中xxx是各种基本数据类型，如果数组元素是引用类型，则该方法变成set(Object array, int index，Object val)。

```java
public class ArrayDemo {
    public static void main(String[] args) {
        // 创建一个元素类型为String ，长度为10的数组
        Object arr = Array.newInstance(String.class, 10);
        Array.set(arr, 5, "Java");
        Array.set(arr, 6, "Maven");
        Object o5 = Array.get(arr, 5);
        Object o6 = Array.get(arr, 6);
        System.out.println(o5);
        System.out.println(o6);

        /*
        创建一个三维数组。
        根据前面介绍数组时讲的：三维数组也是一维数组，
        是数组元素是二维数组的一维数组，
        因此可以认为arr是长度为3的一维数组
      */
        Object arr_3 = Array.newInstance(String.class, 3, 4, 10);
        // 获取arr_3数组中index为2的元素，该元素应该是二维数组
        Object arr_3_2  = Array.get(arr_3, 2);
        // 使用Array为二维数组的数组元素赋值。二维数组的数组元素是一维数组，
        // 所以传入Array的set()方法的第三个参数是一维数组。
        Array.set(arr_3_2, 2, new String[]{"Java", "Maven"});
        // 获取arr_3_2数组中index为3的元素，该元素应该是一维数组。
        Object arr_3_2_3 = Array.get(arr_3_2, 3);
        Array.set(arr_3_2_3, 8, "Bazel");
        String[][][] cast = (String[][][]) arr_3;
        // 遍历输出数组
        Arrays.stream(cast).forEach(twoArr -> Arrays.stream(twoArr).forEach(oneArr -> System.out.println(Arrays.toString(oneArr))));

    }
}
```

## 使用反射生成JDK动态代理

在Java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口，通过使用这个
类和接口可以生成JDK动态代理类或动态代理对象。

### 使用Proxy和InvocationHandler创建动态代理

Proxy提供了用于创建动态代理类和代理对象的静态方法，它也是所有动态代理类的父类。

- 为一个或多个接口动态地生成实现类
- 为一个或多个接口动态地创建实例

Proxy提供了如下两个方法来创建动态代理类和动态代理实例:

- static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces)：创建一个动态代理类所对应的Class对象，该代理类将实现interfaces所指定的多个接口。第一个ClassLoader参数指定生成动态代理类的类加载器。
- static Object newProxylnstance(ClassLoader loader,Class<?>[] interfaces, InvocationHandler h):直接创建一个动态代理对象，该代理对象的实现类实现了interfaces指定的系列接口，执行代理对象的每个方法时都会祓替换执行InvocationHandler对象的invoke方法。

**实际上，即使采用第一个方法生成动态代理类之后，如果程序需要通过该代理类来创建对象，依然需要传入一个InvocationHandler对象。也就是说，系统生成的每个代理对象都有一个与之关联的InvocationHandler对象。**

```java
public interface People {
    void walk();
    void sayHello(String name);
}
```

```java
public class MyInvocationHandler implements InvocationHandler{

    /*
   执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
   其中：
   proxy：代表动态代理对象
   method：代表正在执行的方法
   args：代表调用目标方法时传入的实参。
   */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----正在执行的方法:" + method);
        if (args != null)
        {
            System.out.println("下面是执行该方法时传入的实参为：");
            for (Object val : args)
            {
                System.out.println(val);
            }
        }
        else
        {
            System.out.println("调用该方法没有实参！");
        }
        return null;
    }
}
```

```java
public class ProxyDemo {
    public static void main(String[] args) {
        People p = (People) Proxy.newProxyInstance(People.class.getClassLoader(),new Class[]{People.class}, new MyInvocationHandler());
        p.walk();
        p.sayHello("selfish_gene");
    }
}
```

```java
----正在执行的方法:public abstract void com.selfish.gene.reflect.People.walk()
调用该方法没有实参！
----正在执行的方法:public abstract void com.selfish.gene.reflect.People.sayHello(java.lang.String)
下面是执行该方法时传入的实参为：
selfish_gene
```

### 动态代理和AOP

 由于JDK动态代理只能为接口创建动态代理，所以下面先提供一个Dog接口，该接口代码非常简单，仅仅在该接口里定义了两个方法:

```java
public interface Dog {
    void info();
    void run();
}
```

上面接口里只是简单地定义了两个方法，并未提供方法实现。如果直接使用Proxy为该接口创建动态代理对象，则动态代理对象的所有方法的执行效果又将完全一样。实际情况通常是，软件系统会为该Dog接口提供一个或多个实现类。此处先提供一个简单的实现类：GunDog

```java
public class GunGog implements Dog {
    @Override
    public void info() {
        System.out.println("a gun dog");
    }

    @Override
    public void run() {
        System.out.println("run");
    }
}
```

借助于Proxy和InvocationHandler就可以实现——当程序调用info()方法和run()方法时，系统可以“自动”将所需要的操作（比如打印调用方法的日志信息）插入info()和run()方法中执行。

这个程序的关键在于下面的MylnvokationHandler类，该类是一个InvocationHandler实现类，该实现类的invoke()方法将会作为代理对象的方法实现。

```java
public class MyInvocationHandler implements InvocationHandler {

    /**
    * Get the log object
    */
    private static final Logger LOGGER = LogManager.getLogger();

    private Object object;

    public Object getProxyInstance(Object target){
        this.object = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        LOGGER.info("Begin to invoke method: " + method.getName());
        Object invoke = null;
        if(args == null){
            invoke = method.invoke(object);
        } else {
            invoke = method.invoke(object, args);
        }
        LOGGER.info("invoke end ");

        return invoke;
    }
}
```

```java
public class Demo {
    public static void main(String[] args) throws Exception{
        MyInvocationHandler handler = new MyInvocationHandler();
        Dog dog = (Dog) handler.getProxyInstance(new GunGog());
        dog.info();
        dog.run();
    }
}
```

```java
2017-03-18 13:57:28,293 [main] INFO : Begin to invoke method: info
a gun dog
2017-03-18 13:57:28,295 [main]  INFO : invoke end 
2017-03-18 13:57:28,295 [main]  INFO : Begin to invoke method: run
run
2017-03-18 13:57:28,295 [main]  INFO : invoke end 
```

不难发现采用动态代理可以非常灵活地实现解耦。通常而言，使用Proxy生成一个动态代理时，往往并不会凭空产生一个动态伐理，这样没有太大的实际意义。通常都是为指定的目标对象生成动态代理。

这种动态代理在**AOP（Aspect Orient Programming，面向切面编程）**中被称为AOP代理，AOP代理可代替目标对象，AOP代理包含了目标对象的全部方法。但AOP代理中的方法与目标对象的方法存在差异：AOP代理里的方法可以在执行目标方法之前、之后插入一些通用处理。

## 反射和泛型

### 泛型和Class类

使用Class<T>泛型可以避免强制类型转换:

```java
public class ObjectFactory {
    public static void main(String[] args) {
        // 不使用泛型，获取实例后需要强制转换，编译期没报错，但运行期会报错
        Date date = (Date) getInstance("java.util.Date");
//        JFrame jFrame = (JFrame) getInstance("java.util.Date");
        // 获取实例后无须类型转换
        Date date2 = getInstance2(Date.class);
        JFrame jFrame2= getInstance2(JFrame.class);
    }

    public static Object getInstance(String className){
        try {
            Class clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T>  T getInstance2(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

### 使用反射来获取泛型信息

通过指定类对应的Class对象，可以获得该类里包含的所有成员变量，不管该成员变量是使用private修饰，还是使用public修饰。获得了成员变量对应的Field对象后，就可以很容易地获得该成员变量的数据类型，即使用如下代码即可获得指定成员变量的类型:

```java
//获取成员变量f的类型
Class<?> a = f.getType();
```

但这种方式只对普通类型的成员变量有效。如果该成员变量的类型是有泛型类型的类型，如Map<String，Integer>类型，则不能准确地得到该成员变量的泛型参数。

为了获得指定成员变量的泛型类型，应先使用如下方法来获取该成员变量的泛型类型。

```java
//获得成员变量f的泛型类型
Type gType = f.getGenericrype();
```

然后将Type对象强制类型转换为ParameterizedType对象，ParameterizedType代表被参数化的类型，也就是增加了泛型限制的类型。ParameterizedType类提供了如下两个方法:

- getRawType():返回没有泛型信息的原始类塑。
- getActuaITypeArguments()：返回泛型参数的类型。

下面是一个获取泛型类型的完整程序:

```java
public class GenericDemo {
    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception {
        Class<GenericDemo> clazz = GenericDemo.class;
        Field score = clazz.getDeclaredField("score");
        // 直接使用getType()取出的类型只对普通类型的成员变量有效,下面将看到仅输出java.util.Map
        Class<?> type = score.getType();
        System.out.println(type);
        // 获得成员变量f的泛型类型
        Type genericType = score.getGenericType();
        // 如果gType类型是ParameterizedType对象
        if (genericType instanceof ParameterizedType){
            // 强制类型转换
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Arrays.stream(actualTypeArguments).forEach(type1 -> System.out.println(type1));
        } else {
            System.out.println("获取泛型类型出错！");
        }
    }
}
```

**Type也是java.lang.reflect包下的一个接口，该接口代表所有类型的公共高级接口, Class走Type接口的实现类。Type包括原始类型、参数化类型、数组类型、类型变量和基本类型等。**