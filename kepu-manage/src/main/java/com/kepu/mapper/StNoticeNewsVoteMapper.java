package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsVote;
import com.kepu.pojo.StNoticeNewsVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsVoteMapper {
    int countByExample(StNoticeNewsVoteExample example);

    int deleteByExample(StNoticeNewsVoteExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StNoticeNewsVote record);

    int insertSelective(StNoticeNewsVote record);

    List<StNoticeNewsVote> selectByExample(StNoticeNewsVoteExample example);

    StNoticeNewsVote selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StNoticeNewsVote record, @Param("example") StNoticeNewsVoteExample example);

    int updateByExample(@Param("record") StNoticeNewsVote record, @Param("example") StNoticeNewsVoteExample example);

    int updateByPrimaryKeySelective(StNoticeNewsVote record);

    int updateByPrimaryKey(StNoticeNewsVote record);
}