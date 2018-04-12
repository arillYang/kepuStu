package com.kepu.service;
/**
 * 积分商城
 * @author Administrator
 *
 */

import java.util.List;

import org.springframework.stereotype.Service;

import com.kepu.pojo.StIntegralProduct;

@Service
public interface IntegralProductService {

	List<StIntegralProduct>  findIntegralProductlist(int userid);
	
	
}
