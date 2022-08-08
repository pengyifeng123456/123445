package com.example.controller;

import com.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class Main03Controller {

    // http://localhost:8080/mvc/index201
    @RequestMapping("/index201")
    //@RequestParam("username")用来传递参数,访问路径必须带username参数才能访问成功，并把参数保存在String username，下面需要用username
    public String index201(@RequestParam("username") String username) {
        System.out.println("收到一个请求参数:" + username);
        return "index";
    }

    // http://localhost :8080/mvc/index202
    @RequestMapping("/index202")
    public String index202(@RequestParam(value = "username", required = false, defaultValue = "lbwnb") String username) {
        //@RequestParam(@RequestParam(value = "username", required = false, defaultValue = "lbwnb")用来传递参数,但和上面不同，
        // required = false代表如果访问路径为带有username也能进行访问，并且此时username=null。但是如果有defaultValue = "lbwnb"，username=lbwnb
        //并把参数保存在String username，下面需要用username
        System.out.println("收到一个请求参数: " + username);
        return "index";
    }

    // http://localhost:8080/mvc/index203
    @RequestMapping("/index203")
    public String index203(HttpServletRequest request) {//把路径中携带的参数保存在HttpServletRequest中，然后下面在HttpServletRequest中找数据
        System.out.println("收到一个请求参数:" + request.getParameterMap().keySet());
        return "index";
    }

    // http://localhost:8080/mvc/index204
    @RequestMapping(value = "/index204 ")
    public ModelAndView index204(HttpSession session) {//把路径中携带的参数保存在session中，然后下面再到session中找数据
        System.out.println(session.getAttribute("test"));
        session.setAttribute("test", "鸡你太美");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index205 ")
    public ModelAndView index205(User user) {//把路径中携带的参数保存在user中，然后下面再到session中找数据
        System.out.println(user);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index206")
    public ModelAndView index206(@SessionAttribute(value = "test", required = false) String test, HttpSession session) {
        //@SessionAttribute和上面的@RequestParam用法一样，最后把SessionAttribute中名字叫test的东西保存在String test中
        //HttpSession session用来进行一些操作
        session.setAttribute("test", "XXXX");
        System.out.println(test);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index207")
    public ModelAndView index207(HttpServletResponse response, @CookieValue(value = "test", required = false) String test) {
        // @CookieValue和上面的@RequestParam用法一样，最后把SessionAttribute中名字叫test的东西保存在String test中
        //HttpServletResponse response用来进行一些操作
        System.out.println("获取到cookie值为: " + test);
        response.addCookie(new Cookie("test", "lbwnb"));
        return new ModelAndView("index");
    }

}
