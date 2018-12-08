package com.qs.bluewhale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qs.bluewhale.entity.base.SuperEntity;
import lombok.Data;

@Data
@TableName("t_bw_role")
public class Role extends SuperEntity<Role> {

    @TableId(type = IdType.ID_WORKER_STR)
    private String roleId;

    private String roleCode;

    private String roleName;

    private String roleDesc;
}
