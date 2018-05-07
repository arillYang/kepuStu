package com.kepu.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.UserAddress;


@Service
public interface AddressService {
	public Map<String, Object> findUserAddress(PageBean pageBean,UserAddress userAddress);

}

