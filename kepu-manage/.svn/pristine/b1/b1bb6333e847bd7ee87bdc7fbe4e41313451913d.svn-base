package com.kepu.mapper;

import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StLotteryMapper {
    int countByExample(StLotteryExample example);

    int deleteByExample(StLotteryExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StLottery record);

    int insertSelective(StLottery record);

    List<StLottery> selectByExample(StLotteryExample example);

    StLottery selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StLottery record, @Param("example") StLotteryExample example);

    int updateByExample(@Param("record") StLottery record, @Param("example") StLotteryExample example);

    int updateByPrimaryKeySelective(StLottery record);

    int updateByPrimaryKey(StLottery record);
}