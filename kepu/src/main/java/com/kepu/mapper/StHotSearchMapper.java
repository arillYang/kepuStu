package com.kepu.mapper;

import com.kepu.pojo.StHotSearch;
import com.kepu.pojo.StHotSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StHotSearchMapper {
    int countByExample(StHotSearchExample example);

    int deleteByExample(StHotSearchExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StHotSearch record);

    int insertSelective(StHotSearch record);

    List<StHotSearch> selectByExample(StHotSearchExample example);

    StHotSearch selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StHotSearch record, @Param("example") StHotSearchExample example);

    int updateByExample(@Param("record") StHotSearch record, @Param("example") StHotSearchExample example);

    int updateByPrimaryKeySelective(StHotSearch record);

    int updateByPrimaryKey(StHotSearch record);
}