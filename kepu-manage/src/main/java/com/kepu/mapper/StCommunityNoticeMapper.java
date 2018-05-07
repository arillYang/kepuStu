package com.kepu.mapper;

import com.kepu.pojo.StCommunityNotice;
import com.kepu.pojo.StCommunityNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StCommunityNoticeMapper {
    int countByExample(StCommunityNoticeExample example);

    int deleteByExample(StCommunityNoticeExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StCommunityNotice record);

    int insertSelective(StCommunityNotice record);

    List<StCommunityNotice> selectByExampleWithBLOBs(StCommunityNoticeExample example);

    List<StCommunityNotice> selectByExample(StCommunityNoticeExample example);

    StCommunityNotice selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StCommunityNotice record, @Param("example") StCommunityNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") StCommunityNotice record, @Param("example") StCommunityNoticeExample example);

    int updateByExample(@Param("record") StCommunityNotice record, @Param("example") StCommunityNoticeExample example);

    int updateByPrimaryKeySelective(StCommunityNotice record);

    int updateByPrimaryKeyWithBLOBs(StCommunityNotice record);

    int updateByPrimaryKey(StCommunityNotice record);
}