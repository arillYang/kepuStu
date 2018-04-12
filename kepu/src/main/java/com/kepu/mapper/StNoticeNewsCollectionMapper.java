package com.kepu.mapper;

import com.kepu.pojo.StNoticeNewsCollection;
import com.kepu.pojo.StNoticeNewsCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNoticeNewsCollectionMapper {
    int countByExample(StNoticeNewsCollectionExample example);

    int deleteByExample(StNoticeNewsCollectionExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(StNoticeNewsCollection record);

    int insertSelective(StNoticeNewsCollection record);

    List<StNoticeNewsCollection> selectByExample(StNoticeNewsCollectionExample example);

    StNoticeNewsCollection selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") StNoticeNewsCollection record, @Param("example") StNoticeNewsCollectionExample example);

    int updateByExample(@Param("record") StNoticeNewsCollection record, @Param("example") StNoticeNewsCollectionExample example);

    int updateByPrimaryKeySelective(StNoticeNewsCollection record);

    int updateByPrimaryKey(StNoticeNewsCollection record);
}