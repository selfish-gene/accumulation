# 多线程

[TOC]

## 线程概述

### 进程和线程

进程是处于运行过程中的程序，并且具有一定的独立功能，进程是系统进行资源分配和调度的一个独立单位，一般而言，进程包含如下三个特征：

- **独立性**：进程是系统中独立存在的实体，它可以拥有自己独立的资源，每一个进程都拥有自己私有的地址空间。在没有经过进程本身允许的情况下，一个用户进程不可以直接访问其他进程的地址空间。
- **动态性**：进程与程序的区别在于，程序只是一个静态的指令集合，而进程是一个正在系统中活动的指令集合。在进程中加入了时间的概念。进程具有自己的生命周期和各种不同的状态，这些概念在程序中都是不具备的。
- **并发性**：多个进程可以在单个处理器上并发执行，多个进程之间不会互相影响。

线程( Thread)也被称作轻量级进程（Lightweight Process），线程是进程的执行单元。线裎是独立运行的，它并不知道进程中是否还有其他线程存在。线程的执行是**抢占式的**，也就是说，当前运行的线程在任何时候都可能被挂起，以便另外一个线程可以运行。

**简而言之，一个程序运行后至少有一个进程，一个进程里可以包含多个线程，但至少要包含一个线程。**

### 并发性和并行性

并发（concurrency）指在同一时刻只能有一条指令执行，但多个进程指被快速轮换执行，使得在宏观上具有多个进程同时执行的效果。

并行（parallel）指在同一时刻，有多指令在多个处理器上同时执行。

## 线程创建和启动

### 继承Thread类

通过继承Thread类来创建并启动多线程的步骤如下：

1. 定义Thread类的子类，并重写该类的run()方法，该run()方法的方法体就代表了线程需要完成的任务。因此把run()方法称为线程执行体。
2. 创建Thread子类的实例，即创建了线程对象。
3. 调用线程对象的start()方法来启动该线程。

```java
public class FirstThread extends Thread {
    private int i;
  	@Override
    public void run(){
        for(; i < 8; i++){
            System.out.println(getName() + " " + i);
        }
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                new FirstThread().start();
                new FirstThread().start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
```

运行结果：

```java
main 5
Thread-0 0
Thread-0 1
Thread-0 2
Thread-0 3
Thread-0 4
Thread-1 0
Thread-1 1
Thread-1 2
Thread-1 3
Thread-1 4
main 6
```

Thread-0和Thread-l两个线程输出的i变量不连续，注意：i变量是FirstThread的实例变量，而不是局部变量，但因为程序每次创建线程对象时都需要创建一个FirstThread对象，所以Thread-0和Thread-1**不能共享该实例变量**。

### 实现Runnable接口

实现Runnable接口来创建并启动多线程的步骤如下：

1. 定义Runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的线程执行体。
2. 创建Runnable实现类的实例，并以此实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程对象。

```java
public class SecondThread implements Runnable {
    private int i;
    @Override
    public void run() {
        for(; i < 50; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                SecondThread st = new SecondThread();
                new Thread(st, "new_thread_1").start();
                new Thread(st, "new_thread_2").start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
```

运行结果：

```java
new_thread_1 29
new_thread_1 30
new_thread_1 31
new_thread_1 32
new_thread_1 33
new_thread_2 33
new_thread_1 34
new_thread_2 35
new_thread_1 36
new_thread_2 37
```

两个子线程的i变量是连续的，也就是采用Runnable接口的方式创建的多个线程可以**共享线程类的实例变量**。这是因为在这种方式下，程序所创建的Runnable对象只是线程的target，而多个线程可以共享同一个target，所以多个线程可以共享同一个线程类（实际上应该是线程的target类）的实例变量。

### 使用Callable和Future创建线程

Callable的call()和Runnable的run()方法类似，或者说应该是加强版，因为：

**call()是有返回值且可以声明抛出异常的。**

创建并启动有返回值的线程的步骤如下：

1. 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，且该call()方法有返回值，再创建Callable实现类的实例。从Java 8歼始，可以直接使用Lambda表达式创建Callable对象。
2. 使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
3. 使用FutureTask对象作为Thread对象的target创建并启动新线程。
4. 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值。

```java
public class ThirdThread {
    public static void main(String[] args) throws Exception {
        ThirdThread rt = new ThirdThread();
        // 创建Callable<Integer>对象
        Callable<Integer> integerCallable = () -> {
            int i = 0;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            return i;
        };
        FutureTask<Integer> task = new FutureTask<>(integerCallable);
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                new Thread(task, "thread_with_returnValue").start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
```

### 创建线程三种方式的对比

通过继承Thread类或实现Runnable、Callable接口都可以实现多线程，不过实现Runnable接口与实现Callable接口的方式基本相同，只是Callable接口里定义的方法有返回值，可以声明抛出异常而已。因此可以将实Runnable接口和实现Callable接口归为一种方式。这种方式与继承Thread方式之间的主要差别如下：

采用实现Runnable、Callable接口的方式创建多线程的优缺点：

**优点**：

- **避免单继承**：线程类只是实现了Runnable接口或Callable接口，还可以继承其他类。
- **共享资源**：多个线程可以共享同一个target对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以将CPU、代码和数据分开，形成清晰的模型，较好地体现了面向对象的思想

**缺点**：编程稍稍复杂，如果需要访问当前线程，则必须使用Thread.currentThread()方法。

采用继承Thread类的方式创建多线程的优缺点：

**优点**：编写简单，如果需要访问当前线程，则无须使用Thread.currentThread()方法，直接使用this即可获得当前线程。

**缺点**：因为线程类已经继承了Thread类，所以不能再继承其他父类。

**鉴于上面分析，因此一般推荐采用实现Runnable接口、Callable接口的方式来创建多线程。**

## 线程的生命周期

