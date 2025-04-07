package com.jameshao.nhsserver.controller;

import com.jameshao.nhsserver.common.JSONReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.neudu.yyzx_service.utils.FLAGS.RETURN_SUCCESS;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private JSONReturn jsonReturn;

    //登录验证
    @RequestMapping("/login")
    public String login(String username,String password){

        return RETURN_SUCCESS;
    }


    //获取用户信息
    @RequestMapping("/getInfo")
    public String getInfo(){

        return RETURN_SUCCESS;
    }


    //退出登录
    @RequestMapping("/logout")
    public String logout(){

        return RETURN_SUCCESS;
    }
}
