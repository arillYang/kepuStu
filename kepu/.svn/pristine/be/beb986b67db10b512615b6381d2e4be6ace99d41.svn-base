package com.kepu.mapper;

import com.kepu.pojo.StVote;
import com.kepu.pojo.StVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVoteMapper {
    int countByExample(StVoteExample example);

    int deleteByExample(StVoteExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StVote record);

    int insertSelective(StVote record);

    List<StVote> selectByExample(StVoteExample example);

    StVote selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StVote record, @Param("example") StVoteExample example);

    int updateByExample(@Param("record") StVote record, @Param("example") StVoteExample example);

    int updateByPrimaryKeySelective(StVote record);

    int updateByPrimaryKey(StVote record);
}