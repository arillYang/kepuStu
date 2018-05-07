package com.kepu.mapper;

import com.kepu.pojo.StCommonSearch;
import com.kepu.pojo.StCommonSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommonSearchMapper {
    int countByExample(StCommonSearchExample example);

    int deleteByExample(StCommonSearchExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommonSearch record);

    int insertSelective(StCommonSearch record);

    List<StCommonSearch> selectByExample(StCommonSearchExample example);

    StCommonSearch selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommonSearch record, @Param("example") StCommonSearchExample example);

    int updateByExample(@Param("record") StCommonSearch record, @Param("example") StCommonSearchExample example);

    int updateByPrimaryKeySelective(StCommonSearch record);

    int updateByPrimaryKey(StCommonSearch record);
}