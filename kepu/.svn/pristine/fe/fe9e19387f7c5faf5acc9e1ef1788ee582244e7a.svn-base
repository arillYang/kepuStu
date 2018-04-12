package com.kepu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.ScoreDetail;
import com.kepu.pojo.ScoreDetailExample;

public interface ScoreDetailMapper {
    int countByExample(ScoreDetailExample example);

    int deleteByExample(ScoreDetailExample example);

    int deleteByPrimaryKey(Integer scoreDetailId);

    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);

    List<ScoreDetail> selectByExample(ScoreDetailExample example);

    ScoreDetail selectByPrimaryKey(Integer scoreDetailId);

    int updateByExampleSelective(@Param("record") ScoreDetail record, @Param("example") ScoreDetailExample example);

    int updateByExample(@Param("record") ScoreDetail record, @Param("example") ScoreDetailExample example);

    int updateByPrimaryKeySelective(ScoreDetail record);

    int updateByPrimaryKey(ScoreDetail record);
    
    int updateRebateRatio(double ratio);
}