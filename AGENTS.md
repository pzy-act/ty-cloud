# Repository Guidelines

## 项目结构与模块组织
本项目为 `Spring Boot 3 + Java 17` 后端，使用 `MyBatis-Plus`、`Lombok`、`Spring Security`。

- 主代码目录：`src/main/java/com/vip/tycloud`
- 配置层：`config`（MyBatis、Security、JWT）
- 控制层：`controller/<module>`
- 业务层：`service/<module>`、`service/<module>/impl`
- 数据层：`repository/<module>`、`repository/<module>/impl`、`mapper/<module>`
- 模型层：`entity/<module>`、`dto/<module>`、公共 DTO 在 `common/dto`
- 资源目录：`src/main/resources`（含 `application.yml`）
- 测试目录：`src/test/java/com/vip/tycloud`

按业务域分包，例如：`system`、`student`、`finance`、`artwork`。

## 构建、测试与本地运行
优先使用 Maven Wrapper：

- `mvnw.cmd spring-boot:run`：本地启动
- `mvnw.cmd test`：运行全部测试
- `mvnw.cmd -DskipTests package`：快速打包
- `mvnw.cmd -DskipTests compile`：仅编译校验

若本机 `mvn` 与 wrapper 版本不一致，以 `mvnw.cmd` 为准。

## 编码规范与命名约定
- 缩进 4 空格，文件编码 UTF-8。
- 类名用 `PascalCase`，方法/字段用 `camelCase`，常量用 `UPPER_SNAKE_CASE`。
- 优先使用 Lombok（如 `@Data`、`@RequiredArgsConstructor`）。
- SQL 仅放在 `mapper/repository` 层，`service/controller` 禁止写 SQL。
- 遵循现有分层架构，避免跨层直接调用。

## 测试规范
- 测试框架：Spring Boot Test（JUnit 5）。
- 测试类命名：`*Tests.java`，位于 `src/test/java`。
- 优先覆盖登录鉴权、仓储查询、控制层参数校验等关键路径。
- Web 接口建议使用 `MockMvc`。

## 提交与 PR 规范
- 提交信息建议：`type(scope): description`（如 `feat(system): 新增登录接口`）。
- 常用类型：`feat`、`fix`、`refactor`、`docs`、`test`。
- PR 至少包含：变更说明、影响模块、测试结果（命令或关键输出）。

## 安全与配置建议
- 禁止硬编码账号、密码、Token、密钥。
- JWT 密钥通过环境变量配置：`TY_JWT_SECRET`。
- 提交前检查 `application.yml`，避免敏感信息入库。
