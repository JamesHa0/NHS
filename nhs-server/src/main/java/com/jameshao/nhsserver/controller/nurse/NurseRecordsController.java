package com.jameshao.nhsserver.controller.nurse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Nurserecord;
import com.jameshao.nhsserver.service.NurserecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    @RequestMapping("/add")
    public String add(@RequestBody Nurserecord nurserecord){
        try{
            if (nurserecord.getNursingTime() == null){
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Date newDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                nurserecord.setNursingTime(newDate);
            }
            boolean save = nurseRecordService.save(nurserecord);
            if(save){
                return jsonReturn.returnSuccess();
            }else{
                return jsonReturn.returnFailed();
            }
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/update")
    public String update(@RequestBody Nurserecord nurserecord){
        try{
            boolean update = nurseRecordService.updateById(nurserecord);
            if(update){
                return jsonReturn.returnSuccess();
            }else{
                return jsonReturn.returnFailed();
            }
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public String delete(Integer id){
        try{
            boolean delete = nurseRecordService.deleteById(id);
            if(delete){
                return jsonReturn.returnSuccess();
            }else{
                return jsonReturn.returnFailed();
            }
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }



}
