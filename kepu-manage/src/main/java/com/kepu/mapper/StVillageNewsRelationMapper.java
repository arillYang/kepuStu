package com.kepu.mapper;

import com.kepu.pojo.StVillageNewsRelation;
import com.kepu.pojo.StVillageNewsRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVillageNewsRelationMapper {
    int countByExample(StVillageNewsRelationExample example);

    int deleteByExample(StVillageNewsRelationExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StVillageNewsRelation record);

    int insertSelective(StVillageNewsRelation record);

    List<StVillageNewsRelation> selectByExample(StVillageNewsRelationExample example);

    StVillageNewsRelation selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StVillageNewsRelation record, @Param("example") StVillageNewsRelationExample example);

    int updateByExample(@Param("record") StVillageNewsRelation record, @Param("example") StVillageNewsRelationExample example);

    int updateByPrimaryKeySelective(StVillageNewsRelation record);

    int updateByPrimaryKey(StVillageNewsRelation record);
}