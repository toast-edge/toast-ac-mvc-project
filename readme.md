# 手写实现MVC框架-核心原理实现

## 01 - WEB历史回顾

​	WEB开发模式一：所有的请求都交由给JSP页面，使得HTML,CSS，JAVAScript与JAVA代码混乱，维护繁琐。

​	WEB开发模式二：引入Servlet程序，将请求直接发送给Servlet，Servlet是Java纯后端代码块。在代码分层处理，形成Servlet层，Service, Dao层，每一层有自己所有的任务。在各个层级上编写相关业务很合理 。

​		这种请求方式形成一种架构思想：MVC

​		【M】: 模型层：提供各个独立于平台的Java业务类，工具类等等。可以理解为业务中心所处的层级。

​		【V】：显示层：负责内容数据的展示

​		【C】：控制层：由于接收请求并进行相关的处理，响应模型层所提供的数据



## 02 - MVC的设计问题

​	由于Servlet对于请求地址处理方式是通过继承手段实现。导致一个请求对应一个类。这样每个业务都会有四个CRUD类基本操作类维护。在真实项目当中需要维护大量的控制层类。

​	请求参数和VO对象之间转换处理往往都需要手工处理。



## 03 - MVC多业务设计(DispatchServlet)

​	由于Servlet对于请求地址处理方式是通过继承手段实现。导致一个请求对应一个类。这样每个业务都会有四个CRUD类基本操作类维护。在真实项目当中需要维护大量的控制层类。

​	通过维护一个DispatcherServlet 请求分发的处理机制。根据请求地址进行映射至相应的Action处理类进行业务处理。本次练习使用Action为层次项目的控制层。



## 04 - MVC多业务具体实现

​	Action代替的是传统的控制层，也就是所有用户的请求一定会先交给Action处理(所有的Action控制层通过DispatcherServlet分发找到)；实现这个机制需要满足以下需要：

> 实现分发处理需求：
>
> 1. 请求地址与方法之间的绑定映射；使用@RequestMapping注解来实现。
>
>    提供一个用于存储控制层方法和请求进行映射绑定的结构类 CntrollerRequestMapping
>
> 2. 分发机制需要知道Action类为控制层，所以使用@Controller注解标记来实现。
>
> 3. 采用MVC架构思想进行WEB项目的开发，进行项目分层处理时各层都提供一个标记。
>
>    控制层 -->   @Controller
>
>    业务层-->    @Service
>
>    数据层-->    @Repository



## 05 - 解析@Controller注解

​		通过包扫描得到所有标记@Controller注解的配置类；包扫描的多路径通过“;”进行分组；先进行磁盘路径扫描的处理工作，将磁盘路径的class文件，截取获取到全限定类名称，之后将 '/' 替换为 ‘.’ 号。

​		获取到Class类名称，通过反射获取这个类上的所有Annotation注解，直到有@Controller注解时，进行处理类的@RequestMapping 注解类指定的父路径。类的路径处理只要记录一下父路径。

​       获取到父路径之后, 具体处理这个类的所有方法所对应的请求地址；对方法与请求地址进行绑定映射。具体实现就是获取这个类的所有Method类。判断处理method是否包含@RequestMapping注解，有的话，获取路径，并和父路径进行拼接形成完整的路径。【详情见ConfigAnnotationParseUtil】



## 06 - 包扫描实现

​		包扫描实现通过Tomcat的web.xml配置文件中的<context-param>进行设置包扫描路径，这样可以直接通过Tomcat的Context组件直接获取定义的路径。从Context组件在Tomcat中每一个WEB项目只有一个所以，通过它进行配置。不提供@ScannerPackage 等这样的注解类进行设置路径；配置规则，包路径，多个路径用 ';'分隔；

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <context-param>
        <param-name>base-package</param-name>
        <param-value>com.toast.action</param-value>
    </context-param>
</web-app>
```

具体包扫描工具类【ScannerPackageUtil】实现思想

提供存储用于绑定方法与请求的绑定绑定映射类；

```java
private static final Map<String, ControllerRequestMapping> ACTION_MAP = new HashMap<>();
```

```java
public class ControllerRequestMapping {
    private Class<?> actionClass; // 保存匹配的Action类的信息
    private Method actionMethod; // 保存映射的访问

