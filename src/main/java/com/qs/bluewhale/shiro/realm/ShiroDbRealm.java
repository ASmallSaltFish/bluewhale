package com.qs.bluewhale.shiro.realm;

import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.UserRole;
import com.qs.bluewhale.entity.enums.UserStatusEnum;
import com.qs.bluewhale.service.RoleService;
import com.qs.bluewhale.service.UserRoleService;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.PwdEncryptUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义域，实现登录授权
 * 在subject.login()时，会执行doGetAuthenticationInfo()方法进行身份校验；
 * 在访问标记@RequiresRoles("role")注解的请求或包含shiro标签的页面时，会执行doGetAuthorizationInfo()进行权限认证；
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 权限校验
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("====>>>开始进行权限认证啦。。。");

        //获取当前登录的用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //设置用户编号
        authorizationInfo.setRoles(roleService.findRoleCodesByUserName(username));
        return authorizationInfo;
    }

    /**
     * 身份校验
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("====>>>开始进行登录身份认证啦。。。");
        String username = (String) token.getPrincipal();
        User user = userService.findUserByUserName(username);
        //账号不存在，抛出异常
        if (user == null) {
            throw new UnknownAccountException();
        }

        String userStatus = user.getUserStatus();
        //用户被锁定
        if (UserStatusEnum.LOCKED.getCode().equals(userStatus)) {
            throw new LockedAccountException();
        }

        //用户被禁用
        if (UserStatusEnum.DISABLED.getCode().equals(userStatus)) {
            throw new DisabledAccountException();
        }

        //将userId+userName的md5后，作为密码加密salt
        ByteSource saltSource = new Md5Hash(user.getUserId() + user.getUserName());
        return new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                saltSource,//salt=username+salt
                this.getName());
    }

//    /**
//     * 设置认证加密方式（经过测试，在项目启动时会加载登录认证加密方式，这里的加密需要和修改密码时加密算法一致）
//     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
//        md5CredentialsMatcher.setHashAlgorithmName(PwdEncryptUtil.hashAlgorithmName);
//        md5CredentialsMatcher.setHashIterations(PwdEncryptUtil.hashIterations);
//        super.setCredentialsMatcher(md5CredentialsMatcher);
//    }

}
