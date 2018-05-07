package com.kepu.mapper;

import com.kepu.pojo.StCommunityUser;
import com.kepu.pojo.StCommunityUserExample;
import com.kepu.pojo.community.MemberList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StCommunityUserMapper {
    int countByExample(StCommunityUserExample example);

    int deleteByExample(StCommunityUserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityUser record);

    int insertSelective(StCommunityUser record);

    List<StCommunityUser> selectByExample(StCommunityUserExample example);

    StCommunityUser selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityUser record, @Param("example") StCommunityUserExample example);

    int updateByExample(@Param("record") StCommunityUser record, @Param("example") StCommunityUserExample example);

    int updateByPrimaryKeySelective(StCommunityUser record);

    int updateByPrimaryKey(StCommunityUser record);
    
    List<MemberList> getMemberList(@Param("record") Map record);
}