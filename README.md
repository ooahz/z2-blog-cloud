微服务项目——Z次元

个人主页：https://ahzoo.cn

Github：（十玖八柒）https://github.com/ooahz


## 项目技术栈

前端主要技术栈：
Vue3、Nuxt3、Vite、Pinia、TypeScript、Tailwind CSS、SCSS

后端主要技术栈：
JDK21、SpringCloud Alibaba 2023.0.0 全家桶、SpringBoot 3+、Mybatis Plus、Mysql、Redis、ElasticSearch 8+

## 项目运行必要工具

运行项目前，请确认是否已启动下面的工具：
Redis 7+、MySQL 8+、Elasticsearch 8+、Nacos 2.3.0


## 启动部署

### 启动客户端

分别启动Redis 7+、Elasticsearch 8+、Nacos 2.3.0客户端

### 参数补充

将项目下的 `application-pub.yml` 文件配置，根据提示，按照实际情况补充完整

### 启动项目

博客项目需要先启动gateway模块，然后启动search模块，最后启动blog模块

后台管理项目需要先启动gateway模块，然后启动search模块，然后启动admin模块


### 打包

使用maven打包命令
```shell
mvn clean package
```

### 部署运行

将打包好后jar包上传到服务器，然后使用 `java -jar` 命令启动即可

## 数据库文件

数据库文件在[project-db](https://github.com/ooahz/project-db)仓库中，根据当前项目对应的版本选择数据库文件
