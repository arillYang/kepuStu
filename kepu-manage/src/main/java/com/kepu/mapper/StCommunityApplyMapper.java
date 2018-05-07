package com.kepu.mapper;

import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StCommunityApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityApplyMapper {
    int countByExample(StCommunityApplyExample example);

    int deleteByExample(StCommunityApplyExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityApply record);

    int insertSelective(StCommunityApply record);

    List<StCommunityApply> selectByExample(StCommunityApplyExample example);

    StCommunityApply selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityApply record, @Param("example") StCommunityApplyExample example);

    int updateByExample(@Param("record") StCommunityApply record, @Param("example") StCommunityApplyExample example);

    int updateByPrimaryKeySelective(StCommunityApply record);

    int updateByPrimaryKey(StCommunityApply record);
}