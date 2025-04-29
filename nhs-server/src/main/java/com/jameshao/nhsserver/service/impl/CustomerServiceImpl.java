package com.jameshao.nhsserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jameshao.nhsserver.po.Customer;
import com.jameshao.nhsserver.service.CustomerService;
import com.jameshao.nhsserver.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【customer】的数据库操作Service实现
* @createDate 2025-04-09 09:22:36
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> getCustomerDetailsByName(String customerName) {
        return customerMapper.getCustomerDetailsByName(customerName);
    }

    @Override
    public List<Customer> getCustomerByUserId(Integer userId) {
            return customerMapper.getCustomerByUserId(userId);
    }
}




