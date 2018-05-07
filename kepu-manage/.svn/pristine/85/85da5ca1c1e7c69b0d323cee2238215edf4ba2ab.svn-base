package com.kepu.mapper;

import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.news.VillageResult;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StVillageMapper {
    int countByExample(StVillageExample example);

    int deleteByExample(StVillageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StVillage record);

    int insertSelective(StVillage record);

    List<StVillage> selectByExample(StVillageExample example);

    StVillage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StVillage record, @Param("example") StVillageExample example);

    int updateByExample(@Param("record") StVillage record, @Param("example") StVillageExample example);

    int updateByPrimaryKeySelective(StVillage record);

    int updateByPrimaryKey(StVillage record);
    
    List<VillageResult> getAddressName();
}