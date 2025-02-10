微服务项目——Z次元

个人主页：https://ahzoo.cn

博客地址：https://blog.ahzoo.cn

Github：（十玖八柒）https://github.com/ooahz

## 项目预览

### 博客

![](https://s.ahzoo.cn/img/24/open/ob100101.webp)
![](https://s.ahzoo.cn/img/24/open/ob10005.webp)
![](https://s.ahzoo.cn/img/24/open/ob10008.webp)
![](https://s.ahzoo.cn/img/24/open/ob10013.webp)
![](https://s.ahzoo.cn/img/24/open/ob100102.webp)

### 后台管理

![](https://s.ahzoo.cn/img/24/open/ob100021.webp)

## 项目技术栈

前端主要技术栈：
Vue3、Nuxt3、Vite、Pinia、TypeScript、Tailwind CSS、SCSS

后端主要技术栈：
JDK21、SpringCloud Alibaba 2023.0.0 全家桶、SpringBoot 3+、Mybatis Plus、Mysql、Redis、ElasticSearch 8+

## 项目运行必要工具

运行项目前，请确认是否已启动下面的工具：
Redis 7+、MySQL 8+、Elasticsearch 8+、Nacos 2.3.0

> ES客户端记得以非SSL模式启动，并关闭密码验证，否则请自行配置search服务的SSL及密码的相关配置

## 服务启动

本项目是微服务项目，所以若你只想运行单个服务也是可以的。
如果未启用完整的功能，需在前端关闭对应的接口调用。
如果要启用完整功能，建议按照下方的推荐的服务进行启动：

- 博客项目完整功能建议启动gateway服务、search服务、comment服务、blog服务
- 后台管理项目完整功能建议启动gateway服务、search服务、blog-admin服务

> 默认的配置未开启将文章储存在es的功能（全文索引），如果需要开启，请将config.elasticsearch配置为true

### 可选服务

gateway服务、comment服务以及search可选是否启用。
关闭search服务时需要将config.elasticsearch配置为false，同时关闭前端对SearchController接口中的调用。
关闭comment服务，也需要在前端关闭对CommentController接口的调用。

### 打包

使用maven打包命令
```shell
mvn clean package
```

### 部署运行

将打包好后jar包上传到服务器，然后使用 `java -jar` 命令启动即可

## 数据库文件

数据库文件在[project-db](https://github.com/ooahz/project-db)仓库中，根据当前项目对应的版本选择数据库文件

数据库文件默认为空，如果需要登录后台管理，需要先在数据库中新增账号数据。

数据示例：

在`sys_user表`新增账号

账号：`admin@ahzoo.cn`
密码：`a123456`

| id | email | password | salt | status |
| :--:| :----: | :------: | :---: | :--: |
| 1001 | admin@ahzoo.cn | 08780460b94d39592c9eb1b1cbc01a89654a756bded0d05000972361394c782f | 5c7a7321841702a50b834e | 1 |

在`sys_role`表中新增角色：

| id |user_id | role_code |
| :--:| :----: | :---: |
| 1 | 1001 | admin |


### 版本参考

| 项目            | 版本    |
|---------------|-------|
| MySQL         | 8.0.31 |
| Redis         | 7.2.4 |
| Elasticsearch | 8.12.2 |
| Nacos         | 2.3.0  |

## 赞助支持

本项目免费开源，因此没有任何收益。若您觉得本项目对您有帮助，您的赞助支持将是我最大的动力。

|                       微信                       |                      支付宝                       |
| :----------------------------------------------: | :-----------------------------------------------: |
| <img src="https://s.ahzoo.cn/mine/wechat.png" width="200" /> | <img src="https://s.ahzoo.cn/mine/alipay.png" width="200" /> |
