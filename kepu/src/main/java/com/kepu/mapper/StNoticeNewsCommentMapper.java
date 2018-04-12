package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsComment;
import com.kepu.pojo.StNoticeNewsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsCommentMapper {
    int countByExample(StNoticeNewsCommentExample example);

    int deleteByExample(StNoticeNewsCommentExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StNoticeNewsComment record);

    int insertSelective(StNoticeNewsComment record);

    List<StNoticeNewsComment> selectByExample(StNoticeNewsCommentExample example);

    StNoticeNewsComment selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StNoticeNewsComment record, @Param("example") StNoticeNewsCommentExample example);

    int updateByExample(@Param("record") StNoticeNewsComment record, @Param("example") StNoticeNewsCommentExample example);

    int updateByPrimaryKeySelective(StNoticeNewsComment record);

    int updateByPrimaryKey(StNoticeNewsComment record);
}