package com.kepu.mapper;

import com.kepu.pojo.StCollection;
import com.kepu.pojo.StCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCollectionMapper {
    int countByExample(StCollectionExample example);

    int deleteByExample(StCollectionExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StCollection record);

    int insertSelective(StCollection record);

    List<StCollection> selectByExample(StCollectionExample example);

    StCollection selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StCollection record, @Param("example") StCollectionExample example);

    int updateByExample(@Param("record") StCollection record, @Param("example") StCollectionExample example);

    int updateByPrimaryKeySelective(StCollection record);

    int updateByPrimaryKey(StCollection record);
}