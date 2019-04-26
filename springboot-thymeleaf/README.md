# 模板引擎 Thymeleaf 基础使⽤

## 模板引擎
模板引擎是为了使⽤户界⾯与业务数据（内容）分离⽽产⽣的，它可以⽣成特定格式的⽂档，⽤于⽹站的模
板引擎就会⽣成⼀个标准的HTML⽂档。

模板引擎的实现⽅式有很多，最简单的是“置换型”模板引擎，这类模板引擎只是将指定模板内容（字符串）
中的特定标记（⼦字符串）替换，便⽣成了最终需要的业务数据（如⽹页）。

“置换型”模板引擎实现简单，但其效率低下，⽆法满⾜⾼负载的应⽤需求（⽐如有海量访问的⽹站），因此
还出现了“解释型”模板引擎和“编译型”模板引擎等。

## Thymeleaf介绍
Thymeleaf是⾯向Web和独⽴环境的现代服务器端Java模板引擎，能够处理 HTML、 XML、 JavaScript、
CSS 甚⾄纯⽂本。

## 快速使用
### 相关配置
```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```
在 application.properties 中添加配置：
```
spring.thymeleaf.cache=false
```
其中， propertiesspring.thymeleaf.cache=false 是关闭 Thymeleaf 的缓存，不然在开发过程中修改⻚⾯不会
⽴刻⽣效需要重启，⽣产可配置为 true。
### 写一个简单的页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8"></meta>
<title>Hello</title>
</head>
<body>
<h1 th:text="${message}">Hello World</h1>
</body>
</html>
```
所有使⽤ Thymeleaf 的⻚⾯必须在 HTML 标签声明 Thymeleaf：
```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```
表明⻚⾯使⽤的是 Thymeleaf 语法.
### 展示层 Controller
```java
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
    map.addAttribute("message", "http://www.ityouknow.com");
    return "hello";
    }
}
```
这样就完成了，是不是很简单。启动项⽬后在浏览器中输⼊⽹址： http://localhost:8080/