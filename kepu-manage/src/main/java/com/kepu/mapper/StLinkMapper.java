package com.kepu.mapper;

import com.kepu.pojo.StLink;
import com.kepu.pojo.StLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StLinkMapper {
    int countByExample(StLinkExample example);

    int deleteByExample(StLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StLink record);

    int insertSelective(StLink record);

    List<StLink> selectByExample(StLinkExample example);

    StLink selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StLink record, @Param("example") StLinkExample example);

    int updateByExample(@Param("record") StLink record, @Param("example") StLinkExample example);

    int updateByPrimaryKeySelective(StLink record);

    int updateByPrimaryKey(StLink record);
}