package com.kepu.service.impl;

import java.text.Collator;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.mapper.StBuildingrentContentMapper;
import com.kepu.mapper.StBuildingsellContentMapper;
import com.kepu.mapper.StOutchainDetailMapper;
import com.kepu.mapper.StProductCollectionMapper;
import com.kepu.mapper.StProductHotSearchMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StSkillContentMapper;
import com.kepu.mapper.StSkillandbuildingCommentMapper;
import com.kepu.mapper.StTabMapper;
import com.kepu.mapper.StTaskContentMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StOutchainDetail;
import com.kepu.pojo.StOutchainDetailExample;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StProductCollection;
import com.kepu.pojo.StProductCollectionExample;
import com.kepu.pojo.StProductExample;
import com.kepu.pojo.StProductHotSearch;
import com.kepu.pojo.StProductHotSearchExample;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillandbuildingComment;
import com.kepu.pojo.StSkillandbuildingCommentExample;
import com.kepu.pojo.StTab;
import com.kepu.pojo.StTabExample;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.good.Good;
import com.kepu.pojo.good.HomeListPojo;
import com.kepu.service.ProductService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.GpsSession;
import com.kepu.util.MapUtils;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class ProductServiceImpl implements ProductService {

	private final static Comparator CHINA_COMPARE=Collator.getInstance(java.util.Locale.CHINA);
	@Autowired
	private StProductMapper productMapper;
	@Autowired
	private StTabMapper stTabMapper;
	@Autowired
	private StOutchainDetailMapper outchainDetailMapper;
	@Autowired
	private StUserMapper userMapper;
	@Autowired
	private StProductCollectionMapper collectionMapper;
	@Autowired
	private StProductHotSearchMapper productHotSearchMapper;
	@Autowired
	private StSkillandbuildingCommentMapper commentMapper;
	@Autowired
	private StSkillContentMapper skillContentMapper;
	@Autowired
	private StTaskContentMapper stTaskContentMapper;
	@Autowired
	private StBuildingsellContentMapper buildingsellContentMapper;
	@Autowired
	private StBuildingrentContentMapper buildingrentContentMapper;
	@Autowired
	private SysService sysService;
	@Override
	public KePuResult getCarousel(Integer total, Integer classfyId) {
		StProductExample example=new StProductExample();
		StProductExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("carouselTime  desc");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		if(classfyId!=null){
			criteria.andClassfyidEqualTo(classfyId);
		}
		PageHelper.startPage(1, total);
		List<StProduct> list = productMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		String version=SystemSession.get().getAppVersion();
		int isNew=1;
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0)
			isNew=0;
		for (StProduct product:list ) {
			temp=new HashMap<String, String>();
			temp.put("productId", product.getUid()+"");
			temp.put("title", product.getTitle());
			String pic=product.getDetailpics();
			String[] pics=pic.split(",");
			temp.put("pic", pics.length==0?"":pics[0]);
			if(isNew==1)
				temp.put("type", "1");
			data.add(temp);
		}
		if(isNew==1){
			List<Map<String, String>> linkList = sysService.getLinkMapByType(classfyId==null?4:5);
			for (Map<String, String> map : linkList) {
				data.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getServiceKind(Integer type,String appVersion) {
		StTabExample example=new StTabExample();
		StTabExample.Criteria criteria =example.createCriteria();
		example.setOrderByClause(" createtime ");
		criteria.andStateEqualTo(0);
		criteria.andTabtypeNotEqualTo(3);
		/*if(type==2)
			criteria.andTabtypeEqualTo(2);*/
		List<StTab> list = stTabMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StTab stTab : list) {
			if(stTab.getTabtype()==4&&(StringUtil.isEmpty(appVersion)||StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("5.0.1")<0)){
				continue;
			}
			if(stTab.getTabtype()==4&&stTab.getTabid()==11&&(StringUtil.isEmpty(appVersion)||StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("6.1.0")<0)){
				temp=new HashMap<String, String>();
				temp.put("tabId", stTab.getTabid()+"");
				temp.put("tabName", stTab.getTabname());
				temp.put("tabPic", stTab.getTabpic());
				temp.put("tabType", 1+"");
				continue;
			}
			temp=new HashMap<String, String>();
			temp.put("tabId", stTab.getTabid()+"");
			temp.put("tabName", stTab.getTabname());
			temp.put("tabPic", stTab.getTabpic());
			temp.put("tabType", stTab.getTabtype()+"");
			if(stTab.getTabtype()==1) { 
				//外链类型
				String tabUrl=stTab.getTaburl();
				temp.put("tabUrl", tabUrl);
			}
			data.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getHomeProduct(Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example=new StProductExample();
		example.setOrderByClause("createTime desc");
		StProductExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(page, size);
		List<StProduct> myList = productMapper.selectByExample(example);
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StProduct product : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", product.getTitle());
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			temp.put("price", product.getMoney()+"");
			temp.put("productId", product.getUid()+"");
			temp.put("coverPic", product.getCoverpic());
			temp.put("address", product.getAddress());
			productList.add(temp);
		}
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getOutChainList(Integer type,Integer tabId) {
		Map<String,Object> map=new HashMap<String, Object>();
		//  获取所有 更多小类
		Map<String,Object> m1=new TreeMap<String, Object>(CHINA_COMPARE);
		
		Map<Integer,String> m2=new HashMap<Integer, String>();
		StTabExample example2=new StTabExample();
		StTabExample.Criteria criteria2=example2.createCriteria();
		if(tabId!=11)
			criteria2.andTabtypeEqualTo(5);
		else
			criteria2.andTabtypeEqualTo(3);
		List<StTab> l2 = stTabMapper.selectByExample(example2);
		for (StTab stTab : l2) {
			m1.put(stTab.getTabname(), null);
			m2.put(stTab.getTabid(), stTab.getTabname());
		}
		
		StOutchainDetailExample example=new StOutchainDetailExample();
		StOutchainDetailExample.Criteria criteria=example.createCriteria();
		//criteria.andOutchainidEqualTo(outChainId);
		//PageHelper.startPage(page, size);
		List<StOutchainDetail> myList = outchainDetailMapper.selectByExample(example);
		List<Map<String,String>> siteList;
		Map<String,String> temp;
		for (StOutchainDetail outchainDetail : myList) {
			temp=new HashMap<String, String>();
			if(type==1){
				temp.put("title", outchainDetail.getSitetitle());
				temp.put("pic",outchainDetail.getSitepic());
				temp.put("type", outchainDetail.getSitetype()+"");
				if(type==1)
					temp.put("site", outchainDetail.getAndroidsite()+"");
				else
					temp.put("site", outchainDetail.getIossite()+"");
				String tabName=m2.get(outchainDetail.getOutchainid());
				if(m1.get(tabName)==null){
					siteList=new LinkedList<Map<String,String>>();
					siteList.add(temp);
					m1.put(tabName, siteList);
				}else{
					siteList=(List<Map<String, String>>) m1.get(tabName);
					siteList.add(temp);
				}
			}else{
				temp.put("tabName", outchainDetail.getSitetitle());
				temp.put("tabPic",outchainDetail.getSitepic());
				temp.put("tabType", outchainDetail.getSitetype()+"");
				if(type==1)
					temp.put("tabUrl", outchainDetail.getAndroidsite()+"");
				else
					temp.put("tabUrl", outchainDetail.getIossite()+"");
				String tabName=m2.get(outchainDetail.getOutchainid());
				if(m1.get(tabName)==null){
					siteList=new LinkedList<Map<String,String>>();
					siteList.add(temp);
					m1.put(tabName, siteList);
				}else{
					siteList=(List<Map<String, String>>) m1.get(tabName);
					siteList.add(temp);
				}
			}
		}
		Map<String,Object> m3;
		LinkedList<Object> ll=new LinkedList<Object>();
		Iterator iter= m1.keySet().iterator();
		while(iter.hasNext()){
			m3=new HashMap<String, Object>();
			//Map.Entry entry=(Map.Entry) iter.next();
			String key=(String) iter.next();
			Object val=m1.get(key);
			m3.put("serviceName", key);
			m3.put("serviceContent", val);
			ll.add(m3);
		}
		map.put("list", ll);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getClassfyProduct(Integer classfyId, Integer page,
			Integer size, Integer all, Integer distance, Integer money,
			Integer time) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example=new StProductExample();
		/*排序部分*/
		StringBuffer orderString=new StringBuffer();
		if(all!=null){
			if(all==0)
				orderString.append("UID desc").append(",");
			if(all==1)
				orderString.append("UID ").append(",");
		}
		// 按照距离远近排序
		if(distance!=null){
			if(distance==0)
				orderString.append("UID").append(",");
			if(distance==1)
				orderString.append("UID desc").append(",");
		}
		if(money!=null){
			if(money==0)
				orderString.append("money").append(",");
			if(money==1)
				orderString.append("money desc").append(",");
		}
		if(time!=null){
			if(time==0)
				orderString.append("createtime").append(",");
			if(time==1)
				orderString.append("createtime desc").append(",");
		}
		/*end*/
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}else{
			example.setOrderByClause("createtime desc");
		}
		StProductExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andClassfyidEqualTo(classfyId);
		PageHelper.startPage(page, size);
		List<StProduct> myList = productMapper.selectByExample(example);
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StProduct product : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", product.getTitle());
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			temp.put("price", product.getMoney()+"");
			temp.put("productId", product.getUid()+"");
			temp.put("coverPic", product.getCoverpic());
			temp.put("address", product.getAddress());
			productList.add(temp);
		}
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	
	@Override
	public KePuResult searchProduct(String query,Integer page,
			Integer size, Integer all, Integer distance, Integer money,
			Integer time) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example=new StProductExample();
		/*排序部分*/
		StringBuffer orderString=new StringBuffer();
		if(all!=null){
			if(all==0)
				orderString.append("UID").append(",");
			if(all==1)
				orderString.append("UID desc").append(",");
		}
		/*if(distance==0)
			orderString.append("UID").append(",");
		if(distance==1)
			orderString.append("UID").append(",");*/
		if(money!=null){
			if(money==0)
				orderString.append("money").append(",");
			if(money==1)
				orderString.append("money desc").append(",");
		}
		if(time!=null){
			if(time==0)
				orderString.append("createtime").append(",");
			if(time==1)
				orderString.append("createtime desc").append(",");
		}
		/*end*/
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}
		StProductExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(query))
			criteria.andTitleLike("%"+query+"%");
		PageHelper.startPage(page, size);
		List<StProduct> myList = productMapper.selectByExample(example);
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StProduct product : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", product.getTitle());
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			temp.put("price", product.getMoney()+"");
			temp.put("productId", product.getUid()+"");
			temp.put("coverPic", product.getCoverpic());
			temp.put("address", product.getAddress());
			productList.add(temp);
		}
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getProductDetail(Integer productId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProduct product = productMapper.selectByPrimaryKey(productId);
		if(product==null||product.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "该商品已被删除或不存在", map);
		}
		map.put("title", product.getTitle());
		map.put("price", product.getMoney()+"");
		map.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
		map.put("address", product.getAddress());
		map.put("avatar", product.getAvatar());
		map.put("realName", product.getUsername());
		map.put("mobile", product.getMobile());
		DeviceMessage device=SystemSession.get();
		if(device!=null&&StringUtil.isNotEmpty(device.getAppVersion())&&device.getAppVersion().compareTo("6.0.0")>=0){
			List<HashMap<String, String>> list=new LinkedList<HashMap<String,String>>();
			String introduce=product.getIntroduce();
			String pics=product.getDetailpics();
			if(StringUtil.isNotEmpty(introduce)&&StringUtil.isNotEmpty(pics)){
				String[] s1=introduce.split("<br/>");
				String[] s2=pics.split(",");
				int size=s1.length;
				int size2=s2.length;
				int maxSize=size>size2?size:size2;
				HashMap<String, String> m;
				for (int i = 0; i <maxSize; i++) {
					m=new HashMap<String, String>();
					m.put("goodsDesc", i>=size?"":s1[i]);
					m.put("goodsDescImg", i>=size2?"":s2[i]);
					list.add(m);
				}
			}
			map.put("goods", list);
		}else{
			map.put("introduce", product.getIntroduce().replaceAll("<br/>", ""));
			map.put("detailPics", product.getDetailpics());
		}
		map.put("userId", product.getUserid()+"");
		map.put("classfyId", product.getClassfyid()+"");
		map.put("classfyName", product.getClassfyname());
		map.put("productId", product.getUid()+"");
		map.put("coverPic", product.getCoverpic());
		DeviceMessage deviceMessage = SystemSession.get();
		if(deviceMessage!=null){
			String appVersion=deviceMessage.getAppVersion();
			if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("5.2.1")>0){
				int count=getCommentNum(product.getUid());
				map.put("commentCount", count+"");
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getProductClassfy() {
		StTabExample example=new StTabExample();
		StTabExample.Criteria criteria =example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andTabtypeEqualTo(2);
		List<StTab> list = stTabMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StTab stTab : list) {
			temp=new HashMap<String, String>();
			temp.put("classfyId", stTab.getTabid()+"");
			temp.put("classfyName", stTab.getTabname());
			data.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult publishProduct(StProduct product) {
		int state=productMapper.insertSelective(product);
		Map<String,String> map=new HashMap<String, String>();
		map.put("productId", product.getUid()+"");
		if(state==1){
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}
		return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
	}
	@Override
	public StProduct getProductById(Integer productId) {
		return productMapper.selectByPrimaryKey(productId);
	}
	@Override
	public void editProduct(StProduct product) {
		productMapper.updateByPrimaryKeySelective(product);	
	}
	@Override
	public KePuResult getAllProduct(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example=new StProductExample();
		StProductExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StProduct> myList = productMapper.selectByExample(example);
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StProduct product : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", product.getTitle());
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			temp.put("price", product.getMoney()+"");
			temp.put("productId", product.getUid()+"");
			temp.put("coverPic", product.getCoverpic());
			temp.put("address", product.getAddress());
			productList.add(temp);
		}
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getMyCollection(Integer userId, Integer page, Integer size) {
		StProductCollectionExample example=new StProductCollectionExample();
		StProductCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andStateEqualTo(0);
		List<StProductCollection> list = collectionMapper.selectByExample(example);
		List<Integer> l=new LinkedList<Integer>();
		for (StProductCollection stProductCollection : list) {
			l.add(stProductCollection.getProductid());
		}
		Map<String,Object> map=new HashMap<String, Object>();
		StProductExample example2=new StProductExample();
		StProductExample.Criteria criteria2=example2.createCriteria();
		criteria2.andStateEqualTo(0);
		criteria2.andUidIn(l);
		PageHelper.startPage(page, size);
		List<StProduct> myList = productMapper.selectByExample(example2);
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StProduct product : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", product.getTitle());
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			temp.put("price", product.getMoney()+"");
			temp.put("productId", product.getUid()+"");
			temp.put("coverPic", product.getCoverpic());
			temp.put("address", product.getAddress());
			productList.add(temp);
		}
		PageInfo<StProduct> pageInfo=new PageInfo<StProduct>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult likeProduct(Integer userId, Integer productId) {
		StProductCollectionExample example=new StProductCollectionExample();
		StProductCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andProductidEqualTo(productId);
		criteria.andUseridEqualTo(userId);
		List<StProductCollection> list = collectionMapper.selectByExample(example);
		if(list.size()>0){
			return KePuResult.ok(ResultConstant.code_ok, "已收藏", "");
		}
		StProductCollection collection=new StProductCollection();
		collection.setProductid(productId);
		collection.setUserid(userId);
		collectionMapper.insertSelective(collection);
		return KePuResult.ok(ResultConstant.code_ok, "收藏成功", "");
	}
	@Override
	public KePuResult deletelikeProducts(Integer userId, String[] ps) {
		List<Integer> values=null;
		try {
			values = StringUtil.asIntegerList(ps);
		} catch (Exception e) {
			e.printStackTrace();
			return KePuResult.ok(ResultConstant.code_yewu, "商品ID有误，操作失败", "");
		}
		if(values==null)
			return KePuResult.ok(ResultConstant.code_yewu, "请选择要删除的收藏", "");
		StProductCollectionExample example=new StProductCollectionExample();
		StProductCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andProductidIn(values);
		collectionMapper.deleteByExample(example);
		return KePuResult.ok(ResultConstant.code_ok, "删除成功", "");
	}
	@Override
	public void addHotSearch(String query) {
		if(StringUtil.isEmpty(query)||query.length()<2)
			return;
		StProductHotSearchExample example=new StProductHotSearchExample();
		StProductHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StProductHotSearch> list = productHotSearchMapper.selectByExample(example);
		float maxSilimar=0l;
		int tempId=1;
		for (StProductHotSearch stHotSearch : list) {
			String word=stHotSearch.getWord();
			float current=StrSimilarityUtils.getSimilarityRatio(word, query);
			if(current>maxSilimar){
				maxSilimar=current;
				tempId=stHotSearch.getUid();
			}	
		}
		if(maxSilimar>0.4){
			StProductHotSearch r = productHotSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum()+1);
			r.setUpdatetime(new Date());
			productHotSearchMapper.updateByPrimaryKeySelective(r);
		}else{
			StProductHotSearch r =new StProductHotSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			productHotSearchMapper.insertSelective(r);
		}
		
	}
	@Override
	public KePuResult getHotSearch() {
		Map<String,Object> map=new HashMap<String, Object>();
		StProductHotSearchExample example=new StProductHotSearchExample();
		StProductHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, 20);
		List<StProductHotSearch> list = productHotSearchMapper.selectByExample(example);
		List<String> r=new LinkedList<String>();
		for (StProductHotSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public Integer getCommentNum(Integer productId) {
		StSkillandbuildingCommentExample example=new StSkillandbuildingCommentExample();
		StSkillandbuildingCommentExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(5);
		criteria.andTypeidEqualTo(productId);
		List<StSkillandbuildingComment> list = commentMapper.selectByExample(example);
		return list.size();
	}
	@Override
	public KePuResult getHomeProductNew(Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(page, size);
		List<HomeListPojo> myList = productMapper.getHomeListPojo();
		List<Map<String,String>> productList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (HomeListPojo product : myList) {
			temp=new HashMap<String, String>();
			int type=product.getStyle();
			int tyleId=product.getTypeId();
			temp.put("typeId", tyleId+"");
			temp.put("type",type+"");
			temp.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
			if(type==5){
				StProduct product2=productMapper.selectByPrimaryKey(tyleId);
				temp.put("title", product2.getTitle());
				temp.put("price", product2.getMoney()+"");
				temp.put("coverPic", product2.getCoverpic());
			}else if(type==1){
				StSkillContent skill = skillContentMapper.selectByPrimaryKey(tyleId);
				temp.put("title", skill.getTitle());
				temp.put("price", skill.getPrice()+"");
				temp.put("uid", skill.getUid()+"");
				temp.put("coverPic", skill.getCoverpic());
				temp.put("realName", skill.getRealname());
				temp.put("classifyName", skill.getClassifyname());
				// 计算距离
				double lat1=GpsSession.getLat();
				double lon1=GpsSession.getLon();
				double lat2=skill.getLat();
				double lon2=skill.getLon();
				double distance=MapUtils.GetDistance(lat1, lon1, lat2, lon2);
				temp.put("distance",(int)distance/1000+"" );
			}else if(type==2){
				StTaskContent skill = stTaskContentMapper.selectByPrimaryKey(tyleId);
				temp.put("title", skill.getTitle());
				temp.put("price", skill.getPrice()+"");
				temp.put("uid", skill.getUid()+"");
				temp.put("coverPic", skill.getCoverpic());
				temp.put("realName", skill.getRealname());
				temp.put("classifyName", skill.getClassifyname());
				temp.put("startTime", DateUtil.formatDate(skill.getStarttime(), "yyyy-MM-dd"));
				// 计算距离
				double lat1=GpsSession.getLat();
				double lon1=GpsSession.getLon();
				double lat2=skill.getLat();
				double lon2=skill.getLon();
				double distance=MapUtils.GetDistance(lat1, lon1, lat2, lon2);
				temp.put("distance",(int)distance/1000+"" );
			}else if(type==3){
				StBuildingsellContent content = buildingsellContentMapper.selectByPrimaryKey(tyleId);
				temp.put("title", content.getTitle());
				temp.put("price", content.getPrice()+"");
				temp.put("uid", content.getUid()+"");
				temp.put("coverPic", content.getCoverpic());
				temp.put("classifyName", content.getClassifyname());
				temp.put("trade", content.getTrade());
				temp.put("size", content.getSize()+""); 
				temp.put("houseType", content.getHousetype()+"");
			}else if(type==4){
				StBuildingrentContent content = buildingrentContentMapper.selectByPrimaryKey(tyleId);
				temp.put("title", content.getTitle());
				temp.put("startPrice", content.getLowprice()+"");
				temp.put("endPrice", content.getHighprice()+"");
				temp.put("startArea", content.getLowsize()+"");
				temp.put("endArea", content.getHighsize()+"");
				temp.put("rentId", content.getUid()+"");
				temp.put("classifyName", content.getClassifyname());
				temp.put("trade", content.getTrade()+"");
				temp.put("locationRequire", content.getLocationrequire());
				temp.put("hourseTypeRequire", content.getHoursetyperequire());
			}
			productList.add(temp);
		}
		PageInfo<HomeListPojo> pageInfo=new PageInfo<HomeListPojo>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("productList", productList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getProductDetail2(Integer productId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StProduct product = productMapper.selectByPrimaryKey(productId);
		if(product==null||product.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "该商品已被删除或不存在", map);
		}
		map.put("title", product.getTitle());
		map.put("price", product.getMoney()+"");
		map.put("createTime",DateUtil.formatDate(product.getCreatetime(), MyConstant.updatetime));
		map.put("address", product.getAddress());
		map.put("avatar", product.getAvatar());
		map.put("realName", product.getUsername());
		map.put("mobile", product.getMobile());
		DeviceMessage device=SystemSession.get();
		if(null!=product){
			List<HashMap<String, String>> list=new LinkedList<HashMap<String,String>>();
			String introduce=product.getIntroduce();
			String pics=product.getDetailpics();
			if(StringUtil.isNotEmpty(introduce)&&StringUtil.isNotEmpty(pics)){
				String[] s1=introduce.split("<br/>");
				String[] s2=pics.split(",");
				int size=s1.length;
				int size2=s2.length;
				int maxSize=size>size2?size:size2;
				HashMap<String, String> m;
				for (int i = 0; i <maxSize; i++) {
					m=new HashMap<String, String>();
					m.put("goodsDesc", i>=size?"":s1[i]);
					m.put("goodsDescImg", i>=size2?"":s2[i]);
					list.add(m);
				}
			}
			map.put("goods", list);
		}else{
			map.put("introduce", product.getIntroduce().replaceAll("<br/>", ""));
			map.put("detailPics", product.getDetailpics());
		}
		map.put("userId", product.getUserid()+"");
		map.put("classfyId", product.getClassfyid()+"");
		map.put("classfyName", product.getClassfyname()); 
		map.put("productId", product.getUid()+"");
		map.put("coverPic", product.getCoverpic());
		DeviceMessage deviceMessage = SystemSession.get();
		if(deviceMessage!=null){
			String appVersion=deviceMessage.getAppVersion();
			if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("5.2.1")>0){
				int count=getCommentNum(product.getUid());
				map.put("commentCount", count+"");
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}



}
