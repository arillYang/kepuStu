package com.kepu.mapper;

import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StJobMapper {
    int countByExample(StJobExample example);

    int deleteByExample(StJobExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StJob record);

    int insertSelective(StJob record);

    List<StJob> selectByExample(StJobExample example);

    StJob selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StJob record, @Param("example") StJobExample example);

    int updateByExample(@Param("record") StJob record, @Param("example") StJobExample example);

    int updateByPrimaryKeySelective(StJob record);

    int updateByPrimaryKey(StJob record);
}