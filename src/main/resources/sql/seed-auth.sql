USE `ty_cloud`;

INSERT INTO `ty_sys_campus` (
    `campus_code`,
    `campus_name`,
    `status`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    'DEFAULT',
    '默认校区',
    1,
    0,
    0,
    0
WHERE NOT EXISTS (
    SELECT 1
    FROM `ty_sys_campus`
    WHERE `campus_code` = 'DEFAULT'
);

INSERT INTO `ty_sys_role` (
    `campus_id`,
    `role_code`,
    `role_name`,
    `status`,
    `remark`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    c.`id`,
    'ADMIN',
    '系统管理员',
    1,
    '初始化管理员角色',
    0,
    0,
    0
FROM `ty_sys_campus` c
WHERE c.`campus_code` = 'DEFAULT'
    AND NOT EXISTS (
        SELECT 1
        FROM `ty_sys_role`
        WHERE `role_code` = 'ADMIN'
    );

INSERT INTO `ty_sys_user` (
    `campus_id`,
    `username`,
    `password_hash`,
    `real_name`,
    `status`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    c.`id`,
    'admin',
    '$2a$10$KwoyKkhwIlnLnUnTYqzwvuluRajKzgd1vr2mWTexD.gJckfllj4qm',
    '系统管理员',
    1,
    0,
    0,
    0
FROM `ty_sys_campus` c
WHERE c.`campus_code` = 'DEFAULT'
    AND NOT EXISTS (
        SELECT 1
        FROM `ty_sys_user`
        WHERE `username` = 'admin'
    );

INSERT INTO `ty_sys_user_role` (
    `campus_id`,
    `user_id`,
    `role_id`,
    `is_deleted`,
    `created_by`,
    `updated_by`
)
SELECT
    u.`campus_id`,
    u.`id`,
    r.`id`,
    0,
    0,
    0
FROM `ty_sys_user` u
JOIN `ty_sys_role` r ON r.`role_code` = 'ADMIN'
WHERE u.`username` = 'admin'
    AND NOT EXISTS (
        SELECT 1
        FROM `ty_sys_user_role` ur
        WHERE ur.`user_id` = u.`id`
          AND ur.`role_id` = r.`id`
    );
