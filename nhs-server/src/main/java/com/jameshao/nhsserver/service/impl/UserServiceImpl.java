package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.User;
import com.jameshao.nhsserver.service.UserService;
import com.jameshao.nhsserver.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-04-07 16:25:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




