package com.kepu.service;




import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;

public interface BuildService {
	
	KePuResult getClassify();
	
	// 发布出租出售
    KePuResult releaseSell(StBuildingsellContent buildingsellContent);
    
    KePuResult getCarousel();
    
    KePuResult getSellDetail(Integer buildingId);
    
    // 筛选条件
 	KePuResult getSellList(Integer page,Integer size,Map<String, String> map);
 	
 	StBuildingsellContent getSellContentById(Integer sellId);
 	
 	int saveSellContent(StBuildingsellContent buildingsellContent);
 	
 	// 发布求租求购
    KePuResult releaseRent(StBuildingrentContent buildingrentContent);
    
    // 筛选条件
  	KePuResult getRentList(Integer page,Integer size,Map<String, String> map);
  	
  	KePuResult getRentDetail(Integer rentId);
  	
  	StBuildingrentContent getRentContentById(Integer rentId);
  	
  	int saveRentContent(StBuildingrentContent buildingrentContent);
  	
  	KePuResult getMyBuilding(Integer userId,Integer page, Integer size); 
 	
	KePuResult getMyRent(Integer userId,Integer page, Integer size); 
}
