package com.qs.bluewhale.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.qs.bluewhale.controller.base.BaseController;
import com.qs.bluewhale.entity.User;
import com.qs.bluewhale.entity.enums.UserStatusEnum;
import com.qs.bluewhale.service.UserService;
import com.qs.bluewhale.utils.JsonResult;
import com.qs.bluewhale.utils.JsonStatus;
import com.qs.bluewhale.utils.PwdEncryptUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/")
@Controller
public class ResgisterController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/ajaxRegister")
    @ResponseBody
    public JsonResult ajaxRegister(User user) {
        JsonResult jsonResult = new JsonResult();
        boolean isExist = userService.existUserName(user.getUserName());
        if (BooleanUtils.isTrue(isExist)) {
            jsonResult.setMsg("用户名重复！");
            return jsonResult;
        }

        String userId = IdWorker.getIdStr();
        String salt = userId + user.getUserName();
        String newPassword = PwdEncryptUtil.getPassword(user.getPassword(), salt);
        user.setUserId(userId);
        user.setPassword(newPassword);
        user.setCreateBy(userId);
        user.setLastModifyBy(userId);
        //设置用户状态
        user.setUserStatus(UserStatusEnum.ACTIVE.getCode());
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            logger.error("注册保存用户失败！", e);
        }

        jsonResult.setStatus(JsonStatus.SUCCESS);
        jsonResult.setMsg("注册成功!");
        return jsonResult;
    }
}
