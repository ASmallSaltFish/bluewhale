package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import com.qs.bluewhale.entity.enums.SexEnum;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author qinyupeng
 * @since 2018-11-27 19:02:38
 */
@TableName("t_bw_user")
@Data
public class User extends SuperEntity<User> {

    @TableId(value = "USER_ID", type = IdType.ID_WORKER_STR)
    private String userId;

    private String userName;

    private String email;

    private String phone;

    private String password;

    private String position;

    private SexEnum sex;

    private String signature;
}
