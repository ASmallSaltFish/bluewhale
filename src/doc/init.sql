-- 角色表
create table t_bw_role
(
  role_id varchar(32) not null
    primary key,
  role_name varchar(32) null,
  role_desc varchar(32) null,
  create_time datetime null,
  last_modify_time datetime null,
  create_by varchar(32) null,
  last_modify_by varchar(32) null,
  role_code varchar(50) null,
  constraint t_bw_role_role_code_uindex
    unique (role_code)
);

-- 用户表
create table t_bw_user
(
  user_id varchar(32) not null
    primary key,
  user_name varchar(32) null,
  password varchar(32) null,
  phone varchar(20) null,
  email varchar(50) null,
  signature varchar(500) null,
  position varchar(200) null,
  sex varchar(1) null,
  user_status varchar(1) null,
  create_time datetime null,
  last_modify_time datetime null,
  create_by varchar(32) null,
  last_modify_by varchar(32) null
);

-- 用户角色表
create table t_bw_user_role
(
  user_role_id varchar(32) not null
    primary key,
  user_id varchar(32) null,
  role_id varchar(32) null,
  create_time datetime null,
  last_modify_time datetime null,
  create_by varchar(32) null,
  last_modify_by varchar(32) null
);

-- 文章表
create table t_bw_article
(
  article_id       varchar(32),
  title            varchar(32),
  author           varchar(50),
  status           varchar(1),
  personal_flag    varchar(1),
  content          text,
  preview_content  text,
  create_time      datetime,
  last_modify_time datetime,
  create_by        varchar(32),
  last_modify_by   varchar(32)
);


INSERT INTO bw.t_bw_role (role_id, role_name, role_desc, create_time, last_modify_time, create_by, last_modify_by, role_code) VALUES ('1069918047802437634', '管理员', '测试', '2018-12-08 00:00:00', '2018-12-08 00:00:00', null, null, 'admin');
INSERT INTO bw.t_bw_role (role_id, role_name, role_desc, create_time, last_modify_time, create_by, last_modify_by, role_code) VALUES ('1071279311002771458', '用户', '测试', '2018-12-08 00:00:00', '2018-12-08 00:00:00', null, null, 'user');