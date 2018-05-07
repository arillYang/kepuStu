package com.kepu.service;


import java.util.Map;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StBuildingClassify;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StOutchainDetail;
import com.kepu.pojo.StPositionClassify;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StSkillClassify;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTab;
import com.kepu.pojo.StTaskContent;

public interface ProductService {

	Map<String, Object> findStProduct(PageBean pageBean,StProduct product);
	
	StProduct getProductById(Integer Id);
	
	void deleteProductById(Integer Id);
	
	void saveProduct(StProduct product);
	
	Map<String, Object> findStTab(PageBean pageBean, int type);
	
	StTab getStTabById(Integer tabId);
	
	void saveTab(StTab stTab);
	
	int deleteTabById(Integer tabId);
	
	Map<String, Object> findLink(PageBean pageBean, Integer outchainId);
	
	StOutchainDetail getStOutchainDetailById(Integer id);
	
	void saveStOutchainDetail(StOutchainDetail outchainDetail);
	
	int deleteStOutchainDetail(Integer id);
	
	Map<String, Object> findPersonallist(PageBean pageBean,StAuthenticPeople authenticPeople);
	
	StAuthenticPeople getStAuthenticPeopleById(Integer applyId);
	
	void saveStAuthenticPeople(StAuthenticPeople authenticPeople);
	
	Map<String, Object> findCompanylist(PageBean pageBean,StAuthenticCompany authenticCompany);
	
	StAuthenticCompany getStAuthenticCompanyById(Integer applyId);
	
	void saveStAuthenticCompany(StAuthenticCompany authenticCompany);
	
	// 技能部分
	Map<String, Object> findStSkillContent(PageBean pageBean,StSkillContent product);
	
	StSkillContent getSkillContentById(Integer Id);
	
	void deleteSkillContentById(Integer Id);
	
	void saveSkillContent(StSkillContent product);
	
	// 任务部分
	Map<String, Object> findTaskContent(PageBean pageBean,StTaskContent product);
	
	StTaskContent getTaskContentById(Integer Id);
	
	void deleteTaskContentById(Integer Id);
	
	void saveTaskContent(StTaskContent product);
	
	// 出租出售
	Map<String, Object> findSellContent(PageBean pageBean,StBuildingsellContent product);
	
	StBuildingsellContent getSellContentById(Integer Id);
	
	void deleteSellContentById(Integer Id);
	
	void saveSellContent(StBuildingsellContent product);
	
	// 求租求购
	Map<String, Object> findRentContent(PageBean pageBean,StBuildingrentContent product);
	
	StBuildingrentContent getRentContentById(Integer Id);
	
	void deleteRentContentById(Integer Id);
	
	void saveRentContent(StBuildingrentContent product);
	
	// 技能分类
	Map<String, Object> findSkillClassify(PageBean pageBean, int parent);
	
	StSkillClassify getSkillClassifyById(Integer Id);
	
	void saveStSkillClassify(StSkillClassify classify);
	
	int deleteSkillClassifyById(Integer Id);
	
	// 房屋分类
	Map<String, Object> findBuildClassify(PageBean pageBean, int parent);
	
	StBuildingClassify getBuildClassifyById(Integer Id);
	
	void saveBuildClassify(StBuildingClassify classify);
	
	int deleteBuildClassifyById(Integer Id);
	
	// 职业分类
	Map<String, Object> findJobClassify(PageBean pageBean, int parent);
	
	StPositionClassify getPositionClassifyById(Integer Id);
	
	void saveStPositionClassify(StPositionClassify classify);
	
	int deleteStPositionClassifyById(Integer Id);
	
	// 招聘部分
	Map<String, Object> findStJob(PageBean pageBean,StJob job);
	
	StJob getStJobById(Integer Id);
	
	void deleteStJobById(Integer Id);
	
	void saveStJob(StJob job);
	
	// 求职部分
	Map<String, Object> findStJobApply(PageBean pageBean,StJobApply job);
	
	StJobApply getStJobApplyById(Integer Id);
	
	void deleteStJobApplyById(Integer Id);
	
	void saveStJobApply(StJobApply job);
}
