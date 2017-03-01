package com.selfish.gene.third.party.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.selfish.gene.modles.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by Administrator on 2017/2/28.
 */
public class Fastjson {

    private static final Logger LOGGER = LogManager.getLogger("console_log");

    public static void main(String[] args) throws Exception {
        LOGGER.info("Transform Map to json ...");
        // map to json
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", null);
        String mapJson = JSON.toJSONString(map, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue);
        System.out.println(mapJson);
        LOGGER.info("Transform json to map ...");
        // json to map
        Map<String, Object> jsonMap = JSON.parseObject(mapJson);
        Map<String, Object> jsonMap_bak = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>(){});
        System.out.println(jsonMap);
        System.out.println(jsonMap_bak);

        LOGGER.info("Transform list to json ...");
        // list to json
        List<String> list = new ArrayList<>(16);
        list.add("a");
        list.add("b");
        String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
        System.out.println(listJson);
        LOGGER.info("Transform json to list ...");
        // json to list
        List<String> jsonList = (List<String>) JSON.parse(listJson);
        List<String> jsonList_bak = JSON.parseArray(listJson, String.class);
        System.out.println(jsonList);
        System.out.println(jsonList_bak);

        LOGGER.info("Transform List<Map<String, Object>> to json ...");
        // List<Map<String, Object>> to json
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map_1 = new HashMap<>();
        map_1.put("a", "1");
        map_1.put("b", "2");
        Map<String, Object> map_2 = new HashMap<>();
        map_2.put("c", "3");
        map_2.put("d", "4");
        listMap.add(map_1);
        listMap.add(map_2);
        String listMapJson = JSON.toJSONString(listMap);
        System.out.println(listMapJson);
        LOGGER.info("Transform json to List<Map<String, Object>> ...");
        // json to List<Map<String, Object>>
        List<Map<String, Object>> jsonListMap = (List<Map<String, Object>>) JSON.parse(listMapJson);
        List<Map> jsonListMap_bak = JSON.parseArray(listMapJson, Map.class);
        List<Map<String, Object>> jsonListMap_bak2 = JSON.parseObject(listMapJson, new TypeReference<List<Map<String, Object>>>(){});
        System.out.println(jsonListMap);
        System.out.println(jsonListMap_bak);
        System.out.println(jsonListMap_bak2);

        LOGGER.info("Transform JavaBean to json ...");
        // JavaBean to json
        User user = new User("Edam", 20);
        // 此处必须声明SerializerFeature.WriteClassName，序列化类型信息
        String userJson = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(userJson);
        LOGGER.info("Transform json to JavaBean ...");
        // json to JavaBean
        User jsonUser = (User) JSON.parse(userJson);
        System.out.println(jsonUser);

        LOGGER.info("JSONObject demo ...");
        //此处的泛型必须是String, Object
        Map<String, Object> m = new HashMap<>();
        m.put("key1", 1);
        m.put("key2", 2);
        JSONObject jsonObject = new JSONObject(m);
        jsonObject.put("key3", 3);
        String jToJson = JSON.toJSONString(jsonObject);
        System.out.println(jToJson);

        LOGGER.info("JSONArray demo ...");
        //此处的泛型必须是Object
        List<Object> l = new ArrayList<>();
        l.add(1);
        l.add("1");
        JSONArray jsonArray = new JSONArray(l);
        String jsonArrayToJson = JSON.toJSONString(jsonArray);
        System.out.println(jsonArrayToJson);

        LOGGER.info("fastjson format date ...");
        String dateJson_1 = JSON.toJSONString(new Date());
        System.out.println(dateJson_1);
        String dateJson_2 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateJson_2);
        String dateJson_3 = JSON.toJSONStringWithDateFormat(new Date(),  "yyyy.MM.dd HH:mm:ss.SSS");
        System.out.println(dateJson_3);
    }

}
