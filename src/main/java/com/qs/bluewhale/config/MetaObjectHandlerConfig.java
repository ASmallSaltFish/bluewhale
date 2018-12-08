package com.qs.bluewhale.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.qs.bluewhale.utils.ExecutionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段自动填充配置
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    //插入填充数据库字段
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("--->>开始插入方法实体填充");
        final Object createTime = getFieldValByName("createTime", metaObject);
        final Object lastModifyTime = getFieldValByName("lastModifyTime", metaObject);
        final Object createBy = getFieldValByName("createBy", metaObject);
        final Object lastModifyBy = getFieldValByName("lastModifyBy", metaObject);

        if (createTime == null) {
            metaObject.setValue("createTime", new Date());
        }

        if (lastModifyTime == null) {
            metaObject.setValue("lastModifyTime", new Date());
        }

        String userId = ExecutionContext.getUserId();
        if (StringUtils.isNotBlank(userId) && createBy == null) {
            metaObject.setValue("create_by", userId);
        }

        if (StringUtils.isNotBlank(userId) && lastModifyBy == null) {
            metaObject.setValue("lastModifyBy", userId);
        }
    }

    //更新填充数据库字段
    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("--->>开始更新方法实体填充");
        final Object lastModifyTime = getFieldValByName("lastModifyTime", metaObject);
        final Object lastModifyBy = getFieldValByName("lastModifyBy", metaObject);
        if (lastModifyTime == null) {
            metaObject.setValue("lastModifyTime", new Date());
        }

        String userId = ExecutionContext.getUserId();
        if (StringUtils.isNotBlank(userId) && lastModifyBy == null) {
            metaObject.setValue("lastModifyBy", userId);
        }
    }
}
