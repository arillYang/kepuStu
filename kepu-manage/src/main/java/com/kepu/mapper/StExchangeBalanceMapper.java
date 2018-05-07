package com.kepu.mapper;

import com.kepu.pojo.StExchangeBalance;
import com.kepu.pojo.StExchangeBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StExchangeBalanceMapper {
    int countByExample(StExchangeBalanceExample example);

    int deleteByExample(StExchangeBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StExchangeBalance record);

    int insertSelective(StExchangeBalance record);

    List<StExchangeBalance> selectByExample(StExchangeBalanceExample example);

    StExchangeBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StExchangeBalance record, @Param("example") StExchangeBalanceExample example);

    int updateByExample(@Param("record") StExchangeBalance record, @Param("example") StExchangeBalanceExample example);

    int updateByPrimaryKeySelective(StExchangeBalance record);

    int updateByPrimaryKey(StExchangeBalance record);
}