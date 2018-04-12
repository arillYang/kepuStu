package com.kepu.mapper;

import com.kepu.pojo.StArticleComment;
import com.kepu.pojo.StArticleCommentExample;
import com.kepu.pojo.community.RemindResult;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StArticleCommentMapper {
    int countByExample(StArticleCommentExample example);

    int deleteByExample(StArticleCommentExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StArticleComment record);

    int insertSelective(StArticleComment record);

    List<StArticleComment> selectByExample(StArticleCommentExample example);

    StArticleComment selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StArticleComment record, @Param("example") StArticleCommentExample example);

    int updateByExample(@Param("record") StArticleComment record, @Param("example") StArticleCommentExample example);

    int updateByPrimaryKeySelective(StArticleComment record);

    int updateByPrimaryKey(StArticleComment record);
    
    List<RemindResult> getMyRemind(@Param("param") Map<String,Object> param);
}