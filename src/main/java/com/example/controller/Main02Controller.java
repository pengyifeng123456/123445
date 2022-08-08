package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/12345")//路径可以嵌套
public class Main02Controller {
    //http://localhost:8080/mvc/12345/index100
    @RequestMapping({"/index100","/te56"})//可以用多个路径
    public String index100(Map<String,Object> map) {
        map.put("name","124124214");
        return "home";
    }

    //http://localhost:8080/mvc/12345/index102
    @RequestMapping("/index102/x?")
    //?表示任意一个字符，比如@Reques tMapping("/index/x?")可以匹配/index/xa、/index/xb等等 。
    //*表示任意0-n个字符，比如@RequestMappingC"/index/*")可以匹配/index/lbwnb、/index/yyds等 。
    //**表示当前目录或基于当前目录的多级目录，@RequestMapping("/index102/**")可以匹配/index  、/index/xxx等 。
    public ModelAndView index102() {
        return new ModelAndView("home");
    }

    //http://localhost:8080/mvc/12345/index101
    @PostMapping("/index101")// @PostMapping指定只能接受Post请求
    public String index101(Map<String,Object> map) {
        map.put("name","124124214");
        return "home";
    }

    @RequestMapping(value = "/index103",method = {RequestMethod.GET,RequestMethod.POST})//只能接受Post请求和GET请求
    public String index103() {
        return "home";
    }


    @RequestMapping(value = "/index104",params = {"！name","password"})//必须携带了password这个参数才能接收，和不能有name这个参数
    public String index104() {
        return "home";
    }

    @RequestMapping(value = "/index105",params = {"name=！123","password=124"})//name不等于123的都行且password=124
    public String index105() {
        return "home";
    }
    @RequestMapping(value = "/index106",headers={"!Connection","Name"})//响应头必须满足没有Connection且包含Name
    public String index106() {
        return "home";
    }

}
