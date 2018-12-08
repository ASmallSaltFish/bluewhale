package com.qs.bluewhale.controller;

import com.qs.bluewhale.controller.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

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

    //用户信息
    @GetMapping(value = "/userInfo")
    public String userInfo() {
        return "userInfo";
    }

    //博客管理(管理员权限)
    @RequiresRoles("admin")
    @GetMapping(value = "/blogManager")
    public String blogManager() {
        return "blogManager";
    }
}
