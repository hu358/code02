package com.bjpowernode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("err")
public class ErrController {
    @RequestMapping("indexView")
    public String indexView(Model model) {
        model.addAttribute("msg","正在开发中....");
        return "/err";
    }
}
