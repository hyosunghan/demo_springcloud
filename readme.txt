架构:_____________________________________________________________
    |                ____________                                |
    |               |            |<-配置中心:8769 <- git          |<->数据库
    |               |            |  config-server                |   mysql
    |               |用户服务:8762|->注册中心:8761                 |<->消息队列
    |               |user-service|  eureka-server                |   rabbitmq
    |网关服务:5000 ->|授权服务:9999|->链路中心:9411                 |  _________
    |gateway-service|uaa-service |  zipkin-server(jar)           |  |       |
    |               |博客服务:8763|->日志服务:9997                 |<-|commons|
    |               |blog-service|  log-service                  |  |_______|
    |               |____________|->熔断仪表:8766 -> 聚合监控:9998 |   数据sql
    |                               monitor-service admin-service|   配置config
    |____________________________________________________________|   日志logs
==============================================================================
技术栈:
    eureka-server           http://localhost:8761/
        Eureka              服务注册发现中心
    config-server           http://localhost:8769/{app}/{pro}/[lab]   http://localhost:8769/[lab]/{app-pro}.yml
        Spring cloud config 分布式服务配置中心
    uaa-service
        Feign               声明式服务调用（Ribbon负载均衡）
        Spring cloud oauth2 安全解决方案
    user-serviceblog-service
        Feign               声明式服务调用（Ribbon负载均衡）
        actuator            监控
        Hystrix             熔断器               http://localhost:8762/hystrix
        Hystrix dashboard   熔断器仪表盘
    blog-service
        Feign               声明式服务调用（Ribbon负载均衡）
        actuator            监控
        Hystrix             熔断器               http://localhost:8763/hystrix
        Hystrix dashboard   熔断器仪表盘
    monitor-service         http://localhost:8766/turbine.stream
        Turbine             聚合熔断器仪表盘
    zipkin-server(jar)
                            zipkin用于服务链路追踪
    admin-service           http://localhost:9998/
        Spring boot admin   聚合监控微服务
    gateway-service
        zuul                服务网关（Ribbon负载均衡）
    log-service

        spring data jpa     持久化
        swagger             接口文档管理
        restful api         接口风格
        rabbitmq            消息服务器
===============================================================================