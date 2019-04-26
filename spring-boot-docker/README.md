# Spring Boot 项目添加 Docker 支持

在 pom.xml-properties 中添加 Docker 镜像名称
```
<properties>
	<docker.image.prefix>springboot</docker.image.prefix>
</properties>
```
plugins 中添加 Docker 构建插件：
```
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
		<!-- Docker maven plugin -->
		<plugin>
			<groupId>com.spotify</groupId>
			<artifactId>docker-maven-plugin</artifactId>
			<version>1.0.0</version>
			<configuration>
				<imageName>${docker.image.prefix}/${project.artifactId}</imageName>
				<dockerDirectory>src/main/docker</dockerDirectory>
				<resources>
					<resource>
						<targetPath>/</targetPath>
						<directory>${project.build.directory}</directory>
						<include>${project.build.finalName}.jar</include>
					</resource>
				</resources>
			</configuration>
		</plugin>
		<!-- Docker maven plugin -->
	</plugins>
</build>
```
在目录src/main/docker下创建 Dockerfile 文件，Dockerfile 文件用来说明如何来构建镜像
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD spring-boot-docker-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
这个 Dockerfile 文件很简单，构建 Jdk 基础环境，添加 Spring Boot Jar 到镜像中，简单解释一下:

FROM ，表示使用 Jdk8 环境 为基础镜像，如果镜像不是本地的会从 DockerHub 进行下载
VOLUME ，VOLUME 指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录
ADD ，拷贝文件并且重命名
ENTRYPOINT ，为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT
这样 Spring Boot 项目添加 Docker 依赖就完成了。

## 构建打包环境
```
yum install docker
```
安装完成后，使用下面的命令来启动 docker 服务，并将其设置为开机启动：
```
service docker start
chkconfig docker on

#LCTT 译注：此处采用了旧式的 sysv 语法，如采用CentOS 7中支持的新式 systemd 语法，如下：
systemctl  start docker.service
systemctl  enable docker.service
```
使用Docker 中国加速器
```
vi  /etc/docker/daemon.json

#添加后：
{
    "registry-mirrors": ["https://registry.docker-cn.com"],
    "live-restore": true
}
```
重新启动docker
```
systemctl restart docker
```
输入docker version 返回版本信息则安装正常。
## 安装JDK
```
yum -y install java-1.8.0-openjdk*
```
配置环境变量 打开 vim /etc/profile 添加一下内容
```
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.161-0.b14.el7_4.x86_64 
export PATH=$PATH:$JAVA_HOME/bin 
```
修改完成之后，使其生效
```
source /etc/profile
```
输入java -version 返回版本信息则安装正常。
## 安装MAVEN
下载：http://mirrors.shu.edu.cn/apache/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz
```
## 解压
tar vxf apache-maven-3.5.2-bin.tar.gz
## 移动
mv apache-maven-3.5.2 /usr/local/maven3
```
修改环境变量， 在/etc/profile中添加以下几行
```
MAVEN_HOME=/usr/local/maven3
export MAVEN_HOME
export PATH=${PATH}:${MAVEN_HOME}/bin
```
输入mvn -version 返回版本信息则安装正常。

## 使用 Docker 部署 Spring Boot 项目
将项目 spring-boot-docker 拷贝服务器中，进入项目路径下进行打包测试。
```
#打包
mvn package
#启动
java -jar target/spring-boot-docker-1.0.jar
```
看到 Spring Boot 的启动日志后表明环境配置没有问题，接下来我们使用 DockerFile 构建镜像
```
mvn package docker:build
```
看日志builder成功后，使用docker images命令查看构建好的镜像
springboot/spring-boot-docker 就是我们构建好的镜像，下一步就是运行该镜像
````
docker run -p 8080:8080 -t springboot/spring-boot-docker
````
启动完成之后我们使用docker ps查看正在运行的镜像

可以看到我们构建的容器正在在运行，访问浏览器：http://192.168.0.x:8080/,返回:Hello Docker

 Docker 部署 Spring Boot 项目成功！

























