本文件夹作用：
后端开发源代码文件夹，使用springboot框架,进行对数据库的操作和为前端提供数据服务。

各文件夹作用：
.idea:    idea配置
.mvn:    后端依赖文件
src:        后端源代码
       src/main
               java   Java文件夹   \src\main\java\com\evan\wj
                            pojo:存放后端的java类，各个类对应数据库的数据表
                            dao:后端的接口类，用来执行增删改查
                            service:用来存放服务类，定义函数来调用dao层接口
                            controller:容器类，用来响应接口服务
                            result:用来返回响应码
                            error:返回错误信息
                           config:配置信息
                           interceptor:登录拦截

               resources:  资源文件夹
                          static  : 存放前端打包生成的文件
                           application.properties   配置文件

target:   springboot 运行和打包时产生的文件
                  wj-1.0.0.jar : 打包后生成的jar包，输入Java-jar  wj-1.0.0.jar 可以运行
