package com.kepu.mapper;

import com.kepu.pojo.TRelation;
import com.kepu.pojo.TRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRelationMapper {
    int countByExample(TRelationExample example);

    int deleteByExample(TRelationExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(TRelation record);

    int insertSelective(TRelation record);

    List<TRelation> selectByExample(TRelationExample example);

    TRelation selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") TRelation record, @Param("example") TRelationExample example);

    int updateByExample(@Param("record") TRelation record, @Param("example") TRelationExample example);

    int updateByPrimaryKeySelective(TRelation record);

    int updateByPrimaryKey(TRelation record);
}