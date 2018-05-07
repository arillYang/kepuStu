package com.kepu.mapper;

import com.kepu.pojo.StVillageContent;
import com.kepu.pojo.StVillageContentExample;
import com.kepu.pojo.StVillageContentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVillageContentMapper {
    int countByExample(StVillageContentExample example);

    int deleteByExample(StVillageContentExample example);

    int deleteByPrimaryKey(Integer villageid);

    int insert(StVillageContentWithBLOBs record);

    int insertSelective(StVillageContentWithBLOBs record);

    List<StVillageContentWithBLOBs> selectByExampleWithBLOBs(StVillageContentExample example);

    List<StVillageContent> selectByExample(StVillageContentExample example);

    StVillageContentWithBLOBs selectByPrimaryKey(Integer villageid);

    int updateByExampleSelective(@Param("record") StVillageContentWithBLOBs record, @Param("example") StVillageContentExample example);

    int updateByExampleWithBLOBs(@Param("record") StVillageContentWithBLOBs record, @Param("example") StVillageContentExample example);

    int updateByExample(@Param("record") StVillageContent record, @Param("example") StVillageContentExample example);

    int updateByPrimaryKeySelective(StVillageContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(StVillageContentWithBLOBs record);

    int updateByPrimaryKey(StVillageContent record);
}