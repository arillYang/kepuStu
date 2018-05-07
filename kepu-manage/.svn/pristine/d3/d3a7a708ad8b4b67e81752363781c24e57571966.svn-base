package com.kepu.mapper;

import com.kepu.pojo.StLog;
import com.kepu.pojo.StLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StLogMapper {
    int countByExample(StLogExample example);

    int deleteByExample(StLogExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StLog record);

    int insertSelective(StLog record);

    List<StLog> selectByExample(StLogExample example);

    StLog selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StLog record, @Param("example") StLogExample example);

    int updateByExample(@Param("record") StLog record, @Param("example") StLogExample example);

    int updateByPrimaryKeySelective(StLog record);

    int updateByPrimaryKey(StLog record);
}