package com.wyh.ccwl.service;

import com.wyh.ccwl.bean.Menu;
import com.wyh.ccwl.mapper.AuthorityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorityService {
    @Resource
    AuthorityMapper authorityMapper;
    public List<Menu> hasPermission(String id,String menuId){
        return authorityMapper.hasPermission(id,menuId);
    }
    public List<Menu> getMenu(String id){
        return authorityMapper.getMenu(id);
    }
}
