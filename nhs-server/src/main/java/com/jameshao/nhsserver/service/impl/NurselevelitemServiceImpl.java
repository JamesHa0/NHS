package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Nurselevelitem;
import com.jameshao.nhsserver.service.NurselevelitemService;
import com.jameshao.nhsserver.mapper.NurselevelitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【nurselevelitem】的数据库操作Service实现
* @createDate 2025-04-21 14:32:47
*/
@Service
public class NurselevelitemServiceImpl extends ServiceImpl<NurselevelitemMapper, Nurselevelitem>
    implements NurselevelitemService{
    @Autowired
    private NurselevelitemMapper nurselevelitemMapper;

    @Override
    public boolean deleteByIds(Integer levelId, Integer itemId) {
        return nurselevelitemMapper.deleteByIds(levelId,itemId);
    }
}




