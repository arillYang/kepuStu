package com.kepu.mapper;

import com.kepu.pojo.StComment;
import com.kepu.pojo.StCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommentMapper {
    int countByExample(StCommentExample example);

    int deleteByExample(StCommentExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StComment record);

    int insertSelective(StComment record);

    List<StComment> selectByExample(StCommentExample example);

    StComment selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StComment record, @Param("example") StCommentExample example);

    int updateByExample(@Param("record") StComment record, @Param("example") StCommentExample example);

    int updateByPrimaryKeySelective(StComment record);

    int updateByPrimaryKey(StComment record);
}