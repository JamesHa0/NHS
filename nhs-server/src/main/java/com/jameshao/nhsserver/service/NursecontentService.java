package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Nursecontent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author JamesHao
* @description 针对表【nursecontent】的数据库操作Service
* @createDate 2025-04-15 11:26:40
*/
public interface NursecontentService extends IService<Nursecontent> {

    boolean deleteById(Integer id);
}
