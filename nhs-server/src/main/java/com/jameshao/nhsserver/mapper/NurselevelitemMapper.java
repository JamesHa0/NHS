package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Nurselevelitem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author JamesHao
* @description 针对表【nurselevelitem】的数据库操作Mapper
* @createDate 2025-04-21 14:32:47
* @Entity com.jameshao.nhsserver.po.Nurselevelitem
*/
public interface NurselevelitemMapper extends BaseMapper<Nurselevelitem> {

    boolean deleteByIds(Integer levelId, Integer itemId);
}




