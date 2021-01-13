package com.wyh.ccwl.util;

import com.wyh.ccwl.bean.Menu;
import com.wyh.ccwl.bean.User;
import com.wyh.ccwl.service.AuthorityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AuthorityInterceptor implements HandlerInterceptor {
    @Resource
    AuthorityService authorityService;
    @Value("${server.servlet.context-path}")
    String serverName;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler){
        try{
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("loginUser") == null) {
                httpServletResponse.sendRedirect(serverName+"/view/error.html");
                return false;
            }
            HandlerMethod hand = (HandlerMethod) handler;
            Authority authority = hand.getMethodAnnotation(Authority.class);
            if (authority == null) {
                //等于空就是这个方法不进行权限控制
                return true;
            }
            String authorityId = authority.value();
            User user =  (User) session.getAttribute("loginUser");
            if(authorityService.hasPermission(user.getAdminType(),authorityId).isEmpty()){
                httpServletResponse.sendRedirect(serverName+"/view/error.html");
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            LogUtil.error(e);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
