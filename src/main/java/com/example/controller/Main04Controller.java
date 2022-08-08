package com.example.controller;

import com.example.entity.TestBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class Main04Controller {
    @Resource
    TestBean bean;

    @RequestMapping(value = "/index301")
    public ModelAndView index301() {
        // return new ModelAndView("redirect:home");//调转到 @RequestMapping("/home")
        return new ModelAndView("forward:home");
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/index302")
    public ModelAndView index() {
        System.out.println(bean);// 这里用来测试bean配置类WebConfiguration中@RequestScope,@SessionScope,@Bean的作用域
        return new ModelAndView("index");
    }

}