当线程被创建并启动以后，它既不是一启动就进入了执行状态，也不是一直处于执行状态，在线程的生命周期中，它要经过**新建(New)**、**就绪(Runnable)**、**运行(Running)**、**阻塞(Blocked)**和**死亡(Dead)**5种状态。尤其是当线程启动以后，它不可能一直“霸占”着CPU独自运行，所以CPU需要在多条线程之间切换，于是线程状态也会多次在运行、阻塞之间切换。状态转换图如下：

![LifeCycle](C:\Users\Administrator\Desktop\LifeCycle.png)

不要尝试对一个已经死亡的线程调用start()方法，否则引起IllegalThreadStateException异常。

```java
public class StartDead extends Thread {
    private int i;
    public void run(){
        for(;i<10;i++){
            System.out.println(getName() + " " + i);
        }
    }
    //运行程序，将引发IllegalThreadStateException异常，这表明处于死亡状态的线程无法再次运行了
    public static void main(String[] args) throws Exception {
        StartDead startDead = new StartDead();
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                startDead.start();
                System.out.println(startDead.isAlive());
            }
            // 只有当线程处于新建、死亡两种状态时isAlive()方法返回false。
            // 当i > 5，则该线程肯定已经启动过了，如果startDead.isAlive()为假时，
            // 那只能是死亡状态了。
            if(i > 5 && !startDead.isAlive()){
                startDead.start();
            }
        }
    }
}
```

## 控制线程

### join线程

Thread提供了让一个线程等待另一个线程完成的方法-join()方法。当在某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，直到被join()方法加入的join线程执行完为止。

```java
public class JoinThread extends Thread {
    public JoinThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(getName() + " " + i);
        }
    }
    public static void main(String[] args) throws Exception {
        new JoinThread("new_thread").start();
        for (int i = 0; i < 10; i++){
            if(i == 5){
                JoinThread jt = new JoinThread("join_thread");
                jt.start();
                //main线程调用了jt线程的join()方法，main线程，必须等jt执行结束才会向下执行
                jt.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
```

join()方法有如下三种重载形式:

- join()：等待被join的线程执行完成。
- join(long millis)：等待被join的线程的时间最长为millis毫秒。如果在millis毫秒内被join的线程还没有执行结束，则不再等待。
- join(long millis，int nanos):等待被join的线程的时间最长为millis毫秒加nanos毫微秒。

常很少使用第三种形式，原因有两个：

​	程序对时间的精度无须精确到毫微秒；

​	计算操作系统本身也无法精确到毫微秒。

### 后台线程

有一种线程，它是在后台运行的，它的任务是为其他的线程提供服务，这种线程被称为“后台线程( Daemon Thread)”，又称为“守护线程”或“精灵线程”。JVM的垃圾回收线程就是典型的后台线程。

后台线程有个特征：**如果所有的前台线程都死亡，后台线程会自动死亡。**

调用Thread对象的setDaemon(true)方法可将指定线程设置成后台线程。下面程序将执行线程设置成后台线程，可以看到当所有的前台线程死亡时，后台线程随之死亡。当整个虚拟机中只剩下后台线程时，程序就没有继续远行的必要了，所以虚拟机也就退出了。

```java
public class DaemonThread extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++){
            System.out.println(getName() + " " + i);
        }
    }
    public static void main(String[] args) throws Exception {
        DaemonThread dt = new DaemonThread();
        dt.setDaemon(true);
        dt.start();
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        //程序到此结束，前台线程（main）结束，后台线程也随之结束。
    }
}
```

从上面程序可以看出，主线程默认是前台线程，t线程默认也是前台线程。并不是所有的线程默认都是前台线程，有些线程默认就是后台线程——前台线程创建的子线程默认是前台线程，后台线程创建的子线程默认是后台线程。

### 线程睡眠

如果需要让当前正在执行的线程暂停一段时间，并进入阻塞状态，则可以通过调用Thread类的静态sleep()方法来实现。sleep()方法有两种重载形式：

- static void sleep(long millis)：让当前正在执行的线程暂停millis毫秒，并进入阻塞状态，该方法受到系统计时器和线程调度器的精度与准确度的影响。
- static void sleep(long millis，int nanos)：让当前正在执行的线程暂停millis毫秒加nanos毫微秒，并进入阻塞状态，该方法受到系统计时器和线程调度器的精度与准确度的影响。

与前面类似的是，程序很少调用第二种形式的sleep()方法。

### 线程让步

yield()方法是一个和sleep()方法有点相似的方法，它也是Thread类提供的一个静态方法，它也可以让当前正在执行的线程暂停，但它不会阻塞该线程，它只是将该线程转入就绪状态。yield()只是让当前线程暂停一下，让系统的线程调度器重新调度一次，完全可能的情况是：当某个线程调用了yield()方法暂停之后，线程调度器又将其调度出来重新执行。

关于sleep()方法和yiel()方法的区别如下：

- sleep()方法暂停当前线程后，会给其他线程执行机会，不会理会其他线程的优先级；但yield0方法只会给优先级相同，或优先级更高的线程执行机会。
- sleep()方法会将线程转入阻塞状态，直到经过阻塞时间才会转入就绪状态；而yield()不会将线程转入阻塞状态，它只是强制当前线程进入就绪状态。因此宪全有可能某个线程调用yield()方法暂停之后，立即再次获得处理器资源被执行。
- sleep()方法声明抛出了InterruptedException异常，所以调用sleep()方法时要么捕捉该异常，要么显式声明抛出该异常；而yield()方法则没有声明抛出任何异常。
- sleep()方法比yield()方法有更好的可移植性，通常不建议使用yield()方法来控制并发线程的执行。

### 改变线程优先级

每个线程执行时都具有一定的优先级，优先级高的线程获得较多的执行机会，而优先级低的线程则获得较少的执行机会。

每个线程默认的优先级都与创建它的父线程的优先级相同，在默认情况下，main线程具有普通优先级，由main线程创建的子线程也具有普通优先级。

Thread类提供了setPriority(int newPriority)、getPriority()方法来设置和返回指定线程的优先级，其setPriority()方法的参数可以是一个整数，范围是1～10之间，也可以使用Thread类的如下三个静态常量：

