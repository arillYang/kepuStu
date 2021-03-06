package com.kepu.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StProductCollectionMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StProductCollection;
import com.kepu.pojo.StProductCollectionExample;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.good.Good;
import com.kepu.pojo.news.ZheJiang;
import com.kepu.service.ProductService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StProductCollectionMapper collectionMapper;
	
	private static final Logger LOG = Logger.getLogger(ProductController.class);
	/**
	 * 获取轮播图 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(@RequestParam(required=false) Integer classfyId){
		try {
			// classfyId=null  则为首页轮播  否则为分类轮播
			return productService.getCarousel(5,classfyId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 获取所有产品分类
	 * @return
	 */
	@RequestMapping(value="getProductClassfy")
	public @ResponseBody Object getProductClassfy(){
		try {
			return productService.getProductClassfy();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取所有产品列表
	 * @return
	 */
	@RequestMapping(value="getHomeProduct/{page}")
	public @ResponseBody Object getHomeProduct(@PathVariable Integer page){
		try {
			/*DeviceMessage device=SystemSession.get();
			if(device!=null&&StringUtil.isNotEmpty(device.getAppVersion())&&device.getAppVersion().compareTo("6.0.0")>=0){
				return productService.getHomeProductNew(page, 10);
			}else*/
			return productService.getHomeProduct(page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取分类产品列表
	 * @return
	 */
	@RequestMapping(value="getClassfyProduct/{classfyId}/{page}")
	public @ResponseBody Object getClassfyProduct(@PathVariable Integer classfyId,@PathVariable Integer page,
			@RequestParam(required=false) Integer all,
			@RequestParam(required=false) Integer distance,
			@RequestParam(required=false) Integer money,
			@RequestParam(required=false) Integer time){
		try {
			//all 综合排序	0，正序；1，负序
			//distance 距离排序	0，近距离；1，远距离
			//money 价格排序	0，便宜；1，贵
			//time 时间排序	0，时间近；1，时间远
			
			//GeohashUtils.encodeLatLon();
			/*if(distance!=null){
				String lon=map.get("lon");
				String lat=map.get("lat");
			}*/
			return productService.getClassfyProduct(classfyId, page, 20, all, distance, money, time);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 搜索商品
	 * @return
	 */
	@RequestMapping(value="searchProduct/{page}")
	public @ResponseBody Object searchProduct(@PathVariable Integer page,
			@RequestParam(required=false) String q,
			@RequestParam(required=false) Integer all,
			@RequestParam(required=false) Integer distance,
			@RequestParam(required=false) Integer money,
			@RequestParam(required=false) Integer time){
		try {
			// q 搜索词
			//all 综合排序	0，正序；1，负序
			//distance 距离排序	0，近距离；1，远距离
			//money 价格排序	0，便宜；1，贵
			//time 时间排序	0，时间近；1，时间远
			if(page==1)
				productService.addHotSearch(q);
			return productService.searchProduct(q, page, 20, all, distance, money, time);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 获取商品详情
	 * @return
	 */
	@RequestMapping(value="getProductDetail2/{productId}")
	public @ResponseBody Object getProductDetail2(@PathVariable Integer productId,HttpServletRequest request){
		try {
			// 判断是否登录
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			if ( null==token||"".equals(token)) {
				KePuResult   kr=productService.getProductDetail2(productId); 
				Map map=new HashMap<>();
				map.put("data", kr.getData());
				map.put("isCollection", "2");
				return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
			}else{
				StUser user = userService.getUserByToken(token);
				Integer	buyUserId = user.getUserid();
				StProductCollectionExample example=new StProductCollectionExample();
				StProductCollectionExample.Criteria criteria=example.createCriteria();
				criteria.andUseridEqualTo(buyUserId);
				criteria.andStateEqualTo(0);
				criteria.andProductidEqualTo(productId);
				if(collectionMapper.selectByExample(example).isEmpty()){
					KePuResult  kr=productService.getProductDetail2(productId);
					Map map=new HashMap<>();
					map.put("data", kr.getData());
					map.put("isCollection", "2");
					return KePuResult.ok(ResultConstant.code_ok, "未收藏", map);
				}else{
					KePuResult  kr=productService.getProductDetail2(productId);
					Map map=new HashMap<>();
					map.put("data", kr.getData());
					map.put("isCollection", "1");
					return KePuResult.ok(ResultConstant.code_ok, "已收藏", map);
				}
			}
//			return productService.getProductDetail(productId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 获取所有服务
	 * @return
	 */
	@RequestMapping(value="getServiceKind")
	public @ResponseBody Object getServiceKind(HttpServletRequest request){
		try {
			int type=1;  //默认安卓   1安卓 2IOS
			String application=(String) request.getAttribute("application");
			String appVersion=(String) request.getAttribute("appVersion");
			if(StringUtil.isNotEmpty(application)){
				if(application.equalsIgnoreCase("IOS"))
					type=2;
			}
			return productService.getServiceKind(type,appVersion);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 获取外链列表  区分安卓和IOS
	 * @return
	 */
	@RequestMapping(value="getOutChainList")
	public @ResponseBody Object getOutChainList(HttpServletRequest request,@RequestParam(required=false) Integer tabId){
		try {
			int type=1;  //默认安卓   1安卓 2IOS
			String application=(String) request.getAttribute("application");
			if(StringUtil.isNotEmpty(application)){
				if(application.equalsIgnoreCase("IOS"))
					type=2;
			}
			return productService.getOutChainList(type,tabId==null?11:tabId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 上传商品
	 * @param map
	 * @return
	 */
	@RequestMapping(value="publishProduct",method=RequestMethod.POST)
	public @ResponseBody Object publishProduct(@RequestBody Map<String, Object> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			long t=jedisClient.incr("QPS_Product"+token);
			//String temp=jedisClient.get("QPS_Product"+token);
			if(t==1){
				jedisClient.expire("QPS_Product"+token, 5);
			}else{
				return KePuResult.build(ResultConstant.code_yewu, "请求太频繁,请稍后再试", "");
			}
			LOG.info(map.toString());
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StUser stUser=userService.getUserById(stUser2.getUserid());
			String title="";
			String introduce="";
			String price="";
			String address="";
			String mobile="";
			String detailPics="";
			String coverPic="";
			String classfyId="";
			String classfyName="";
			String userName="";
			StringBuffer sb=new StringBuffer();
			String appVersion=(String) request.getAttribute("appVersion");
			// 6.0.0版本以下
			if(StringUtil.isEmpty(appVersion)||StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("6.0.0")<0){
				if(map.containsKey("introduce")){
					introduce=(String) map.get("introduce");
				}else{
					sb.append("introduce").append(",");
				}
				if(map.containsKey("detailPics"))
					detailPics=(String) map.get("detailPics");
				else{
					sb.append("detailPics").append(",");
				}
				if(map.containsKey("classfyId"))
					classfyId=(String) map.get("classfyId");
				else{
					sb.append("classfyId").append(",");
				}
				if(map.containsKey("classfyName"))
					classfyName=(String) map.get("classfyName");
				else{
					sb.append("classfyName").append(",");
				}
			}else{
				List<HashMap<String, String>> goods=(List<HashMap<String, String>>) map.get("goods");
				StringBuffer pics=new StringBuffer();
				StringBuffer desc=new StringBuffer();
				for (HashMap<String, String> hashMap : goods) {
					if(hashMap.get("goodsDesc").contains("<br/>"))
						return KePuResult.build(ResultConstant.code_yewu, "包含非法字符", "");
					pics.append(hashMap.get("goodsDescImg")).append(",");
					desc.append(hashMap.get("goodsDesc")).append("<br/>");
				}
				introduce=desc.length()==0?"":desc.delete(desc.length()-5, desc.length()).toString();
				detailPics=pics.length()==0?"":pics.deleteCharAt(pics.length()-1).toString();
				classfyId="5";
				classfyName="商品买卖";
			}
			if(map.containsKey("title")){
				title=(String) map.get("title");
			}else{
				sb.append("title").append(",");
			}
			if(map.containsKey("price"))
				price=(String) map.get("price");
			else{
				sb.append("price").append(",");
			}
			if(map.containsKey("address"))
				address=(String) map.get("address");
			else{
				sb.append("address").append(",");
			}
			if(map.containsKey("mobile")){
				mobile=(String) map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
			}
			else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("coverPic"))
				coverPic=(String) map.get("coverPic");
			else{
				sb.append("coverPic").append(",");
			}
			
			if(map.containsKey("userName"))
				userName=(String) map.get("userName");
			else{
				sb.append("userName").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StProduct product=new StProduct();
			product.setCreatetime(new Date());
			product.setClassfyid(Integer.valueOf(classfyId));
			product.setTitle(title);
			product.setAddress(address);
			product.setClassfyname(classfyName);
			// 拼接
			product.setIntroduce(introduce);
			product.setMobile(mobile);
			product.setCoverpic(coverPic);
			// 拼接
			product.setDetailpics(detailPics);
			try {
				product.setMoney(new BigDecimal(price));
			} catch (Exception e) {
				return KePuResult.build(ResultConstant.code_param, "价格输入有误", "");	
			}
			product.setUserid(stUser.getUserid());
			product.setAvatar(stUser.getAvatar());
			product.setUsername(userName);
			product.setVillageid(stUser.getArea());
			return  productService.publishProduct(product);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 修改商品
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editProduct",method=RequestMethod.POST)
	public @ResponseBody Object editProduct(@RequestBody Map<String, Object> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StUser stUser=userService.getUserById(stUser2.getUserid());
			String title="";
			String introduce="";
			String price="";
			String address="";
			String mobile="";
			String detailPics="";
			String coverPic="";
			String classfyId=""; 
			String classfyName="";
			String userName="";
			StringBuffer sb=new StringBuffer();
			
			String productId=(String) map.get("productId");
			if(StringUtil.isEmpty(productId))
				return KePuResult.build(ResultConstant.code_param, "productId不能为空"+sb.toString(), "");
			StProduct product = productService.getProductById(Integer.valueOf(productId));
			if(product==null||product.getState()==1)
				return KePuResult.build(ResultConstant.code_param, "该商品已被删除或不存在"+sb.toString(), "");
			int userId=product.getUserid();
			int real=stUser2.getUserid();
			if(userId!=real)
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许修改商品"+sb.toString(), "");
			String appVersion=(String) request.getAttribute("appVersion");
			if(StringUtil.isEmpty(appVersion)||StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("6.0.0")<0){
				if(map.containsKey("introduce")){
					introduce=(String) map.get("introduce");
					product.setIntroduce(introduce);
				}
				if(map.containsKey("detailPics")){
					detailPics=(String) map.get("detailPics");
					product.setDetailpics(detailPics);
				}
			}else{
				if(map.containsKey("goods")){
					List<HashMap<String, String>> goods=(List<HashMap<String, String>>) map.get("goods");
					StringBuffer pics=new StringBuffer();
					StringBuffer desc=new StringBuffer();
					for (HashMap<String, String> hashMap : goods) {
						if(hashMap.get("goodsDesc").contains("<br/>"))
							return KePuResult.build(ResultConstant.code_yewu, "包含非法字符", "");
						pics.append(hashMap.get("goodsDescImg")).append(",");
						desc.append(hashMap.get("goodsDesc")).append("<br/>");
					}
					introduce=desc.length()==0?"":desc.delete(desc.length()-5, desc.length()).toString();
					detailPics=pics.length()==0?"":pics.deleteCharAt(pics.length()-1).toString();
					product.setIntroduce(introduce);
					product.setDetailpics(detailPics);
				}
			}
			if(map.containsKey("title")){
				title=(String) map.get("title");
				product.setTitle(title);
			}
			if(map.containsKey("price")){
				price=(String) map.get("price");
				product.setMoney(new BigDecimal(price));
			}
			if(map.containsKey("address")){
				address=(String) map.get("address");
				product.setAddress(address);
			}
			if(map.containsKey("mobile")){
				mobile=(String) map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
				product.setMobile(mobile);
			}
			if(map.containsKey("coverPic")){
				coverPic=(String) map.get("coverPic");
				product.setCoverpic(coverPic);
			}
			if(map.containsKey("classfyId")&&map.containsKey("classfyName")){
				classfyId=(String) map.get("classfyId");
				classfyName=(String) map.get("classfyName");
				product.setClassfyid(Integer.valueOf(classfyId));
				product.setClassfyname(classfyName);
			}
			if(map.containsKey("userName")){
				userName=(String) map.get("userName");
				product.setUsername(userName);
			}
			product.setCreatetime(new Date());
			productService.editProduct(product);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取发布人/我的发布  所有商品
	 * @return
	 */
	@RequestMapping(value="getAllPublish/{page}")
	public @ResponseBody Object getAllPublish(@PathVariable Integer page,
			@RequestParam(required=false) Integer userId,HttpServletRequest request){
		try {
			//userId 为空，则表示获取我的发布   
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}*/
			Integer my=stUser2==null?-1:stUser2.getUserid();
			return productService.getAllProduct(userId==null?my:userId,page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我的收藏 商品
	 * @return
	 */
	@RequestMapping(value="getMyCollection/{page}")
	public @ResponseBody Object getMyCollection(@PathVariable Integer page,
			@RequestParam(required=false) Integer userId,HttpServletRequest request){
		try { 
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			Integer my=stUser2.getUserid();
			return productService.getMyCollection(my,page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	
	/**
	 * 删除收藏的商品
	 * @return
	 */
	@RequestMapping(value="delete/likeProduct")
	public @ResponseBody Object deletelikeProduct(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String productIds=map.get("productIds");
			if(map.containsKey("productIds"))
				productIds=map.get("productIds");
			else{
				sb.append("productIds").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			String[] ps=productIds.split(",");
			return productService.deletelikeProducts(user.getUserid(),ps);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 *  服务定位到浙江省
	 */
	@RequestMapping(value="getProductArea")
	public @ResponseBody Object getProductArea() { 
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", ZheJiang.getZheJiang());
	}
	
	
	
	/**
	 * 获取热搜词汇
	 * @return
	 */
	@RequestMapping(value="getHotSearch")
	public @ResponseBody Object getHotSearch(HttpServletRequest request){
		try {
			return productService.getHotSearch();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取商品详情
	 * @return
	 */
	@RequestMapping(value="getProductDetail/{productId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer productId){
		try {
			
			return productService.getProductDetail(productId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
