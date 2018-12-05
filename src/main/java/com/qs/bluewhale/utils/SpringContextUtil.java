package com.qs.bluewhale.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 获取ApplicationContext容器对象工具类，在有些地方提供spring注解，无法获取到bean，
 * 可以使用原始的applicationContext.getBean("serviceName")获取到spring管理的bean
 *
 * @author qinyupeng
 * @since 2018-12-05 19:25:09
 */
@Component
public class SpringContextUtil {

    @Autowired
    private ApplicationContext applicationContext;

    private static SpringContextUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static SpringContextUtil getInstance() {
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
