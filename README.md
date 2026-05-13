# GymManager - 健身房管理系统

GymManager 是一个基于 Spring Boot + MyBatis-Plus + Thymeleaf 的健身房后台管理系统，提供会员管理、课程管理、私教预约、健身计划等功能。

## 技术栈

| 类别 | 技术/框架 | 版本 |
|------|-----------|------|
| 基础框架 | Spring Boot | 2.3.7.RELEASE |
| JDK | Java | 11 |
| ORM | MyBatis-Plus | 3.5.5 |
| 数据库 | MySQL | 8.0.23 |
| 连接池 | Druid | 1.2.24 |
| 模板引擎 | Thymeleaf | - |
| 分页插件 | PageHelper | 1.4.6 |
| API 文档 | Swagger (Springfox) | 3.0.0 |
| JSON 处理 | Fastjson2 | 2.0.53 |
| 工具库 | Lombok | 1.18.34 |

## 环境要求

- JDK 11 或更高版本
- MySQL 8.0 或更高版本
- Maven 3.6+
- IntelliJ IDEA 或 Eclipse

## 快速开始

### 1. 克隆项目

```bash
git clone <你的仓库地址>
cd GymManager
```
### 2. 数据库配置

- 创建 MySQL 数据库：`gymmanager`（字符集使用 utf8mb4）
- 执行项目根目录下的 SQL 脚本创建表结构（如有 `gymmanager.sql` 文件）
- 复制 `src/main/resources/application.yml.example` 并重命名为 `application.yml`
- 修改 `application.yml` 中的数据库连接信息（用户名、密码等）

### 3. 启动后端

```bash
mvn clean install
mvn spring-boot:run
```
后端启动后默认监听 `8080` 端口，上下文路径为 `/`。

### 4. 访问系统

打开浏览器访问：`http://localhost:8080`

默认管理员账号：请查看数据库 `users` 表，或使用 `admin` / `admin`（以实际项目为准）。

## 项目结构

```text
GymManager/
├── src/main/java/com/test/GymManager/
│   ├── config/            # 配置类（Swagger、WebConfig）
│   ├── controller/        # 控制器（Course、Member、Reservation等）
│   ├── entity/            # 实体类（Coach、Course、Member等）
│   ├── mapper/            # MyBatis 数据访问接口
│   ├── service/           # 业务逻辑接口及实现
│   └── GymManagerApplication  # 启动类
├── src/main/resources/
│   ├── mapper/            # MyBatis XML 映射文件
│   ├── static/            # 静态资源（CSS、JS、图片等）
│   ├── templates/         # Thymeleaf 模板（HTML 页面）
│   ├── application.yml    # 主配置文件（本地使用，不提交）
│   ├── application.yml.example  # 配置模板（提交）
│   └── logback.xml        # 日志配置
├── target/                # 编译输出（已忽略）
├── .gitignore
├── pom.xml
└── README.md
```
## 主要功能

- 会员管理（增删改查、会员卡管理）
- 课程管理（团课、私教课程）
- 私教预约与排期
- 健身计划制定
- 教练管理
- 操作日志记录
- 后台页面使用 Thymeleaf 渲染，前后端不分离

## 注意事项

- `application.yml` 包含数据库密码等敏感信息，**请勿提交到 Git 仓库**。
- 上传的图片等静态文件默认保存在 `static/` 目录下，如需持久化请配置外部存储路径。
- 若使用 Swagger 接口文档，启动后访问：`http://localhost:8080/swagger-ui/index.html`
- 日志配置文件为 `logback.xml`，可按需调整日志级别和输出路径。

## 开发调试建议

- 推荐使用 IntelliJ IDEA，直接导入 Maven 项目。
- 使用 `mvn spring-boot:run` 启动，支持热加载（需开启 IDEA 的自动编译）。
- 调试接口可使用 Swagger UI 或 Postman。
- 如果遇到数据库连接错误，请检查 MySQL 服务是否启动以及 `application.yml` 中的连接参数是否正确。

## 许可证

[待补充]