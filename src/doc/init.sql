-- 用户表
drop table t_bw_user;
create table t_bw_user
(
  user_id          varchar(32) null,
  user_name        varchar(32) null,
  password         varchar(32) null,
  phone            varchar(20) null,
  email            varchar(50) null,
  sex              int null,
  signature        varchar(500),
  create_time      date null,
  last_modify_time date null,
  create_by        varchar(32) null,
  last_modify_by   varchar(32) null
);


-- 用户角色表
create table t_bw_user_role
(
  user_role_id     varchar(32) not null
    primary key,
  user_id          varchar(32) null,
  role_id          varchar(32) null,
  create_time      date null,
  last_modify_time date null,
  create_by        varchar(32) null,
  last_modify_by   varchar(32) null
);


-- 角色表
create table t_bw_role
(
  role_id          varchar(32) not null
    primary key,
  role_name        varchar(32) null,
  role_desc        varchar(32) null,
  create_time      date null,
  last_modify_time date null,
  create_by        varchar(32) null,
  last_modify_by   varchar(32) null
);
