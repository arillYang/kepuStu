package com.kepu.mapper;

import com.kepu.pojo.StCommunityTeach;
import com.kepu.pojo.StCommunityTeachExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityTeachMapper {
    int countByExample(StCommunityTeachExample example);

    int deleteByExample(StCommunityTeachExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityTeach record);

    int insertSelective(StCommunityTeach record);

    List<StCommunityTeach> selectByExample(StCommunityTeachExample example);

    StCommunityTeach selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityTeach record, @Param("example") StCommunityTeachExample example);

    int updateByExample(@Param("record") StCommunityTeach record, @Param("example") StCommunityTeachExample example);

    int updateByPrimaryKeySelective(StCommunityTeach record);

    int updateByPrimaryKey(StCommunityTeach record);
}