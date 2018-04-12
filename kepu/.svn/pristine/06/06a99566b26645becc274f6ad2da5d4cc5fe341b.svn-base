package com.kepu.mapper;

import com.kepu.pojo.StArticleVote;
import com.kepu.pojo.StArticleVoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StArticleVoteMapper {
    int countByExample(StArticleVoteExample example);

    int deleteByExample(StArticleVoteExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StArticleVote record);

    int insertSelective(StArticleVote record);

    List<StArticleVote> selectByExample(StArticleVoteExample example);

    StArticleVote selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StArticleVote record, @Param("example") StArticleVoteExample example);

    int updateByExample(@Param("record") StArticleVote record, @Param("example") StArticleVoteExample example);

    int updateByPrimaryKeySelective(StArticleVote record);

    int updateByPrimaryKey(StArticleVote record);
}