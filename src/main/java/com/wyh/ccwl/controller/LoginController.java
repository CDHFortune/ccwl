package com.wyh.ccwl.controller;

import com.wyh.ccwl.bean.Menu;
import com.wyh.ccwl.bean.RespBean;
import com.wyh.ccwl.service.AuthorityService;
import com.wyh.ccwl.service.LoginService;
import com.wyh.ccwl.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wyh.ccwl.bean.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

@RestController
public class LoginController {
    @Resource
    LoginService loginService;
    @Resource
    AuthorityService authorityService;

    @Value("${server.servlet.context-path}")
    String serverName;
    @PostMapping("/login")
    public RespBean login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return RespBean.error("必填项不能为空");
        }
        try{
            User loginUser = loginService.getUser(username);
            if (loginUser != null){
                if (loginUser.getPassword().equals(password)) {
                    session.setAttribute("loginUser",loginUser);
                    return new RespBean(200);
                } else {
                    return RespBean.error("用户名或密码错误");
                }
            } else {
                return RespBean.error("用户名或密码错误");
            }
        }catch (Exception e){
            LogUtil.error(e);
            return RespBean.error("服务器内部错误！");
        }
    }
    @RequestMapping("/exitLogin")
    public RespBean exitLogin( HttpSession session,HttpServletResponse httpServletResponse){
        try{
            session.removeAttribute("loginUser");
            httpServletResponse.sendRedirect(serverName+"/index.html");
            return new RespBean(200);
        }catch (Exception e){
            LogUtil.error(e);
            return RespBean.error("服务器内部错误！");
        }
    }
    @PostMapping("/getMenu")
    public RespBean getMenu(HttpSession session){
        try{
            User user = (User) session.getAttribute("loginUser");
            List<Menu> menuList =  authorityService.getMenu(user.getAdminType());
            return new RespBean(menuList);
        }catch (Exception e){
            LogUtil.error(e);
            return RespBean.error("服务器内部错误");
        }
    }
}
