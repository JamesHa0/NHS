package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Nurselevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurselevel】的数据库操作Service
* @createDate 2025-04-15 11:26:39
*/
public interface NurselevelService extends IService<Nurselevel> {

    List<Nurselevel> getLevelsWithItems(Integer id,String levelName);

    boolean deleteById(Integer id);

    String getNameById(Integer id);
}
