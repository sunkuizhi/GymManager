# 基础镜像：OpenJDK 11 精简版
FROM openjdk:11-jre-slim

# 设置时区（可选，解决日志时间问题）
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 工作目录
WORKDIR /app

# 复制打包好的 JAR 文件（注意文件名通配符）
COPY target/GymManager-*.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动应用，支持通过环境变量覆盖配置文件参数
ENTRYPOINT ["java", "-jar", "app.jar"]