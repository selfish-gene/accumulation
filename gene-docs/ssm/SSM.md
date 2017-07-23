# SSM搭建流程

[TOC]



## 创建Maven Web项目，版本为3.1

maven创建的默认web版本为2.3，可以先创建一个3.1版本的java web项目，将3.1版本的web.xml复制使用，如下：

![java_web_1](D:\git\accumulation\gene-docs\java_web_1.png)

![java_web_2](D:\git\accumulation\gene-docs\java_web_2.png)



## 用Mybatis创建数据连接

### 添加Maven依赖

```xml
<!-- mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.1</version>
</dependency>

<!-- sql connector-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.38</version>
</dependency>
```

### mybatis的xml文件配置

**路径为resources/config.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties>
        <property name="driver" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/test?useUnicode=true&amp;useSSL=true" />
        <property name="username" value="root" />
        <property name="password" value="93130226" />
    </properties>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <!-- 配置数据库连接信息 -->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}" />
                <property name="url" value="${url}" />
                <property name="username" value="${username}" />
                <property name="password" value="${password}" />
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper class="com.selfish.gene.web.dao.UserDao"/>
        <mapper resource="com/selfish/gene/web/entity/userMapper.xml" />
    </mappers>
</configuration>
```

### 其他的源文件

**com\selfish\gene\web\entity\User.java**

```java
package com.selfish.gene.web.entity;
public class User {
   private int id;
   private String username;
   private String password;
   /*省略getter、setter*/
}
```

**com\selfish\gene\web\dao\UserDao.java**

```java
package com.selfish.gene.web.dao;
import com.selfish.gene.web.entity.User;
import org.apache.ibatis.annotations.Param;
public interface UserDao {
   UserById(@Param("id") int id);
}
```

**com\selfish\gene\web\entity\userMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 命名空间 -->
<mapper namespace="com.selfish.gene.web.dao.UserDao">
    <select id="selectUserById" resultType="com.selfish.gene.web.entity.User">
        select
        u.id as id,
        u.username as username,
        u.password as password
        from user u where u.id=#{id}
    </select>
</mapper>
```

**sql语句**

```sql
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DELETE FROM `user`;
INSERT INTO `user` (`id`, `username`, `password`) VALUES
	(1, 'selfish', 'abc@123'),
	(2, 'gene', 'abc@123');
```

测试类及运行结果：

```java
package com.selfish.gene.web;

import com.selfish.gene.web.dao.UserDao;
import com.selfish.gene.web.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class TestMybatis {

    public static void main(String[] args) throws Exception {
        String resource = "config.xml";
        InputStream inputStream = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.selectUserById(1);
        System.out.println(user.toString());
    }
}

```

```java
User{id=1, username='selfish', password='abc@123'}
```

## 搭建Spring环境

添加Maven依赖，这里所使用的**spring版本为4.2.5**

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
<!-- spring-aop依赖 -->
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.8.6</version>
</dependency>
```

添加spring.xml，路径为src\main\resources\spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                     http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                     http://www.springframework.org/schema/context
                     http://www.springframework.org/schema/context/spring-context-3.0.xsd
                     http://www.springframework.org/schema/aop
                     http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
                     http://www.springframework.org/schema/tx
                     http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
                     http://www.springframework.org/schema/mvc
                     http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">

    <context:annotation-config />
    <context:component-scan base-package="com.selfish.gene.web" />

</beans>
```

测试Spring环境，在**src\test\resources**下添加springTest.xml，内容同上。添加测试类，如下：

src\test\java\com\selfish\gene\web\SpringTest.java

```java
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void testSpring(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springTest.xml");
        ServiceTest testService = applicationContext.getBean(ServiceTest.class);
        testService.test();
    }
}
```

src\test\java\com\selfish\gene\web\ServiceTest.java

