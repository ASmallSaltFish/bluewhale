package com.qs.bluewhale.shiro.filter;

import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.ExecutionContext;
import com.qs.bluewhale.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权过滤器（权限认证过滤器）
 * <p>
 * 按照shiro提供的过滤器执行链，在用户登录后的每一个请求都会走该过滤器，该类没有交给spring管理，是因为交给spring管理会存在执行顺序不一致问题，
 * 所以这里引发了另一个问题，就是关于service的获取，可以手动从spring容器中获取管理的bean对象；
 *
 * @author qinyupeng
 * @since 2018-12-05 19:25:25
 */
public class CustomerAuthFilter extends AuthorizationFilter {

    private static Logger logger = LoggerFactory.getLogger(CustomerAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("====>>>自定义授权过滤器执行了。。。");
        ApplicationContext applicationContext = SpringContextUtil.getInstance().getApplicationContext();
        UserService userService = (UserService) applicationContext.getBean("userService");
        if (userService == null) {
            logger.error("spring容器获取userService对象失败！");
            throw new RuntimeException("spring容器获取userService对象失败！");
        }

        //将登录用户信息保存在本地线程中，在整个项目中获取当前登录用户信息可以从ExecutionContext中获取
        //（todo 是否需要缓存，避免每次请求查询数据库）
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (username == null) {
            logger.warn("未获取到登录用户名！");
            return false;
        }

        User loginUser = userService.findUserByUserName(username);
        if (loginUser == null) {
            logger.warn("登录用户不存在！");
            return false;
        }

        Map<String, String> contextMap = new HashMap<>();
        contextMap.put(ExecutionContext.USER_ID, loginUser.getUserId());
        contextMap.put(ExecutionContext.USER_NAME, loginUser.getUserName());
        ExecutionContext.setContextMap(contextMap);

        //页面展示时使用
        servletRequest.setAttribute("loginUser", loginUser);
        return true;
    }
}
