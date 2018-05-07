package com.kepu.mapper;

import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StJobApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StJobApplyMapper {
    int countByExample(StJobApplyExample example);

    int deleteByExample(StJobApplyExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StJobApply record);

    int insertSelective(StJobApply record);

    List<StJobApply> selectByExample(StJobApplyExample example);

    StJobApply selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StJobApply record, @Param("example") StJobApplyExample example);

    int updateByExample(@Param("record") StJobApply record, @Param("example") StJobApplyExample example);

    int updateByPrimaryKeySelective(StJobApply record);

    int updateByPrimaryKey(StJobApply record);
}