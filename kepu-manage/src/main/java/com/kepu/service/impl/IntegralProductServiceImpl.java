package com.kepu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.StIntegralProductMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StIntegralProduExample;
import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StIntegralProductExample;
import com.kepu.pojo.StIntegralProductExample.Criteria;
import com.kepu.pojo.WithdrawCash;
import com.kepu.pojo.WithdrawCashExample;
import com.kepu.service.IntegralProductService;

@Service("IntegralProductService")
public class IntegralProductServiceImpl implements IntegralProductService{
	@Autowired
	private StIntegralProductMapper integralProductDao;
	@Override
	public List<StIntegralProduct> findIntegralProductlist(int userid) {
		// TODO Auto-generated method stub
		return integralProductDao.findIntegralProductlist(userid);
	}
	@Override
	public List<StIntegralProduct> findintegralmall(String name) {
		// TODO Auto-generated method stub
		return integralProductDao.findintegralmall(name);
	}

	
	public Map<String, Object> findWithdrawCash(PageBean pageBean, StIntegralProduct st) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		StIntegralProductExample example = new StIntegralProductExample();
		example.setOrderByClause("id desc ");
		Criteria criteria=example.createCriteria();
		if(st.getUserid()!=null)
			criteria.andTitleEqualTo(st.getUserid().toString());
		List<StIntegralProduct> list = integralProductDao.selectByExample(example);
		System.err.println("---------------dsadhjkdas------------54645"+list.size());
		PageInfo<StIntegralProduct> pageInfo = new PageInfo<StIntegralProduct>(list);
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("list", list);
		return map;
	}
}