- MAX_ PRIORITY:其值是10。
- MIN_PRIORITY:其值是1。
- NORM_ PRIORITY:其值是5。

## 线程同步

### 线程安全问题--案例：**银行取钱**

银行取钱的基本流程基本上可以分为如下几个步骤：

1. 用户输入账户、密码，系统判断用户的账户、密码是否匹配

2. 用户输入取款金额

3. 系统判断账户余额是否大于取款金额

4. 如果余额大于取款金额，则取款成功；如果余额小于取款金额，则取款失败

   ​

按上面的流程去编写取款程序，并使用两个线程来模拟取钱操作，模拟两个人使用同一个账户并发取钱的问题。此处忽略检查账户和密码的操作，仅仅模拟后面三步操作。下面先定义一个账户类，该账户类封装了账户编号和余额两个实例变量：

```java
public class Account {
    private String accountNo;
    private double balance;
    public Account() {
    }
    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    // 下面两个方法根据accountNo来重写hashCode()和equals()方法
    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == Account.class){
            Account target = (Account) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
```

接下来提供一个取钱的线程类，该线程类根据执行账户、取钱数量进行取钱操作，取钱的逻辑是当其余额不足时无法提取现金，当余额足够时系统吐出钞票，余额减少。

```java
public class DrawThread extends Thread{
    private Account account;
    private double drawAccount;
    public DrawThread(String name, Account account, double drawAccount){
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }
    @Override
    public void run() {
//        synchronized (account) {
            if(account.getBalance() >= drawAccount){
                System.out.println("取钱成功，数量为：" + drawAccount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawAccount);
                System.out.println("\t余额为:" + account.getBalance());
            } else {
                System.out.println("余额不足");
            }
//        }
    }
    public static void main(String[] args) throws Exception {
        Account account = new Account("123", 1000);
        new DrawThread("A", account,600).start();
        new DrawThread("B", account,600).start();
    }
}
```

运行结果：

```java
取钱成功，数量为：600.0
取钱成功，数量为：600.0
	余额为:400.0
	余额为:-200.0
```

这是由于线程不同步所导致的。下面将会利用synchronized和Lock两种方式达到同步效果。

### synchronized

#### 同步代码块

同步代码块语法如下：

```java
synchronized (obj){//obj就是同步监视器
//此处的代码就是同步代码块
}
```
只需要将上述案例中run()方法里注释的同步代码块打开即可:

```java
public class DrawThread extends Thread{

    private Account account;

    private double drawAccount;

    public DrawThread(String name, Account account, double drawAccount){
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }

    @Override
    public void run() {
     	//使用account作为同步监视器，任何线程进入下面同步代码块之前
    	//必须先获得对account账户的锁定——其他线程无法获得锁，也就无法修改它
    	//这种做法符合：“加锁一修改一释放锁”的逻辑
        synchronized (account) {
            if(account.getBalance() >= drawAccount){
                System.out.println("取钱成功，数量为：" + drawAccount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawAccount);
                System.out.println("\t余额为:" + account.getBalance());
            } else {
                System.out.println("余额不足");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Account account = new Account("123", 1000);
        new DrawThread("A", account,600).start();
        new DrawThread("B", account,600).start();
    }
}
```

运行结果如下：

```java
取钱成功，数量为：600.0
	余额为:400.0
余额不足
```

上面语法格式中synchronized后括号里的obj就是**同步监视器**，上面代码的含义是：**线程开始执行同步代码块之前，必须先获得对同步监视器的锁定。**

**同步监视器的目的：阻止两个线程对同一个共享资源进行并发访问，因此通常推荐使用可能被并发访问的共享资源充当同步监视器。**

#### 同步方法

与同步代码块对应，Java的多线程安全支持还提供了同步方法，同步方法就是使用synchronized关键字来修饰某个方法，则该方法称为**同步方法**。对于synchronized修饰的实例方法（非static万法）而言，无须显式指定同步监视器，**同步方法的同步监视器是this**，也就是调用该方法的对象。

下面将Account2类对balance的访问设置成线程安全的，那么只要把修改balance的方法变成同步方法即可。程序如下所示：

```java
public class Account2 {
    private String accountNo;
    private double balance;
    public Account2() {
    }
    public Account2(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    //余额不允许修改，只提供getter方法
    public double getBalance() {
        return this.balance;
    }
    // 下面两个方法根据accountNo来重写hashCode()和equals()方法
    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == Account2.class){
            Account2 target = (Account2) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }

    public synchronized void draw(double drawAccount){
        if(balance >= drawAccount){
            System.out.println(Thread.currentThread().getName() + "取钱成功，数量为：" + drawAccount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= drawAccount;
            System.out.println("\t余额为:" + this.getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() +"余额不足");
        }
    }
}
```

而取钱的线程类改为如下即可：

```java
public class DrawThread2 extends Thread{
    private Account2 account;
    private double drawAccount;
    public DrawThread2(String name, Account2 account, double drawAccount){
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }
    @Override
    public void run() {
        account.draw(drawAccount);
    }
    public static void main(String[] args) throws Exception {
        Account2 account = new Account2("123", 1000);
        new DrawThread2("A", account,600).start();
        new DrawThread2("B", account,600).start();
    }
}
```

运行结果：

```java
A取钱成功，数量为：600.0
	余额为:400.0
B余额不足
```

### 同步锁

从Java 5开始，Java提供了一种功能更强大的线程同步机制——通过显式定义同步锁对象来实现同步，在这种机制下，同步锁由Lock对象充当。

Lock是控制多个线程对共享资源进行访问的工具。通常，锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应先获得Lock对象。

在实现线程安全的控制中，比较常用的是ReentrantLock（可重入锁）。使用该Lock对象可以显式地加锁、释放锁，通常使用ReentrantLock韵代码格式如下：

```java
   class X {
   		//定义锁对象
   		private final ReentrantLock lock = new ReentrantLock();
   }
   public void m(){
     lock.lock();
     try{
       //需要保证线程安全的代码块
     }
     finally{
       lock.unlock();
     }
   }
```

