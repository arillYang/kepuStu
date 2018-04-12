package com.kepu.mapper;

import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StBuildingsellContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StBuildingsellContentMapper {
    int countByExample(StBuildingsellContentExample example);

    int deleteByExample(StBuildingsellContentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StBuildingsellContent record);

    int insertSelective(StBuildingsellContent record);

    List<StBuildingsellContent> selectByExample(StBuildingsellContentExample example);

    StBuildingsellContent selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StBuildingsellContent record, @Param("example") StBuildingsellContentExample example);

    int updateByExample(@Param("record") StBuildingsellContent record, @Param("example") StBuildingsellContentExample example);

    int updateByPrimaryKeySelective(StBuildingsellContent record);

    int updateByPrimaryKey(StBuildingsellContent record);
}