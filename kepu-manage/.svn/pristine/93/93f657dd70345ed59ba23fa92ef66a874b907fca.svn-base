package com.kepu.mapper;

import com.kepu.pojo.StLotteryVote;
import com.kepu.pojo.StLotteryVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StLotteryVoteMapper {
    int countByExample(StLotteryVoteExample example);

    int deleteByExample(StLotteryVoteExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StLotteryVote record);

    int insertSelective(StLotteryVote record);

    List<StLotteryVote> selectByExample(StLotteryVoteExample example);

    StLotteryVote selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StLotteryVote record, @Param("example") StLotteryVoteExample example);

    int updateByExample(@Param("record") StLotteryVote record, @Param("example") StLotteryVoteExample example);

    int updateByPrimaryKeySelective(StLotteryVote record);

    int updateByPrimaryKey(StLotteryVote record);
}