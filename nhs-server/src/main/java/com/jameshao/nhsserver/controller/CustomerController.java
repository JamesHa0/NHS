package com.jameshao.nhsserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jameshao.nhsserver.common.JSONReturn;
import com.jameshao.nhsserver.po.Customer;
import com.jameshao.nhsserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/listByUserId")
    public String getCustomersByUserId(Integer userId) {
        try{
            List<Customer> list = customerService.getCustomerByUserId(userId);
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

//    @RequestMapping("/listById")
//    public String getById(String customerIds){
//        try {
//            LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
//            List<Customer> list = null;
//            if (customerIds != null && !customerIds.isEmpty()) {
//
//                String[] customersArray = customerIds.split(",");
//                Integer[] customersId = new Integer[customersArray.length];
//
//                for(int i = 0; i < customersArray.length; i++) {
//                    customersId[i] = Integer.parseInt(customersArray[i]);
//                }
//
//                for (Integer customerId : customersId){
//                    queryWrapper.eq(Customer::getId, customerId)
//                            .eq(Customer::getIsDeleted, 0);
//                    list.add(customerService.getById(customerId));
//                }
//
//            }else {
//                queryWrapper.eq(Customer::getIsDeleted, 0);
//                list = customerService.list(queryWrapper);
//            }
//
//            return jsonReturn.returnSuccess(list);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return jsonReturn.returnError(e.getMessage());
//        }
//    }


    @RequestMapping("/update")
    public String update(@RequestBody Customer customer){
        try{
            boolean update = customerService.updateById(customer);
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

    // 添加护理人员
    @RequestMapping("/addUser")
    public String addUser(Integer userId, String customerIds){
        try{

            if (customerIds == null || userId == null) {
                return jsonReturn.returnError("Invalid parameters");
            }

            // 将逗号分隔的字符串转换为整数数组
            String[] itemsArray = customerIds.split(",");
            Integer[] itemsId = new Integer[itemsArray.length];
            for(int i = 0; i < itemsArray.length; i++) {
                itemsId[i] = Integer.parseInt(itemsArray[i]);
            }
            for (Integer itemId : itemsId){
                if (itemId == null){
                    continue;
                }
                Customer customer = new Customer();
                customer.setId(itemId);
                customer.setUserId(userId);
                boolean save = customerService.updateById(customer);
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

    // 删除护理人员
    @RequestMapping("/deleteUser")
    public String deleteUser(Integer customerId){
        try{
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setUserId(-1);
            boolean delete = customerService.updateById(customer);
            if (delete){
                return jsonReturn.returnSuccess();
            }else{
                return jsonReturn.returnFailed();
            }
        } catch (Exception e){
            e.printStackTrace();
            return jsonReturn.returnError(e.getMessage());
        }
    }

}
