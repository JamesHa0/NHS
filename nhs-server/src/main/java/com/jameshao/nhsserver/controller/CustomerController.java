package com.jameshao.nhsserver.controller;


import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Customer;
import com.jameshao.nhsserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JSONReturn jsonReturn;

    @RequestMapping("/get_all_customers")
    public String getAllCustomers() {
        try {
            List<Customer> list = customerService.list();
            return jsonReturn.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

    @RequestMapping("/get_by_id")
    public String getById(@RequestParam("customerid") Integer customerid){
        try {
            Customer customer = customerService.getById(customerid);
            return jsonReturn.returnSuccess(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

}
