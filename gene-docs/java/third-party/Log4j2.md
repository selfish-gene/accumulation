

**Apache Log4j 2是对Log4j的升级，它对其前身Log4j 1.x提供了显着的改进，并提供了Logback中提供的许多改进，同时修复了Logback架构中的一些固有问题。**

#Log4j2的导入

[TOC]

##普通java项目：
手动添加以下jar包：
log4j-api-2.5.jar  
log4j-core-2.5.jar  

##maven项目：
pom文件中添加以下依赖：
```
<dependencies>  
    <dependency>  
        <groupId>org.apache.logging.log4j</groupId>  
        <artifactId>log4j-api</artifactId>  
        <version>2.5</version>  
    </dependency>  
    <dependency>  
        <groupId>org.apache.logging.log4j</groupId>  
        <artifactId>log4j-core</artifactId>  
        <version>2.5</version>  
    </dependency>  
</dependencies>  
```

#基础案例

##配置文件类型说明
配置文件只能采用.xml, .json或者 .jsn。在默认情况下，系统选择configuration文件的优先级如下：
**注：classpath为src\resources文件夹**

 1. classpath下名为 log4j-test.json 或者log4j-test.jsn的文件
 2. classpath下名为 log4j2-test.xml的文件
 3. classpath下名为 log4j.json 或者log4j.jsn的文件
 4. classpath下名为 log4j2.xml的文件

##配置文件
```
<?xml version="1.0" encoding="UTF-8"?>  
<Configuration status="WARN">  
    <Appenders>  
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
    </Appenders>  
    <Loggers>  
        <Root level="error">  
            <AppenderRef ref="Console" />  
        </Root>  
    </Loggers>  
</Configuration>  
```
配置根节点Configuration有一个status属性（不过这里的status属性是显示og4j2本身加载的运行日志级别），日志级别如下：

- OFF (most specific, no logging)
- FATAL (most specific, little data)
- ERROR
- WARN
- INFO
- DEBUG
- TRACE (least specific, a lot of data)
- ALL (least specific, all data)

`Appenders`标签可以中定义appenders类型、日志输出目的地及输出格式等信息，上述Console表示输出到控制台
`Loggers`中指定日志对象，可以自定义日志对象，上述使用rootLogger，引用是在Appenders中指定的name为Console的Appender，该日志对象获取方式有两种：
```
private static final Logger LOGGER = `LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);`
```
```
private static final Logger LOGGER = LogManager.getLogger();
```

##测试代码

```
public class Log4j2 {

    private static final Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) throws Exception {
        LOGGER.trace("This is trace level");
        LOGGER.debug("This is debug level");
        LOGGER.info("This is info level");
        LOGGER.warn("This is warn level");
        LOGGER.error("This is error level");
        LOGGER.fatal("This is fatal level");
    }
}
```

#自定义logger

修改log4j2.xml文件如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="300">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="console_log" level="trace" additivity="false">
            <AppenderRef ref="Console" />
        </Logger>    
        <Root level="error">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

根节点增加了一个monitorInterval属性，含义是每隔300秒重新读取配置文件，可以不重启应用的情况下修改配置，还是很好用的功能。
自定义Logger中增加了additivity属性，additivity="false"表示在该logger中输出的日志不会再延伸到父层logger。这里如果改为true，则会延伸到Root Logger，遵循Root Logger的配置也输出一次。
获取名为console_log的日志对象：	
```
private static final Logger CONSOLE_LOGGER = LogManager.getLogger("console_log");
```
使用方式与前面一样

#引用多种类型的Appender

修改log4j2.xml文件如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="300">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>

        <File name="fileLog" fileName="D://logs/file.log">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </File>
    </Appenders>
    <Loggers>
        <Logger name="console_log" level="trace" additivity="false">
            <AppenderRef ref="Console" />
        </Logger>

        <Logger name="file_log" level="trace" additivity="false">
            <AppenderRef ref="fileLog" />
        </Logger>

        <Root level="error">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

**注：Appender在其标签名后添加Appender即为它对应的类，如Console对应的类是ConsoleAppender，File对应的是FileAppender。要自定义Appender的话需要实现AbstractAppender类或者其它Appender类（如FileAppender）来自定义更多功能。**
获取名为file_log的日志对象：
```java
private static final Logger FILE_LOGGER = LogManager.getLogger("file_log");
```
使用方式与前面一样

#自定义配置文件的位置

在普通java工程中，通过传入配置文件的位置来定义，如下：

```java
public Logger getRootLogger(String logger4j2Path, String loggerName){
      File file = new File(logger4j2Path);
      if(file == null){
          LOGGER.error("The file with name of log4j2.xml is not found");
          return null;
      }
      if(StringUtils.isEmpty(loggerName)){
         loggerName = ""; 
      }
      Logger logger = null;
      try {
          BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
          final ConfigurationSource source = new ConfigurationSource(bis);
          Configurator.initialize(null, source);
          logger = LogManager.getLogger(loggerName);
      } catch (FileNotFoundException e) {
          LOGGER.error("The file with name of log4j2.xml is not found" + e.getMessage());
          e.printStackTrace();
      } catch (IOException e) {
          LOGGER.error("IO error" + e.getMessage());
          e.printStackTrace();
      }
      return logger;
   }
}
```
如果是web项目，在web.xml中添加:

```xml
<context-param>  
    <param-name>log4jConfiguration</param-name>  
    <param-value>/WEB-INF/conf/log4j2.xml</param-value>  
</context-param>  
  
<listener>  
    <listener-class>org.apache.logging.log4j.web.Log4jServletContextListener</listener-class>  
</listener>  
```




