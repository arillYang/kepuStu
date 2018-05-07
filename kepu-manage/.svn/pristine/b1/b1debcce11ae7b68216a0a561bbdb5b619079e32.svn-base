package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.TMenu;
import com.kepu.pojo.TMenuExample;

public interface TMenuMapper {
    int countByExample(TMenuExample example);

    int deleteByExample(TMenuExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    List<TMenu> selectByExample(TMenuExample example);

    TMenu selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);
    
    List<String> getMenuUrlsByRoleId(@Param("param") Map param);
}