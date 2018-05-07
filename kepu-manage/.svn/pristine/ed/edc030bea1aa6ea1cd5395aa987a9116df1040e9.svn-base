package com.kepu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.WithdrawCash;
import com.kepu.pojo.WithdrawCashExample;
import com.kepu.service.WithdrawCashService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WithdrawCashServiceImpl implements WithdrawCashService{

	@Autowired
	private WithdrawCashMapper withdrawCashMapper;

	@Override
	public Map<String, Object> findWithdrawCash(PageBean pageBean, WithdrawCash withdrawCash) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		WithdrawCashExample example = new WithdrawCashExample();
		example.setOrderByClause("wc_time desc ");
		WithdrawCashExample.Criteria criteria=example.createCriteria();
		if(withdrawCash.getBuyUserPhone()!=null)
			criteria.andBuyUserPhoneEqualTo(withdrawCash.getBuyUserPhone());
		if(withdrawCash.getWcId()!=null)
			criteria.andWcIdEqualTo(withdrawCash.getWcId());
		List<WithdrawCash> list = withdrawCashMapper.selectByExample(example);
		PageInfo<WithdrawCash> pageInfo = new PageInfo<WithdrawCash>(list);
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("list", list);
		return map;
	}
}
