package com.bjpowernode.web.controller;

import com.bjpowernode.Utils.UUIDUtils;
import com.bjpowernode.beans.Type;
import com.bjpowernode.beans.Value;
import com.bjpowernode.services.TypeService;
import com.bjpowernode.services.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("value")
public class ValueController {
    @Autowired
    ValueService valueService;

    @Autowired
    TypeService typeService;

    @RequestMapping("indexView")
    public ModelAndView indexView(ModelAndView modelAndView){
        List<Value> all = valueService.getAll();
        modelAndView.addObject("vList",all);
        modelAndView.setViewName("settings/dictionary/value/index");
        return modelAndView;
    }

    @RequestMapping("saveView")
    public String saveView(Model model){
        List<Type> all = typeService.getAll();
        model.addAttribute("tList",all);
        return "settings/dictionary/value/save";
    }

    @RequestMapping("save.do")
    public String saveDo(Value value,String typeCode){
        Type type = new Type();
        type.setCode(typeCode);
        value.setType(type);

        //UUID
        String id = UUIDUtils.getUUID();
        value.setId(id);
        valueService.save(value);
        return "redirect:/value/indexView";
    }

    @RequestMapping("editView")
    public String editView(String id,Model model){
        Value byId = valueService.getById(id);
        List<Type> all = typeService.getAll();
        model.addAttribute("tList",all);
        model.addAttribute("value",byId);
        return "settings/dictionary/value/edit";
    }

    @RequestMapping("update.do")
    public String updateDo(Value value){
        valueService.edit(value);
        return "redirect:/value/indexView";
    }

    @RequestMapping("delete.do")
    public String deleteDo(String[] ids){
        valueService.delete(ids);
        return "redirect:/value/indexView";
    }
}