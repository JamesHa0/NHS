package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Nurselevelitem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author JamesHao
* @description 针对表【nurselevelitem】的数据库操作Service
* @createDate 2025-04-21 14:32:47
*/
public interface NurselevelitemService extends IService<Nurselevelitem> {
    public boolean deleteByIds(Integer levelId, Integer itemId);

}
