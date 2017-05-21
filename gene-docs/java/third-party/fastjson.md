Fastjson是一个Java库，可用于将Java对象转换为其JSON表示。 它也可以用于将JSON字符串转换为等效的Java对象。 

# fastjson基础篇

[TOC]

## 导入jar包

maven项目pom.xml中添加：

```xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>fastjson</artifactId>
   <version>1.2.17</version>
</dependency>
```

## 入门案例

### Map的序列化与反序列化

序列化代码如下：

```java
Map<String, Object> map = new HashMap<>();
map.put("key1", 1);
map.put("key2", 2);
String mapJson = JSON.toJSONString(map, SerializerFeature.WriteClassName);
```

SerializerFeature.WriteClassName表示序列化时输出类型信息。

输出结果：`{"@type":"java.util.HashMap","key1":1,"key2":2}`

反序列化有如下两种方式：

- ```java
  Map<String, Object> jsonMap = JSON.parseObject(mapJson);
  ```

  输出结果：`{"key1":1,"key2":2}`

- ```java
  Map<String, Object> jsonMap = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>(){});
  ```

  输出结果：`{key1=1, key2=2}`

### List的序列化与反序列化

**案例1：**

序列化如下：

```java
List<String> list = new ArrayList<>(16);
list.add("a");
list.add("b");
String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
```

输出结果：`['a','b']`

反序列化有如下两种方式：

- ```java
  List<String> jsonList = (List<String>) JSON.parse(listJson);
  ```

  输出结果：`["a","b"]`

- ```java
  List<String> jsonList_bak = JSON.parseArray(listJson, String.class);
  ```

  输出结果：`[a, b]`

**案例2：**

序列化如下：

```java
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
```

输出结果：`[{"a":"1","b":"2"},{"c":"3","d":"4"}]`

反序列化有如下三种方式：

- ```java
  List<Map<String, Object>> jsonListMap = (List<Map<String, Object>>) JSON.parse(listMapJson);
  ```

  输出结果：`[{"a":"1","b":"2"},{"c":"3","d":"4"}]`

- ```java
  List<Map> jsonListMap_bak = JSON.parseArray(listMapJson, Map.class);
  ```

  输出结果：`[{a=1, b=2}, {c=3, d=4}]`

- ```java
  List<Map<String, Object>> jsonListMap_bak2 = JSON.parseObject(listMapJson, new TypeReference<List<Map<String, Object>>>(){});
  ```

  输出结果：`[{a=1, b=2}, {c=3, d=4}]`

### JavaBean的序列化与反序列化

序列化如下：

```java
User user = new User("Edam", 20);
// 此处必须声明SerializerFeature.WriteClassName，序列化类型信息
String userJson = JSON.toJSONString(user, SerializerFeature.WriteClassName);
```

输出结果：`{"@type":"com.selfish.gene.modles.User","age":20,"name":"Edam"}`

反序列化如下：

```java
User jsonUser = (User) JSON.parse(userJson);
```

输出结果：`User{name='Edam', age=20}`

### JSONArray与JSONObject

- JSONArray与JSONObject是JSON的两个子类。
- JSONArray实现了List<Object>
- JSONObject实现了Map<String, Object>

JSONArray:

```java
//此处的泛型必须是Object
List<Object> l = new ArrayList<>();
l.add(1);
l.add("1");
JSONArray jsonArray = new JSONArray(l);
String jsonArrayToJson = JSON.toJSONString(jsonArray);
System.out.println(jsonArrayToJson);
```

JSONObject:

```java
//此处的泛型必须是String, Object
Map<String, Object> m = new HashMap<>();
m.put("key1", 1);
m.put("key2", 2);
JSONObject jsonObject = new JSONObject(m);
jsonObject.put("key3", 3);
String jToJson = JSON.toJSONString(jsonObject);
System.out.println(jToJson);
```

### 格式化日期

```java
String dateJson_1 = JSON.toJSONString(new Date());
```

输出结果：`1488323555154`

```java
String dateJson_2 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
```

输出结果：`"2017-03-01 07:12:35"`

```java
String dateJson_3 = JSON.toJSONStringWithDateFormat(new Date(),  "yyyy.MM.dd HH:mm:ss.SSS");
```

输出结果：`"2017.03.01 07:12:35.155"`

## 特性支持SerializerFeature

- WriteClassName

  ```java
  Map<String, Object> map = new HashMap<>();
  map.put("key1", 1);
  map.put("key2", 2);
  String mapJson = JSON.toJSONString(map, SerializerFeature.WriteClassName);
  ```

  输出结果：`{"@type":"java.util.HashMap","key1":1,"key2":2}`

- WriteDateUseDateFormat

  ```java
  String dateJson_2 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
  ```

  输出结果：`"2017-03-01 07:12:35"`

- WriteMapNullValue

  ```java
  Map<String, Object> map = new HashMap<>();
  map.put("key1", 1);
  map.put("key2", 2);
  map.put("key3", null);
  String mapJson = JSON.toJSONString(map, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue);
  ```

  输出结果：`{"@type":"java.util.HashMap","key1":1,"key2":2,"key3":null}`

- UseSingleQuotes

  ```java
  List<String> list = new ArrayList<>(16);
  list.add("a");
  list.add("b");
  String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
  ```

  输出结果：`['a','b']`

更多请参考：[alibaba/fastjson](https://github.com/alibaba/fastjson)
