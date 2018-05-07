package com.kepu.mapper;

import com.kepu.pojo.StNewsTimetask;
import com.kepu.pojo.StNewsTimetaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsTimetaskMapper {
    int countByExample(StNewsTimetaskExample example);

    int deleteByExample(StNewsTimetaskExample example);

    int deleteByPrimaryKey(Integer newsid);

    int insert(StNewsTimetask record);

    int insertSelective(StNewsTimetask record);

    List<StNewsTimetask> selectByExample(StNewsTimetaskExample example);

    StNewsTimetask selectByPrimaryKey(Integer newsid);

    int updateByExampleSelective(@Param("record") StNewsTimetask record, @Param("example") StNewsTimetaskExample example);

    int updateByExample(@Param("record") StNewsTimetask record, @Param("example") StNewsTimetaskExample example);

    int updateByPrimaryKeySelective(StNewsTimetask record);

    int updateByPrimaryKey(StNewsTimetask record);
}