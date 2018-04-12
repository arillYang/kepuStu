package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsHotSearch;
import com.kepu.pojo.StNoticeNewsHotSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsHotSearchMapper {
    int countByExample(StNoticeNewsHotSearchExample example);

    int deleteByExample(StNoticeNewsHotSearchExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StNoticeNewsHotSearch record);

    int insertSelective(StNoticeNewsHotSearch record);

    List<StNoticeNewsHotSearch> selectByExample(StNoticeNewsHotSearchExample example);

    StNoticeNewsHotSearch selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StNoticeNewsHotSearch record, @Param("example") StNoticeNewsHotSearchExample example);

    int updateByExample(@Param("record") StNoticeNewsHotSearch record, @Param("example") StNoticeNewsHotSearchExample example);

    int updateByPrimaryKeySelective(StNoticeNewsHotSearch record);

    int updateByPrimaryKey(StNoticeNewsHotSearch record);
}