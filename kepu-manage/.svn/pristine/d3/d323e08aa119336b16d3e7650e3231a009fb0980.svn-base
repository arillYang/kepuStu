package com.kepu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StDictionary;
import com.kepu.pojo.StDictionaryExample;

public interface StDictionaryMapper {
    int countByExample(StDictionaryExample example);

    int deleteByExample(StDictionaryExample example);

    int insert(StDictionary record);

    int insertSelective(StDictionary record);

    List<StDictionary> selectByExample(StDictionaryExample example);

    int updateByExampleSelective(@Param("record") StDictionary record, @Param("example") StDictionaryExample example);

    int updateByExample(@Param("record") StDictionary record, @Param("example") StDictionaryExample example);
}