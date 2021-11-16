package com.bjpowernode.web.interceptor;

import com.bjpowernode.beans.User;
import com.bjpowernode.services.UserService;
import com.bjpowernode.web.controller.LoginController;
import com.bjpowernode.web.exception.MyLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    //根据拦截规则,除"/"、"/user/login"、"/user/login.do"不拦截外,其他页面全部都要拦截
    /*总体业务:除不拦截路径外,其他页面都要判断请求对象是否有session和cookie
              如果有session,且有cookie,且账号状态正常,直接放行
    */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否有session,如果没有session,那么user为null
        HttpSession session = request.getSession();
        Object user = session.getAttribute(LoginController.SESSION_USER);

        System.out.println("request = " + request);
        System.out.println("request.getRequestedSessionId() = " + request.getRequestedSessionId());

        //1、请求没有对应的session
        if (user == null) {
            //判断是否有对应的cooke
            String username = null;
            String password = null;
            Cookie[] cookies = request.getCookies();

            //1.1 如果有对应的cookie
            if (cookies!=null){
                //取出cookie中的值
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(LoginController.COOKIE_LOGINACT)) {
                        username = cookie.getValue();
                    }
                    if (cookie.getName().equals(LoginController.COOKIE_LOGINPWD)) {
                        password = cookie.getValue();
                    }
                }

                //如果cookie中的两个值都不为空
                if (username != null && password != null) {
                    User login = null;
                    //判断现在的用户状态是否正常
                    try {
                        //不报异常表示用户状态正常,满足业务要求
                        login = userService.getUser(username, password, request.getRemoteAddr());
                    }catch (MyLoginException e){
                        //报异常表示用户状态不正常,不满足业务要求,直接跳转到首页
                        System.out.println("自动登录："+e.getMessage());
                        response.sendRedirect("/user/login");
                        return false;
                    }

                    //用户有cookie,且用户状态正常,此时 添加session
                    session = request.getSession();
                    session.setAttribute(LoginController.SESSION_USER, login);
                    return true;
                }
            }

            //1.2 cookie为空,跳转到登录页
            System.out.println("nonono");
            response.sendRedirect("/user/login");
            //拒绝向下执行
            return false;
        } else {
            //2、请求有对应的session,执行放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
