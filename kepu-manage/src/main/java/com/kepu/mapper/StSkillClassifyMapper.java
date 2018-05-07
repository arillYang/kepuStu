package com.kepu.mapper;

import com.kepu.pojo.StSkillClassify;
import com.kepu.pojo.StSkillClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StSkillClassifyMapper {
    int countByExample(StSkillClassifyExample example);

    int deleteByExample(StSkillClassifyExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StSkillClassify record);

    int insertSelective(StSkillClassify record);

    List<StSkillClassify> selectByExample(StSkillClassifyExample example);

    StSkillClassify selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StSkillClassify record, @Param("example") StSkillClassifyExample example);

    int updateByExample(@Param("record") StSkillClassify record, @Param("example") StSkillClassifyExample example);

    int updateByPrimaryKeySelective(StSkillClassify record);

    int updateByPrimaryKey(StSkillClassify record);
}