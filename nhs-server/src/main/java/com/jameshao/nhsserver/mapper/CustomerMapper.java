package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【customer】的数据库操作Mapper
* @createDate 2025-04-09 09:22:36
* @Entity com.jameshao.nhsserver.po.Customer
*/
public interface CustomerMapper extends BaseMapper<Customer> {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "customerName", column = "customer_name"),
            @Result(property = "customerAge", column = "customer_age"),
            @Result(property = "customerSex", column = "customer_sex"),
            @Result(property = "roomNo", column = "room_no"),
            @Result(property = "buildingNo", column = "building_no"),
            @Result(property = "bedId", column = "bed_id"),
            @Result(property = "levelName", column = "level_id", one = @One(select = "com.jameshao.nhsserver.mapper.NurselevelMapper.getNameById"))
    })
    @Select("SELECT * FROM customer WHERE id = #{id} AND is_deleted = '0'")
    Customer getCustomerById(Integer id);

    @Select("<script>" +
            "SELECT c.*, u.nickname, n.level_name FROM customer c " +
            "LEFT JOIN user u ON c.user_id = u.id " +
            "LEFT JOIN nurselevel n ON c.level_id = n.id " +
            "<where>" +
            "<if test='customerName != null and customerName != \"\"'>" +
            "AND c.customer_name LIKE CONCAT('%', #{customerName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    List<Customer> getCustomerDetailsByName(String customerName);

    @Select("<script>" +
            "SELECT c.*, u.nickname, n.level_name FROM customer c " +
            "LEFT JOIN user u ON c.user_id = u.id " +
            "LEFT JOIN nurselevel n ON c.level_id = n.id " +
            "<where>" +
            "<if test='userId != null and userId != \"\" and userId != -2 '>" +
            "AND c.user_id = #{userId} " +
            "</if>" +
            "</where>" +
            "</script>"
    )
    List<Customer> getCustomerByUserId(Integer userId);
}




