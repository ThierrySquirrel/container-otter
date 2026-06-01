# container-otter

Otter

[中文](./README_zh_CN.md)

Support Function：

- [x] Ensure Call Consistency

# Ensure Call Consistency：

Before Using, Make Sure That The Call Link Is Idempotent  
Simple Explanation: Service A Calls Service B, Service B Calls Service C,   
in the link, B and C execute successfully     
A Execute Exception, Then The Data Is Inconsistent   
The Otter Will Try To Call The Link Again  
Service A Calls Service B, And Service B Calls Service C  
If The Link Call Is Successful, The Data Is Consistent

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>container-otter-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
```  

# Start Using

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
        //Local SQL Execution
        return "HelloWorld";
    }

    @Repair
    public Object helloHttp() {
        httpService.update();
        System.out.println("helloHttp");
        //Local SQL Execution
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

# If HTTP supports

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