    public ControllerRequestMapping(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
```

2. 提供 scannerHandler（Class<?> clazz, String package）和 listDirClass();

3. 谁来调用这个包扫描工具的扫描处理方法；项目里面唯一的DispatcherServlet; 请求处理分发中，一开始就该将配置的路径获取到；

4. ```java
   public class DispatcherServlet extends HttpServlet {
       @Override
       public void init() throws ServletException {    // 初始化的时候进行扫描配置
           // 获取ServletContext所配置的初始化上下文参数
           String basePackage = // 获取初始化参数
               super.getServletContext().getInitParameter("base-package"); 
           ScannerPackageUtil.scannerHandle(super.getClass(), basePackage); // 扫描处理
       } // 省略其他代码
   }
   ```



## 07 - 控制层分层处理

​		包扫描提供好了之后，所有的请求被DispatcherServlet拦截；再由包扫描进行处理；但是如果请求是静态资源的话，就需要进行实现分发机制的实现。如果是静态资源则不走DispatcherServlet类。所以DispatcherServlet只拦截  *.action  请求地址的后缀名称。和Spring-MVC会有点不同，毕竟重在思想。



## 08 - ModelAndView

​		当前的实现没有设计的Action程序返回值的设计，实际开发当中会通过Action也就是控制层进行返回。返回内容一般三种：

​		直接返回字符串；

​		直接返回对象；比如：**要通过控制器跳转到JSP页面；ModelAndView就是为前后端为分离而准备的。model是数据，view是要跳转的视图**。

​		返回json, xml

​        void: 无数据



## 09 - 引入依赖注入

​		建立@Service,@Repository注解类，其获取方式和@Controller一致，都是通过ScannerPackageUtil进行包扫描。之后将获取的到类型存储到 按名称查询的容器和按类型查找的容器

```java
    // 保存整个项目之中全部根据名称匹配的Class实例
    private static final Map<String, Class> BY_NAME_MAP = new HashMap<>();
    // 保存整个项目之中全局根据类型匹配的Class实例（根据类型实现自动注入管理）
    private static final Map<Class, Class> BY_TYPE_MAP = new HashMap<>();
```



## 10 - 参数接收方案

​		java的反射机制当中并没有提供获取方法参数名称的接口。所以。需要借助额外的组件。

​		比如     可以使用spring-core的方法；JD的工具反编译的工具。

​		第三方反编译工具：javassist 很繁琐；使用spring-core;

## IOC&DI, MVC实现

依赖自动注入实现

包扫描

【ScannerPackage - 确定基础路径】

​		路径的获取，通过获取 Class对象中getResource()方法获取的路径作为基础路径。对于指定包扫描的路径作为子路径，遍历出所有的Class文件路径，并将Class文件路径转换为 全限定类名。

【SacnnerPackage- 根据全限定类名获取Class对象】		

​		获取全限定类名之后，则可以获取相应的Class对象获取该对象依赖中所有的Annotation 注解。根据各个注解进行一一处理。

【ConfigAnnotationParseUtil - 注解配置解析工具类并注入Bean】

​		通过Class对象的全限定类名，提供一个注解配置解析工具类根据全限定类名类解析类上所有的Annotataion注解配置类型。

​		如果是@Controller 控制层；则获取注解的所有@RequestMapping 注解，并根据RequestMapping所指定的地址与相应的Method对象进行映射绑定

```java
this.controllerMapResult.put(path, new ControllerRequestMapping(this.clazz, method));
```



​		如果是@Service 服务层，如果指定了名称则使用指定了的命令，如果没有指定名称则使用StringUilt工具类生成一个“以类名首字母小写”为例。注入到容器当中，这种是以名称类型进行注入的，并且还提供根据类型来进行注入的，将它所有的接口也注入到（类型容器）容器当中

​		

【DependObject - Autowired 依赖注入实现】

	1. 获取该的Class对象实例，并获取全部的成员属性变量
		1. 