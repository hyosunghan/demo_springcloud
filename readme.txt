架构:_____________________________________________________________
    |                ____________                                |
    |               |            |<-配置中心:8769 <- git          |<->数据库
    |               |            |  config-server                |   mysql
    |               |用户服务     |->注册中心:8761                 |<->消息队列
    |               |user-service|  eureka-server                |   rabbitmq
    |网关服务:5000 ->|授权服务:9999|->日志服务:9997                 |  _________
    |gateway-service|uaa-service |  log-service                  |  |       |
    |               |博客服务     |->链路中心:9411                 |<-|commons|
    |               |blog-service|  zipkin-server(jar)           |  |_______|
    |               |____________|->熔断仪表:8766 -> 聚合监控:     |   数据sql
    |                               monitor-service admin-service|   配置respo
    |____________________________________________________________|   日志logs
==============================================================================
技术栈:
    Eureka              服务注册和发现
    Spring cloud config 分布式服务配置中心
    Spring cloud oauth2 安全解决方案
    Feign               声明式服务调用
    Ribbon              负载均衡
    Hystrix             熔断器
    Hystrix dashboard   熔断器仪表盘
    Turbine             聚合熔断器仪表盘
    Spring cloud sleuth 集成zipkin用于服务链路追踪
    Spring boot admin   聚合监控微服务
    zuul                服务网关
    spring data jpa     持久化
    swagger             接口文档管理
    restful api         接口风格
    rabbitmq            消息服务器
===============================================================================
13421