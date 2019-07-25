架构:_______________________________________________________________
    |                ____________                                  |
    |               |            |<---配置中心:8769 <- git          |<->数据库
    |               |            |    config-server                |   mysql
    |               |用户服务:8762|--->注册中心:8761                 |<->消息队列
    |               |user-service|    eureka-server                |   rabbitmq
    |网关服务:5000 ->|授权服务:9999|->日志服务:9997                   |  _________
    |gateway-service|uaa-service |  log-service                    |  |       |
    |               |博客服务:8763|--->链路中心:9411                 |<-|commons|
    |               |blog-service|    zipkin-server(jar)           |  |_______|
    |               |____________|--->熔断仪表:8766 -> 聚合监控:9998 |   数据sql
    |                                 monitor-service admin-service|   配置config
    |______________________________________________________________|   日志logs
====================================================================================
技术栈:
    eureka-server
        Eureka              服务注册发现中心        http://localhost:8761/
    config-server
        Spring cloud config 分布式服务配置中心      http://localhost:8769/{app}/{pro}/[lab]   http://localhost:8769/[lab]/{app-pro}.yml
    uaa-service
        Feign               声明式服务调用（Ribbon负载均衡）
        Spring cloud oauth2 安全解决方案
        mybatis plus        持久化
    user-service|blog-service                    ******|*****
        Feign               声明式服务调用（Ribbon负载均衡）
        actuator            监控
        Hystrix             熔断器                http://localhost:8762|8763/hystrix
        Hystrix dashboard   熔断器仪表盘
        swagger             接口文档管理
        restful api         接口风格
        mybatis plus        持久化
        rabbitmq            消息服务器
    monitor-service
        Turbine             聚合熔断器仪表盘        http://localhost:8766/turbine.stream
    zipkin-server(jar)
        zipkin              用于服务链路追踪        http://localhost:9411/
    admin-service
        Spring boot admin   监控微服务             http://localhost:9998/
        Turbine             聚合熔断器仪表盘
    gateway-service                               ******
        zuul                服务网关（Ribbon负载均衡）
    log-service                                   ******
        mybatis plus        持久化
        rabbitmq            消息服务器
===============================================================================