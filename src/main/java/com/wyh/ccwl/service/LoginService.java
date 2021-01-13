package com.wyh.ccwl.service;

import com.wyh.ccwl.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyh.ccwl.mapper.LoginMapper;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    LoginMapper loginMapper;
    public User getUser(String username){
        return loginMapper.getUser(username);
    }
}
