package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
/*@RestController//如果下面的方法均带有@ResponseBody，那么直接把@Controller替换成@RestController，这样下面就可以不用每个都写@ResponseBody*/
public class Main07Controller {


    @RequestMapping(value = "/index601")
    public String get() {
        return "index";
    }

    @RequestMapping(value = "/home601", produces = "application/json")
    @ResponseBody//return返回什么就给页面什么
    public String home3() {
        JSONObject object = new JSONObject();
        object.put("name ", "杰哥");
        object.put("age", 18);
        return object.toJSONString();
    }

    @RequestMapping(value = "/home602", produces = "application/json")
    @ResponseBody//return返回什么就给页面什么
    public String home1() {
        User user = new User();
        user.setUsername("ww");
        user.setPassword("123456");
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/home603", produces = "application/json")
    @ResponseBody//return返回什么就给页面什么
    public User home2() {//注意这里返回的是对象
        User user = new User();
        user.setUsername("ww");
        user.setPassword("123456");
        return user;//虽然返回的是个对象，但由于WebConfiguration中的configureMessageConverters构造方法，会自动解析成json字符串；
    }

}

