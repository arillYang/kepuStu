package com.kepu.mapper;

import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StCommunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityMapper {
    int countByExample(StCommunityExample example);

    int deleteByExample(StCommunityExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunity record);

    int insertSelective(StCommunity record);

    List<StCommunity> selectByExample(StCommunityExample example);

    StCommunity selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunity record, @Param("example") StCommunityExample example);

    int updateByExample(@Param("record") StCommunity record, @Param("example") StCommunityExample example);

    int updateByPrimaryKeySelective(StCommunity record);

    int updateByPrimaryKey(StCommunity record);
}