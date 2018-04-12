package com.kepu.mapper;

import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingrentContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StBuildingrentContentMapper {
    int countByExample(StBuildingrentContentExample example);

    int deleteByExample(StBuildingrentContentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StBuildingrentContent record);

    int insertSelective(StBuildingrentContent record);

    List<StBuildingrentContent> selectByExample(StBuildingrentContentExample example);

    StBuildingrentContent selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StBuildingrentContent record, @Param("example") StBuildingrentContentExample example);

    int updateByExample(@Param("record") StBuildingrentContent record, @Param("example") StBuildingrentContentExample example);

    int updateByPrimaryKeySelective(StBuildingrentContent record);

    int updateByPrimaryKey(StBuildingrentContent record);
}