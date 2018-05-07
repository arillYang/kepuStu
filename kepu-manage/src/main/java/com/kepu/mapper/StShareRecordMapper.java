package com.kepu.mapper;

import com.kepu.pojo.StShareRecord;
import com.kepu.pojo.StShareRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StShareRecordMapper {
    int countByExample(StShareRecordExample example);

    int deleteByExample(StShareRecordExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StShareRecord record);

    int insertSelective(StShareRecord record);

    List<StShareRecord> selectByExample(StShareRecordExample example);

    StShareRecord selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StShareRecord record, @Param("example") StShareRecordExample example);

    int updateByExample(@Param("record") StShareRecord record, @Param("example") StShareRecordExample example);

    int updateByPrimaryKeySelective(StShareRecord record);

    int updateByPrimaryKey(StShareRecord record);
}