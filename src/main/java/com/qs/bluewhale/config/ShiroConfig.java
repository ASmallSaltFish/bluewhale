package com.qs.bluewhale.config;

import com.qs.bluewhale.shiro.filter.CustomerAuthFilter;
import com.qs.bluewhale.shiro.login.RetryCredentialsMatcher;
import com.qs.bluewhale.shiro.realm.ShiroDbRealm;
import com.qs.bluewhale.utils.PwdEncryptUtil;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 凭证匹配器（匹配登录次数）
     */
    @Bean
    public RetryCredentialsMatcher retryCredentialsMatcher(CacheManager cacheManager) {
        RetryCredentialsMatcher retryCredentialsMatcher = new RetryCredentialsMatcher(cacheManager);
        retryCredentialsMatcher.setHashAlgorithmName(PwdEncryptUtil.hashAlgorithmName);
        retryCredentialsMatcher.setHashIterations(PwdEncryptUtil.hashIterations);
        retryCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryCredentialsMatcher;
    }

    /**
     * 自定义域配置（身份校验、权限校验）
     */
    @Bean
    public ShiroDbRealm shiroDbRealm(RetryCredentialsMatcher credentialsMatcher) {
        ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
        //凭证匹配缓存
        shiroDbRealm.setCredentialsMatcher(credentialsMatcher);
        shiroDbRealm.setCachingEnabled(true);

        //身份验证缓存
        shiroDbRealm.setAuthenticationCachingEnabled(true);
        shiroDbRealm.setAuthenticationCacheName("authenticationCache");

        //权限验证缓存
        shiroDbRealm.setAuthorizationCachingEnabled(true);
        shiroDbRealm.setAuthorizationCacheName("authorizationCache");
        return shiroDbRealm;
    }


    /**
     * 会话ID生成器
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 会话Cookie模板
     */
    @Bean(value = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        //设置cookie失效时间，maxAge=-1表示浏览器关闭时cookie失效
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /**
     * 设置rememberMe的cookie的失效时间
     */
    @Bean(value = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("SHIRO-COOKIE");
        //path为/用于多个系统共享SHIRO-COOKIE
        simpleCookie.setPath("/");
        //浏览器中通过document.cookie可以获取cookie属性，设置了HttpOnly=true,在脚本中就不能的到cookie，可以避免cookie被盗用
        simpleCookie.setHttpOnly(true);
        //这里设置cookie失效时间7天（单位是秒）
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);
        return simpleCookie;
    }


    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Ymx1ZXdoYWxlAAAAAAAAAA=="));
        manager.setCookie(this.rememberMeCookie());
        return manager;
    }

    /**
     * 会话dao
     */
    @Bean
    public EnterpriseCacheSessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(this.sessionIdGenerator());
        return sessionDAO;
    }

    /**
     * session管理器
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session失效时间为1小时
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true); //默认开启
        return sessionManager;
    }


    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(ShiroDbRealm shiroDbRealm, CacheManager cacheManager, SessionManager sessionManager,
                                           RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDbRealm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }


    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //默认的登陆访问url
        shiroFilter.setLoginUrl("/login");
        //登陆成功后跳转的url
        shiroFilter.setSuccessUrl("/");
        //todo 没有权限跳转的url
        shiroFilter.setUnauthorizedUrl("/global/error");
        //覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
        HashMap<String, Filter> myFilters = new HashMap<>();
        myFilters.put("myAuthFilter", new CustomerAuthFilter());
        shiroFilter.setFilters(myFilters);

        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        //配置静态资源不需要认证
        hashMap.put("/static/**", "anon");
        //配置登录请求不需要认证
        hashMap.put("/login", "anon");
        hashMap.put("/ajaxLogin", "anon");
        //配合注册请求不需要认证
        hashMap.put("/register", "anon");
        hashMap.put("/ajaxRegister", "anon");

        //如果用户登录勾选了记住我，则不会进行authc认证
        hashMap.put("/**", "user,myAuthFilter");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }


    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:ShiroDbRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
