# Spring-Cloud-Gateway
```text
一些文章博客:
https://blog.csdn.net/a745233700/article/details/122917167
```
# Spring-Cloud-Gateway配置
在介绍Spring-Cloud-Gateway的配置项的时候，我们先了解一些术语
- 断言(Predicate):参照Java8新特性Predicate,允许开发人员匹配HTTP请求中的任何内容，例如：请求头，或请求参数，最后根据匹配结果返回一个布尔值。
- 路由(Route):由ID,目标URI,断言集合和过滤器集合组成。如果聚合断言为真，则转发到该路由。
- 过滤器(Filter):可以在返回请求或者之后修改请求和相应内容。

## Route
Route主要由：路由id,目标uri,断言集合，过滤器集合等组成。
- id:路由标识，要求唯一(默认UUID)，一般自定义
- uri:请求最终被转发到的目标地址
- order:路由优先级，数字越小优先级越高
- predicates:断言数组，即判断条件，如果断言数组返回为True,则转发该请求。
- filters:过滤器数组，在请求传递过程中，对请求做一些修改。

## Predicate
Predicate来自于Java8的接口，Predicate接收一个输入参数，返回一个布尔值结果。该接口包含多种默认方法将Predicate数组合成组合其他的复杂逻辑(例如：与，或，非)

在Spring-Cloud-Gateway中内置了许多实现的Predicate,这些Predicate在`org.springframework.cloud.gateway.handler.predicate`包中。这些断言实现的Predicate命名都是有规范的,格式:`XxxxxRoutePreficateFactory`

如果路由匹配了两个或以上，则是按照配置先后顺序转发。

下面是一些内置的Predicate:
- datetime(请求发起的时间断言)
  - AfterRoutePredicateFactory:请求时间满足配置时间之后返回true
  - BeforeRoutePredicateFactory:请求时间满足配置时间之前返回True
  - BetweenRoutePredicateFactory:请求时间满足配置时间之间返回True
- Cookie(请求中携带的Cookie断言)
  - CookieRoutePredicateFactory:请求是是否携带满足配置的正则表达式的Cookie，满足返回True
- Header(请求中的Header断言)
  - HeaderRoutePredicateFactory:请求的Header是否满足正则表达式，满足返回True
- Host(请求Host断言)
  - HostRoutePredicateFactory:请求中的Host是否匹配指定的值，匹配返回True
- Method(请求Method断言)
  - MethodRoutePredicateFactory:请求方法是否是配置的方法，匹配返回True
- Path(请求path断言)
  - PathRoutePredicateFactory:请求路径是否满足配置的正则表达式，匹配返回True
- QueryParam(请求query参数断言)
  - QueryRoutePredicateFactory:请求参数正则匹配
- RemoteAddr(请求远程地址断言)
  - RemoteAddrRoutePredicateFactory:请求远程地址是否匹配值，匹配返回True
- Weight(请求权重)
  - WeightRoutePredicateFactory:请求按照权重分组，路由到不同的目标URI

## Filter
在网关(Spring-Cloud-Gateway)中Filter有两种:
1. PRE:该过滤器在请求被路由到目标URI之前调用，常用的场景是：身份验证，记录调试信息等等。
2. POST：该过滤器在请求被上游的微服务所响应后，常用来添加一些标准的响应报文头等。

在网关(Spring-Cloud-Gateway)中Filter的作用范围有两种：
1. 局部路由Filter:该过滤器只被应用到单个路由或者一个分组的路由上。需要在配置文件中配置。
2. 全局路由Filter:应用到所有的路由上。无需配置，全局生效。

### 内置的局部过滤器
- header
  - AddRequestHeaderGatewayFilterFactory:添加请求头
  - RemoveRequestHeaderGatewayFilterFactory:移除请求头
  - AddResponseHeaderGatewayFilterFactory:添加响应头
  - RemoveResponseHeaderGatewayFilterFactory:移除响应头
  - SetResponseHeaderGatewayFilterFactory:设置响应头
  - SecureHeadersGatewayFilterFactory:安全头
  - PreserveHostGatewayFilterFactory:保留原请求头
  - RemoveNonProxyHeadersGatewayFilterFactory:删除重置的请求头
- path
  - RewritePathGatewayFilterFactory:重写请求路径
  - SetPathGatewayFilterFactory:设置路径
  - StripPrefixGatewayFilterFactory:去掉前缀路径
  - PrefixPathGatewayFilterFactory:
- param
  - AddRequestParameterGatewayFilterFactory:添加请求参数
- session
  - SaveSessionGatewayFilterFactory:保存Session
- status
  - SetStatusGatewayFilterFactory:设置响应状态
- retry
  - RetryGatewayFilterFactory:重试
- other
  - HystrixGatewayFilterFactory:
  - RequestRateLimiterGatewayFilterFactory:
  - RedirectToGatewayFilterFactory:
### 自定义过滤器和过滤器工厂

### 内置的全局过滤器

# 集成注册中心nacos
如果不集成注册中心，那么上游服务的服务地址需要写死在我们的配置文件中。一旦上游服务地址变更，那么业务网关的配置也得变更。并且无法实现负载均衡。
1.引入依赖
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```
2.启动注册中心
```java
@EnableDiscoveryClient(autoRegister = true)
```
3.添加配置
```yaml
spring:
  application:
    name: api
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: lb://auth-server  # lb://serviceName  启动负载均衡方式动态转发到目标URI,这里使用了全局过滤器:LoadBalancerClientFilter
          predicates:
            - Path=/api/auth/**
          filters:
            - AddRequestHeader=gatewayAdd,chimera
            - StripPath=2
    nacos:
      discovery:
        server-addr: localhost:8848
        namespace: public
        register-enabled: true
```

# 自定义Gateway全局异常处理器
