package com.kepu.mapper;

import com.kepu.pojo.StDevice;
import com.kepu.pojo.StDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StDeviceMapper {
    int countByExample(StDeviceExample example);

    int deleteByExample(StDeviceExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StDevice record);

    int insertSelective(StDevice record);

    List<StDevice> selectByExample(StDeviceExample example);

    StDevice selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StDevice record, @Param("example") StDeviceExample example);

    int updateByExample(@Param("record") StDevice record, @Param("example") StDeviceExample example);

    int updateByPrimaryKeySelective(StDevice record);

    int updateByPrimaryKey(StDevice record);
}