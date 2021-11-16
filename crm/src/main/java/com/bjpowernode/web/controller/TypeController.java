package com.bjpowernode.web.controller;

import com.bjpowernode.beans.Type;
import com.bjpowernode.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("type")
public class TypeController {
    @Autowired
    TypeService typeService;

    //显示类型首页
    @RequestMapping("indexView")
    public ModelAndView indexView(ModelAndView modelAndView){
        List<Type> all = typeService.getAll();
        modelAndView.addObject("tList",all);
        modelAndView.setViewName("settings/dictionary/type/index");
        return modelAndView;
    }

    //显示添加界面
    @RequestMapping("saveView")
    public String saveView(){
        return "/settings/dictionary/type/save";
    }
    //判定code是否重复
    @RequestMapping("checkCode.do")
    @ResponseBody
    public boolean checkCodeDo(String code){
        Type byId = typeService.getById(code);
        return byId != null;
    }
    //添加
    @RequestMapping("save.do")
    //用type对象接收前端给的3个参数
    public String savaDo(Type type){
        typeService.save(type);
        return "redirect:/type/indexView";
    }

    @RequestMapping("editView")
    public ModelAndView editView(String code,ModelAndView modelAndView){
        Type type = typeService.getById(code);
        modelAndView.addObject("type",type);
        modelAndView.setViewName("settings/dictionary/type/edit");
        return modelAndView;
    }

    @RequestMapping("edit.do")
    public String editDo(Type type) {
        typeService.edit(type);
        return "redirect:/type/indexView";
    }

    @RequestMapping("delete.do")
    public String deleteDo(String[] ids) {
        typeService.delete(ids);
        return "redirect:/type/indexView";
    }
}