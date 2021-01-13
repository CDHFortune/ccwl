package com.wyh.ccwl.mapper;

import com.wyh.ccwl.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    public List<Menu> hasPermission(@Param("id") String id,@Param("menuId") String menuId);
    public List<Menu> getMenu(@Param("id") String id);
}
