package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Nurserecord;
import com.jameshao.nhsserver.service.NurserecordService;
import com.jameshao.nhsserver.mapper.NurserecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurserecord】的数据库操作Service实现
* @createDate 2025-04-15 11:26:40
*/
@Service
public class NurserecordServiceImpl extends ServiceImpl<NurserecordMapper, Nurserecord>
    implements NurserecordService{
    @Autowired
    private NurserecordMapper nurserecordMapper;

    @Override
    public List<Nurserecord> getByCustomerId(Integer customerId) {
        return nurserecordMapper.getByCustomerId(customerId);
    }

    @Override
    public boolean deleteById(Integer id) {
        return nurserecordMapper.deleteById(id);
    }
}




