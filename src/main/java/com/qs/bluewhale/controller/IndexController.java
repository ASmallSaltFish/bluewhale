package com.qs.bluewhale.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qs.bluewhale.entity.Role;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.UserRole;
import com.qs.bluewhale.service.RoleService;
import com.qs.bluewhale.service.UserRoleService;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

    @GetMapping(value = "/album")
    public String album() {
        return "album";
    }

    @GetMapping(value = "/details")
    public String details() {
        return "details";
    }

    @GetMapping(value = "/leacots")
    public String leacots() {
        return "leacots";
    }

    @GetMapping(value = "/whisper")
    public String whisper() {
        return "whisper";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


    @PostMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(User user, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isNotBlank(rememberMe) && BooleanUtils.isTrue(Boolean.valueOf(rememberMe))) {
            token.setRememberMe(true);
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            //执行subject.login()方法时，会执行ShiroDbRealm中定义的doGetAuthenticationInfo方法
            subject.login(token);
        } catch (UnknownAccountException e) {
            jsonResult.setMsg("用户名不存在");
            return jsonResult;
        } catch (LockedAccountException e) {
            jsonResult.setMsg("用户被锁定");
            return jsonResult;
        } catch (DisabledAccountException e) {
            jsonResult.setMsg("用户已失效");
            return jsonResult;
        } catch (IncorrectCredentialsException e) {
            jsonResult.setMsg("密码不存在");
            return jsonResult;
        } catch (ExcessiveAttemptsException e) {
            jsonResult.setMsg("尝试输入错误次数过多");
            return jsonResult;
        }

        jsonResult.setStatus(JsonStatus.SUCCESS);
        jsonResult.setMsg("登录成功!");
        return jsonResult;
    }

    @GetMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/ajaxRegister")
    @ResponseBody
    public JsonResult ajaxRegister(User user, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        User us=userService.findUserByUserName(user.getUserName());
        String roleName=request.getParameter("role");
        Role role=roleService.findRoleByName(roleName);
        if (us!=null){
            jsonResult.setMsg("用户名已被使用");
            return jsonResult;
        }
        user.setUserId(IdWorker.getIdStr());
        userService.saveUser(user);
        UserRole userRole=new UserRole();
        userRole.setUserRoleId(IdWorker.getIdStr());
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(role.getRoleId());
        userRoleService.saveUserRole(userRole);
        jsonResult.setStatus(JsonStatus.SUCCESS);
        jsonResult.setMsg("注册成功!");
        return jsonResult;
    }
}
