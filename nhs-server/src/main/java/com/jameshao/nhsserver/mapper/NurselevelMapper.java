package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Nurselevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nurselevel】的数据库操作Mapper
* @createDate 2025-04-15 11:26:39
* @Entity com.jameshao.nhsserver.po.Nurselevel
*/
public interface NurselevelMapper extends BaseMapper<Nurselevel> {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "levelName", column = "level_name"),
            @Result(property = "levelStatus", column = "level_status"),
            @Result(property = "nurseContents", column = "id", many = @Many(select = "com.jameshao.nhsserver.mapper.NursecontentMapper.getNurseContentsByLevelId"))
    })
    @Select("SELECT * FROM nurselevel WHERE is_deleted = '0' " +
            "AND (id = #{id} OR #{id} IS NULL) " +
            "AND (level_name LIKE CONCAT('%', #{levelName}, '%') OR #{levelName} IS NULL) ")
    List<Nurselevel> getLevelsWithItems(Integer id, String levelName);

    boolean deletebyid(Integer id);

    @Select("SELECT level_name FROM nurselevel WHERE id = #{id}")
    String getNameById(Integer id);
}




