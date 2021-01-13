package com.wyh.ccwl.mapper;

import com.wyh.ccwl.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    public User getUser(String username);
}
