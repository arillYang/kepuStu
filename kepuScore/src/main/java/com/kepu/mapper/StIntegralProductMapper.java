package com.kepu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StIntegralProduExample;
import com.kepu.pojo.StIntegralProduct;

public interface StIntegralProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StIntegralProduct record);

    int insertSelective(StIntegralProduct record);

    StIntegralProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StIntegralProduct record);

    int updateByPrimaryKey(StIntegralProduct record);
    
    
    List<StIntegralProduct> findProductlist();
    
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