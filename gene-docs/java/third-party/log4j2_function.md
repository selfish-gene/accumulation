# log4j2 functions

[TOC]

## RollingRandomAccessFileAppender

Rolling的意思是当满足一定条件后，就重命名原日志文件用于备份，并从新生成一个新的日志文件。例如需求是每天生成一个日志文件，但是如果一天内的日志文件体积已经超过1G，就从新生成，两个条件满足一个即可。这在log4j 1.x原生功能中无法实现，在log4j2中就很简单了。

配置如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--这里的status属性是log4j2加载时详细信息的level级别，与自定义的不同-->
<Configuration status="warn" monitorInterval="300">
    <properties>
        <property name="LOG_HOME">D://logs</property>
        <property name="FILE_NAME">roll_log</property>
    </properties>
    <Appenders>
        <RollingRandomAccessFile name="roll_random" fileName="${LOG_HOME}/${FILE_NAME}.log" filePattern="${LOG_HOME}/$${date:yyyy-MM}/${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i.log">
            <PatternLayout pattern="%highlight{%d [%t] %-5level: %msg%n%throwable}{FATAL=Bright red, ERROR=red, WARN=Bright Yellow, INFO=Green, DEBUG=Cyan, TRACE=Black}" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" />
                <SizeBasedTriggeringPolicy size="10 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20" />
        </RollingRandomAccessFile>
    </Appenders>
    <Loggers>
        <Logger name="roll_log" level="trace" additivity="false">
            <AppenderRef ref="roll_random" />
        </Logger>

        <Root level="error">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

<properties>定义了两个常量方便后面复用

RollingRandomAccessFile的属性：

- **fileName**  指定当前日志文件的位置和文件名称


- **filePattern**  指定当发生Rolling时，文件的转移和重命名规则


- **SizeBasedTriggeringPolicy**  指定当文件体积大于size指定的值时，触发Rolling
- **DefaultRolloverStrategy**  指定最多保存的文件个数
- **TimeBasedTriggeringPolicy**  这个配置需要和filePattern结合使用，注意filePattern中配置的文件重命名规则是${FILE_NAME}-%d{yyyy-MM-dd HH-mm}-%i，最小的时间粒度是mm，即分钟，TimeBasedTriggeringPolicy指定的size是1，结合起来就是每1分钟生成一个新文件。如果改成%d{yyyy-MM-dd HH}，最小粒度为小时，则每一个小时生成一个文件。

修改测试代码，模拟文件体积超过10M和时间超过1分钟来验证结果

```java
public static void main(String[] args) {  
    Logger logger = LogManager.getLogger("mylog");  
    for(int i = 0; i < 50000; i++) {  
        logger.trace("trace level");  
        logger.debug("debug level");  
        logger.info("info level");  
        logger.warn("warn level");  
        logger.error("error level");  
        logger.fatal("fatal level");  
    }  
    try {  
        Thread.sleep(1000 * 61);  
    } catch (InterruptedException e) {}  
    logger.trace("trace level");  
    logger.debug("debug level");  
    logger.info("info level");  
    logger.warn("warn level");  
    logger.error("error level");  
    logger.fatal("fatal level");  
}  
```

## 按日志级别区分文件输出

需求是把INFO及以下级别的信息输出到info.log，WARN和ERROR级别的信息输出到error.log，FATAL级别输出到fatal.log，配置文件如下：

```xml
<Configuration status="WARN" monitorInterval="300">  
    <properties>  
        <property name="LOG_HOME">D://logs</property>  
    </properties>  
    <Appenders> 
        <Console name="Console" target="SYSTEM_OUT">  
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />  
        </Console>  
  
        <RollingRandomAccessFile name="InfoFile"  
            fileName="${LOG_HOME}/info.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="warn" onMatch="DENY" onMismatch="NEUTRAL" />  
                <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
          
        <RollingRandomAccessFile name="ErrorFile"  
            fileName="${LOG_HOME}/error.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="fatal" onMatch="DENY" onMismatch="NEUTRAL" />  
                <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
          
        <RollingRandomAccessFile name="FatalFile"  
            fileName="${LOG_HOME}/fatal.log"  
            filePattern="${LOG_HOME}/$${date:yyyy-MM}/fatal-%d{yyyy-MM-dd}-%i.log">  
            <Filters>  
                <ThresholdFilter level="fatal" onMatch="ACCEPT" onMismatch="DENY" />  
            </Filters>  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
            <Policies>  
                <TimeBasedTriggeringPolicy />  
                <SizeBasedTriggeringPolicy size="10 MB" />  
            </Policies>  
            <DefaultRolloverStrategy max="20" />  
        </RollingRandomAccessFile>  
    </Appenders>  
  
    <Loggers>  
        <Root level="trace">  
            <AppenderRef ref="Console" />  
            <AppenderRef ref="InfoFile" />  
            <AppenderRef ref="ErrorFile" />  
            <AppenderRef ref="FatalFile" />  
        </Root>  
    </Loggers>  
</Configuration>  
```

测试代码：

```java
public static void main(String[] args) {  
    Logger logger = LogManager.getLogger(Client.class);  
    logger.trace("trace level");  
    logger.debug("debug level");  
    logger.info("info level");  
    logger.warn("warn level");  
    logger.error("error level");  
    logger.fatal("fatal level");  
}  
```





