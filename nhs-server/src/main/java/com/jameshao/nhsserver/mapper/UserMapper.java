package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
* @author JamesHao
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-04-07 16:25:37
* @Entity com.jameshao.nhsserver.po.User
*/
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE id = #{id} AND is_deleted = '0'")
    User getUserById(Integer id);

    @Select("SELECT nickname FROM user WHERE id = #{id}")
    String getNameById(Integer id);

}




