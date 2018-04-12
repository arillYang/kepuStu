package com.kepu.mapper;

import com.kepu.pojo.StCommunityHotSearch;
import com.kepu.pojo.StCommunityHotSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityHotSearchMapper {
    int countByExample(StCommunityHotSearchExample example);

    int deleteByExample(StCommunityHotSearchExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityHotSearch record);

    int insertSelective(StCommunityHotSearch record);

    List<StCommunityHotSearch> selectByExample(StCommunityHotSearchExample example);

    StCommunityHotSearch selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityHotSearch record, @Param("example") StCommunityHotSearchExample example);

    int updateByExample(@Param("record") StCommunityHotSearch record, @Param("example") StCommunityHotSearchExample example);

    int updateByPrimaryKeySelective(StCommunityHotSearch record);

    int updateByPrimaryKey(StCommunityHotSearch record);
}