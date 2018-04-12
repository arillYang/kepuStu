package com.kepu.mapper;

import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsMapper {
    int countByExample(StNoticeNewsExample example);

    int deleteByExample(StNoticeNewsExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StNoticeNews record);

    int insertSelective(StNoticeNews record);

    List<StNoticeNews> selectByExampleWithBLOBs(StNoticeNewsExample example);

    List<StNoticeNews> selectByExample(StNoticeNewsExample example);

    StNoticeNews selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StNoticeNews record, @Param("example") StNoticeNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") StNoticeNews record, @Param("example") StNoticeNewsExample example);

    int updateByExample(@Param("record") StNoticeNews record, @Param("example") StNoticeNewsExample example);

    int updateByPrimaryKeySelective(StNoticeNews record);

    int updateByPrimaryKeyWithBLOBs(StNoticeNews record);

    int updateByPrimaryKey(StNoticeNews record);
}