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
<meta charset="UTF-8"/>
<title>Hello</title>
</head>
<body>
<h1 th:text="${message}">Hello World</h1>
</body>
</html>
```
所有使⽤ Thymeleaf 必须在 HTML 标签声明 Thymeleaf：
```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```
表明使⽤的是 Thymeleaf 语法.
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

## for 循环
在后端定义⼀个⽤户列表:
```
private List<User> getUserList(){
    List<User> list=new ArrayList<User>();
    User user1=new User("⼤⽜",12,"123456");
    User user2=new User("⼩⽜",6,"123563");
    User user3=new User("纯洁的微笑",66,"666666");
    list.add(user1);
    list.add(user2);
    list.add(user3);
    return list;
}
```
按照键 users，传递到前端：
```
@RequestMapping("/list")
public String list(ModelMap map) {
    map.addAttribute("users", getUserList());
    return "list";
}
```
页面list.html 进⾏数据展示：
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Example User</title>
</head>
<body>
    <div>
        <h1>for 循环</h1>
        <table>
            <!---->
            <tr th:each="user, iterStat : ${users}">
                <td th:text="${user.name}">neo</td>
                <td th:text="${user.age}">6</td>
                <td th:text="${user.pass}">213</td>
                <td th:text="${iterStat.index}">index</td>

            </tr>
        </table>
    </div>
</body>
</html>
```
iterStat 称作状态变量，属性有：
    **index**，当前迭代对象的 index（从 0 开始计算）；
    **count**，当前迭代对象的 index（从 1 开始计算）；
    **size**，被迭代对象的⼤⼩；
    **current**，当前迭代变量；
    **even/odd**，布尔值，当前循环是否是偶数/奇数（从 0 开始计算）；
    **first**，布尔值，当前循环是否是第⼀个；
    **last**，布尔值，当前循环是否是最后⼀个。
## URL
URL 在 Web 应⽤模板中占据着⼗分重要的地位，需要特别注意的是 Thymeleaf 对于 URL 的处理是通过语
法 @{...} 来处理的。如果需要 Thymeleaf 对 URL 进⾏渲染，那么务必使⽤ th:href、 th:src 等属性
```html
<a th:href="@{http://www.ityouknow.com/{type}(type=${type})}">link1</a>
<a th:href="@{http://www.ityouknow.com/{pageId}/can-use-springcloud.html(pageId=${
pageId})}">view</a>
```
可以使⽤ @{...} 设置背景：
```
<div th:style="'background:url(' + @{${img url}} + ');'">
```
说明：
上例中 URL 最后的 (pageId=${pageId}) 表示将括号内的内容作为 URL 参数处理，该语法避免使
⽤字符串拼接，⼤⼤提⾼了可读性；
@{...} 表达式中可以通过 {pageId} 访问 Context 中的 pageId 变量；
@{/order} 是 Context 相关的相对路径，在渲染时会⾃动添加上当前 Web 应⽤的 Context 名字，假
设 context 名字为 app，那么结果应该是 /app/order 。
完整的页面内容 url.html：
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8"></meta>
<title>Example If/Unless </title>
</head>
<body>
<div >
<h1>URL</h1>
<a th:href="@{http://www.ityouknow.com/{type}(type=${type})}">link1</a>
<br/>
<a th:href="@{http://www.ityouknow.com/{pageId}/can-use-springcloud.html(pageI
d=${pageId})}">view</a>
<br/>
<div th:style="'background:url(' + @{${img}} + ');'">
<br/><br/><br/>
</div>
</div>
</body>
</html>
```
后端程序：
```
@RequestMapping("/url")
public String url(ModelMap map) {
    map.addAttribute("type", "link");
    map.addAttribute("pageId", "springcloud/2017/09/11/");
    map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
    return "url";
}
```
## switch 选择
switch\case 多⽤于多条件判断的场景下，以性别举例：
```html
<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"></meta>
    <title>Example switch </title>
</head>
<body>
<div >
    <div th:switch="${sex}">
        <p th:case="'woman'">她是一个姑娘...</p>
        <p th:case="'man'">这是一个爷们!</p>
        <!-- *: case的默认的选项 -->
        <p th:case="*">未知性别的一个家伙。</p>
    </div>
</div>
</body>
</html>
```
后端程序：
```
@RequestMapping("/switch")
public String switchcase(ModelMap map) {
    map.addAttribute("sex", "woman");
    return "switch";
}
```
# 模板引擎 Thymeleaf ⾼阶⽤法
## 内联 [ [ ] ]
如果不想通过 th 标签⽽是简单地访问 model 对象数据，或是想在 javascript 代码块⾥访问 model 中的数据，则要
使⽤内联的⽅法。

内联⽂本： [ [...] ] 内联⽂本的表示⽅式，使⽤时，必须先⽤在 th:inline="text/javascript/none" 激活， th:inline 可以在⽗级标签内
使⽤，甚⾄可以作为 body 的标签。内联⽂本⽐ th:text 的代码少，不利于原型显示。
页⾯ inline.html（⽂本内联）：
```
<div>
    <h1>内联</h1>
    <div th:inline="text" >
        <p>Hello, [[${userName}]] !</
        <br/>
    </div>
</div>
```
以上代码等价于：
```
<div>
    <h1>不使⽤内联</h1>
    <p th:text="'Hello, ' + ${userName} + ' !'"></p>
    <br/>
</div>
```
通过以上代码可以看出使⽤内联语法会更简洁⼀些。
如果想在脚本中使⽤后端传递的值，则必须使⽤脚本内联，脚本内联可以在 js 中取到后台传过来的参数：
```
<script th:inline="javascript">
    var name = [[${userName}]] + ', Sebastian';
    alert(name);
