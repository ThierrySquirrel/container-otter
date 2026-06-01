# container-otter

水獭

[English](./README.md)

支持功能：

- [x] 确保调用一致性

# 确保调用一致性：

使用之前,请确保调用链路,为幂等性.   
简单讲解:服务A调用服务B,服务B调用服务C,链路中,B与C执行成功,  
A执行异常,这时则出现数据不一致.    
水獭则会尝试重新调用链路,  
服务A调用服务B,服务B调用服务C.    
链路调用成功,则数据保持一致性.

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>container-otter-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
```  

# 开始使用

```java

@ScannerPackage(packageName = "com.hello.world.web.otter")

public class OtterRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        OtterRegistration.otterRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
```

```java

@Otter
public class OtterDemo {

    @Set
    public HttpService httpService;

    @Repair
    public Object helloWorld() {
        System.out.println("OtterDemo HelloWorld");
        //本地SQL执行
        return "HelloWorld";
    }

    @Repair
    public Object helloHttp() {
        httpService.update();
        System.out.println("helloHttp");
        //本地SQL执行
        return "http";
    }
}
```

```java

@Http("/web")
public class Demo {

    @Set
    private OtterDemo otterDemo;

    @Get("/local")
    public String local() {
        otterDemo.helloWorld();
        return "local";
    }

    @Get("/http")
    public String feign() {
        otterDemo.helloHttp();
        return "http";
    }
}
```

# 如果HTTP支持

```java
public class HttpInterceptor {
    public void before(HttpTemplate httpTemplate) {
        Optional<Long> globalId = Optional.ofNullable(GlobalIdUtils.getId());
        globalId.ifPresent(id -> httpTemplate.header(InterceptorConstant.INTERCEPTOR_IDENTIFIER, String.valueOf(id)));
    }

    public void after(HttpTemplate httpTemplate) {
        Optional<String> globalId = Optional.ofNullable(httpTemplate.getHeader(InterceptorConstant.INTERCEPTOR_IDENTIFIER));
        globalId.ifPresent(id -> GlobalIdUtils.setId(Long.valueOf(id)));
        httpTemplate.doFilter();
    }
}
```



