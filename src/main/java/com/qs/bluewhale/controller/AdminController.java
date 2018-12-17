package com.qs.bluewhale.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiresRoles("admin")
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/index")
    public String index(){
        return "/admin/index";
    }

    @GetMapping(value = "/articleManage")
    public String articleManage(){
        return "/admin/articleManage";
    }

    @GetMapping(value = "/categoryManage")
    public String categoryManage(){
        return "/admin/categoryManage";
    }

    @GetMapping(value = "/tagManage")
    public String tagManage(){
        return "/admin/tagManage";
    }
}
