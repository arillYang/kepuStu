package com.kepu.mapper;

import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsMapper {
    int countByExample(StNewsExample example);

    int deleteByExample(StNewsExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StNews record);

    int insertSelective(StNews record);

    List<StNews> selectByExampleWithBLOBs(StNewsExample example);

    List<StNews> selectByExample(StNewsExample example);

    StNews selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StNews record, @Param("example") StNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") StNews record, @Param("example") StNewsExample example);

    int updateByExample(@Param("record") StNews record, @Param("example") StNewsExample example);

    int updateByPrimaryKeySelective(StNews record);

    int updateByPrimaryKeyWithBLOBs(StNews record);

    int updateByPrimaryKey(StNews record);
    
    Integer getTotalViewNumber();
}