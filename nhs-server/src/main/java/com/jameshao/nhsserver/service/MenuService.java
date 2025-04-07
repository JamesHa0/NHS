package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【menu】的数据库操作Service
* @createDate 2025-04-07 16:25:37
*/
public interface MenuService extends IService<Menu> {
    List<Menu> getByroleid(Integer roleid);
}
