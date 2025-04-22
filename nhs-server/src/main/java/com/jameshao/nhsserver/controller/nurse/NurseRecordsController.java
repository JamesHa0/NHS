package com.jameshao.nhsserver.controller.nurse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Nurserecord;
import com.jameshao.nhsserver.service.NurserecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nurse/nurseRecords")
public class NurseRecordsController {
    @Autowired
    JSONReturn jsonReturn;
    @Autowired
    NurserecordService nurseRecordService;

    @RequestMapping("/list")
    public String list() {
        try{
            LambdaQueryWrapper<Nurserecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Nurserecord::getIsDeleted, 0);
            List<Nurserecord> list = nurseRecordService.list(queryWrapper);
            return jsonReturn.returnSuccess(list);
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/detail")
    public String detail(Integer customerId) {
        try{
            List<Nurserecord> list =  nurseRecordService.getByCustomerId(customerId);
            return jsonReturn.returnSuccess(list);
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }



}
