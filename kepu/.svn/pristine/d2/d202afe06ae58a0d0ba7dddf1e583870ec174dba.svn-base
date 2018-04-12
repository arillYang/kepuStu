package com.kepu.mapper;

import com.kepu.pojo.ProportionSetting;
import com.kepu.pojo.ProportionSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProportionSettingMapper {
    int countByExample(ProportionSettingExample example);

    int deleteByExample(ProportionSettingExample example);

    int deleteByPrimaryKey(Integer settingId);

    int insert(ProportionSetting record);

    int insertSelective(ProportionSetting record);

    List<ProportionSetting> selectByExample(ProportionSettingExample example);

    ProportionSetting selectByPrimaryKey(Integer settingId);

    int updateByExampleSelective(@Param("record") ProportionSetting record, @Param("example") ProportionSettingExample example);

    int updateByExample(@Param("record") ProportionSetting record, @Param("example") ProportionSettingExample example);

    int updateByPrimaryKeySelective(ProportionSetting record);

    int updateByPrimaryKey(ProportionSetting record);
}