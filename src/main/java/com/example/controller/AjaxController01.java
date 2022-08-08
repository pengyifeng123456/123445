package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.AjaxUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController01 {

    @RequestMapping(value = "/ajax01", produces = "application/json")
    @ResponseBody
    public ModelAndView ajax01() {
        return new ModelAndView("ajax01");
    }

    @RequestMapping(value = "/ajax02", produces = "application/json")
    @ResponseBody
    public AjaxUser ajax02() {
        AjaxUser user = new AjaxUser();
        user.setUsername("小明");
        user.setAge(18);
        return user;
    }

    @RequestMapping("/submit")
    @ResponseBody
    public String submit(AjaxUser user) {
        System.out.println("接收到前端数据:" +user);
        return "{\"success\":true01}";
    }

    @RequestMapping("/submit02" )
    @ResponseBody
    public String submit02(@RequestBody AjaxUser user){
        //因为传过来的是josn格式所以用@RequestBody接收
        System. out. println("接收到前端数据: "+user);
        return "{\"success\": true02}";
    }

    @RequestMapping("/submit03" )
    @ResponseBody
    public String submit03(@RequestBody JSONObject object){
        System. out. println("接收到前端数据: "+object);
        return "{\"success\": true03}";
    }
}


