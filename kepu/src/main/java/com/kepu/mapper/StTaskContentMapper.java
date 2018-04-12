package com.kepu.mapper;

import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StTaskContentExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StTaskContentMapper {
    int countByExample(StTaskContentExample example);

    int deleteByExample(StTaskContentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StTaskContent record);

    int insertSelective(StTaskContent record);

    List<StTaskContent> selectByExample(StTaskContentExample example);

    StTaskContent selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StTaskContent record, @Param("example") StTaskContentExample example);

    int updateByExample(@Param("record") StTaskContent record, @Param("example") StTaskContentExample example);

    int updateByPrimaryKeySelective(StTaskContent record);

    int updateByPrimaryKey(StTaskContent record);
    
    List<StTaskContent> selectOrderByDistance(@Param("param") Map<String,Object> param);
}