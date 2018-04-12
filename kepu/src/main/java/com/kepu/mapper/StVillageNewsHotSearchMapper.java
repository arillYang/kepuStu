package com.kepu.mapper;

import com.kepu.pojo.StVillageNewsHotSearch;
import com.kepu.pojo.StVillageNewsHotSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVillageNewsHotSearchMapper {
    int countByExample(StVillageNewsHotSearchExample example);

    int deleteByExample(StVillageNewsHotSearchExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StVillageNewsHotSearch record);

    int insertSelective(StVillageNewsHotSearch record);

    List<StVillageNewsHotSearch> selectByExample(StVillageNewsHotSearchExample example);

    StVillageNewsHotSearch selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StVillageNewsHotSearch record, @Param("example") StVillageNewsHotSearchExample example);

    int updateByExample(@Param("record") StVillageNewsHotSearch record, @Param("example") StVillageNewsHotSearchExample example);

    int updateByPrimaryKeySelective(StVillageNewsHotSearch record);

    int updateByPrimaryKey(StVillageNewsHotSearch record);
}