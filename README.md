Z次元博客项目——Lite版本

Lite版本为轻量级版本，在保留原本功能的基础上，去除了需要额外部署的中间件，并将模块归一。现在后端只需部署一个项目即可运行项目完整功能。

个人主页：https://ahzoo.cn

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
JDK21、SpringBoot 3+、Mybatis Plus、Mysql

## 服务启动

### 打包

使用maven打包命令

```shell
mvn clean package
```

### 部署运行

将打包好后jar包上传到服务器，然后使用 `java -jar` 命令启动即可

## 数据库文件

数据库文件在[project-db](https://github.com/ooahz/project-db)仓库中，根据当前项目（z2blog-lite)对应的版本选择数据库文件

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

## 赞助支持

本项目免费开源，因此没有任何收益。若您觉得本项目对您有帮助，您的赞助支持将是我最大的动力。

|                              微信                              |                             支付宝                              |
|:------------------------------------------------------------:|:------------------------------------------------------------:|
| <img src="https://s.ahzoo.cn/mine/wechat.png" width="200" /> | <img src="https://s.ahzoo.cn/mine/alipay.png" width="200" /> |
