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

import java.util.List;

@RestController
@RequestMapping("/nurse/nurseLevel")
public class NurseLevelController {

    @Autowired
    private NurselevelService nurseLevelService;
    @Autowired
    private NurselevelitemService nurseLevelItemService;
    @Autowired
    private JSONReturn jsonReturn;

    @RequestMapping("/list")
    public String list(Integer id){
        try{
            LambdaQueryWrapper<Nurselevel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(!ObjectUtils.isEmpty(id), Nurselevel::getId, id)
                    .eq(Nurselevel::getIsDeleted, 0);
            return jsonReturn.returnSuccess(nurseLevelService.list(queryWrapper));
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/getNameById")
    public String getNameById(Integer id){
        try{
            return jsonReturn.returnSuccess(nurseLevelService.getNameById(id));
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
                Nurselevelitem nurselevelitem = new Nurselevelitem();
                nurselevelitem.setLevelId(levelId);
                nurselevelitem.setItemId(itemId);
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


    @RequestMapping("/deleteLevelItem")
    public String deleteLevelItem(@RequestBody Nurselevelitem nurselevelitem){
        try{
            boolean delete = nurseLevelItemService.deleteByIds(nurselevelitem.getLevelId(),nurselevelitem.getItemId());
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
