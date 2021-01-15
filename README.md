# mola

#### 介绍
基于Spring Boot的微服务脚手架

#### 软件架构
1. Spring Boot 2.4.1
2. Spring Cloud 2020.0.0


#### 安装教程

1. 运行参数
    ```shell script
    --spring.profiles.active=dev
    ```
2. 打包命令
    ```shell script
    mvn clean package install deploy -DskipTests
    ```
3. 运行命令(可以去掉网络代理配置https.proxy)
    ```shell script
    nohup java -Djava.net.useSystemProxies=true -Dhttps.proxyHost=172.16.0.99 -Dhttps.proxyPort=3128 -Dhttp.proxyHost=172.16.0.99 -Dhttp.proxyPort=3128 -XX:MetaspaceSize=$MetaspaceSize -XX:MaxMetaspaceSize=$MaxMetaspaceSize -Xms$XMS -Xmx$XMX -jar 应用jar包 --spring.profiles.active=prod -Djava.net.useSystemProxies=true >/dev/null 2>&1 &
    ```

#### 使用说明

1. swagger文档地址
    ```shell script
    http://127.0.0.1:8080/swagger-ui/index.html
    ```
2. druid监控后台
    ```shell script
    http://127.0.0.1:8080/druid/index.html
    ```  
3. 应用健康状态
    ```shell script
    http://127.0.0.1:8080/actuator
    ```

#### 特技

* mola-core 核心包（框架、工具）
* mola-common 微服务间共享对象包
* mola-gateway 微服务网关
* mola-center 微服务注册中心
* mola-config 微服务配置中心
* mola-pay 支付
* mola-push 推送
* mola-bi 数据分析
* mola-oa 工作流
* mola-cron 定时调度
* mola-report 报表系统
* mola-risk 风控
* mola-file 文件管理
* mola-ds 多数据源
* mola-media 流媒体
* mola-rush 抢购
* mola-ops 运维相关
* mola-generator 各种生成器（代码生成器、二维码生成器等等）
* mola-book 技术文档

#### 参与人员
1. hatim

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request