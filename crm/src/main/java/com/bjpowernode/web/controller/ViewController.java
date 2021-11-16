package com.bjpowernode.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    //每个都是这种形式，不可取
    /*@RequestMapping("/workbench/index.html")
    public String workbenchIndex(){
        return "/workbench/index";
    }

    @RequestMapping("/workbench/main/index.html")
    public String workbenchMainIndex(){
        return "/workbench/main/index";
    }*/

    @Autowired
    HttpServletRequest request;

    //改进
    @RequestMapping("/**/*.html")
    public String workbenchViewAction(){
        String requestURI = request.getRequestURI();
        String result = requestURI.substring(0, requestURI.lastIndexOf("."));
        return result;
    }

    @RequestMapping("/")
    public String root(){
        return "login";
    }
}