可以把上面银行的账户类修改如下：

```java
public class Account3 {
    private final ReentrantLock lock = new ReentrantLock();
    private String accountNo;
    private double balance;
    public Account3() {
    }
    public Account3(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public double getBalance() {
        return this.balance;
    }
    // 下面两个方法根据accountNo来重写hashCode()和equals()方法
    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == Account3.class){
            Account3 target = (Account3) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
    public void draw(double drawAccount){
        lock.lock();
        try {
            if(balance >= drawAccount){
                System.out.println(Thread.currentThread().getName() + "取钱成功，数量为：" + drawAccount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= drawAccount;
                System.out.println("\t余额为:" + this.getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() +"余额不足");
            }
        } finally {
            lock.unlock();
        }
    }
}
```

Lock提供了比synchronized方法和synchronized伐码块更广泛的锁定操作，Lock允许实现更灵活的结构，可以具有差别很大的属性，并且支持多个相关的Condition对象（用于线程通信）。

ReentrantLock锁具有**可重入性**，也就是说，一个线程可以对已被加锁的ReentrantLock锁再次加锁，ReentrantLock对象会维持一个计数器来追踪lock()方法的嵌套调用，线程在每次调用lock()加锁后，必须显式调用unlock()来释放锁，所以一段被锁保护的代码可以调用另一个被相同锁保护的方法。

### 死锁

当**两个线程相互等待对方释放同步监视器**时就会发生死锁，Java虚拟机没有监测，也没有采取措施来处理死锁情况，所以多线程编程时应该采取措施避免死锁出现。一旦出现死锁，整个程序既不会发生任何异常，也不会给出任何提示，只是所有线程处于阻塞状态，无法继续。

死锁是很容易发生的，尤其在系统中出现多个同步监视器的情况下，如下程序将会出现死锁：

```java
public class A {
    public synchronized void userA(B b){
        System.out.println(Thread.currentThread().getName() + " userA方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 企图调用B实例的last方法");
        b.last();
    }
    public synchronized void last(){
        System.out.println("调用A的last方法");
    }
}
```

```java
public class B {
    public synchronized void userB(A a){
        System.out.println(Thread.currentThread().getName() + " userB方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 企图调用A实例的last方法");
        a.last();
    }
    public synchronized void last(){
        System.out.println("调用B的last方法");
    }
}
```

```java
public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();
    public void init(){
        Thread.currentThread().setName("primary");
        a.userA(b);
        System.out.println("进入primary线程");
    }
    @Override
    public void run() {
        Thread.currentThread().setName("vice");
        b.userB(a);
        System.out.println("进入vice线程");
    }
    public static void main(String[] args) throws Exception {
        DeadLock deadLock = new DeadLock();
        Thread thread = new Thread(deadLock);
        thread.start();
        //在此处通过join控制，必须等thread线程执行完毕，main线程才能继续往下执行，则不会出现死锁。
//        thread.join();

        // 调用init方法
        deadLock.init();
        /*  死锁发生的原因：
            1.两个线程执行需要一定的时间（在各自的同步方法里有sleep了200毫秒）
            2.两个线程开始执行的顺序没有进行控制
            3.两个线程互相持有对方的锁（同步监视器）
         */
    }
}
```

运行结果：

```java
primary userA方法
vice userB方法
primary 企图调用B实例的last方法
vice 企图调用A实例的last方法
```

## 线程通信

### **生产者与消费者**

预期达到效果：

1.只能消费已经生产的的产品

2.消费数量应小于所生产的数量

代码实现：

```java
public class Product {
    private int n;
    /**
     * for consumer
     * @return
     */
    public synchronized int get(){
        System.out.println("消费：" + n);
        return n;
    }
    /**
     * for producer
     * @param n
     */
    public synchronized void put(int n){
        this.n = n;
        System.out.println("生产：" + n);
    }
}
```

```java
public class Producer implements Runnable{
    private Product product;
    public Producer(Product product) {
        this.product = product;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            //模拟生产产品，产品数量为5
            product.put(i);
        }
    }
}
```

```java
public class Consumer implements Runnable {
    private Product product;
    public Consumer(Product product) {
        this.product = product;
    }
    @Override
    public void run() {
       	for (int i = 0; i < 5; i++){
          	//模拟消费产品，产品数量为5
         	product.get();
      	}
    }
```

```java
public class Demo {
    public static void main(String[] args) throws Exception {
        //执行代码
        Product p = new Product();
        new Thread(new Producer(p)).start();
        new Thread(new Consumer(p)).start();
    }
}
```

运行结果：

```
生产：0					
生产：1
生产：2
生产：3
生产：4
消费：4
消费：4
消费：4
消费：4
消费：4
```

或者：

```
生产：0
消费：0
消费：0
消费：0
消费：0
消费：0
生产：1
生产：2
生产：3
生产：4
```

总是存在**过度消费**或者**过度生产**的问题。

#### 解决方案一：传统通信机制wait()与notify()

利用传统通信机制wait()与notify()，只需修改Product类，代码如下：

