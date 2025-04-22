package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author JamesHao
* @description 针对表【customer】的数据库操作Mapper
* @createDate 2025-04-09 09:22:36
* @Entity com.jameshao.nhsserver.po.Customer
*/
public interface CustomerMapper extends BaseMapper<Customer> {
    @Select("SELECT * FROM customer WHERE id = #{id} AND is_deleted = '0'")
    Customer getCustomerById(Integer id);
}




