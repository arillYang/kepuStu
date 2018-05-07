package com.kepu.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.StAuthenticCompanyMapper;
import com.kepu.mapper.StAuthenticPeopleMapper;
import com.kepu.mapper.StBuildingClassifyMapper;
import com.kepu.mapper.StBuildingrentContentMapper;
import com.kepu.mapper.StBuildingsellContentMapper;
import com.kepu.mapper.StJobApplyMapper;
import com.kepu.mapper.StJobMapper;
import com.kepu.mapper.StOutchainDetailMapper;
import com.kepu.mapper.StPositionClassifyMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StSkillClassifyMapper;
import com.kepu.mapper.StSkillContentMapper;
import com.kepu.mapper.StTabMapper;
import com.kepu.mapper.StTaskContentMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticCompanyExample;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StAuthenticPeopleExample;
import com.kepu.pojo.StBuildingClassify;
import com.kepu.pojo.StBuildingClassifyExample;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingrentContentExample;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StBuildingsellContentExample;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StJobApplyExample;
import com.kepu.pojo.StJobExample;
import com.kepu.pojo.StOutchainDetail;
import com.kepu.pojo.StOutchainDetailExample;
import com.kepu.pojo.StPositionClassify;
import com.kepu.pojo.StPositionClassifyExample;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StProductExample;
import com.kepu.pojo.StSkillClassify;
import com.kepu.pojo.StSkillClassifyExample;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillContentExample;
import com.kepu.pojo.StTab;
import com.kepu.pojo.StTabExample;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StTaskContentExample;
import com.kepu.pojo.StUser;
import com.kepu.service.ProductService;
import com.kepu.util.StringUtil;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private StProductMapper productMapper;
	@Autowired
	private StTabMapper stTabMapper;
	@Autowired
	private StOutchainDetailMapper outchainDetailMapper;
	@Autowired
	private StAuthenticPeopleMapper authenticPeopleMapper;
	@Autowired
	private StAuthenticCompanyMapper authenticCompanyMapper;
	@Autowired
	private StUserMapper userMapper;
	@Autowired
	private StSkillContentMapper skillContentMapper;
	@Autowired
	private StTaskContentMapper taskContentMapper;
	@Autowired
	private StBuildingsellContentMapper sellContentMapper;
	@Autowired
	private StBuildingrentContentMapper rentContentMapper;
	@Autowired
	private StSkillClassifyMapper skillClassifyMapper;
	@Autowired
	private StBuildingClassifyMapper buildingClassifyMapper;
	@Autowired
	private StPositionClassifyMapper  positionClassifyMapper;
	@Autowired
	private StJobMapper jobMapper;
	@Autowired
	private StJobApplyMapper jobApplyMapper;
	@Override
	public Map<String, Object> findStProduct(PageBean pageBean,StProduct product) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example=new StProductExample();
		example.setOrderByClause("createtime desc");
		StProductExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(product.getTitle()))
			criteria.andTitleLike("%"+product.getTitle()+"%");
		List<StProduct> list = productMapper.selectByExample(example);
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StProduct getProductById(Integer Id) {
		return productMapper.selectByPrimaryKey(Id);
	}


	@Override
	public void deleteProductById(Integer Id) {
		StProduct p = productMapper.selectByPrimaryKey(Id);
		if(p!=null)
		{
			p.setState(1-p.getState());
		}
		productMapper.updateByPrimaryKeySelective(p);
	}

	@Override
	public void saveProduct(StProduct product) {
		if(StringUtil.isEmpty(product.getCoverpic()))
			product.setCoverpic(null);
		if(StringUtil.isEmpty(product.getDetailpics()))
			product.setDetailpics(null);
		productMapper.updateByPrimaryKeySelective(product);	
	}

	@Override
	public Map<String, Object> findStTab(PageBean pageBean, int type) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StTabExample example=new StTabExample();
		example.setOrderByClause("createtime");
		StTabExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(type==3)
			criteria.andTabtypeEqualTo(3);
		else
			criteria.andTabtypeNotEqualTo(3);
		List<StTab> list = stTabMapper.selectByExample(example);
		PageInfo<StTab> pageInfo=new PageInfo<StTab>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StTab getStTabById(Integer tabId) {
		return stTabMapper.selectByPrimaryKey(tabId);
	}

	@Override
	public void saveTab(StTab stTab) {	
		if(StringUtil.isEmpty(stTab.getTabpic())){
			stTab.setTabpic(null);
		}
		if(stTab.getTabid()==null){
			stTab.setCreatetime(new Date());
			stTab.setState(0);
			stTab.setTabtype(3);
			stTabMapper.insertSelective(stTab);
		}else
			stTabMapper.updateByPrimaryKeySelective(stTab);
	}

	@Override
	public int deleteTabById(Integer tabId) {
		StOutchainDetailExample example=new StOutchainDetailExample();
		StOutchainDetailExample.Criteria criteria=example.createCriteria();
		criteria.andOutchainidEqualTo(tabId);
		List<StOutchainDetail> list = outchainDetailMapper.selectByExample(example);
		if(list.size()==0){
			stTabMapper.deleteByPrimaryKey(tabId);
			return 1;
		}
		else
			return -1;
			
	}

	@Override
	public Map<String, Object> findLink(PageBean pageBean, Integer outchainId) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StOutchainDetailExample example=new StOutchainDetailExample();
		StOutchainDetailExample.Criteria criteria=example.createCriteria();
		criteria.andOutchainidEqualTo(outchainId);
		List<StOutchainDetail> list = outchainDetailMapper.selectByExample(example);
		PageInfo<StOutchainDetail> pageInfo=new PageInfo<StOutchainDetail>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StOutchainDetail getStOutchainDetailById(Integer id) {
		return outchainDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveStOutchainDetail(StOutchainDetail outchainDetail) {
		if(StringUtil.isEmpty(outchainDetail.getSitepic())){
			outchainDetail.setSitepic(null);
		}
		if(outchainDetail.getUid()==null){
			outchainDetailMapper.insertSelective(outchainDetail);
		}else{
			outchainDetailMapper.updateByPrimaryKeySelective(outchainDetail);
		}
		
	}

	@Override
	public int deleteStOutchainDetail(Integer id) {
		int r=outchainDetailMapper.deleteByPrimaryKey(id);
		return r;
	}

	@Override
	public Map<String, Object> findPersonallist(PageBean pageBean,
			StAuthenticPeople authenticPeople) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StAuthenticPeopleExample example=new StAuthenticPeopleExample();
		example.setOrderByClause("createtime desc");
		StAuthenticPeopleExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(authenticPeople.getRealname()))
			criteria.andRealnameLike("%"+authenticPeople.getRealname()+"%");
		List<StAuthenticPeople> list = authenticPeopleMapper.selectByExample(example);
		PageInfo<StAuthenticPeople> pageInfo=new PageInfo<StAuthenticPeople>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StAuthenticPeople getStAuthenticPeopleById(Integer applyId) {
		return authenticPeopleMapper.selectByPrimaryKey(applyId);
	}

	@Override
	public void saveStAuthenticPeople(StAuthenticPeople authenticPeople) {
		if(authenticPeople!=null){
			StUser user = userMapper.selectByPrimaryKey(authenticPeople.getUserid());
			String mobile=user.getMobile();
			int state=authenticPeople.getState();
			if(state==0){
				//SendTemplateSMSUtil.SendCommunityReject(mobile, communityName);  ���Ͷ���
			}else if(state==2){
				//SendTemplateSMSUtil.SendCommunityPass(mobile, communityName);
			}
			authenticPeopleMapper.updateByPrimaryKeySelective(authenticPeople);
		}
		
	}

	@Override
	public Map<String, Object> findCompanylist(PageBean pageBean,
			StAuthenticCompany authenticCompany) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StAuthenticCompanyExample example=new StAuthenticCompanyExample();
		example.setOrderByClause("createtime desc");
		StAuthenticCompanyExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(authenticCompany.getCompanyname()))
			criteria.andCompanynameLike("%"+authenticCompany.getCompanyname()+"%");
		List<StAuthenticCompany> list = authenticCompanyMapper.selectByExample(example);
		PageInfo<StAuthenticCompany> pageInfo=new PageInfo<StAuthenticCompany>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StAuthenticCompany getStAuthenticCompanyById(Integer applyId) {
		return authenticCompanyMapper.selectByPrimaryKey(applyId);
	}

	@Override
	public void saveStAuthenticCompany(StAuthenticCompany authenticCompany) {
		if(authenticCompany!=null){
			StUser user = userMapper.selectByPrimaryKey(authenticCompany.getUserid());
			String mobile=user.getMobile();
			int state=authenticCompany.getState();
			if(state==0){
				//SendTemplateSMSUtil.SendCommunityReject(mobile, communityName);  ���Ͷ���
			}else if(state==2){
				//SendTemplateSMSUtil.SendCommunityPass(mobile, communityName);
			}
			authenticCompanyMapper.updateByPrimaryKeySelective(authenticCompany);
		}
		
	}

	@Override
	public Map<String, Object> findStSkillContent(PageBean pageBean,
			StSkillContent product) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillContentExample example=new StSkillContentExample();
		example.setOrderByClause("createtime desc");
		StSkillContentExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(product.getTitle()))
			criteria.andTitleLike("%"+product.getTitle()+"%");
		List<StSkillContent> list = skillContentMapper.selectByExample(example);
		PageInfo<StSkillContent> pageInfo=new PageInfo<StSkillContent>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StSkillContent getSkillContentById(Integer Id) {
		return skillContentMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteSkillContentById(Integer Id) {
		StSkillContent r = skillContentMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			skillContentMapper.updateByPrimaryKeySelective(r);
		}
	}

	@Override
	public void saveSkillContent(StSkillContent product) {
		if(StringUtil.isEmpty(product.getCoverpic()))
			product.setCoverpic(null);
		if(StringUtil.isEmpty(product.getDetailpics()))
			product.setDetailpics(null);
		skillContentMapper.updateByPrimaryKeySelective(product);	
	}

	@Override
	public Map<String, Object> findTaskContent(PageBean pageBean,
			StTaskContent product) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StTaskContentExample example=new StTaskContentExample();
		example.setOrderByClause("createtime desc");
		StTaskContentExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(product.getTitle()))
			criteria.andTitleLike("%"+product.getTitle()+"%");
		List<StTaskContent> list = taskContentMapper.selectByExample(example);
		PageInfo<StTaskContent> pageInfo=new PageInfo<StTaskContent>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StTaskContent getTaskContentById(Integer Id) {
		return taskContentMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteTaskContentById(Integer Id) {
		StTaskContent r = taskContentMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			taskContentMapper.updateByPrimaryKeySelective(r);
		}
		
	}

	@Override
	public void saveTaskContent(StTaskContent product) {
		if(StringUtil.isEmpty(product.getCoverpic()))
			product.setCoverpic(null);
		if(StringUtil.isEmpty(product.getDetailpics()))
			product.setDetailpics(null);
		taskContentMapper.updateByPrimaryKeySelective(product);	
		
	}

	@Override
	public Map<String, Object> findSellContent(PageBean pageBean,
			StBuildingsellContent product) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingsellContentExample example=new StBuildingsellContentExample();
		example.setOrderByClause("createtime desc");
		StBuildingsellContentExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(product.getTitle()))
			criteria.andTitleLike("%"+product.getTitle()+"%");
		List<StBuildingsellContent> list = sellContentMapper.selectByExample(example);
		PageInfo<StBuildingsellContent> pageInfo=new PageInfo<StBuildingsellContent>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StBuildingsellContent getSellContentById(Integer Id) {
		return sellContentMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteSellContentById(Integer Id) {
		StBuildingsellContent r = sellContentMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			sellContentMapper.updateByPrimaryKeySelective(r);
		}
		
	}

	@Override
	public void saveSellContent(StBuildingsellContent product) {
		if(StringUtil.isEmpty(product.getCoverpic()))
			product.setCoverpic(null);
		if(StringUtil.isEmpty(product.getDetailpics()))
			product.setDetailpics(null);
		sellContentMapper.updateByPrimaryKeySelective(product);
		
	}

	@Override
	public Map<String, Object> findRentContent(PageBean pageBean,
			StBuildingrentContent product) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingrentContentExample example=new StBuildingrentContentExample();
		example.setOrderByClause("createtime desc");
		StBuildingrentContentExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(product.getTitle()))
			criteria.andTitleLike("%"+product.getTitle()+"%");
		List<StBuildingrentContent> list = rentContentMapper.selectByExample(example);
		PageInfo<StBuildingrentContent> pageInfo=new PageInfo<StBuildingrentContent>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StBuildingrentContent getRentContentById(Integer Id) {
		return rentContentMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteRentContentById(Integer Id) {
		StBuildingrentContent r = rentContentMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			rentContentMapper.updateByPrimaryKeySelective(r);
		}
		
	}

	@Override
	public void saveRentContent(StBuildingrentContent product) {
		rentContentMapper.updateByPrimaryKeySelective(product);
		
	}

	@Override
	public Map<String, Object> findSkillClassify(PageBean pageBean, int parent) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillClassifyExample example=new StSkillClassifyExample();
		example.setOrderByClause("createtime desc");
		StSkillClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andParentEqualTo(parent);
		List<StSkillClassify> list = skillClassifyMapper.selectByExample(example);
		PageInfo<StSkillClassify> pageInfo=new PageInfo<StSkillClassify>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StSkillClassify getSkillClassifyById(Integer Id) {
		return skillClassifyMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void saveStSkillClassify(StSkillClassify classify) {
		classify.setUpdatetime(new Date());
		if(classify.getUid()==null){
			classify.setCreatetime(new Date());
			skillClassifyMapper.insertSelective(classify);
		}
		else
			skillClassifyMapper.updateByPrimaryKeySelective(classify);
		
	}

	@Override
	public int deleteSkillClassifyById(Integer Id) {
		StSkillClassify r = skillClassifyMapper.selectByPrimaryKey(Id);
		if(r!=null){
			int parent=r.getParent();
			if(parent==-1){
				StSkillClassifyExample example=new StSkillClassifyExample();
				StSkillClassifyExample.Criteria criteria=example.createCriteria();
				criteria.andParentEqualTo(Id);
				List<StSkillClassify> p = skillClassifyMapper.selectByExample(example);
				if(p.size()!=0)
					return -1;
				else
					return skillClassifyMapper.deleteByPrimaryKey(Id);
			}else
				return skillClassifyMapper.deleteByPrimaryKey(Id);
		}
		return -1;
	}

	@Override
	public Map<String, Object> findBuildClassify(PageBean pageBean, int parent) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingClassifyExample example=new StBuildingClassifyExample();
		example.setOrderByClause("createtime desc");
		StBuildingClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andParentEqualTo(parent);
		List<StBuildingClassify> list = buildingClassifyMapper.selectByExample(example);
		PageInfo<StBuildingClassify> pageInfo=new PageInfo<StBuildingClassify>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StBuildingClassify getBuildClassifyById(Integer Id) {
		return buildingClassifyMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void saveBuildClassify(StBuildingClassify classify) {
		classify.setUpdatetime(new Date());
		if(classify.getUid()==null){
			classify.setCreatetime(new Date());
			buildingClassifyMapper.insertSelective(classify);
		}
		else
			buildingClassifyMapper.updateByPrimaryKeySelective(classify);
		
	}

	@Override
	public int deleteBuildClassifyById(Integer Id) {
		StBuildingClassify r = buildingClassifyMapper.selectByPrimaryKey(Id);
		if(r!=null){
			int parent=r.getParent();
			if(parent==-1){
				StBuildingClassifyExample example=new StBuildingClassifyExample();
				StBuildingClassifyExample.Criteria criteria=example.createCriteria();
				criteria.andParentEqualTo(Id);
				List<StBuildingClassify> p = buildingClassifyMapper.selectByExample(example);
				if(p.size()!=0)
					return -1;
				else
					return buildingClassifyMapper.deleteByPrimaryKey(Id);
			}else
				return buildingClassifyMapper.deleteByPrimaryKey(Id);
		}
		return -1;
	}

	@Override
	public Map<String, Object> findJobClassify(PageBean pageBean, int parent) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StPositionClassifyExample example=new StPositionClassifyExample();
		example.setOrderByClause("createtime desc");
		StPositionClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andParentEqualTo(parent);
		List<StPositionClassify> list = positionClassifyMapper.selectByExample(example);
		PageInfo<StPositionClassify> pageInfo=new PageInfo<StPositionClassify>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StPositionClassify getPositionClassifyById(Integer Id) {
		return positionClassifyMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void saveStPositionClassify(StPositionClassify classify) {
		classify.setUpdatetime(new Date());
		if(classify.getUid()==null){
			classify.setCreatetime(new Date());
			positionClassifyMapper.insertSelective(classify);
		}
		else
			positionClassifyMapper.updateByPrimaryKeySelective(classify);
		
	}

	@Override
	public int deleteStPositionClassifyById(Integer Id) {
		StPositionClassify r = positionClassifyMapper.selectByPrimaryKey(Id);
		if(r!=null){
			int parent=r.getParent();
			if(parent==-1){
				StPositionClassifyExample example=new StPositionClassifyExample();
				StPositionClassifyExample.Criteria criteria=example.createCriteria();
				criteria.andParentEqualTo(Id);
				List<StPositionClassify> p = positionClassifyMapper.selectByExample(example);
				if(p.size()!=0)
					return -1;
				else
					return positionClassifyMapper.deleteByPrimaryKey(Id);
			}else
				return positionClassifyMapper.deleteByPrimaryKey(Id);
		}
		return -1;
	}

	@Override
	public Map<String, Object> findStJob(PageBean pageBean, StJob job) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StJobExample example=new StJobExample();
		example.setOrderByClause("createtime desc");
		StJobExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(job.getJobname()))
			criteria.andJobnameLike("%"+job.getJobname()+"%");
		List<StJob> list = jobMapper.selectByExample(example);
		PageInfo<StJob> pageInfo=new PageInfo<StJob>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StJob getStJobById(Integer Id) {
		return jobMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteStJobById(Integer Id) {
		StJob r = jobMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			jobMapper.updateByPrimaryKeySelective(r);
		}
		
	}

	@Override
	public void saveStJob(StJob job) {
		if(StringUtil.isEmpty(job.getCoverpic()))
			job.setCoverpic(null);
		if(StringUtil.isEmpty(job.getDetailpics()))
			job.setDetailpics(null);
		jobMapper.updateByPrimaryKeySelective(job);
		
	}

	@Override
	public Map<String, Object> findStJobApply(PageBean pageBean, StJobApply job) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StJobApplyExample example=new StJobApplyExample();
		example.setOrderByClause("createtime desc");
		StJobApplyExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(job.getTitle()))
			criteria.andTitleLike("%"+job.getTitle()+"%");
		List<StJobApply> list = jobApplyMapper.selectByExample(example);
		PageInfo<StJobApply> pageInfo=new PageInfo<StJobApply>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StJobApply getStJobApplyById(Integer Id) {
		return jobApplyMapper.selectByPrimaryKey(Id);
	}

	@Override
	public void deleteStJobApplyById(Integer Id) {
		StJobApply r = jobApplyMapper.selectByPrimaryKey(Id);
		if(r!=null){
			r.setState(3);
			jobApplyMapper.updateByPrimaryKeySelective(r);
		}
	}

	@Override
	public void saveStJobApply(StJobApply job) {
		jobApplyMapper.updateByPrimaryKeySelective(job);
		
	}

	
}
