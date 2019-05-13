#  Springboot初始化加载资源神器 ---- CommandLineRunner
CommandLineRunner 接口的 Component 会在所有 Spring Beans 都初始化之后，SpringApplication.run() 之前执行，非常适合在应用程序
启动之初进行一些数据初始化的工作。

添加 @Order 注解的实现类最先执行，并且@Order()里面的值越小启动越早。