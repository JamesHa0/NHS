package com.jameshao.nhsserver.controller.nurse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Nurselevel;
import com.jameshao.nhsserver.service.NurselevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nurse/nurseLevel")
public class NurseLevelController {

    @Autowired
    private NurselevelService nurseLevelService;
    @Autowired
    private JSONReturn jsonReturn;

    @RequestMapping("/list")
    public String list(String levelName){
        try{
            LambdaQueryWrapper<Nurselevel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(!ObjectUtils.isEmpty(levelName), Nurselevel::getLevelName, levelName)
                    .eq(Nurselevel::getIsDeleted, 0)
                    .select(Nurselevel::getId, Nurselevel::getLevelName, Nurselevel::getLevelStatus);
            List<Nurselevel> nurseLevelList = nurseLevelService.list(queryWrapper);
            return jsonReturn.returnSuccess(nurseLevelList);
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/add")
    public String add(@RequestBody Nurselevel nurselevel){
        try{
            boolean save = nurseLevelService.save(nurselevel);
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
    public String update(@RequestBody Nurselevel nurselevel){
        try{
            boolean update = nurseLevelService.updateById(nurselevel);
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
            boolean delete = nurseLevelService.deleteById(id);
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
