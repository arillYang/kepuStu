package com.kepu.mapper;

import com.kepu.pojo.StSkillandbuildingComment;
import com.kepu.pojo.StSkillandbuildingCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StSkillandbuildingCommentMapper {
    int countByExample(StSkillandbuildingCommentExample example);

    int deleteByExample(StSkillandbuildingCommentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StSkillandbuildingComment record);

    int insertSelective(StSkillandbuildingComment record);

    List<StSkillandbuildingComment> selectByExample(StSkillandbuildingCommentExample example);

    StSkillandbuildingComment selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StSkillandbuildingComment record, @Param("example") StSkillandbuildingCommentExample example);

    int updateByExample(@Param("record") StSkillandbuildingComment record, @Param("example") StSkillandbuildingCommentExample example);

    int updateByPrimaryKeySelective(StSkillandbuildingComment record);

    int updateByPrimaryKey(StSkillandbuildingComment record);
}