package com.kepu.mapper;

import com.kepu.pojo.StPush;
import com.kepu.pojo.StPushExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StPushMapper {
    int countByExample(StPushExample example);

    int deleteByExample(StPushExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StPush record);

    int insertSelective(StPush record);

    List<StPush> selectByExample(StPushExample example);

    StPush selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StPush record, @Param("example") StPushExample example);

    int updateByExample(@Param("record") StPush record, @Param("example") StPushExample example);

    int updateByPrimaryKeySelective(StPush record);

    int updateByPrimaryKey(StPush record);
}