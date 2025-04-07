package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Menu;
import com.jameshao.nhsserver.service.MenuService;
import com.jameshao.nhsserver.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2025-04-07 16:25:37
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




