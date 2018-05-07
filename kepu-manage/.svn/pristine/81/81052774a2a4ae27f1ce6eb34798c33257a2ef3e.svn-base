package com.kepu.mapper;

import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsContentMapper {
    int countByExample(StNewsContentExample example);

    int deleteByExample(StNewsContentExample example);

    int deleteByPrimaryKey(Integer newsid);

    int insert(StNewsContent record);

    int insertSelective(StNewsContent record);

    List<StNewsContent> selectByExampleWithBLOBs(StNewsContentExample example);

    List<StNewsContent> selectByExample(StNewsContentExample example);

    StNewsContent selectByPrimaryKey(Integer newsid);

    int updateByExampleSelective(@Param("record") StNewsContent record, @Param("example") StNewsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") StNewsContent record, @Param("example") StNewsContentExample example);

    int updateByExample(@Param("record") StNewsContent record, @Param("example") StNewsContentExample example);

    int updateByPrimaryKeySelective(StNewsContent record);

    int updateByPrimaryKeyWithBLOBs(StNewsContent record);

    int updateByPrimaryKey(StNewsContent record);
}