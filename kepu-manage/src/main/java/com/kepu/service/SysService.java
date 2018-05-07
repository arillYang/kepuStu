package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.ProportionSetting;
import com.kepu.pojo.StDevice;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.activity.CoefficientSet;







public interface SysService {
	
	String getLaunchPage();
	
	String getAboutUs();
	
	List<String> getGuidePage();
	
	void insertDeviceMessage(StDevice stDevice);
	
	

	KePuResult reportNewsComment(Integer userId,Long commentId);
	
	StVillage getVillageById(Integer villageId);
	
	List<StVillage> getTowns();
	
	List<StVillage> getVillages(Integer townId);
	
	Integer getTownIdByVillageId(Integer villageId);
	
	Map<Integer,String>  getAddressName();
	
	Map<String,List<String>>  getAddressName2();
	
	Map<String,List<String>>  getR();
	
	String getVillageNameById(Integer villageId);
	
	void SaveSystemParams(String launchPage,String guides,String about_us,
			String JsSecretKey,String need_approve,CoefficientSet set);
	
	String getParameter(String key);
	
	void saveParameter(String key,String value);
	
	ProportionSetting findProportionSetting(int setting_id);

	int updateProportionSetting(ProportionSetting proport);
}
