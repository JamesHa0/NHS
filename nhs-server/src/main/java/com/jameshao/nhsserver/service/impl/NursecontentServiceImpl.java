package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Nursecontent;
import com.jameshao.nhsserver.service.NursecontentService;
import com.jameshao.nhsserver.mapper.NursecontentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【nursecontent】的数据库操作Service实现
* @createDate 2025-04-15 11:26:40
*/
@Service
public class NursecontentServiceImpl extends ServiceImpl<NursecontentMapper, Nursecontent>
    implements NursecontentService{
    @Autowired
    private NursecontentMapper nursecontentMapper;

    @Override
    public boolean deleteById(Integer id) {
        return nursecontentMapper.deletebyid(id);
    }
}




