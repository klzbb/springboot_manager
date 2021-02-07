-- auto-generated definition
create table sys_dept
(
    id           int auto_increment
        primary key,
    level        varchar(255) null,
    name         varchar(255) null,
    operate_time datetime(6)  null,
    operate_ip   varchar(255) null,
    operator     varchar(255) null,
    parent_id    int          not null,
    remark       varchar(255) null,
    seq          int          not null
);

INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (3, '0', 'IT研发部门', null, null, null, 0, '', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (4, '0.3', '前端开发部门', null, null, null, 3, 'undefined', 3);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (5, '0.3', '后端开发部门', null, null, null, 3, 'undefined', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (6, '0', 'UI研发部门', null, null, null, 0, 'undefined', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (7, '0.6', '移动端UI研发部门', null, null, null, 6, 'undefined', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (8, '0.6', 'pc端UI研发部门', null, null, null, 6, 'undefined', 2);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (9, '0', '后勤管理部门', null, null, null, 0, 'undefined', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (10, '0', '产品营销部门1', '2020-09-30 16:58:20.819000', '127.0.0.1', 'klz', 0, 'undefined', 1);
INSERT INTO java_manager.sys_dept (id, level, name, operate_time, operate_ip, operator, parent_id, remark, seq) VALUES (11, '0.10', '硬件营销部门', null, null, null, 10, 'undefined', 1);




-- auto-generated definition
create table sys_menu
(
    id             int auto_increment
        primary key,
    code           varchar(255) null,
    level          varchar(255) null,
    name           varchar(255) null,
    operate_ip     varchar(255) null,
    operate_time   datetime(6)  null,
    operator       varchar(255) null,
    parent_id      int          null,
    remark         varchar(255) null,
    seq            int          null,
    status         int          null,
    type           int          null,
    url            varchar(255) null,
    component      varchar(255) null,
    icon           varchar(255) null,
    path           varchar(255) null,
    component_name varchar(255) null
);

INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (35, null, '0', '牛人汇', '127.0.0.1', '2021-01-20 13:27:55.553000', 'admin', 0, '', 1, 1, 1, null, '/nrh', 'icon', '/nrh', 'Layout');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (36, null, '0.35', '活动设置', '127.0.0.1', '2021-01-29 10:10:16.335000', 'kong', 35, '', 2, 1, 1, null, '/nrh/activity-setting', 'home', '/nrh/activity-setting', 'undefined');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (43, null, '0', '系统设置', '127.0.0.1', '2021-01-24 22:30:23.931000', 'admin', 0, '', 1, 1, 1, null, '/sys-manager', 'password', '/sys-manager', 'Layout');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (44, null, '0.43', '用户管理', '127.0.0.1', '2021-01-24 22:32:35.209000', 'admin', 43, '用户管理模块', 1, 1, 1, null, '/user/index', 'tab', '/sys-manager/user', 'User');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (45, null, '0.43', '角色管理', '127.0.0.1', '2021-01-24 22:34:53.131000', 'admin', 43, '', 2, 1, 1, null, '/role/index', 'peoples', '/sys-manager/role', 'Role');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (46, null, '0.43', '菜单管理', '127.0.0.1', '2021-01-24 22:35:57.222000', 'admin', 43, '', 3, 1, 1, null, '/menu/index', 'documentation', '/sys-manager/menu', 'Menu');
INSERT INTO java_manager.sys_menu (id, code, level, name, operate_ip, operate_time, operator, parent_id, remark, seq, status, type, url, component, icon, path, component_name) VALUES (47, null, '0.43', '部门管理', '127.0.0.1', '2021-01-24 22:36:41.378000', 'admin', 43, '', 4, 1, 1, null, '/dept/index', 'star', '/sys-manager/dept', 'Dept');





-- auto-generated definition
create table sys_role
(
    id           int auto_increment
        primary key,
    name         varchar(255) null,
    operate_ip   varchar(255) null,
    operate_time datetime(6)  null,
    operator     varchar(255) null,
    remark       varchar(255) null,
    status       int          null,
    type         int          null
);

INSERT INTO java_manager.sys_role (id, name, operate_ip, operate_time, operator, remark, status, type) VALUES (2, '超级管理员', '127.0.0.1', '2021-01-24 22:36:52.722000', 'kong', '', 1, 1);
INSERT INTO java_manager.sys_role (id, name, operate_ip, operate_time, operator, remark, status, type) VALUES (4, '牛人汇管理员', '127.0.0.1', '2021-01-26 11:10:03.496000', 'kong', '', 1, 1);
INSERT INTO java_manager.sys_role (id, name, operate_ip, operate_time, operator, remark, status, type) VALUES (19, '模拟炒股管理员', '127.0.0.1', '2021-01-19 17:09:50.783000', '小白', '', 1, 1);



-- auto-generated definition
create table sys_role_menu
(
    id           int auto_increment
        primary key,
    menu_id      int          null,
    operate_ip   varchar(255) null,
    operate_time datetime(6)  null,
    operator     varchar(255) null,
    role_id      int          null
);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (111, 35, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (112, 36, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (113, 43, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (114, 44, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (115, 45, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (116, 46, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (117, 47, '127.0.0.1', '2021-01-24 22:36:52.731000', 'kong', 2);
INSERT INTO java_manager.sys_role_menu (id, menu_id, operate_ip, operate_time, operator, role_id) VALUES (118, 35, '127.0.0.1', '2021-01-26 11:10:03.845000', 'kong', 4);




-- auto-generated definition
create table sys_user
(
    id           int auto_increment
        primary key,
    dept_id      int          null,
    is_enabled   bit          null,
    mail         varchar(255) null,
    operate_ip   varchar(255) null,
    operate_time datetime(6)  null,
    operator     varchar(255) null,
    password     varchar(255) null,
    remark       varchar(255) null,
    status       int          null,
    telephone    varchar(255) null,
    username     varchar(255) null
);

INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (5, 11, null, '13143361048@qq.com', '127.0.0.1', '2020-09-29 16:40:27.358000', 'klz', '$2a$10$kduXriBQtjCQspUU10352Oar8yffYObzHTktQse9NqyRl42RmFiWe', '2222', 1, '13143361048', 'jiyanzhen');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (7, 5, null, '13622894596@qq.com', '127.0.0.1', '2020-09-30 16:54:02.727000', 'klz', '$2a$10$wTPrArkt9XfLp6tev./kbO3h56yExAdtASwRUpFbqn1YDV/F9q2o.', '', 1, '13622894596', '莫李广');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (8, 8, null, '13622894597@qq.com', '127.0.0.1', '2020-09-30 16:56:39.785000', 'klz', '$2a$10$1ADCaKcFxxd5768A7D6hVOzJU8BH.I5VFcpa4ICOKe67xr0tZtGr2', '', 1, '13622894597', '李四');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (9, 9, null, '13622894598@qq.com', '127.0.0.1', '2020-09-30 16:57:07.068000', 'klz', '$2a$10$JSkep0N46geHq4c2UAxZ0.Wc59j4KjKYt3qAykUJbocTt/zzQBUOS', '', 1, '13622894598', '王无四');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (10, 5, null, '13622894599@qq.com', '127.0.0.1', '2020-10-19 17:47:43.036000', 'klz', '$2a$10$eNjS4dHQ5zRYWrFpQqQ55efDNmmxoHS4EogEVGIJ1VCivjfa/FYOe', '', 1, '13622894599', '孔子');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (11, 8, null, '13143362045@qq.com', '127.0.0.1', '2021-01-20 17:13:56.981000', '小白', '$2a$10$R.KnBTqy//ti7rmu1TJs3OuWhNZCQbqhVx0BM1t.6izEZo9LYr4Y2', '水电费', 1, '13143362045', 'jyz');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (12, 8, null, '13018665492', '127.0.0.1', '2021-01-18 17:57:34.783000', '小白', '$2a$10$8z7nNjmWNEjA8vFY1bq77eP5ajY82YlfMnLcj4InaEE.jrZsx9jyO', '水电费', 1, '13018665492', 'jxl');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (13, 5, null, '13622894593@qq.com', '127.0.0.1', '2021-01-20 17:14:05.550000', '小白', '$2a$10$JotWvgkyJzsm3CAar8bia.6DuIL7JK5dEKkXUV5ePUd5SY4Ndfv5C', '', 1, '13622894593', '小黑');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (15, 8, null, '13622894595@qq.com', '127.0.0.1', '2021-01-21 15:27:09.426000', '小白', '$2a$10$THOa0mPXcVu0JbcQmk4JyOAFmHZyXAi1gVfv5WRX5T8s0t5fqbOIW', '我是超级管理员', 1, '13622894595', 'kong');
INSERT INTO java_manager.sys_user (id, dept_id, is_enabled, mail, operate_ip, operate_time, operator, password, remark, status, telephone, username) VALUES (16, 8, null, '13018556423@qq.com', '127.0.0.1', '2021-01-21 15:22:32.490000', 'kong', '$2a$10$ABHl/LUSEXEcAscAXTWMO.611tk6MNDBP3VT7/p.yZ3izZcWU48x6', '', 1, '13018556423', '小白');






-- auto-generated definition
create table sys_user_role
(
    id           int auto_increment
        primary key,
    operate_ip   varchar(255) null,
    operate_time datetime(6)  null,
    operator     varchar(255) null,
    role_id      int          null,
    user_id      int          null
);

INSERT INTO java_manager.sys_user_role (id, operate_ip, operate_time, operator, role_id, user_id) VALUES (2, '127.0.0.1', '2020-09-09 15:39:30.314000', 'admin', 2, 3);
INSERT INTO java_manager.sys_user_role (id, operate_ip, operate_time, operator, role_id, user_id) VALUES (3, '127.0.0.1', '2020-09-09 15:40:19.749000', 'admin', 2, 3);
INSERT INTO java_manager.sys_user_role (id, operate_ip, operate_time, operator, role_id, user_id) VALUES (4, '127.0.0.1', '2020-09-09 15:40:45.636000', 'admin', 2, 3);
INSERT INTO java_manager.sys_user_role (id, operate_ip, operate_time, operator, role_id, user_id) VALUES (32, null, null, null, 4, 16);
INSERT INTO java_manager.sys_user_role (id, operate_ip, operate_time, operator, role_id, user_id) VALUES (33, null, null, null, 2, 15);