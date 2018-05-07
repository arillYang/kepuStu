package com.kepu.mapper;

import com.kepu.pojo.StTab;
import com.kepu.pojo.StTabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StTabMapper {
    int countByExample(StTabExample example);

    int deleteByExample(StTabExample example);

    int deleteByPrimaryKey(Integer tabid);

    int insert(StTab record);

    int insertSelective(StTab record);

    List<StTab> selectByExample(StTabExample example);

    StTab selectByPrimaryKey(Integer tabid);

    int updateByExampleSelective(@Param("record") StTab record, @Param("example") StTabExample example);

    int updateByExample(@Param("record") StTab record, @Param("example") StTabExample example);

    int updateByPrimaryKeySelective(StTab record);

    int updateByPrimaryKey(StTab record);
}