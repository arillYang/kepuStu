package com.kepu.mapper;

import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StAuthenticPeopleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StAuthenticPeopleMapper {
    int countByExample(StAuthenticPeopleExample example);

    int deleteByExample(StAuthenticPeopleExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StAuthenticPeople record);

    int insertSelective(StAuthenticPeople record);

    List<StAuthenticPeople> selectByExample(StAuthenticPeopleExample example);

    StAuthenticPeople selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StAuthenticPeople record, @Param("example") StAuthenticPeopleExample example);

    int updateByExample(@Param("record") StAuthenticPeople record, @Param("example") StAuthenticPeopleExample example);

    int updateByPrimaryKeySelective(StAuthenticPeople record);

    int updateByPrimaryKey(StAuthenticPeople record);
}