package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {
    // http://localhost :8080/mvc/test
    @RequestMapping("/test")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    // http://localhost :8080/mvc/index01
    @RequestMapping(value = "/index01")
    public String index01() {
        return "home";//虽然返回的时String,但会默认把String转换为 ModelAndView
    }

    // http://localhost :8080/mvc/index02
    @RequestMapping(value = "/index02")
    public ModelAndView index02() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.getModel().put("name", "啊这");
        return modelAndView;
    }

    // http://localhost :8080/mvc/index03
    @RequestMapping(value = "/index03")
    public String index03(Model model) {
        model.addAttribute("name", "啊这");
        return "index";
    }

    // http://localhost :8080/mvc/index04
    @RequestMapping(value = "/index04")
    public String index04(Map<String,Object> map) {
        map.put("name","124124214");
        return "index";
    }

}




