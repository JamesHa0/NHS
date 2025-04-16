package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Nurselevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author JamesHao
* @description 针对表【nurselevel】的数据库操作Mapper
* @createDate 2025-04-15 11:26:39
* @Entity com.jameshao.nhsserver.po.Nurselevel
*/
public interface NurselevelMapper extends BaseMapper<Nurselevel> {

    boolean deletebyid(Integer id);
}




