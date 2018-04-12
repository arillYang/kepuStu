package com.kepu.mapper;

import com.kepu.pojo.StCommunityTeachDetail;
import com.kepu.pojo.StCommunityTeachDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityTeachDetailMapper {
    int countByExample(StCommunityTeachDetailExample example);

    int deleteByExample(StCommunityTeachDetailExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityTeachDetail record);

    int insertSelective(StCommunityTeachDetail record);

    List<StCommunityTeachDetail> selectByExample(StCommunityTeachDetailExample example);

    StCommunityTeachDetail selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityTeachDetail record, @Param("example") StCommunityTeachDetailExample example);

    int updateByExample(@Param("record") StCommunityTeachDetail record, @Param("example") StCommunityTeachDetailExample example);

    int updateByPrimaryKeySelective(StCommunityTeachDetail record);

    int updateByPrimaryKey(StCommunityTeachDetail record);
}