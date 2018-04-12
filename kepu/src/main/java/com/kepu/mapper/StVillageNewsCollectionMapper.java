package com.kepu.mapper;

import com.kepu.pojo.StVillageNewsCollection;
import com.kepu.pojo.StVillageNewsCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StVillageNewsCollectionMapper {
    int countByExample(StVillageNewsCollectionExample example);

    int deleteByExample(StVillageNewsCollectionExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StVillageNewsCollection record);

    int insertSelective(StVillageNewsCollection record);

    List<StVillageNewsCollection> selectByExample(StVillageNewsCollectionExample example);

    StVillageNewsCollection selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StVillageNewsCollection record, @Param("example") StVillageNewsCollectionExample example);

    int updateByExample(@Param("record") StVillageNewsCollection record, @Param("example") StVillageNewsCollectionExample example);

    int updateByPrimaryKeySelective(StVillageNewsCollection record);

    int updateByPrimaryKey(StVillageNewsCollection record);
}