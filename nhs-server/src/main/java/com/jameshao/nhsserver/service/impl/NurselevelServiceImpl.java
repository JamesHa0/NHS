package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.mapper.NursecontentMapper;
import com.jameshao.nhsserver.po.Nurselevel;
import com.jameshao.nhsserver.service.NurselevelService;
import com.jameshao.nhsserver.mapper.NurselevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurselevel】的数据库操作Service实现
* @createDate 2025-04-15 11:26:39
*/
@Service
public class NurselevelServiceImpl extends ServiceImpl<NurselevelMapper, Nurselevel>
    implements NurselevelService{
    @Autowired
    private NurselevelMapper nurselevelMapper;

    @Override
    public List<Nurselevel> getLevelsWithItems(Integer id,String levelName) {
        return nurselevelMapper.getLevelsWithItems(id, levelName);
    }

    @Override
    public boolean deleteById(Integer id) {
        return nurselevelMapper.deletebyid(id);
    }

    @Override
    public String getNameById(Integer id) {
        Nurselevel nurselevel = nurselevelMapper.selectById(id);
        return nurselevel != null ? nurselevel.getLevelName() : null;
    }

}




