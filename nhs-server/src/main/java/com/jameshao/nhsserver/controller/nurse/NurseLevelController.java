package com.jameshao.nhsserver.controller.nurse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Nurselevel;
import com.jameshao.nhsserver.po.Nurselevelitem;
import com.jameshao.nhsserver.service.NurselevelService;
import com.jameshao.nhsserver.service.NurselevelitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/nurse/nurseLevel")
public class NurseLevelController {

    @Autowired
    private NurselevelService nurseLevelService;
    @Autowired
    NurselevelitemService nurseLevelItemService;
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


    @RequestMapping("/items")
    public String getLevelsWithItems(Integer id,String levelName) {
        try{
            if (id != null && levelName != null){
                levelName = null;
            }
            List<Nurselevel> list =  nurseLevelService.getLevelsWithItems(id, levelName);
            return jsonReturn.returnSuccess(list);
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

    @RequestMapping("/addItems")
    public String addItems(Integer levelId, String nurseItemIds){
        try{

            if (nurseItemIds == null || levelId == null) {
                return jsonReturn.returnError("Invalid parameters");
            }

            // 将逗号分隔的字符串转换为整数数组
            String[] itemsArray = nurseItemIds.split(",");
            Integer[] itemsId = new Integer[itemsArray.length];
            for(int i = 0; i < itemsArray.length; i++) {
                itemsId[i] = Integer.parseInt(itemsArray[i]);
            }
            for (Integer itemId : itemsId){
                if (itemId == null){
                    continue;
                }
                System.out.println(levelId);
                System.out.println(itemId);
                Nurselevelitem nurselevelitem = new Nurselevelitem();
                nurselevelitem.setLevelId(levelId);
                nurselevelitem.setItemId(itemId);
                System.out.println(nurselevelitem);
                boolean save = nurseLevelItemService.save(nurselevelitem);
                if (!save){
                    return jsonReturn.returnFailed();
                }
            }
            return jsonReturn.returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }


}
