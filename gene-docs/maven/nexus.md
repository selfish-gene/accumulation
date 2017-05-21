# nexus私服搭建

[TOC]

## download

下载地址：https://www.sonatype.com/download-oss-sonatype

下载版本：nexus-2.14.3-02-bundle.zip

## operator

### 安装与启动nexus服务

解压之后目录如下：

![directory](D:\git\typora\maven\pictures\directory.png)

进入bin\jsw\windows-x86-64目录，如下（OS以windows64为例，其他请在bin\jsw目录下选择）：

![operator](D:\git\typora\maven\pictures\operator.png)

点击install-nexus.bat即可（以下是已经安装成功的截图）：

![install](D:\git\typora\maven\pictures\install.png)

启动点击start-nexus.bat，启动成功之后对话框自动关闭:

![start](D:\git\typora\maven\pictures\start.png)

### 访问

本地访问地址：http://localhost:8081/nexus，管理账号密码：**admin/admin123**

![access](D:\git\typora\maven\pictures\access.png)

## nexus仓库简介

![repo](D:\git\typora\maven\pictures\repo.png)

### 仓库类型

- group(仓库组)：一组仓库的集合
- hosted(宿主)：配置第三方仓库 （包括公司内部私服 ） 
- proxy(代理)：私服会对中央仓库进行代理，用户连接私服，私服自动去中央仓库下载jar包或者插件 
- virtual(虚拟)：兼容Maven1 版本的jar或者插件

### 基本仓库介绍

- 3rd party: 一个策略为Release的宿主类型仓库，用来部署无法从公共仓库获得的第三方发布版本构建

- Apache Snapshots: 一个策略为Snapshot的代理仓库，用来代理Apache Maven仓库的快照版本构建

- Central: 代理Maven中央仓库

- Central M1 shadow: 代理Maven1 版本 中央仓库

- Codehaus Snapshots: 一个策略为Snapshot的代理仓库，用来代理Codehaus Maven仓库的快照版本构件

- Releases: 一个策略为Release的宿主类型仓库，用来部署组织内部的发布版本构件

- Snapshots: 一个策略为Snapshot的宿主类型仓库，用来部署组织内部的快照版本构件

- **Public Repositories:该仓库组将上述所有策略为Release的仓库聚合并通过一致的地址提供服务，平常也是这个仓库使用最多**

  ![public_repo](D:\git\typora\maven\pictures\public_repo.png)

## maven配置nexus

### 设置公共仓库的依赖未找到时解决方案

![central](D:\git\typora\maven\pictures\central.png)

### 自定义maven的setting.xml文件中添加配置如下：

```xml
  <mirrors>
	 <mirror>
		 <!--此处配置所有的构建均从私有仓库中下载 *代表所有，也可以写central -->
		 <id>nexus</id>
		 <mirrorOf>*</mirrorOf>
		 <url>http://localhost:8081/nexus/content/groups/public/</url>
	 </mirror>
  </mirrors>
```
配置依赖jar包时会按照		**本地>>nexus私服>>central**	的顺序依次查找。