```java
public class Product {
    private int n;
    //刚开始，是生产者先生产，消费者等待，为true时消费者才可消费
    boolean flag = false;
    /**
     * for consumer
     * @return
     */
    public synchronized int get(){
        try {
            if(!flag){
                wait();
            }
            System.out.println("消费：" + n);
            flag = false;
            // 通知生产者生产
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }
    /**
     * for producer
     * @param n
     */
    public synchronized void put(int n){
        try {
            if(flag){
                wait();
            }
            this.n = n;
            System.out.println("生产：" + n);
            flag = true;
            // 通知消费者消费
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

Producer、Consumer与Demo代码不变，运行结果：

```java
生产：0
消费：0
生产：1
消费：1
生产：2
消费：2
生产：3
消费：3
生产：4
消费：4
```

#### 解决方案二：Lock与Condition

利用Lock与Condition，也只需修改Product类，代码如下：

```java
public class Product {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int n;
    boolean flag = false;
    /**
     * for consumer
     * @return
     */
    public int get(){
        lock.lock();
        try {
            if(!flag){
                condition.await();
            }
            System.out.println("消费：" + n);
            flag = false;
            // 通知生产者生产
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return n;
    }
    /**
     * for producer
     * @param n
     */
    public void put(int n){
        lock.lock();
        try {
            if(flag){
                condition.await();
            }
            this.n = n;
            System.out.println("生产：" + n);
            flag = true;
            // 通知消费者消费
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

Producer、Consumer与Demo代码不变，运行结果：

```java
生产：0
消费：0
生产：1
消费：1
生产：2
消费：2
生产：3
消费：3
生产：4
消费：4
```

#### 解决方案三：BlockingQueue

Java 5提供了一个BlockingQueue接口，虽然BlockingQueue也是Queue的子接口，但它的主要用途并不是作为容器，而是作为**线程同步的工具**。

BlockingQueue具有一个特征：当生产者线程试图向BlockingQueue中放入元素时，如果该队别已满，则该线程被阻塞：当消费者线程试图从BlockingQueue中取出元素时，如果该队列己空，则该线程被阻塞。代码如下：

```java
public class Product {
    private BlockingQueue<Integer> bq;
    private int n;
    public Product(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }
    /**
     * for consumer
     * @return
     */
    public int get(){
        try {
            n = bq.take();
            System.out.println("消费：" + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }
    /**
     * for producer
     * @param n
     */
    public void put(int n){
        try {
            bq.put(n);
            System.out.println("生产：" + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

```java
public class Demo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(1);
        Product p = new Product(bq);
        new Thread(new Producer(p)).start();
        new Thread(new Consumer(p)).start();
    }
}
```

Producer与Consumer代码不变，运行结果：

```java
生产:0
消费:0
生产:1
消费:1
生产:2
消费:2
生产:3
消费:3
生产:4
消费:4
```

#### 解决方案四：Semaphore

利用concurrent包下的Semaphore工具类，代码如下：

```java
public class SemaphoreDemo {
    private Semaphore consumer = new Semaphore(0);
    private Semaphore producer = new Semaphore(1);
    // 模拟产品
    int n;
    // 消费者
    public int get(){
        try {
            // 获取消费许可，没有则线程挂起，代码不往下执行；有则消费一个许可，许可数量-1
            consumer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费:" + n);
        // 每当消费者消费后，给生产者释放一个许可
        producer.release();
        return n;
    }
    // 生产者
    public void put(int n){
        try {
            // 获取生产许可，没有则线程挂起，代码不往下执行；有则消费一个许可，许可数量-1
            producer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.n = n;
        // 每当生产者生产后，给消费者释放一个许可
        consumer.release();
        System.out.println("生产:" + n);
    }
    public static void main(String[] args) throws Exception {
        SemaphoreDemo q = new SemaphoreDemo();
        for (int i = 0; i < 5; i++){
            q.put(i);
            q.get();
        }
    }
}
```

运行结果：

```java
生产:0
消费:0
生产:1
消费:1
生产:2
消费:2
生产:3
消费:3
生产:4
消费:4
```

### CountDownLatch 计数器

案例如下：

```java
public class CountDownLatchDemo implements Runnable {
    private CountDownLatch start;
    private CountDownLatch end;
    public CountDownLatchDemo(CountDownLatch start, CountDownLatch end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        try {
            start.await();
            for (int i = getEndCount(); i > 0; i--) {
                System.out.println("结束倒计时：" + i + "s");
                Thread.currentThread().sleep(1000);
                end.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static int getBeginCount(){
        return 5;
    }
    private static int getEndCount(){
        return 10;
    }
    public static void main(String[] args) throws Exception {
        // 当计数为0时启动线程，否则线程挂起
        CountDownLatch start = new CountDownLatch(getBeginCount());
        CountDownLatch end = new CountDownLatch(getEndCount());
        new Thread(new CountDownLatchDemo(start, end)).start();
        for (int i = getBeginCount(); i > 0; i--){
            System.out.println("开始倒计时：" + i + "s");
            Thread.currentThread().sleep(1000);
            start.countDown();
        }
        System.out.println("begin something-----------------------------");
        end.await();
        System.out.println("the end");
    }
}
```

运行结果：

```java
开始倒计时：5s
开始倒计时：4s
开始倒计时：3s
开始倒计时：2s
开始倒计时：1s
begin something-----------------------------
结束倒计时：10s
结束倒计时：9s
结束倒计时：8s
结束倒计时：7s
结束倒计时：6s
结束倒计时：5s
结束倒计时：4s
结束倒计时：3s
结束倒计时：2s
结束倒计时：1s
the end
```

### CyclicBarrier

等待所有线程到达同一集合点后，再执行后续线程 ，案例如下：

```java
public class Wait implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private String name;
    public Wait(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(name + " is waiting");
            cyclicBarrier.await();
            if(Thread.currentThread().getName().equals("Allen")){
                System.out.println("我等的时间最长，终于可以走了！");
            } else{
                System.out.println("哈哈，一起走吧!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
```

```java
public class Go implements Runnable {
    @Override
    public void run() {
        System.out.println("人来齐了，Ready to go!!!");
    }
}
```

```java
public class MainDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Go());
        //给定数量的Wait线程全部处于await()时，Go线程开始执行，然后Wait线程随机执行
        new Thread(new Wait(cyclicBarrier, "Allen"), "Allen").start();
        new Thread(new Wait(cyclicBarrier, "Edam")).start();
        new Thread(new Wait(cyclicBarrier, "A")).start();
    }
}
```

运行结果：

```java
Allen is waiting
Edam is waiting
A is waiting
人来齐了，Ready to go!!! 
哈哈，一起走吧!!!
哈哈，一起走吧!!!
我等的时间最长，终于可以走了！
```

## 线程池

系统启动一个新线程的成本是比较高的，因为它涉及与操作系统交互。在这种情形下，使用线程池可以很好地提高性能，尤其是当程序中需要创建大量生存期很短暂的线程时，更应该考虑使用线程池。

### Executors创建线程池

Java 5新增了—个Executors工厂类来产生线程池，该工厂类包含如下几个静态工厂方法来创建线程池：

1. **newCachedThreadPool()**：创建一个具有缓存功能的线程池，系统根据需要创建线程，这些线程将会被缓存在线程池中。
2. **newFixedThreadPool(int nThreads）：**创建一个可重用的、具有固定线程数的线程池。
3. **newSingleThreadExecutor()**：创建一个只有单线程的线程池，它相当于调用newFixedThread Pool()方法时传入参数为1。
4. **newScheduledThreadPool(int corePooISize)**：创建具有指定线程数的线程池，它可以在指定延迟后执行线程任务。corePoolSize指池中所保存的线程数，即使线程是空闲的也被保存在线程池内。
5. **newSingleThreadScheduledExecutor()**：创建只有一个线程的线程池，它可以在指定延迟后执行线程任务。
6. **ExecutorService newWorkStealingPool(int parallelism)**：创建持有足够的线程的线程池来支持给定的并行级别，该方法还会使用多个队列来减少竞争。
7. **ExecutorService newWorkStealingPool()**：该方法是前一个方法的简化版本。如果当前机器有4个CPU，则目标并行级别被设置为4，也就是相当于为前一个方法传入4作为参数。

**1、2、3三个方法返回一个ExecutorService对象，该对象代表一个线程池，它可以执行Runnable对象或Callable对象所代表的线程；**

**4、5两个方法返回一个ScheduledExecutorService线程池，它是ExecutorService的子类，它可以在指定延迟后执行线程任务；**

**6、7两个方法则是Java 8新增的，这两个方法可充分利用多CPU并行的能力。这两个方法生成的work stealing池，都相当于后台线程池，如果所有的前台线程都死亡了，work stealing池中的线程会自动死亡。**

使用线程池来执行线程任务的步骤如下：

1. 调用Executors类的静态工厂方法创建一个ExecutorService对象，该对象代表一个线程池。
2. 创建Runnable实现类或Callable实现类的实例，作为线程执行任务。
3. 调用ExecutorService对象的submit()方法来提交Runnable实例或Callable实例。
4. 当不想提交任何任务时，调用ExecutorService对象的shutdown()方法来关闭线程池。

案例如下：

```java
public class ThreadPoolDemo {
    public static void main(String[] args) throws Exception {
        //创建一个具有固定线程数(6)的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);
        //用Lambda表达式创建Runnable实例
        Runnable target = () -> {
            for (int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + " 的i值为：" + i);
            }
        };
        //向池中添加2个线程
        Future<Integer> future = pool.submit(target, 1);
        System.out.println(future.get());
        pool.submit(target);
        //关闭线程池
        pool.shutdown();
    }
}
```

运行结果：

```java
pool-1-thread-1 的i值为：0
pool-1-thread-1 的i值为：1
pool-1-thread-1 的i值为：2
pool-1-thread-1 的i值为：3
pool-1-thread-1 的i值为：4
1
pool-1-thread-2 的i值为：0
pool-1-thread-2 的i值为：1
pool-1-thread-2 的i值为：2
pool-1-thread-2 的i值为：3
pool-1-thread-2 的i值为：4
```

### ForkJoinPool

为了充分利用多CPU、多核CPU的性能优努，计算机软件系统应该可以充分“挖掘”每个CPU的计算能力，绝不能让某个CPU处于“空闲”状态。为了充分利用多CPU、多核CPU的优势，可以考虑把一个任务拆分成多个“小任务”，把多个“小任务”放到多个处理器核心上并行执行；当多个“小任务”执行完成之后，再将这些执行结果合并起来即可。

Java 7提供了ForkJoinPool来支持将一个任务拆分成多个“小任务”并行计算，再把多个“小任务”的结果合并成总的计算结果。**ForkjoinPool是ExecutorService的实现类，因此是一种特殊的线程池。**ForkjoinPool提供了如下两个常用的构造器：

1. **ForkJoinPool(int parallelism)**：创建一个包含parallelism个并行线程的ForkjoinPool。
2. **ForkJoinPool()**：以Runtime.availableProcessors0方法的返回值作为parallelism参数来创建ForkJoinPool。

Java 8进一步扩展了ForkJoinPool的功能，Java 8为ForkJoinPool增加了通用池功能。ForkJoinPool类通过如下两个静态方泫提供通用池功能：

1. **ForkjoinPool commonPool()**：该方法返回一个通用池，通用池的运行状态不会受shutdown()或shutdownNow()方法的影响。当然，如果程序直接执行System.exit(0);来终止虚拟机，通用池以及通用池中正在执行的任务都会被自动终止。
2. **int getCommonPoolParallelism()**：该方法返回通用池的并行级别。

**创建了ForUoinPool实例之后，就可调用ForkjoinPool的submit(ForUoinTask task)或invoke(ForkjoinTask task)方法来执行指定任务了。其中ForkjoinTask代表一个可以并行、合并的任务。ForkjoinTask是一个抽象类，它还有两个抽象子类：RecursiveAction和RecursiveTask。其中RecursiveTask代表有返回值的任务，而RecursiveAction代表没有返回值的任务。**

![ForkJoinPool](C:\Users\Administrator\Desktop\ForkJoinPool.png)

下面以执行没有返回值的“大任务”（简单地打印0—300的数值）为例，程序将一个“大任务”拆分成多个“小任务”，并将任务交给ForkjoinPool来执行，代码如下：

```java
public class PrintTask extends RecursiveAction {
    //每个“小任务”最多只打印50个数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;
    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++){
                System.out.println(Thread.currentThread().getName() + "的i值为：" + i);
            }
        } else {
            //当end与start之间的差大于THRESHOLD．即要打印的数超过50个时,将大任务分解成两个“小任务”
            int middle = (start + end)/2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }
    }
}
```

```java
public class ForkJoinPoolDemo {
    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(0,300));
        pool.awaitTermination(5, TimeUnit.SECONDS);
        pool.shutdown();
        //计算机CPU并行数，或者说计算机核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
```

运行结果：

```java
ForkJoinPool-1-worker-2的i值为：127
ForkJoinPool-1-worker-2的i值为：128
ForkJoinPool-1-worker-2的i值为：129
ForkJoinPool-1-worker-2的i值为：130
...
ForkJoinPool-1-worker-3的i值为：220
ForkJoinPool-1-worker-5的i值为：257
ForkJoinPool-1-worker-5的i值为：258
ForkJoinPool-1-worker-5的i值为：259
ForkJoinPool-1-worker-5的i值为：260
ForkJoinPool-1-worker-3的i值为：222
ForkJoinPool-1-worker-3的i值为：223
ForkJoinPool-1-worker-3的i值为：224
ForkJoinPool-1-worker-5的i值为：261
8
```

上面定义的任务是一个没有返回值的打印任务，如果大任务是有返回值的任务，则可以让任务继承RecursiveTask<T>，其中泛型参数T就代表了该任务的返囤值类型。下面程序示范了使用Recursive Task对一个长度为100的数组的元素值进行累加，代码如下：

```java
public class CalTask extends RecursiveTask<Integer> {
    //每个“小任务”最多只累加20个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;
    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end与start之间的差小于THRESHOLD，直接累加
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++){
                sum += arr[i];
            }
            return sum;
        } else {
            //当end与start之间的差大于THRESHOLD．即要累加的数超过20个时,将大任务分解成两个“小任务”
            int middle = (start + end)/2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            //把两个任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
```

```java
public class Sum {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[100];
        Random r = new Random();
        int total = 0;
        for (int i = 0; i < 100; i++){
            int tmp = r.nextInt(20);
            arr[i] = tmp;
            total += arr[i];
        }
        System.out.println(total);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        System.out.println(future.get());
        pool.shutdown();
    }
}
```

## 线程相关类

### ThreadLocal

ThreadLocal，是Thread Local Variable（线程局部变量）的意思。线程局部变量( ThreadLocal)的功用其实非常简单，就是为每一个使用该变量的线程都提供一个变量值的副本，使每一个线程都可以独立地改变自己的副本，而不会和其他线程的副本冲突。从线程的角度看，就好像每一个线程都完全拥有该变量一样。

ThreadLocal类的用法非常简单，它只提供了如下三个public方法：

- T get()：返回此线程局部变量中当前线程副本中的值。
- void remove():删除此线程局部变量中当前线程的值。
- void set(T value):设置此线程局部变量中当前线程副本中的值。

案例如下：

```java
public class Account {
    //定义一个ThreadLocal类型的变量，该变最将是一个线程局部变量，每个线程都会保留该变量的一个副本
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public Account(String name) {
        this.threadLocal.set(name);
        //下面代码用于访问当前线程的name副本的值
        System.out.println("---" + this.threadLocal.get());
    }
    public String getName() {
        return threadLocal.get();
    }
    public void setName(String name) {
        this.threadLocal.set(name);
    }
}
```

```java
public class MyDemo extends Thread{
    private Account account;
    public MyDemo(Account account, String name){
        super(name);
        this.account = account;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            if(i == 6){
                account.setName(getName());
            }
            String accountName = account.getName();
            System.out.println(accountName + " 账户的i值：" + i);
        }
    }
    public static void main(String[] args) throws Exception {
        Account account = new Account("init");
        /*虽然两个线程共享同一个账户，即只有一个账户名
        但由于账户名是ThreadLocal类型的，所以每个线程
        都完全拥有各自的账户名副本，因此在i==6之后．将看到两个
        线程访问同一个账户时出现不同的账户名
        */
        new MyDemo(account, "A").start();
        new MyDemo(account, "B").start();
    }
}
```

运行结果：

```java
---init		//Main线程初始化Account是调用了一次
null 账户的i值：0 
null 账户的i值：1
null 账户的i值：2
null 账户的i值：3
null 账户的i值：4
null 账户的i值：5
A 账户的i值：6
A 账户的i值：7
null 账户的i值：0
null 账户的i值：1
null 账户的i值：2
null 账户的i值：3
null 账户的i值：4
null 账户的i值：5
B 账户的i值：6
B 账户的i值：7
```

### 包装线程不安全的集合

Java集合中的ArrayList、LinkedList、HashSet、TreeSet、HashMap、TreeMap等都是线程不安全的，也就是说，当多个并发线程向这些集合中存、取元素时，就可能会破坏这些集合的数据完整性。

如果程序中有多个线程可能访问以上这些集合，就可以使用Collections提供的类方法把这些集合包装成线程安全的集合。Collections提供了如下几个静态方法：

- <T> Collection<T> synchronizedCollection(Collection<T> c)：返回指定collection对应的线程安全的collection。
- static<T> List<T> synchronizedList(List<T> list)：退回指定List对象对应的线程安全的List对象。
- static <K，V> Map<K，V> synchronizedMap(Map<K，V>m)：返回指定Map对象对应的线程安全的Map对象。
- static <T> Set<T> synchronizedSet(Set<T> s)：返回指定Set对象对应的线程安全的Set对象。
- static<K，V> SortedMap<K，V> synchronizedSortedMap(SortedMap<K，V>m)：返[司指定SortedMap对象对应的线程安全的SortedMap对象。
- static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s):返回指定SortedSet对象对应的线程安全的SortedSet对象。

例如需要在多线程中使用线程安全的HashMap对象，则可以采用如下代码：

```java
Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());
```

### 线程安全的集合类

从Java 5开始，在java.util．concurrent包下提供了大量支持高效并发访问的集合接口和实现类。这些线程安全的集合类可分为如下两类：

- **以Concurrent开头的集合类**，如ConcurrentHashMap. ConcurrentSkipListMap. ConcurrentSkip ListSet.

  ConcurrentLinkedQueue和ConcurrentLinkedDeque。


- **以CopyOnWrite开头的集合类**，如CopyOnWriteArrayList. CopyOnWriteArraySet。

## 线程组和未处理异常

 Java使用ThreadGroup来表示线程组，它可以对一批线程进行分类管理，Java允许程序直接对线程组进行控制。对线程组的控制相当于同时控制这批线程。用户创建的所有线程都属于指定线程组，如果程序没有显式指定线程属于哪个线程组，则该线程属于默认线程组。在默认情况下，子线程和创建它的父线程处于同一个线程组内，例如A线程创建了B线程，并且没有指定B线程的线程组，则B线程属于A线程所在的线程组。

**一旦某个线程加入了指定线程组之后，该线程将一直属于该线程组，直到该线程死亡，线程运行中途不能改变它所属的线程组。**

Thread类提供了如下几个构造器来设置新创建的线程属于哪个线程组：

- Thread(ThreadGroup group，Runnable target)：以target的run()方法作为线程执行体创建新线程，属于group线程组。
- Thread(ThreadGroup group，Runnable target，String name)：以target的ru()方法作为线程执行体创建新线程，该线程属于group线程组，且线程名为name。
- Thread(ThreadGroup group，String name)：创建新线程，新线程名为name，属于group线程组。

ThreadGroup类提供了如下两个简单的构造器来创建实例：

- ThreadGroup(Stringname)：以指定的线程组名字来创建新的线程组。
- ThreadGroup(ThreadGroup parent，String name)：以指定的名字、指定的父线程组创建一个新线程组。

上面两个构造器在创建线程组实例时都必须为其指定一个名字，也就是说，线程组总会具有一个字符串类型的名字，该名字可通过调用ThreadGroup的getName()方法来获取，但不允许改变线程组的名字。

ThreadGroup类提供了如下几个常用的方法来操作整个线程组里的所有线程：

- intactiveCount()：返回此线程组中活动线程的数目。
- interrupt():中断此线程组中的所有线程。
- isDaemon():判断该线程组是否是后台线程组。
- setDaemon(boolean daemon)：把该线程纽设置成后台线程组。后台线程组具有一个特征——当后台线程组的最后一个线程执行结束或最后一个线程被销毁后，后台线程组将自动销毁。
- setMaxPriority(intpri)：设置线程组的最高优先级。

案例如下：

```java
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(getName() + " i:" + i);
        }
    }
}
```

```java
public class ThreadGroupDemo {
    public static void main(String[] args) throws Exception {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组名：" + threadGroup);
        System.out.println("主线程是够后台线程组：" + threadGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup newGroup = new ThreadGroup("newGroup");
        newGroup.setDaemon(true);
        new MyThread(newGroup,"newGroup的线程_1").start();
        new MyThread(newGroup,"newGroup的线程_2").start();
    }
}
```

```java
//运行结果：
主线程组名：java.lang.ThreadGroup[name=main,maxpri=10]
主线程是够后台线程组：false
主线程组的线程 i:0
主线程组的线程 i:1
主线程组的线程 i:2
主线程组的线程 i:3
主线程组的线程 i:4
newGroup的线程_1 i:0
newGroup的线程_1 i:1
newGroup的线程_1 i:2
newGroup的线程_1 i:3
newGroup的线程_1 i:4
newGroup的线程_2 i:0
newGroup的线程_2 i:1
newGroup的线程_2 i:2
newGroup的线程_2 i:3
newGroup的线程_2 i:4
```

ThreadGroup内还定义了一个很有用的方法：void uncaughtException(Thread t，Throwable e)，该方法可以处理该线程组内的任意线程所抛出的未处理异常。

从Java 5开始，Java加强了线程的异常处理，如果线程执行过程中抛出了一个未处理异常，JVM在结束该线程之前会自动查找是否有对应的Thread．UncaughtExceptionHandler对象，如果找到该处理器对象，则会调用该对象的uncaughtException(Thread t，Throwable e)方法来处理该异常。Thread.UncaughtExceptionHandler是Thread类的一个静态内部接口，该接口内只有一个方法：void uncaughtException(Thread t，Throwable e)，该方法中的t代表出现异常的线程，而e代表该线程抛出的异常。

Thread类提供了如下两个方法来设置异常处理器：

- static setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)：为该线程类的所有线程实例设置默认的异常处理器。
- setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)：为指定的线程实例设置异常处理器。

ThreadGroup类实现了Thread.UncaughtExceptionHandler接口,所以每个线程所属的线程组将会作为默认的异常处理器。当一个线程抛出未处理异常时,JVM会首先查找该异常对应的异常处理(setUncaughtExceptionHandler()方法设置的异常处理器），如果找到该异常处理器，则将调用该异常处理器处理该异常；否则，JVM将会调用该线程所属的线程组对象的uncaughtException()方法来处理该异常。线程组处理异常的默认流程如下：

1. 如果该线程组有父线程组，则调用父线程组的uncaughtException()方法来处理该异常。
2. 如果该线程实例所属的线程类有默认的异常处理器(由setDefaultUncaughtExceptionHandler()方法设置的异常处理器），那么就调用该异常处理器来处理该异常。
3. 如果该异常对象是ThreadDeath的对象，则不做任何处理；否则，将异常跟踪栈的信息打印到System.err错误输出流，并结束该线程。

案例如下：

```java
public class ExHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " 线程持出现异常：" + e);
    }
    public static void main(String[] args){
        Thread.currentThread().setUncaughtExceptionHandler(new ExHandler());
        int a = 5/0;
        System.out.println("the end");
    }
}
```

运行结果：

```java
Thread[main,5,main] 线程持出现异常：java.lang.ArithmeticException: / by zero
  
Process finished with exit code 1
```

从上面程序的执行结果来看，虽然程序中指定了异常处理器对未捕获的异常进行处理，而且该异常处理器也确实起作用了，但程序依然不会正常结束。这说明异常处理器与通过catch捕获异常是不同的：

**当使用catch捕获异常时，异常不会向上传播给上一级调用者；**

**但使用异常处理器对异常进行处理之后，异常依然会传播给上一级调用者。**