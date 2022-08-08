package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//@ControllerAdvice 配合 @ExceptionHandler 实现全局异常处理(AOP思想，某个地方出现异常进行拦截）
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model) { //可以直接添加形参来获取异常
        e.printStackTrace();
        model.addAttribute("e",e);
        return "error";
    }

}
