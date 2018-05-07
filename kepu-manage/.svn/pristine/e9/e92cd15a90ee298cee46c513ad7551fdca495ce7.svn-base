package com.kepu.mapper;

import com.kepu.pojo.StNewsVote;
import com.kepu.pojo.StNewsVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsVoteMapper {
    int countByExample(StNewsVoteExample example);

    int deleteByExample(StNewsVoteExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StNewsVote record);

    int insertSelective(StNewsVote record);

    List<StNewsVote> selectByExample(StNewsVoteExample example);

    StNewsVote selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StNewsVote record, @Param("example") StNewsVoteExample example);

    int updateByExample(@Param("record") StNewsVote record, @Param("example") StNewsVoteExample example);

    int updateByPrimaryKeySelective(StNewsVote record);

    int updateByPrimaryKey(StNewsVote record);
}