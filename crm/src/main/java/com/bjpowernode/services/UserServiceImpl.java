package com.bjpowernode.services;

import com.bjpowernode.beans.User;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.web.exception.MyLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String loginAct, String loginPwd,String ip) {
        User user = userMapper.getUser(loginAct, loginPwd);

        //todo 判断用户是否存在
        if (user==null){
            throw new MyLoginException("账号或密码");
        }

        //todo 关于失效时间：如果过期，在页面上给出提示
        String expireTime = user.getExpireTime();
        //JDK8新特性【java.time】
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        // 用户时间
        LocalDateTime expireTimeTemp = LocalDateTime.parse(expireTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(expireTimeTemp);
        //使用isBefore来判断是否过期，now是不是在 expireTimeTemp前面
        if (!now.isBefore(expireTimeTemp)){
            throw new MyLoginException("账号过期");
        }

        //todo 是否锁定，如果处于锁定状态，在页面上给出提示
        String lockStatus = user.getLockStatus();
        if (lockStatus.equals("0")){
            throw new MyLoginException("账号锁定");
        }

        //todo 判断用户使用的电脑的IP是否在允许范围内，如果不在范围内，在页面上给出提示
        String[] ips = user.getAllowIps().split(",");
        //利用集合
        List<String> listIp= Arrays.asList(ips);
        //判断是否存在与集合
        if(!listIp.contains(ip)){
            throw new MyLoginException("禁止的IP地址");
        };

        return user;
    }
}
