package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillContentExample;

public interface StSkillContentMapper {
    int countByExample(StSkillContentExample example);

    int deleteByExample(StSkillContentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StSkillContent record);

    int insertSelective(StSkillContent record);

    List<StSkillContent> selectByExample(StSkillContentExample example);

    StSkillContent selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StSkillContent record, @Param("example") StSkillContentExample example);

    int updateByExample(@Param("record") StSkillContent record, @Param("example") StSkillContentExample example);

    int updateByPrimaryKeySelective(StSkillContent record);

    int updateByPrimaryKey(StSkillContent record);
    
    List<StSkillContent> selectOrderByDistance(@Param("param") Map<String,Object> param);
}