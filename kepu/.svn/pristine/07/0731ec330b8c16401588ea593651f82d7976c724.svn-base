package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;

public interface OrderInfoMapper {
	/*查询接口*/
	List<OrderInfo> selectOrder(Map map);
	
    int countByExample(OrderInfoExample example);

    int deleteByExample(OrderInfoExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    List<OrderInfo> selectByExample(OrderInfoExample example);

    OrderInfo selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
    
    void updateStatus(Map map);
}