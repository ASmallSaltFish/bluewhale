package com.qs.bluewhale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/article")
public class ArticalController {

    @GetMapping(value = "/example")
    public String example(){
        return "articles/example";
    }
}
