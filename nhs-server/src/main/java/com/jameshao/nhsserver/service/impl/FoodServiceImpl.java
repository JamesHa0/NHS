package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Food;
import com.jameshao.nhsserver.service.FoodService;
import com.jameshao.nhsserver.mapper.FoodMapper;
import org.springframework.stereotype.Service;

/**
* @author JamesHao
* @description 针对表【food】的数据库操作Service实现
* @createDate 2025-04-15 11:26:39
*/
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food>
    implements FoodService{

}




