package com.kepu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StProduct;
import com.kepu.pojo.StProductExample;
import com.kepu.pojo.good.HomeListPojo;

public interface StProductMapper {
    int countByExample(StProductExample example);

    int deleteByExample(StProductExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StProduct record);

    int insertSelective(StProduct record);

    List<StProduct> selectByExample(StProductExample example);

    StProduct selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StProduct record, @Param("example") StProductExample example);

    int updateByExample(@Param("record") StProduct record, @Param("example") StProductExample example);

    int updateByPrimaryKeySelective(StProduct record);

    int updateByPrimaryKey(StProduct record);
    
    List<HomeListPojo> getHomeListPojo();

	StProduct fandstproductlist(String productId);
}