SET NAMES utf8mb4;

USE `ty_cloud`;

SET @campus_id = COALESCE(
    (SELECT `campus_id` FROM `ty_sys_user` WHERE `username` = 'admin' AND `is_deleted` = 0 LIMIT 1),
    (SELECT `id` FROM `ty_sys_campus` WHERE `is_deleted` = 0 ORDER BY `id` LIMIT 1),
    1
);

SET @admin_role_id = (
    SELECT `id`
    FROM `ty_sys_role`
    WHERE `role_code` = 'ADMIN'
      AND `is_deleted` = 0
    LIMIT 1
);

DROP TEMPORARY TABLE IF EXISTS `tmp_menu_seed`;

CREATE TEMPORARY TABLE `tmp_menu_seed` (
    `parent_key` VARCHAR(64) NOT NULL,
    `parent_route_path` VARCHAR(255) DEFAULT NULL,
    `menu_key` VARCHAR(64) NOT NULL,
    `menu_name` VARCHAR(128) NOT NULL,
    `menu_type` TINYINT NOT NULL,
    `route_path` VARCHAR(255) DEFAULT NULL,
    `component` VARCHAR(255) DEFAULT NULL,
    `perms` VARCHAR(255) DEFAULT NULL,
    `icon` VARCHAR(100) DEFAULT NULL,
    `sort_no` INT NOT NULL,
    `visible` TINYINT NOT NULL,
    PRIMARY KEY (`menu_key`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8mb4;

INSERT INTO `tmp_menu_seed` (
    `parent_key`,
    `parent_route_path`,
    `menu_key`,
    `menu_name`,
    `menu_type`,
    `route_path`,
    `component`,
    `perms`,
    `icon`,
    `sort_no`,
    `visible`
) VALUES
    ('ROOT', NULL, 'system', '系统管理', 1, '/system', 'Layout', NULL, 'setting', 10, 1),
    ('system', '/system', 'system:campus', '校区管理', 2, '/system/campus', 'system/campus/index', 'system:campus:page', NULL, 10, 1),
    ('system', '/system', 'system:user', '用户管理', 2, '/system/user', 'system/user/index', 'system:user:page', NULL, 20, 1),
    ('system', '/system', 'system:role', '角色管理', 2, '/system/role', 'system/role/index', 'system:role:page', NULL, 30, 1),
    ('system', '/system', 'system:menu', '菜单管理', 2, '/system/menu', 'system/menu/index', 'system:menu:page', NULL, 40, 1),

    ('ROOT', NULL, 'student', '学员管理', 1, '/student', 'Layout', NULL, 'team', 20, 1),
    ('student', '/student', 'student:lead', '线索管理', 2, '/student/lead', 'student/lead/index', 'student:lead:page', NULL, 10, 1),
    ('student', '/student', 'student:channel', '渠道管理', 2, '/student/channel', 'student/channel/index', 'student:channel:page', NULL, 20, 1),
    ('student', '/student', 'student:student', '学员管理', 2, '/student/student', 'student/student/index', 'student:student:page', NULL, 30, 1),
    ('student', '/student', 'student:guardian', '家长管理', 2, '/student/guardian', 'student/guardian/index', 'student:guardian:page', NULL, 40, 1),
    ('student', '/student', 'student:tag', '标签管理', 2, '/student/tag', 'student/tag/index', 'student:tag:page', NULL, 50, 1),

    ('ROOT', NULL, 'course', '课程管理', 1, '/course', 'Layout', NULL, 'book', 30, 1),
    ('course', '/course', 'course:course', '课程管理', 2, '/course/course', 'course/course/index', 'course:course:page', NULL, 10, 1),
    ('course', '/course', 'course:course-package', '课程包管理', 2, '/course/course-package', 'course/course-package/index', 'course:course-package:page', NULL, 20, 1),
    ('course', '/course', 'course:class', '班级管理', 2, '/course/class', 'course/class/index', 'course:class:page', NULL, 30, 1),

    ('ROOT', NULL, 'schedule', '排课资源', 1, '/schedule', 'Layout', NULL, 'calendar', 40, 1),
    ('schedule', '/schedule', 'schedule:lesson', '排课管理', 2, '/schedule/lesson', 'schedule/lesson/index', 'schedule:lesson:page', NULL, 10, 1),
    ('schedule', '/schedule', 'schedule:classroom', '教室管理', 2, '/schedule/classroom', 'schedule/classroom/index', 'schedule:classroom:page', NULL, 20, 1),
    ('schedule', '/schedule', 'schedule:equipment', '设备管理', 2, '/schedule/equipment', 'schedule/equipment/index', 'schedule:equipment:page', NULL, 30, 1),
    ('schedule', '/schedule', 'schedule:resource-booking', '资源预订', 2, '/schedule/resource-booking', 'schedule/resource-booking/index', 'schedule:resource-booking:page', NULL, 40, 1),

    ('ROOT', NULL, 'contract', '合同报名', 1, '/contract', 'Layout', NULL, 'file-text', 50, 1),
    ('contract', '/contract', 'contract:contract', '合同管理', 2, '/contract/contract', 'contract/contract/index', 'contract:contract:page', NULL, 10, 1),

    ('ROOT', NULL, 'finance', '财务管理', 1, '/finance', 'Layout', NULL, 'wallet', 60, 1),
    ('finance', '/finance', 'finance:order', '订单管理', 2, '/finance/order', 'finance/order/index', 'finance:order:page', NULL, 10, 1),
    ('finance', '/finance', 'finance:payment', '收款管理', 2, '/finance/payment', 'finance/payment/index', 'finance:payment:page', NULL, 20, 1),
    ('finance', '/finance', 'finance:refund', '退款管理', 2, '/finance/refund', 'finance/refund/index', 'finance:refund:page', NULL, 30, 1),
    ('finance', '/finance', 'finance:invoice', '发票管理', 2, '/finance/invoice', 'finance/invoice/index', 'finance:invoice:page', NULL, 40, 1),

    ('ROOT', NULL, 'attendance', '考勤课消', 1, '/attendance', 'Layout', NULL, 'check-circle', 70, 1),
    ('attendance', '/attendance', 'attendance:attendance', '考勤管理', 2, '/attendance/attendance', 'attendance/attendance/index', 'attendance:attendance:page', NULL, 10, 1),
    ('attendance', '/attendance', 'attendance:leave-request', '请假管理', 2, '/attendance/leave-request', 'attendance/leave-request/index', 'attendance:leave-request:page', NULL, 20, 1),
    ('attendance', '/attendance', 'attendance:makeup-record', '补课记录', 2, '/attendance/makeup-record', 'attendance/makeup-record/index', 'attendance:makeup-record:page', NULL, 30, 1),

    ('ROOT', NULL, 'artwork', '作品管理', 1, '/artwork', 'Layout', NULL, 'appstore', 80, 1),
    ('artwork', '/artwork', 'artwork:artwork', '作品档案', 2, '/artwork/artwork', 'artwork/artwork/index', 'artwork:artwork:page', NULL, 10, 1),
    ('artwork', '/artwork', 'artwork:firing-batch', '烧制批次', 2, '/artwork/firing-batch', 'artwork/firing-batch/index', 'artwork:firing-batch:page', NULL, 20, 1),
    ('artwork', '/artwork', 'artwork:glaze-record', '上釉记录', 2, '/artwork/glaze-record', 'artwork/glaze-record/index', 'artwork:glaze-record:page', NULL, 30, 1),
    ('artwork', '/artwork', 'artwork:pickup-record', '取件记录', 2, '/artwork/pickup-record', 'artwork/pickup-record/index', 'artwork:pickup-record:page', NULL, 40, 1),
    ('artwork', '/artwork', 'artwork:damage-record', '损坏记录', 2, '/artwork/damage-record', 'artwork/damage-record/index', 'artwork:damage-record:page', NULL, 50, 1),

    ('ROOT', NULL, 'inventory', '库存采购', 1, '/inventory', 'Layout', NULL, 'database', 90, 1),
    ('inventory', '/inventory', 'inventory:material', '物料管理', 2, '/inventory/material', 'inventory/material/index', 'inventory:material:page', NULL, 10, 1),
    ('inventory', '/inventory', 'inventory:material-stock', '物料库存', 2, '/inventory/material-stock', 'inventory/material-stock/index', 'inventory:material-stock:page', NULL, 20, 1),
    ('inventory', '/inventory', 'inventory:supplier', '供应商管理', 2, '/inventory/supplier', 'inventory/supplier/index', 'inventory:supplier:page', NULL, 30, 1),
    ('inventory', '/inventory', 'inventory:purchase-order', '采购单管理', 2, '/inventory/purchase-order', 'inventory/purchase-order/index', 'inventory:purchase-order:page', NULL, 40, 1),

    ('ROOT', NULL, 'message', '消息中心', 1, '/message', 'Layout', NULL, 'message', 100, 1),
    ('message', '/message', 'message:task', '消息任务', 2, '/message/task', 'message/task/index', 'message:task:page', NULL, 10, 1),
    ('message', '/message', 'message:template', '消息模板', 2, '/message/template', 'message/template/index', 'message:template:page', NULL, 20, 1),
    ('message', '/message', 'message:feedback', '反馈管理', 2, '/message/feedback', 'message/feedback/index', 'message:feedback:page', NULL, 30, 1),

    ('ROOT', NULL, 'report', '数据报表', 1, '/report', 'Layout', NULL, 'dashboard', 110, 1),
    ('report', '/report', 'report:daily-kpi', '每日经营看板', 2, '/report/daily-kpi', 'report/daily-kpi/index', 'report:daily-kpi:page', NULL, 10, 1),

    ('ROOT', NULL, 'teacher', '教师管理', 1, '/teacher', 'Layout', NULL, 'solution', 120, 1),
    ('teacher', '/teacher', 'teacher:teacher-profile', '教师档案', 2, '/teacher/teacher-profile', 'teacher/teacher-profile/index', 'teacher:teacher-profile:page', NULL, 10, 1),
    ('teacher', '/teacher', 'teacher:commission-rule', '提成规则', 2, '/teacher/commission-rule', 'teacher/commission-rule/index', 'teacher:commission-rule:page', NULL, 20, 1),
    ('teacher', '/teacher', 'teacher:teacher-payroll', '薪资核算', 2, '/teacher/teacher-payroll', 'teacher/teacher-payroll/index', 'teacher:teacher-payroll:page', NULL, 30, 1);

INSERT INTO `ty_sys_menu` (
    `campus_id`,
    `parent_id`,
    `menu_name`,
    `menu_type`,
    `route_path`,
    `component`,
    `perms`,
    `icon`,
    `sort_no`,
    `visible`,
    `status`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    @campus_id,
    0,
    s.`menu_name`,
    s.`menu_type`,
    s.`route_path`,
    s.`component`,
    s.`perms`,
    s.`icon`,
    s.`sort_no`,
    s.`visible`,
    1,
    0,
    0,
    0
FROM `tmp_menu_seed` s
WHERE s.`parent_key` = 'ROOT'
  AND NOT EXISTS (
      SELECT 1
      FROM `ty_sys_menu` m
      WHERE m.`menu_type` = s.`menu_type`
        AND m.`route_path` = s.`route_path`
        AND m.`is_deleted` = 0
  );

INSERT INTO `ty_sys_menu` (
    `campus_id`,
    `parent_id`,
    `menu_name`,
    `menu_type`,
    `route_path`,
    `component`,
    `perms`,
    `icon`,
    `sort_no`,
    `visible`,
    `status`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    @campus_id,
    parent.`id`,
    s.`menu_name`,
    s.`menu_type`,
    s.`route_path`,
    s.`component`,
    s.`perms`,
    s.`icon`,
    s.`sort_no`,
    s.`visible`,
    1,
    0,
    0,
    0
FROM `tmp_menu_seed` s
JOIN `ty_sys_menu` parent
    ON parent.`route_path` = s.`parent_route_path`
   AND parent.`menu_type` = 1
   AND parent.`is_deleted` = 0
WHERE s.`parent_key` <> 'ROOT'
  AND NOT EXISTS (
      SELECT 1
      FROM `ty_sys_menu` m
      WHERE m.`menu_type` = s.`menu_type`
        AND m.`route_path` = s.`route_path`
        AND m.`is_deleted` = 0
  );

INSERT INTO `ty_sys_role_menu` (
    `campus_id`,
    `role_id`,
    `menu_id`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    @campus_id,
    @admin_role_id,
    m.`id`,
    0,
    0,
    0
FROM `ty_sys_menu` m
WHERE @admin_role_id IS NOT NULL
  AND m.`is_deleted` = 0
  AND NOT EXISTS (
      SELECT 1
      FROM `ty_sys_role_menu` rm
      WHERE rm.`role_id` = @admin_role_id
        AND rm.`menu_id` = m.`id`
  );

DROP TEMPORARY TABLE IF EXISTS `tmp_menu_seed`;
