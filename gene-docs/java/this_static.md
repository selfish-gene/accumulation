# this关键字和static关键字

[TOC]

## this

Java提供了一个this关键字，this关键字总是指向调用该方法的对象。根据this出现位置的不同，this作为对象的默认引用有两种情形：

- **构造器中指向引用该构造器正在初始化的对象。**
- **在方法指向中引用调用该方法的对象。**

this可以代表任何对象，当this出现在某个方法体中时，它所代表的对象是不确定的，但它的类型是确定的，它所代表的对象只能是当前类；只有当这个方法被调用时，它所代表的对象才被确定下来：谁在调用这个方法，this就代表谁。

this还以作为方法的返回值，如下代码：

```java
public class ReturnThis {
    public int age;
    public ReturnThis add(){
        age++;
        return this;
    }
    public static void main(String[] args) throws Exception {
        ReturnThis rt = new ReturnThis();
        rt.add().add().add();
        System.out.println(rt.age); //输出3，调用三次add方法，age自增三次
    }
}
```

## static

### 修饰成员变量

既类变量或者静态常量

### 修饰代码块

既静态代码块，为静态常量初始化

### 修饰方法

既类方法或者静态方法

### 修饰内部类

### 静态导入

import static可以连类名都省略，静态导入也有两种语法：

- import static package.subpackage...ClassName.*;
- import static package.subpackage.ClassName. fieldName|methodName;

## 静态成员不能直接访问非静态成员

对于static修饰的方法而言，则可以使用类来直接调用该方法，如果在static修饰的方法中使用this关键字，则**这个关键字就无法指向合适的对象**。所以，static修饰的方法中不能使用this引用。由于static修饰的方法不能使用this引用，所以static修饰的方法不能访问不使用static修饰的普通成员。