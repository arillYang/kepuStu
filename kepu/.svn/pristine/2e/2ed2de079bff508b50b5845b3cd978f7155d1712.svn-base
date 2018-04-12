package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StLotteryJoin;
import com.kepu.pojo.StLotteryJoinExample;

public interface StLotteryJoinMapper {
    int countByExample(StLotteryJoinExample example);

    int deleteByExample(StLotteryJoinExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StLotteryJoin record);

    int insertSelective(StLotteryJoin record);

    List<StLotteryJoin> selectByExample(StLotteryJoinExample example);

    StLotteryJoin selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StLotteryJoin record, @Param("example") StLotteryJoinExample example);

    int updateByExample(@Param("record") StLotteryJoin record, @Param("example") StLotteryJoinExample example);

    int updateByPrimaryKeySelective(StLotteryJoin record);

    int updateByPrimaryKey(StLotteryJoin record);
    
    List<StLotteryJoin> getList(@Param("param") Map param);
}