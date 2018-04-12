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
		Map<String, Object> map = new HashMap<String, Object>();
		WithdrawCashExample example = new WithdrawCashExample();
		example.setOrderByClause("wc_time desc ");
		WithdrawCashExample.Criteria criteria=example.createCriteria();
		if(withdrawCash.getBuyUserId()!=null)
			criteria.andBuyUserIdEqualTo(withdrawCash.getBuyUserId());
		if(withdrawCash.getWcId()!=null)
			criteria.andWcIdEqualTo(withdrawCash.getWcId());
		List<WithdrawCash> list = withdrawCashMapper.selectByExample(example);
		map.put("list", list);
		return map;
	}
}
