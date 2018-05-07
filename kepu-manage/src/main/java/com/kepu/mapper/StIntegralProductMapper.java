package com.kepu.mapper;

import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StIntegralProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StIntegralProductMapper {
    int countByExample(StIntegralProductExample example);

    int deleteByExample(StIntegralProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StIntegralProduct record);

    int insertSelective(StIntegralProduct record);

    List<StIntegralProduct> selectByExample(StIntegralProductExample example);

    StIntegralProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StIntegralProduct record, @Param("example") StIntegralProductExample example);

    int updateByExample(@Param("record") StIntegralProduct record, @Param("example") StIntegralProductExample example);

    int updateByPrimaryKeySelective(StIntegralProduct record);

    int updateByPrimaryKey(StIntegralProduct record);

	List<StIntegralProduct> findIntegralProductlist(int userid);

	List<StIntegralProduct> findintegralmall(String name);
}