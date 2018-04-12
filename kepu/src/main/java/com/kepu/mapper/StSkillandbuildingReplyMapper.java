package com.kepu.mapper;

import com.kepu.pojo.StSkillandbuildingReply;
import com.kepu.pojo.StSkillandbuildingReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StSkillandbuildingReplyMapper {
    int countByExample(StSkillandbuildingReplyExample example);

    int deleteByExample(StSkillandbuildingReplyExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StSkillandbuildingReply record);

    int insertSelective(StSkillandbuildingReply record);

    List<StSkillandbuildingReply> selectByExample(StSkillandbuildingReplyExample example);

    StSkillandbuildingReply selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StSkillandbuildingReply record, @Param("example") StSkillandbuildingReplyExample example);

    int updateByExample(@Param("record") StSkillandbuildingReply record, @Param("example") StSkillandbuildingReplyExample example);

    int updateByPrimaryKeySelective(StSkillandbuildingReply record);

    int updateByPrimaryKey(StSkillandbuildingReply record);
}