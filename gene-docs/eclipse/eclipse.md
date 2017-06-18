# Eclipse

[TOC]

## 快捷键

### 单键

F3	打开声明

### 双键

alt /				自动补全代码

alt up/down		上下移动该行代码

alt left/right		最近的打开的2个文件切换

ctrl D			删除行

ctrl E			导航栏最近打开的文件

ctrl F			查找替换

ctrl H			全局查找

ctrl L			跳转至指定行

ctrl M			当前窗口最大化最小化

ctrl O			打开outline

ctrl W			关闭当前文件

### 三键

alt shift s			source

ctrl 2 L			定义匿名变量

ctrl alt up/down	上下复制行

ctrl shift G		查找选中方法的引用

ctrl shift O		导入包选择

ctrl shift R		open resource

ctrl shift T		open file	

### debug快捷键

F5				into method

F6				next line

F7				step out

F8				next breakpoint 如果没有断点击结束debug

## 常用设置

### 设置代码提示

Windows >> Preferences >> Java >> Editor >> Content Assist >> Auto activation triggers for Java

添加abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ

### 设置server

Windows >> Preferences >> Server >> Runtime Environments

### 设置编码格式

Windows >> Preferences >> General >> Workspace >> Text file encoding

### 设置用户库

Windows >> Preferences >> Java >> User Libraries

### 更换主题

Windows >> Preferences >> General >>  Appearance>>Theme

我的以Dark为主：

![set_theme](D:\git\accumulation\gene-docs\eclipse\pictures\set_theme.png)

### 自定义代码样式

#### 全局的代码样式设置

Windows >> Preferences >> General >> Editors >>Text Editors

最下面的选择框中有几项（不一一介绍）：

Current line highlight	当前行的显示颜色

background color		设置代码背景颜色

![code_style](D:\git\accumulation\gene-docs\eclipse\pictures\code_style.png)

#### 指定语言的代码样式（以Java为例）

Windows >> Preferences  >> Java >> Editor >> Syntax Coloring 

选择框中有3个选项，我以本地的配置为例说明（颜色配置均以RGB为准）:

**Java:**

Abstract classes					抽象类名				187	181	41	

Abstract method invocations			调用抽象方法			255	198	109

Annotations elements references		引用注解元素			235	75	100

Annotations						注解					187	181	41

Brackets							符号					169	183	198

Classes							类名					169	183	198				

Deprecated members				弃用成员				171	31	54

Enums							枚举名				169	183	198			

Fields							属性名				152	118	170

Inherited method invocations			调用继承方法			205	246	104

Interfaces						接口名				169	183	198				

Keyword 'return'					return				204	120	50

Keyword excluding 'return'			除了return的关键字		204	120	50

Local variable declarations			局部变量定义			169	183	198				

Local variables					局部变量				169	183	198	

Method declarations				方法定义				255	198	109

Methods							方法					169	183	198	

Numbers							数字					104	151	187

Operators						运算操作符			169	183	198	

Parameter variables				参数定义				121	171	255

Static fields						静态属性				152	118	170

Static final fields					static final 属性		152	118	170		

Static method invocations			静态方法调用			169	183	198	

Strings							字符串				106	135	89

Type arguments					泛型类型参数			80	120	116

Type variable						泛型变量				80	120	116

**Javadoc:**说明文档

HTML markup						HTML元素			119	183	103

Links							链接					119	183	103

others							主要包含注释文档		98	151	85

Tags							标签					119	183	103

**Comments:**注释

Multi-line comment					多行注释				119	183	103			

Single-ling comment				单行注释				169	183	198	

Task Tags						任务标签				168	192	35

![Java_color](D:\git\accumulation\gene-docs\eclipse\pictures\Java_color.png)

#### 导入导出代码样式

以导出为例（导入与导出相似）

![export_style](D:\git\accumulation\gene-docs\eclipse\pictures\export_style.png)

### 设置代码模板

Windows >> Preferences  >> Java >> Editor >> Templates