package com.kepu.mapper;

import com.kepu.pojo.StSign;
import com.kepu.pojo.StSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StSignMapper {
    int countByExample(StSignExample example);

    int deleteByExample(StSignExample example);

    int insert(StSign record);

    int insertSelective(StSign record);

    List<StSign> selectByExample(StSignExample example);

    int updateByExampleSelective(@Param("record") StSign record, @Param("example") StSignExample example);

    int updateByExample(@Param("record") StSign record, @Param("example") StSignExample example);
    
    List<StSign> selectAll(@Param("userId") int userId);
    
    int updateByPrimaryKeySelective(StSign stSign);
    
    
   int  updateByPrimaryKeyType(StSign stSign);
    StSign findtype(int userId,int keysta);
    
    
    List<StSign> selectAlltype(int userId,int type,int days);
    
    
    List<StSign> selectAllUserId(int userId);
    
}