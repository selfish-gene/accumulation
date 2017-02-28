package com.selfish.gene.log4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.*;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Log4j2 {

    private static final Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private static final Logger CONSOLE_LOGGER = LogManager.getLogger("console_log");
//    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws Exception {

        CONSOLE_LOGGER.trace("This is trace level");
        CONSOLE_LOGGER.debug("This is debug level");
        CONSOLE_LOGGER.info("This is info level");
        CONSOLE_LOGGER.warn("This is warn level");
        CONSOLE_LOGGER.error("This is error level");
        CONSOLE_LOGGER.fatal("This is fatal level");

    }

    /**
     * 自定义Log4j2.xml所在位置
     * @param logger4j2Path Log4j2.xml所在位置
     * @param loggerName 获取的日志对象
     * @return 目标日志对象
     */
    public Logger getLogger(String logger4j2Path, String loggerName){
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
