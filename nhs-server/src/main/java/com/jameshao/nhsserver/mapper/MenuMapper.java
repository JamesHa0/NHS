package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2025-04-07 16:25:37
* @Entity com.jameshao.nhsserver.po.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectbyroleid(Integer roleid);
}




