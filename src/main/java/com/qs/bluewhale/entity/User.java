package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author qinyupeng
 * @since 2018-11-27 19:02:38
 */
@Data
@TableName("t_bw_user")
public class User extends SuperEntity<User> {

    @TableId(value = "USER_ID", type = IdType.ID_WORKER_STR)
    private String userId;

    private String userName;

    private String email;

    private String phone;

    private String password;

    private String position;

    //性别 1-男，2-女，3-保密
    private String sex;

    //用户签名
    private String signature;

    //用户状态 1-正常，2-被锁定，3-禁用
    private String userStatus;
}
