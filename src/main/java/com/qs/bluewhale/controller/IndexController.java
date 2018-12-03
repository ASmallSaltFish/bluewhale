package com.qs.bluewhale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class IndexController {

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
    public String ajaxLogin() {
        return "ajaxLogin";
    }


    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String ajaxRegister() {
        return "ajaxRegister";
    }
}
