package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main06Controller {
    @RequestMapping(value = "/index501")
    public ModelAndView get01() {
        System.out.println("我是处理Controller501");
        if (true) throw new RuntimeException("我是异常!");
        return new ModelAndView("redirect:home502");
       // return "index";
    }

    @RequestMapping(value = "/home502")
    public String home() {
        System.out.println("我是home");
        return "home";
    }
}


