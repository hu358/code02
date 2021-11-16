package com.bjpowernode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("act")
public class ActivityController {
    @RequestMapping("indexView")
    public String indexView(){
        return "/workbench/activity/index";
    }

}
