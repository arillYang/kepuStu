package com.kepu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StProductExample;
import com.kepu.service.OrderService;
import com.kepu.util.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderInfoMapper oimapper;

	public Map<String, Object> findOrderInfo(PageBean pageBean, OrderInfo orderInfo) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfoExample example = new OrderInfoExample();
		example.setOrderByClause("order_date desc ");
		OrderInfoExample.Criteria criteria=example.createCriteria();
		if((orderInfo.getOrderId())!=null)
			criteria.andOrderIdEqualTo(orderInfo.getOrderId());
		if((orderInfo.getOrderUser())!=null)
			criteria.andOrderUserEqualTo(orderInfo.getOrderUser());
		if((orderInfo.getOrderStatu())!=null)
			criteria.andOrderStatuEqualTo(orderInfo.getOrderStatu());
		List<OrderInfo> list = oimapper.selectByExample(example);
		PageInfo<OrderInfo> pageInfo = new PageInfo<OrderInfo>(list);
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("list", list);
		return map;
	}
}
