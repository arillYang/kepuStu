package com.kepu.mapper;

import com.kepu.pojo.StAdvice;
import com.kepu.pojo.StAdviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StAdviceMapper {
    int countByExample(StAdviceExample example);

    int deleteByExample(StAdviceExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StAdvice record);

    int insertSelective(StAdvice record);

    List<StAdvice> selectByExample(StAdviceExample example);

    StAdvice selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StAdvice record, @Param("example") StAdviceExample example);

    int updateByExample(@Param("record") StAdvice record, @Param("example") StAdviceExample example);

    int updateByPrimaryKeySelective(StAdvice record);

    int updateByPrimaryKey(StAdvice record);
}