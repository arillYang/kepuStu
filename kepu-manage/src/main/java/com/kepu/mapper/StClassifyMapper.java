package com.kepu.mapper;

import com.kepu.pojo.StClassify;
import com.kepu.pojo.StClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StClassifyMapper {
    int countByExample(StClassifyExample example);

    int deleteByExample(StClassifyExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StClassify record);

    int insertSelective(StClassify record);

    List<StClassify> selectByExample(StClassifyExample example);

    StClassify selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StClassify record, @Param("example") StClassifyExample example);

    int updateByExample(@Param("record") StClassify record, @Param("example") StClassifyExample example);

    int updateByPrimaryKeySelective(StClassify record);

    int updateByPrimaryKey(StClassify record);
}