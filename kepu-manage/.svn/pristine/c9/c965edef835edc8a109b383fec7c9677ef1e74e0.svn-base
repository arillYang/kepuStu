package com.kepu.mapper;

import com.kepu.pojo.StReply;
import com.kepu.pojo.StReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StReplyMapper {
    int countByExample(StReplyExample example);

    int deleteByExample(StReplyExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StReply record);

    int insertSelective(StReply record);

    List<StReply> selectByExample(StReplyExample example);

    StReply selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StReply record, @Param("example") StReplyExample example);

    int updateByExample(@Param("record") StReply record, @Param("example") StReplyExample example);

    int updateByPrimaryKeySelective(StReply record);

    int updateByPrimaryKey(StReply record);
}