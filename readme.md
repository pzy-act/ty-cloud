
•“招生-排课-上课-收费-复盘”这条主线来设计，建议模块如下：

1. 组织与权限：校区、员工、角色、菜单权限、操作日志。
2. 学员管理：学员档案、家长信息、标签、来源渠道、跟进记录。
3. 课程与班级：课程类型（拉坯/手捏/釉下彩等）、班级、课时、价格。
4. 排课与资源：教师排班、教室、拉坯机/窑炉时段占用、冲突检查。
5. 报名与合同：报名、协议、优惠、课包、有效期。
6. 收费与订单：收款、退款、分期、发票、对账。
7. 考勤与消课：签到、请假、补课、旷课、课时扣减。
8. 陶艺作品管理：作品建档、烧制批次、釉色记录、取件通知、破损记录。
9. 耗材与库存：泥料、釉料、工具出入库、低库存预警、采购记录。
10. 教师与绩效：授课记录、课时统计、提成规则、工资核算。
11. 通知与互动：上课提醒、续费提醒、群发通知、家长反馈。
12. 数据报表：招生转化、满班率、出勤率、续费率、营收与成本。
    要不要我下一步直接给你一版“数据库表清单（含字段）+ 页面菜单结构”？
## 登录鉴权核心流程（Spring Security + JWT，支持 7 天免登录）

### 1. 登录流程
1. 前端调用 `POST /api/system/auth/login`，传入 `username`、`password`、`rememberMe`。
2. Spring Security 使用 `AuthenticationManager + CustomUserDetailsService` 校验账号密码。
3. 校验成功后签发两类令牌：
   - `accessToken`：短期令牌，用于接口访问。
   - `refreshToken`：用于续签。
4. 当 `rememberMe=true` 时，`refreshToken` 有效期为 7 天（604800 秒），可实现 7 天内免登录。
5. 登录成功后会更新 `ty_sys_user.last_login_time`。

### 2. 鉴权流程
1. 前端请求业务接口时，在请求头中携带：`Authorization: Bearer {accessToken}`。
2. `JwtAuthenticationFilter` 解析并校验 `accessToken`。
3. 校验通过后，将用户信息写入 `SecurityContext`，请求进入业务层。
4. 未登录或令牌无效时，由统一处理器返回 JSON：`未登录或登录已过期`。

### 3. 刷新流程（免登录关键）
1. 当前端发现 `accessToken` 过期时，调用 `POST /api/system/auth/refresh`，提交 `refreshToken`。
2. 后端校验 `refreshToken`（类型、签名、过期时间）。
3. 校验通过后签发新的 `accessToken + refreshToken`。
4. 若旧 `refreshToken` 来自 `rememberMe=true` 登录，则新签发的 `refreshToken` 继续按 7 天策略续签。

### 4. 当前用户与退出
1. `GET /api/system/auth/me`：返回当前登录用户基础信息、角色编码、权限标识。
2. `POST /api/system/auth/logout`：当前为无状态退出（前端清理本地 token 即可）。

### 5. 接口清单
- `POST /api/system/auth/login`
- `POST /api/system/auth/refresh`
- `GET /api/system/auth/me`
- `POST /api/system/auth/logout`

### 6. 配置项
`application.yml` 中新增：
- `ty.security.jwt.secret`
- `ty.security.jwt.access-token-expire-seconds`
- `ty.security.jwt.refresh-token-expire-seconds`
- `ty.security.jwt.remember-me-refresh-token-expire-seconds`

生产环境请通过环境变量 `TY_JWT_SECRET` 覆盖默认密钥。
