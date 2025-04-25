package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Nurserecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurserecord】的数据库操作Mapper
* @createDate 2025-04-15 11:26:40
* @Entity com.jameshao.nhsserver.po.Nurserecord
*/
public interface NurserecordMapper extends BaseMapper<Nurserecord> {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "nursingTime", column = "nursing_time"),
            @Result(property = "nursingContent", column = "nursing_content"),
            @Result(property = "nursingCount", column = "nursing_count"),
            @Result(property = "nurseContentInfo", column = "item_id", many = @Many(select = "com.jameshao.nhsserver.mapper.NursecontentMapper.getNurseContentById")),
            @Result(property = "customerInfo", column = "customer_id", many = @Many(select = "com.jameshao.nhsserver.mapper.CustomerMapper.getCustomerById")),
            @Result(property = "userInfo", column = "user_id", many = @Many(select = "com.jameshao.nhsserver.mapper.UserMapper.getUserById"))
    })
    @Select("SELECT * FROM nurserecord " +
            "WHERE is_deleted = '0' " +
            "AND (customer_id = #{customerId} OR #{customerId} IS NULL) " +
            "ORDER BY nursing_time DESC")
    List<Nurserecord> getByCustomerId(Integer customerId);

    boolean deleteById(Integer id);
}




