package com.wyh.ccwl.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RespBean<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private T entity;
    private List<T> list;
    public RespBean() {

    }
    public RespBean(int code) {
        put("code", code);
        put("success", true);
        put("errMsg", "");
    }
    public RespBean(T entity) {
        put("code", 200);
        put("success", true);
        put("errMsg", "");
        put("data",entity);
    }
    public RespBean(List<T> list) {
        put("code", 200);
        put("success", true);
        put("errMsg", "");
        put("data",list);
    }
    public RespBean(List<T> list,int count) {
        put("code", 200);
        put("errMsg", "");
        put("success", true);
        put("count", count);
        put("data",list);
    }
    public RespBean(Map<String,Object> map) {
        put("code", 200);
        put("errMsg", "");
        put("success", true);
        put("data",map);
    }

    public static RespBean error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static RespBean error(String msg) {
        return error(500, msg);
    }

    public static RespBean error(int code, String msg) {
        RespBean respBean = new RespBean();
        respBean.put("success", false);
        respBean.put("code", code);
        respBean.put("errMsg", msg);
        return respBean;
    }

    public static RespBean ok(Map<String, Object> map) {
        RespBean respBean = new RespBean();
        respBean.put("code", 200);
        respBean.put("errMsg", "");
        respBean.put("success", true);
        respBean.put("data",map);
        return respBean;
    }

    public static RespBean ok() {
        return new RespBean(200);
    }

    public RespBean put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