```java
package com.selfish.gene.web;

import org.springframework.stereotype.Component;

@Component
public class ServiceTest {

    public void test(){
        System.out.println("Just for test !");
    }
}
```

运行结果：

```java
// 自动注入成功，说明spring环境搭建正常
Just for test !
Process finished with exit code 0
```

## Spring集成Mybatis

添加maven依赖

```xml
<!-- mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.3.1</version>
</dependency>
<!-- dbcp数据源 -->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2.1</version>
</dependency>
<dependency>
    <groupId>commons-pool</groupId>
    <artifactId>commons-pool</artifactId>
    <version>1.6</version>
</dependency>
```

在spring.xml文件当中集成mybatis，如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans     xmlns="http://www.springframework.org/schema/beans"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xmlns:tx="http://www.springframework.org/schema/tx"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
                              http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                              http://www.springframework.org/schema/context
                              http://www.springframework.org/schema/context/spring-context-3.0.xsd
                              http://www.springframework.org/schema/aop
                              http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
                              http://www.springframework.org/schema/tx
                              http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
                              http://www.springframework.org/schema/mvc
                              http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd ">

    <context:annotation-config/>
    <context:component-scan base-package="com.selfish.gene.web"/>
    <!-- 面向切面注解声明 -->
    <aop:aspectj-autoproxy/>
    <!-- 开启代理模式,默认false表示使用JDK代理，如果为true将使用CGLIB代理 -->
    <aop:config proxy-target-class="true"/>
    <!-- （0）引入配置文件 -->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:jdbc.properties"></property>
    </bean>
    <!-- （1）数据库配置,获取数据源 -->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${driver}" />
        <property name="url" value="${url}" />
        <property name="username" value="${username}" />
        <property name="password" value="${password}" />
    </bean>
    <!-- （2）会话工厂及扫描接口 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 自动扫映射配置目录, 省掉mybatis.xml里的手工配置 -->
        <property name="mapperLocations" value="classpath:com/selfish/gene/web/entity/*.xml" />
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.selfish.gene.web.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
    </bean>
    <!-- （3）配置事务管理器  -->
    <bean id="jdbcTxManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
    <!-- （4）配置事务传播特性  -->
    <tx:advice id="txAdvice" transaction-manager="jdbcTxManager">
        <tx:attributes>
            <tx:method name="domain*" propagation="REQUIRED" isolation="DEFAULT" />
        </tx:attributes>
    </tx:advice>
    <!-- （5）配置事务切入点  -->
    <aop:config>
        <aop:pointcut id="allServiceMethod" expression="execution(* com.selfish.gene.web.service.*.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="allServiceMethod" />
    </aop:config>
    <!-- 注解方式配置事务  -->
    <tx:annotation-driven transaction-manager="jdbcTxManager"/>
</beans>
```

配置jdbc.properties文件，路径为：src\main\resources\jdbc.properties

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false
username=root
password=93130226
```

测试集成效果：

测试类路径：src\main\java\com\selfish\gene\web\SpringMybatisTest.java

```java
package com.selfish.gene.web;

import com.selfish.gene.web.dao.UserDao;
import com.selfish.gene.web.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMybatisTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = userDao.selectUserById(1);
        System.out.println(user);
    }
}
```

运行结果：

```java
// 测试成功
User{id=1, username='selfish', password='abc@123'}
Process finished with exit code 0
```

提前准备service层，如下：

src\main\java\com\selfish\gene\web\service\UserService.java

```java
package com.selfish.gene.web.service;

import com.selfish.gene.web.entity.User;

public interface UserService {
  
    User getUserById(int id);
}
```

\src\main\java\com\selfish\gene\web\service\impl\UserServiceImpl.java

```java
package com.selfish.gene.web.service.impl;

import com.selfish.gene.web.dao.UserDao;
import com.selfish.gene.web.entity.User;
import com.selfish.gene.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.selectUserById(id);
    }
}
```

SpringMybatisTest.java修改如下：

