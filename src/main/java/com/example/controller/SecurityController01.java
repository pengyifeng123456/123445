package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class SecurityController01 {
    @RequestMapping("/csrf")
    public String index(HttpSession session) {
        session.setAttribute("login", true);//这里就正常访问一下index表示登陆
        return "home";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST,
            produces = "text/html;charset=utf-8") //这里要设置一下produces不然会乱码
    @ResponseBody
    public String pay(String account,
                      int amount,
                      @SessionAttribute("login") Boolean isLogin) {
        if (isLogin) return "成功转账￥" + amount + " 给:" + account;
        else return "转账失败，您没有登陆!";
    }

}