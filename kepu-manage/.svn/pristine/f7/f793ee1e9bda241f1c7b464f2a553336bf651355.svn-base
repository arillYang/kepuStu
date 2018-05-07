package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserExample;
import com.kepu.pojo.user.AdviceFeedback;
import com.kepu.pojo.user.AuthorResult;
import com.kepu.pojo.user.ClassifyResult;
import com.kepu.pojo.user.UserStatistic;
import com.kepu.pojo.user.active.CountyActive;
import com.kepu.pojo.user.active.CountySS;
import com.kepu.pojo.user.active.NoActive;
import com.kepu.pojo.user.active.PeopleSS;
import com.kepu.pojo.user.active.TownActive;
import com.kepu.pojo.user.active.TownSS;
import com.kepu.pojo.user.click.ClickResult;
import com.kepu.pojo.user.detail.DetailResult;
import com.kepu.pojo.user.read.ReadResult;

public interface StUserMapper {
    int countByExample(StUserExample example);

    int deleteByExample(StUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(StUser record);

    int insertSelective(StUser record);

    List<StUser> selectByExample(StUserExample example);

    StUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") StUser record, @Param("example") StUserExample example);

    int updateByExample(@Param("record") StUser record, @Param("example") StUserExample example);

    int updateByPrimaryKeySelective(StUser record);

    int updateByPrimaryKey(StUser record);
    
    List<UserStatistic> getStatisticByTownId(@Param("townId")Integer townId);
    
    int getStatisticByTownIdNumber(@Param("townId")Integer townId);
    
    List<UserStatistic> getStatistic();
    
    int getStatisticNumber();
    
    List<String> getPushCids(@Param("param") Map<String,Object> param);
    
    List<AdviceFeedback> getAdviceFeedback(); 
    
    List<TownSS> getTownSS(@Param("param") Map<String,Object> param);
    
    List<CountySS> getCountySS(@Param("param") Map<String,Object> param);
    
    List<PeopleSS> getPeopleSS(@Param("param") Map<String,Object> param);
    
    List<ClickResult> getClickTown(@Param("param") Map<String,Object> param);
    
    List<ClickResult> getClickCounty(@Param("param") Map<String,Object> param);
    
    List<ClickResult> getClickPeople(@Param("param") Map<String,Object> param);
    
    List<ReadResult> getReadDetail(@Param("param") Map<String,Object> param);
    
    List<DetailResult>  getDetail(@Param("param") Map<String,Object> param);
    
    List<DetailResult>  getDetailCounty(@Param("param") Map<String,Object> param);
    
    List<TownActive> getTownActive(@Param("param") Map<String,Object> param);
    
    List<CountyActive> getCountyActive(@Param("param") Map<String,Object> param);
    
    List<NoActive> getAllActiveNumTown();
    
    List<NoActive> getAllActiveNumCounty(@Param("param") Map<String,Object> param);
    
    List<AuthorResult> getAuthorStatistic(@Param("param") Map<String,Object> param);
    
    AuthorResult getAllAuthorStatistic(@Param("param") Map<String,Object> param);
    
    List<ClassifyResult> getClassifyStatistic(@Param("param") Map<String,Object> param);
    
    int getViewByClassifyId(@Param("classfyId")Integer classfyId);
}