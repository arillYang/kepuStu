package com.kepu.mapper;

import com.kepu.pojo.StCommonVote;
import com.kepu.pojo.StCommonVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommonVoteMapper {
    int countByExample(StCommonVoteExample example);

    int deleteByExample(StCommonVoteExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StCommonVote record);

    int insertSelective(StCommonVote record);

    List<StCommonVote> selectByExample(StCommonVoteExample example);

    StCommonVote selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StCommonVote record, @Param("example") StCommonVoteExample example);

    int updateByExample(@Param("record") StCommonVote record, @Param("example") StCommonVoteExample example);

    int updateByPrimaryKeySelective(StCommonVote record);

    int updateByPrimaryKey(StCommonVote record);
}