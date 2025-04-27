package com.jameshao.nhsserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Customer;
import com.jameshao.nhsserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JSONReturn jsonReturn;

    @RequestMapping("/list")
    public String getCustomers(String customerName) {
        try{
            LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(!ObjectUtils.isEmpty(customerName),Customer::getCustomerName, customerName)
                    .eq(Customer::getIsDeleted, 0);
            List<Customer> list = customerService.list(queryWrapper);
            return jsonReturn.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/detail")
    public String getCustomerDetailsByName(String customerName) {
        try{
            List<Customer> list = customerService.getCustomerDetailsByName(customerName);
            return jsonReturn.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/listById")
    public String getById(String customerIds){
        try {
            LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
            List<Customer> list = null;
            if (customerIds != null && !customerIds.isEmpty()) {

                String[] customersArray = customerIds.split(",");
                Integer[] customersId = new Integer[customersArray.length];

                for(int i = 0; i < customersArray.length; i++) {
                    customersId[i] = Integer.parseInt(customersArray[i]);
                }

                for (Integer customerId : customersId){
                    queryWrapper.eq(Customer::getId, customerId)
                            .eq(Customer::getIsDeleted, 0);
                    list.add(customerService.getById(customerId));
                }

            }else {
                queryWrapper.eq(Customer::getIsDeleted, 0);
                list = customerService.list(queryWrapper);
            }

            return jsonReturn.returnSuccess(list);

        } catch (Exception e) {
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

}
