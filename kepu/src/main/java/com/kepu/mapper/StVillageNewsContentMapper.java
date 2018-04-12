package com.kepu.mapper;

import com.kepu.pojo.StVillageNewsContent;
import com.kepu.pojo.StVillageNewsContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVillageNewsContentMapper {
    int countByExample(StVillageNewsContentExample example);

    int deleteByExample(StVillageNewsContentExample example);

    int deleteByPrimaryKey(Integer newsid);

    int insert(StVillageNewsContent record);

    int insertSelective(StVillageNewsContent record);

    List<StVillageNewsContent> selectByExampleWithBLOBs(StVillageNewsContentExample example);

    List<StVillageNewsContent> selectByExample(StVillageNewsContentExample example);

    StVillageNewsContent selectByPrimaryKey(Integer newsid);

    int updateByExampleSelective(@Param("record") StVillageNewsContent record, @Param("example") StVillageNewsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") StVillageNewsContent record, @Param("example") StVillageNewsContentExample example);

    int updateByExample(@Param("record") StVillageNewsContent record, @Param("example") StVillageNewsContentExample example);

    int updateByPrimaryKeySelective(StVillageNewsContent record);

    int updateByPrimaryKeyWithBLOBs(StVillageNewsContent record);

    int updateByPrimaryKey(StVillageNewsContent record);
}