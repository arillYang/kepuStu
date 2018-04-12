package com.kepu.mapper;

import com.kepu.pojo.StCommunityArticle;
import com.kepu.pojo.StCommunityArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityArticleMapper {
    int countByExample(StCommunityArticleExample example);

    int deleteByExample(StCommunityArticleExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityArticle record);

    int insertSelective(StCommunityArticle record);

    List<StCommunityArticle> selectByExample(StCommunityArticleExample example);

    StCommunityArticle selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityArticle record, @Param("example") StCommunityArticleExample example);

    int updateByExample(@Param("record") StCommunityArticle record, @Param("example") StCommunityArticleExample example);

    int updateByPrimaryKeySelective(StCommunityArticle record);

    int updateByPrimaryKey(StCommunityArticle record);
}