</script>
```
这段脚本的含义是在访问⻚⾯的时候，根据后端传值拼接 name 值，并以 alert 的⽅式弹框展示.
后端传值:
```
@RequestMapping("/inline")
public String inline(ModelMap map) {
    map.addAttribute("userName", "neo");
    return "inline";
}
```
## 基本对象
Thymeleaf 包含了⼀些基本对象，可以⽤于我们的视图中，这些基本对象使⽤ # 开头。
**#ctx ：上下⽂对象**
**#vars ：上下⽂变量**
**#locale ：区域对象**
**#request ：（仅 Web 环境可⽤） HttpServletRequest 对象** 直接访问与当前请求关联的 javax.servlet.http.HttpServletRequest 对象
**#response ：（仅 Web 环境可⽤） HttpServletResponse 对象**
**#session ：（仅 Web 环境可⽤） HttpSession 对象** 直接访问与当前请求关联的 javax.servlet.http.HttpSession 对象
**#servletContext ：（仅 Web 环境可⽤） ServletContext 对象**

后台添加⽅法传值：
```
@RequestMapping("/object")
public String object(HttpServletRequest request) {
    request.setAttribute("request","i am request");
    request.getSession().setAttribute("session","i am session");
    return "object";
}
```
使⽤ request 和 session 分别传递了⼀个值，再来查看页面 object.html。
```
<body>
    <div >
        <h1>基本对象</h1>
        <p th:text="${#request.getAttribute('request')}">
        <br/>
        <p th:text="${session.session}"></p>
        Established locale country: <span th:text="${#locale.country}">CN</span>.
    </div>
</body>
```
第⼀个展示了 request 如何使⽤参数，第⼆⾏展示了 session 的使⽤， session 直接使⽤ . 即可获取到 session 中的值，最后
展示了 locale 的⽤法。
## 内嵌变量
为了模板更加易⽤， Thymeleaf 还提供了⼀系列 Utility 对象（内置于 Context 中），可以通过 # 直接访问。
dates： java.util.Date 的功能⽅法类
calendars：类似 #dates，⾯向 java.util.Calendar
numbers：格式化数字的功能⽅法类
strings：字符串对象的功能类， contains、 startWiths、 prepending/appending 等
objects：对 objects 的功能类操作
bools：对布尔值求值的功能⽅法
arrays：对数组的功能类⽅法
lists：对 lists 的功能类⽅法
sets： set 的实⽤⽅法
maps： map 的实⽤⽅法
**1. dates**
可以使⽤ dates 对⽇期格式化，创建当前时间等操作。
```
<!--格式化时间-->
<p th:text="${#dates.format(date, 'yyyy-MM-dd HH:mm:ss')}">neo</p>
<!--创建当前时间 精确到天-->
<p th:text="${#dates.createToday()}">neo</p>
<!--创建当前时间 精确到秒-->
<p th:text="${#dates.createNow()}">neo</p>
```
**2. strings**
strings 内置了⼀些对字符串经常使⽤的函数。
```
<!--判断是否为空-->
<p th:text="${#strings.isEmpty(userName)}">userName</p>
<!--判断 list 是否为空-->
<p th:text="${#strings.listIsEmpty(users)}">userName</p>
<!--输出字符串⻓度-->
<p th:text="${#strings.length(userName)}">userName</p>
<!--拼接字符串-->
<p th:text="${#strings.concat(userName,userName,userName)}"></p>
<!--创建⾃定⻓度的字符串-->
<p th:text="${#strings.randomAlphanumeric(count)}">userName</p>
```
后端传值：
```
@RequestMapping("/utility")
public String utility(ModelMap map) {
    map.addAttribute("userName", "neo");
    map.addAttribute("users", getUserList());
    map.addAttribute("count", 12);
    map.addAttribute("date", new Date());
    return "utility";
}
```
## Thymeleaf 配置
我们可以通过 application.properties ⽂件灵活的配置 Thymeleaf 的各项特性，以下为 Thymeleaf 的配置和默认参数：
```
# THYMELEAF (ThymeleafAutoConfiguration)
#开启模板缓存（默认值： true）
spring.thymeleaf.cache=true
#检查模板是否存在，然后再呈现
spring.thymeleaf.check-template=true
#检查模板位置是否正确（默认值:true）
spring.thymeleaf.check-template-location=true
#Content-Type的值（默认值： text/html）
spring.thymeleaf.content-type=text/html
#开启MVC Thymeleaf视图解析（默认值： true）
spring.thymeleaf.enabled=true
#模板编码
spring.thymeleaf.encoding=UTF-8
#要被排除在解析之外的视图名称列表，⽤逗号分隔
spring.thymeleaf.excluded-view-names=
#要运⽤于模板之上的模板模式。另⻅StandardTemplate-ModeHandlers(默认值： HTML5)
spring.thymeleaf.mode=HTML5
#在构建URL时添加到视图名称前的前缀（默认值： classpath:/templates/）
spring.thymeleaf.prefix=classpath:/templates/
#在构建URL时添加到视图名称后的后缀（默认值： .html）
spring.thymeleaf.suffix=.html
#Thymeleaf 模板解析器在解析器链中的顺序，默认情况下，它排第⼀位，顺序从1开始，只有在定义了额外的 TemplateResolv
er Bean 时才需要设置这个属性。
spring.thymeleaf.template-resolver-order=
#可解析的视图名称列表，⽤逗号分隔
spring.thymeleaf.view-names=
```

