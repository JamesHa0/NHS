package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Nurserecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurserecord】的数据库操作Service
* @createDate 2025-04-15 11:26:40
*/
public interface NurserecordService extends IService<Nurserecord> {

    List<Nurserecord> getByCustomerId(Integer customerId);

    boolean deleteById(Integer id);
}
