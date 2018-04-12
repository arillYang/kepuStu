package com.kepu.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.PageBean;


@Service
public interface OrderService {
	public Map<String, Object> findOrderInfo(PageBean pageBean,OrderInfo orderInfo);

}
/*	public Map<String, Object> findOrderInfo(PageBean pageBean,
			OrderInfo orderInfo) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StClassifyExample example=new StClassifyExample();
		example.setOrderByClause("FIXED desc ");
		StClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(orderInfo.getOrderId()))
			criteria.andClassifynameLike("%"+orderInfo.getOrderId()+"%");
		List<OrderInfo> list = orderMapper.selectByExample();
		PageInfo<StClassify> pageInfo=new PageInfo<StClassify>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}
 * */