```java
package com.selfish.gene.web;

import com.selfish.gene.web.entity.User;
import com.selfish.gene.web.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMybatisTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}

```

运行结果：

```java
/*依然正常*/
User{id=1, username='selfish', password='abc@123'}
Process finished with exit code 0
```

## 搭建Spring-mvc环境

前面已经添加了spring-webmvc依赖，直接配置spring-mvc.xml文件和web.xml文件，如下：

src\main\resources\spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                  http://www.springframework.org/schema/context
                  http://www.springframework.org/schema/context/spring-context-3.0.xsd
                  http://www.springframework.org/schema/aop
                  http://www.springframework.org/schema/aop/spring-aop.xsd
                  http://www.springframework.org/schema/tx
                  http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
                  http://www.springframework.org/schema/mvc
                  http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">
    <!-- 声明注解使用 -->
    <mvc:annotation-driven/>
    <!--  扫描基包 -->
    <context:component-scan base-package="com.selfish.gene.web.controller" />
    <!-- 配置渲染器  -->
    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
        <!-- 结果视图的前缀 -->
        <!-- <property name="prefix" value="/WEB-INF/jsp/"/> -->
        <!-- 结果视图的后缀 -->
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

src\main\webapp\WEB-INF\web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:jsp="http://java.sun.com/xml/ns/javaee/jsp"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <display-name>Archetype Created Web Application</display-name>
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```

src\main\webapp\index.jsp

```jsp
<html>
<body>
<h2>Hello World! selfish-gene</h2>
</body>
</html>
```

src\main\java\com\selfish\gene\web\controller\UserController.java

```java
package com.selfish.gene.web.controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class UserController {
    @RequestMapping("test")
    public String getUserById(){
        return "index";
    }
}
```

启动tomcat，如下：

![index](D:\git\accumulation\gene-docs\index.png)

## Spring-mvc环境与Spring、Mybatis对接

将spring.xml文件整合进web.xml即可，如下：

src\main\webapp\WEB-INF\web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:jsp="http://java.sun.com/xml/ns/javaee/jsp"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <display-name>Archetype Created Web Application</display-name>

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring.xml</param-value>
    </context-param>
    <!-- spring监听器，加载spring.xml文件 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- 防止spring内存溢出监听器 -->
    <listener>
        <listener-class>org.springframework.web.util.IntrospectorCleanupListener</listener-class>
    </listener>

    <servlet>
        <servlet-name>spring-mvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring-mvc</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```

测试代码，src\main\java\com\selfish\gene\web\controller\UserController.java

```java
package com.selfish.gene.web.controller;

import com.selfish.gene.web.entity.User;
import com.selfish.gene.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;
  
    @RequestMapping("welcome.do")
    public String getUserById(ModelMap map){//利用ModelMap将数据设置在页面中显示
        User u = userService.getUserById(1);
        map.put("user", u);
        return "welcome";
    }
}
```

jsp页面需要添加一下依赖：

```xml
<!-- javax -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.2-b02</version>
</dependency>
<!-- JSTL -->
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

src\main\webapp\welcome.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        h2 {
            background-image: -webkit-gradient(linear, left top, right top, color-stop(0, #f22),
            color-stop(0.15, #f2f), color-stop(0.3, #22f), color-stop(0.45, #2ff),
            color-stop(0.6, #2f2), color-stop(0.75, #2f2), color-stop(0.9, #ff2),
            color-stop(1, #f22));
            color: transparent;
            -webkit-background-clip: text;
            font-size: 5em;
        }
    </style>
</head>
<body>
<div style="text-align: center"><h2>Welcome,${user.username} !!!</h2></div>
</body>
</html>
```

启动tomcat效果如下，访问地址 [http://localhost:8080/welcome.do](http://localhost:8080/welcome.do) ，效果如下：

![welcome](D:\git\accumulation\gene-docs\welcome.png)

SSM整合完成。