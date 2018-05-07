package com.kepu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.UserAddressMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.UserAddress;
import com.kepu.pojo.UserAddressExample;
import com.kepu.service.AddressService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserAddressMapper userAddressMapper;


	@Override
	public Map<String, Object> findUserAddress(PageBean pageBean, UserAddress userAddress) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		UserAddressExample example = new UserAddressExample();
		example.setOrderByClause("create_time desc ");
		UserAddressExample.Criteria criteria=example.createCriteria();
		if((userAddress.getUserId())!=null)
			criteria.andUserIdEqualTo(userAddress.getUserId());
		if((userAddress.getContactPhone())!=null)
			criteria.andContactPhoneEqualTo(userAddress.getContactPhone());
		List<UserAddress> list = userAddressMapper.selectByExample(example);
		PageInfo<UserAddress> pageInfo = new PageInfo<UserAddress>(list);
		long total = pageInfo.getTotal();
		map.put("total", total);
		map.put("list", list);
		return map;
	}
}
