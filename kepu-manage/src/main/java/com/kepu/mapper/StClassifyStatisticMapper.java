package com.kepu.mapper;

import com.kepu.pojo.StClassifyStatistic;
import com.kepu.pojo.StClassifyStatisticExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StClassifyStatisticMapper {
    int countByExample(StClassifyStatisticExample example);

    int deleteByExample(StClassifyStatisticExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StClassifyStatistic record);

    int insertSelective(StClassifyStatistic record);

    List<StClassifyStatistic> selectByExample(StClassifyStatisticExample example);

    StClassifyStatistic selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StClassifyStatistic record, @Param("example") StClassifyStatisticExample example);

    int updateByExample(@Param("record") StClassifyStatistic record, @Param("example") StClassifyStatisticExample example);

    int updateByPrimaryKeySelective(StClassifyStatistic record);

    int updateByPrimaryKey(StClassifyStatistic record);
    
    int updateLastWeekState();
}