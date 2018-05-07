package com.kepu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StIntegralProduct;


@Service
public interface IntegralProductService {

	List<StIntegralProduct>  findIntegralProductlist(int userid);
	
	
	List<StIntegralProduct>  findintegralmall(String  name);
	
	
	Map<String, Object>  findWithdrawCash(PageBean pageBean, StIntegralProduct st);
	
	
}
