create table t_bw_user
(
  user_id          varchar(32) not null primary key,
  user_name        varchar(32),
  password         varchar(32),
  phone            varchar(20),
  email            varchar(50),
  signature        varchar(500),
  position         varchar(200),
  sex              varchar(1),
  user_status      varchar(1),
  create_time      date,
  last_modify_time date,
  create_by        varchar(32),
  last_modify_by   varchar(32)
);

create table t_bw_role
(
  role_id          varchar(32) not null primary key,
  role_name        varchar(32),
  role_desc        varchar(32),
  create_time      date,
  last_modify_time date,
  create_by        varchar(32),
  last_modify_by   varchar(32)
);

create table t_bw_user_role
(
  user_role_id     varchar(32) not null primary key,
  user_id          varchar(32),
  role_id          varchar(32),
  create_time      date,
  last_modify_time date,
  create_by        varchar(32),
  last_modify_by   varchar(32)
);


INSERT INTO t_bw_user(user_id, user_name, password, phone, email, signature, create_time, last_modify_time, create_by,
                      last_modify_by, position, sex, user_status)
VALUES ('1069915463289114625', 'admin', '905816fe1799bb76b0d6d866374bbca8', null, null, '咸鱼也要有大大的梦想~~', null, null,
        null, null, null, '1', '1');
INSERT INTO t_bw_role(role_id, role_name, role_desc, create_time, last_modify_time, create_by, last_modify_by)
VALUES ('1069918047802437634', '管理员', '测试', null, null, null, null);
INSERT INTO t_bw_user_role(user_role_id, user_id, role_id, create_time, last_modify_time, create_by, last_modify_by)
VALUES ('1069918550372347906', '1069915463289114625', '1069918047802437634', null, null, null, null);
