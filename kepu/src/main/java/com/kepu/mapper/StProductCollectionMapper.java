package com.kepu.mapper;

import com.kepu.pojo.StProductCollection;
import com.kepu.pojo.StProductCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StProductCollectionMapper {
    int countByExample(StProductCollectionExample example);

    int deleteByExample(StProductCollectionExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StProductCollection record);

    int insertSelective(StProductCollection record);

    List<StProductCollection> selectByExample(StProductCollectionExample example);

    StProductCollection selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StProductCollection record, @Param("example") StProductCollectionExample example);

    int updateByExample(@Param("record") StProductCollection record, @Param("example") StProductCollectionExample example);

    int updateByPrimaryKeySelective(StProductCollection record);

    int updateByPrimaryKey(StProductCollection record);
}