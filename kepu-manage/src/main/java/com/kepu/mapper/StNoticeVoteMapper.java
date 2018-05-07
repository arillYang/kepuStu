package com.kepu.mapper;

import com.kepu.pojo.StNoticeVote;
import com.kepu.pojo.StNoticeVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeVoteMapper {
    int countByExample(StNoticeVoteExample example);

    int deleteByExample(StNoticeVoteExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StNoticeVote record);

    int insertSelective(StNoticeVote record);

    List<StNoticeVote> selectByExample(StNoticeVoteExample example);

    StNoticeVote selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StNoticeVote record, @Param("example") StNoticeVoteExample example);

    int updateByExample(@Param("record") StNoticeVote record, @Param("example") StNoticeVoteExample example);

    int updateByPrimaryKeySelective(StNoticeVote record);

    int updateByPrimaryKey(StNoticeVote record);
}