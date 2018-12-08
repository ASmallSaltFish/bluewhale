package com.qs.bluewhale.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 将一些公用的信息存储在这里
 *
 * @author qinyupeng
 * @since 2018-11-27 19:02:14
 */
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "last_modify_time", fill = FieldFill.INSERT)
    private Date lastModifyTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "last_modify_by", fill = FieldFill.INSERT)
    private String lastModifyBy;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
