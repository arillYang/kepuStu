package com.kepu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StIntegralProduExample;
import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StRecordExchange;

public interface StRecordExchangeMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(StRecordExchange record);

    int insertSelective(StRecordExchange record);

    StRecordExchange selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(StRecordExchange record);

    int updateByPrimaryKey(StRecordExchange record);
    
    //查询全部
    List<StRecordExchange> selectByPrimaryKeylist();
    
    //排行
    List<StRecordExchange> selectPrimaryKeydesclist();
    
    
    //根据user查询
    List<StIntegralProduct> findIntegralProductlist(int userid);

    //模糊查询
	List<StIntegralProduct> findintegralmall(String name);
	
	int countByExample(StIntegralProduExample example);

    int deleteByExample(StIntegralProduExample example);

    List<StIntegralProduExample> selectByExample(StIntegralProduExample example);
    int updateByExampleSelective(@Param("record") StIntegralProduct record, @Param("example") StIntegralProduExample example);

    int updateByExample(@Param("record") StIntegralProduct record, @Param("example") StIntegralProduExample example);
    
    
    
}