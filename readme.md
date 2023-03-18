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
>    提供一个用于存储方法和请求进行映射绑定的类；
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