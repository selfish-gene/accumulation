package com.selfish.gene.third.party.commons;

import com.selfish.gene.models.User;
import org.apache.commons.beanutils.*;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/27.
 */
public class BeanutilsDemo {

    @Test
    public void testMethodUtils() throws Exception{
        User user = new User();
        // 通过反射来调用
        Method setName = MethodUtils.getAccessibleMethod(User.class, "setName", String.class);
        setName.invoke(user, "anlei");
        // 直接调用
        MethodUtils.invokeMethod(user, "setAge", 26);
        System.out.println(user);
    }

    @Test
    public void testConstructorUtils() throws Exception{
        // 注意此处Class中参数须为int.class，内部没有做自动拆箱操作
        Constructor<User> constructor = ConstructorUtils.getAccessibleConstructor(User.class, new Class[]{String.class, int.class});
        User user = constructor.newInstance("Edam", 26);
        System.out.println(user);
        User user1 = ConstructorUtils.invokeConstructor(User.class, new Object[]{"Alice", 22});
        System.out.println(user1);
    }

    @Test
    public void testPropertyUtils() throws Exception{
        User user = new User("anlei", 26);
        User copy = new User();
        PropertyUtils.copyProperties(copy, user);
        System.out.println(copy);
        // 将对象转换为Map
        Map<String, Object> describe = PropertyUtils.describe(user);
        System.out.println(describe);
        PropertyUtils.setProperty(user, "name", "allen");
        System.out.println(user);
    }

    @Test
    public void testBeanUtils() throws Exception{
        User user = new User();
        Map<String, Object> map = new HashedMap();
        map.put("name", "anlei");
        map.put("age", 26);
        // 将属性对应注入，属性不存在则为默认值
        BeanUtils.populate(user, map);
        System.out.println(user);
    }

    @Test
    public void testConvertUtils() throws Exception{
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> aClass, Object o) {
                try {
                    return (T) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) o);
                } catch (ParseException e) {
                    return null;
                }
            }
        }, Date.class);
        System.out.println(ConvertUtils.convert("2016-04-09 12:41:00", Date.class));
    }
}
