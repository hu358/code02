package com.bjpowernode.services;

import com.bjpowernode.beans.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUser(String loginAct, String loginPwd, String ip);
}
