package com.jameshao.nhsserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    JSONReturn jsonReturn;

    @Autowired
    MenuService menuService;
    //获取菜单：当前项目是角色决定菜单，所以查询条件是角色id
    @RequestMapping("/getRouters")
    public String getMenusByRoleId(Integer roleid){
        System.out.println("roleid:"+roleid);
        try {
            return jsonReturn.returnSuccess(menuService.getByroleid(roleid));
        } catch (Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }
}
