package com.jameshao.nhsserver.mapper;

import com.jameshao.nhsserver.po.Nursecontent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author JamesHao
* @description 针对表【nursecontent】的数据库操作Mapper
* @createDate 2025-04-15 11:26:40
* @Entity com.jameshao.nhsserver.po.Nursecontent
*/
public interface NursecontentMapper extends BaseMapper<Nursecontent> {

    @Select("SELECT nc.id, nc.serial_number, nc.nursing_name, nc.service_price, nc.message, nc.status, nc.execution_cycle, nc.execution_times FROM nursecontent nc " +
            "JOIN nurselevelitem nli ON nc.id = nli.item_id " +
            "WHERE nli.level_id = #{levelId} " +
            "AND nc.is_deleted = '0' "+
            "AND nli.is_deleted = '0' "+
            "ORDER BY serial_number")
    List<Nursecontent> getNurseContentsByLevelId(Integer levelId);


    @Select("SELECT * FROM nursecontent WHERE id = #{id} AND is_deleted = '0'")
    Nursecontent getNurseContentById(Integer id);
    boolean deletebyid(Integer id);
}




