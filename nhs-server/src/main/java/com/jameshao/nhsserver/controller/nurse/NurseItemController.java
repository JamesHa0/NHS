package com.jameshao.nhsserver.controller.nurse;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Nursecontent;
import com.jameshao.nhsserver.service.NursecontentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurse/nurseItem")
public class NurseItemController {

    @Autowired
    private NursecontentService nurseContentService;
    @Autowired
    private JSONReturn jsonReturn;

    @RequestMapping("/list")
            public String list(String nursingName){
                try{
                    LambdaQueryWrapper<Nursecontent> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.like(!ObjectUtils.isEmpty(nursingName),Nursecontent::getNursingName, nursingName)
                            .eq(Nursecontent::getIsDeleted, 0)
                            .select(Nursecontent::getId, Nursecontent::getSerialNumber, Nursecontent::getNursingName, Nursecontent::getServicePrice, Nursecontent::getMessage, Nursecontent::getStatus, Nursecontent::getExecutionCycle, Nursecontent::getExecutionTimes)
                            .orderByAsc(Nursecontent::getSerialNumber);
            List<Nursecontent> nursecontentList = nurseContentService.list(queryWrapper);
            return jsonReturn.returnSuccess(nursecontentList);
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/add")
    public String add(@RequestBody Nursecontent nursecontent){
        try{
            boolean save = nurseContentService.save(nursecontent);
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
    public String update(@RequestBody Nursecontent nursecontent){
        try{
            boolean update = nurseContentService.updateById(nursecontent);
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
            boolean delete = nurseContentService.deleteById(id);
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
