package com.kepu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kepu.mapper.StIntegralProductMapper;
import com.kepu.pojo.StIntegralProduct;
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

}
