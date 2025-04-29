package com.jameshao.nhsserver.service;

import com.jameshao.nhsserver.po.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【customer】的数据库操作Service
* @createDate 2025-04-09 09:22:36
*/
public interface CustomerService extends IService<Customer> {

    List<Customer> getCustomerDetailsByName(String customerName);

    List<Customer> getCustomerByUserId(Integer userId);
}
