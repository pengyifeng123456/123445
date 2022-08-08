package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main05Controller {//restful风格

    //http://localhost : 8080/myc/ index401/123456
    @RequestMapping(value = "/index401/{str}")//@PathVariable("str") String text)把路径中的{str}拿来保存在String text
    public ModelAndView index401(@PathVariable("str") String text) {
        System.out.println("接受到一个请求参数: " + text);
        return new
                ModelAndView("index");
    }

    // ■GET http://ocalhost:8080/mvc/index402/{id} -获取用户信息，id直接放在请求路径中
    @RequestMapping(value = "/index402/{id}", method = RequestMethod.GET)
    public String get01(@PathVariable("id") String text) {
        System.out.println("获取用户: " + text);
        return "index";
    }

    //■POST http://localhost:8080/mvc/index403 -添加用户信息，携带表单数据
    @RequestMapping(value = "/index403", method = RequestMethod.POST)
    public String post01(String username) {
        System.out.println("添加用户: " + username);
        return "index";
    }

    // ■DELETE htp://ocalhost:8080/mvc/index404/[id} -删除用户信息，id直接放在请求路径中
    @RequestMapping(value = "/index404/{id}", method = RequestMethod.DELETE)
    public String delete01(@PathVariable("id") String text) {
        System.out.println("删除用户: " + text);
        return "index";
    }

    //■PUT http://localhost:8080/mvc/index405 -修改用户信息，携带表单数据
    @RequestMapping(value = "/index405", method = RequestMethod.PUT)
    public String put01(String username) {
        System.out.println("修改用户: " + username);
        return "index";
    }
}