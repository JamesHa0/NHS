package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Role;
import com.jameshao.nhsserver.service.RoleService;
import com.jameshao.nhsserver.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【role】的数据库操作Service实现
* @createDate 2025-04-07 16:25:37
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




