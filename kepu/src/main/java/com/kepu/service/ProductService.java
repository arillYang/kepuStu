package com.kepu.service;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StProduct;

public interface ProductService {

	KePuResult getCarousel(Integer total,Integer classfyId);
	
	KePuResult getServiceKind(Integer type,String appVersion);
	
	KePuResult getHomeProduct(Integer page, Integer size);
	
	KePuResult getHomeProductNew(Integer page, Integer size);
	
	// ��������
	KePuResult getOutChainList(Integer type,Integer tabId);
	
	KePuResult getClassfyProduct(Integer classfyId,Integer page, Integer size,
			Integer all,Integer distance,Integer money,Integer time);
	
	KePuResult searchProduct(String query,Integer page, Integer size,
			Integer all,Integer distance,Integer money,Integer time);
	
	KePuResult getProductDetail(Integer productId);
	
	KePuResult getProductClassfy();
	
	KePuResult publishProduct(StProduct product);
	
	StProduct getProductById(Integer productId);
	
	void editProduct(StProduct product);
	
	KePuResult getAllProduct(Integer userId,Integer page, Integer size); 
	
	KePuResult getMyCollection(Integer userId,Integer page, Integer size);
	
	KePuResult likeProduct(Integer userId,Integer productId);
	
	KePuResult deletelikeProducts(Integer userId,String[] ps);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
	
	Integer getCommentNum(Integer productId);

	KePuResult getProductDetail2(Integer productId);
}
