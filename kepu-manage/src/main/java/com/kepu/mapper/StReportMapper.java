package com.kepu.mapper;

import com.kepu.pojo.StReport;
import com.kepu.pojo.StReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StReportMapper {
    int countByExample(StReportExample example);

    int deleteByExample(StReportExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StReport record);

    int insertSelective(StReport record);

    List<StReport> selectByExample(StReportExample example);

    StReport selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StReport record, @Param("example") StReportExample example);

    int updateByExample(@Param("record") StReport record, @Param("example") StReportExample example);

    int updateByPrimaryKeySelective(StReport record);

    int updateByPrimaryKey(StReport record);
}