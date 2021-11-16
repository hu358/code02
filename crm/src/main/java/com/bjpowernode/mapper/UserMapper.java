package com.bjpowernode.mapper;

import com.bjpowernode.beans.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {
    User getUser(@Param("loginAct") String loginAct,@Param("loginPwd") String loginPwd);
}
