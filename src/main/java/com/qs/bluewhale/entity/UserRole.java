package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

@Data
@TableName("t_bw_user_role")
public class UserRole extends SuperEntity<UserRole> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String userRoleId;

    private String userId;

    private String roleId;
}
