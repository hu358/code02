package com.bjpowernode.web.controller;

import com.bjpowernode.beans.User;
import com.bjpowernode.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class LoginController {
    //常量也叫字段
    public static final String SESSION_USER="user";
    public static final String COOKIE_LOGINACT="loginAct";
    public static final String COOKIE_LOGINPWD="loginPws";

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    //跳转到登录页
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    //点登录按钮时,进入到此路径进行验证
    @RequestMapping("login.do")
    @ResponseBody
    public Map loginAction(String username, String password, String autoLogin) {
        //获取用户ip
        String remoteAddr = request.getRemoteAddr();

        //登录,不成功不会向下执行,而是交给自定义异常处理
        User user = userService.getUser(username, password, remoteAddr);

        //传入“十天免登陆”,用户,记录在客户端
        setCookie(autoLogin, user);

        //HttpSession记录登陆成功的用户,记录在服务器
        setSession(user);

        //登录成功时,向前端返回 true,前端接收到true后,执行跳转到首页
        Map map = new HashMap() {{
            put("success", true);
        }};
        return map;
    }

    //退出登陆
    @RequestMapping("logout.do")
    public String logoutAction(){
        //退出登陆时,移除Session
        request.getSession().removeAttribute(LoginController.SESSION_USER);

        //退出的时候，要删除cookie
        Cookie cookieLoginAct = new Cookie(LoginController.COOKIE_LOGINACT, "");
        cookieLoginAct.setMaxAge(0);
        cookieLoginAct.setPath("/");  //cookie在哪个页面都有用
        Cookie cookieLoginPwd = new Cookie(LoginController.COOKIE_LOGINPWD, "");
        cookieLoginPwd.setMaxAge(0);
        cookieLoginPwd.setPath("/");  //cookie在哪个页面都有用

        //存储到浏览器
        response.addCookie(cookieLoginAct);
        response.addCookie(cookieLoginPwd);

        //返回到首页
        return "login";
    }


    private void setCookie(String autoLogin, User user) {
        //是否开启“十天免登陆”
        if (Objects.equals(autoLogin, "on")) {
            //创建cookie对象
            Cookie cookieLoginAct = new Cookie(LoginController.COOKIE_LOGINACT, user.getLoginAct());
            cookieLoginAct.setMaxAge(10*24*3600);
            cookieLoginAct.setPath("/");  //cookie在哪个页面都有用

            Cookie cookieLoginPwd = new Cookie(LoginController.COOKIE_LOGINPWD, user.getLoginPwd());
            cookieLoginPwd.setMaxAge(10*24*3600);
            cookieLoginPwd.setPath("/");  //cookie在哪个页面都有用

            //存储到浏览器
            response.addCookie(cookieLoginAct);
            response.addCookie(cookieLoginPwd);
        }
    }

    private void setSession(User user) {
        HttpSession session = request.getSession();
        //每个请求对象都有一个自己的专属user记录在session
        session.setAttribute(LoginController.SESSION_USER, user);
    }
}