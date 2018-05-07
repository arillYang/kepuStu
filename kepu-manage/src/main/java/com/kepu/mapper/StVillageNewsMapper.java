package com.kepu.mapper;

import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StVillageNewsMapper {
    int countByExample(StVillageNewsExample example);

    int deleteByExample(StVillageNewsExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StVillageNews record);

    int insertSelective(StVillageNews record);

    List<StVillageNews> selectByExampleWithBLOBs(StVillageNewsExample example);

    List<StVillageNews> selectByExample(StVillageNewsExample example);

    StVillageNews selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StVillageNews record, @Param("example") StVillageNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") StVillageNews record, @Param("example") StVillageNewsExample example);

    int updateByExample(@Param("record") StVillageNews record, @Param("example") StVillageNewsExample example);

    int updateByPrimaryKeySelective(StVillageNews record);

    int updateByPrimaryKeyWithBLOBs(StVillageNews record);

    int updateByPrimaryKey(StVillageNews record);
    
    Integer getTotalViewNumber(@Param("param") Map param);
}