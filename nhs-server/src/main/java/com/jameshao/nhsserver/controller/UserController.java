package com.jameshao.nhsserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.utils.FLAGS;
import com.jameshao.nhsserver.po.User;
import com.jameshao.nhsserver.service.UserService;
import com.jameshao.nhsserver.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JSONReturn jsonReturn;

    //登录验证
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        try {
            //查询信息
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, user.getUsername())
                    .eq(User::getPassword, user.getPassword()).select(User::getNickname, User::getUsername, User::getRoleId);
            List<User> users = userService.list(queryWrapper);

            if (users != null && users.size() > 0){//登录成功
                //生成Token
                String token = TokenUtil.createToken();
                User loginUser = users.get(0);
                loginUser.setToken(token);
                return jsonReturn.returnSuccess(loginUser);
            } else {//登陆失败
                return jsonReturn.returnFailed(FLAGS.LOGIN_FAIL);
            }
        } catch(Exception e){//程序错误，直接返回错误信息
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }


    //获取用户信息
    @RequestMapping("/getInfo")
    public String getInfo(User conds){
        try{
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            //ObjectUtils.isEmpty(conds.getUsername())
            queryWrapper.eq(!ObjectUtils.isEmpty(conds.getUsername()), User::getUsername, conds.getUsername())
                    .eq(!ObjectUtils.isEmpty(conds.getPassword()), User::getPassword, conds.getPassword())
                    .eq(!ObjectUtils.isEmpty(conds.getId()), User::getId, conds.getId())
                    .like(!ObjectUtils.isEmpty(conds.getNickname()), User::getNickname, conds.getNickname())
                    .eq(!ObjectUtils.isEmpty(conds.getPhoneNumber()), User::getPhoneNumber, conds.getPhoneNumber())
                    .eq(!ObjectUtils.isEmpty(conds.getSex()), User::getSex, conds.getSex())
                    .select(User::getId, User::getNickname, User::getUsername, User::getRoleId);
            List<User> users = userService.list(queryWrapper);
            return jsonReturn.returnSuccess(users);
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    //添加用户
    @RequestMapping("adduser")
    public String addOne(User user){
        try{
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            //ObjectUtils.isEmpty(conds.getUsername())
            queryWrapper.eq(User::getUsername, user.getUsername());
            List<User> users = userService.list(queryWrapper);

            if (users != null && users.size() > 0){
                return jsonReturn.returnFailed("添加失败：登录名重复！");
            }

            userService.save(user);
            return jsonReturn.returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    //更新用户
    @RequestMapping("modifyuser")
    public String modifyOne(User user){
        try{
            if (user == null || user.getId() == null){
                return jsonReturn.returnFailed("添加失败：未能获取用户！");
            }

            userService.updateById(user);
            return jsonReturn.returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }


    //删除用户
    @RequestMapping("removeuser")
    public String removeOne(Integer id){
        try{
            if (id == null){
                return jsonReturn.returnFailed("添加失败：未能获取用户信息！");
            }

            userService.removeById(id);
            return jsonReturn.returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }


    //退出登录
    @RequestMapping("/logout")
    public String logout() throws JsonProcessingException {

        return jsonReturn.returnSuccess();
    }
}
