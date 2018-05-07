package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsContent;
import com.kepu.pojo.StNoticeNewsContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsContentMapper {
    int countByExample(StNoticeNewsContentExample example);

    int deleteByExample(StNoticeNewsContentExample example);

    int deleteByPrimaryKey(Integer newsid);

    int insert(StNoticeNewsContent record);

    int insertSelective(StNoticeNewsContent record);

    List<StNoticeNewsContent> selectByExampleWithBLOBs(StNoticeNewsContentExample example);

    List<StNoticeNewsContent> selectByExample(StNoticeNewsContentExample example);

    StNoticeNewsContent selectByPrimaryKey(Integer newsid);

    int updateByExampleSelective(@Param("record") StNoticeNewsContent record, @Param("example") StNoticeNewsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") StNoticeNewsContent record, @Param("example") StNoticeNewsContentExample example);

    int updateByExample(@Param("record") StNoticeNewsContent record, @Param("example") StNoticeNewsContentExample example);

    int updateByPrimaryKeySelective(StNoticeNewsContent record);

    int updateByPrimaryKeyWithBLOBs(StNoticeNewsContent record);

    int updateByPrimaryKey(StNoticeNewsContent record);
}