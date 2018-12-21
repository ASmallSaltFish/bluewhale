package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    @GetMapping(value = "/login")
    public String login() {
        return "/index/login";
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
            jsonResult.setMsg("尝试输入错误次数过多，最多可以登录5次，请10分钟后重试。");
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
        return "/index/login";
    }
}
