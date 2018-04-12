package com.kepu.mapper;

import com.kepu.pojo.StNewsRelation;
import com.kepu.pojo.StNewsRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsRelationMapper {
    int countByExample(StNewsRelationExample example);

    int deleteByExample(StNewsRelationExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StNewsRelation record);

    int insertSelective(StNewsRelation record);

    List<StNewsRelation> selectByExample(StNewsRelationExample example);

    StNewsRelation selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StNewsRelation record, @Param("example") StNewsRelationExample example);

    int updateByExample(@Param("record") StNewsRelation record, @Param("example") StNewsRelationExample example);

    int updateByPrimaryKeySelective(StNewsRelation record);

    int updateByPrimaryKey(StNewsRelation record);
}