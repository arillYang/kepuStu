package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsReport;
import com.kepu.pojo.StNoticeNewsReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsReportMapper {
    int countByExample(StNoticeNewsReportExample example);

    int deleteByExample(StNoticeNewsReportExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StNoticeNewsReport record);

    int insertSelective(StNoticeNewsReport record);

    List<StNoticeNewsReport> selectByExample(StNoticeNewsReportExample example);

    StNoticeNewsReport selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StNoticeNewsReport record, @Param("example") StNoticeNewsReportExample example);

    int updateByExample(@Param("record") StNoticeNewsReport record, @Param("example") StNoticeNewsReportExample example);

    int updateByPrimaryKeySelective(StNoticeNewsReport record);

    int updateByPrimaryKey(StNoticeNewsReport record);
}