package com.selfish.gene.reflect.DynaProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/3/16.
 */
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
