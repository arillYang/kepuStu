package com.kepu.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.ResultConstant;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.news.VillageContent;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/villages")
public class VillagesController {
		
	@Autowired
	private VillageService villageService;
	@Autowired
	private UserService userService;
	/**
	 * 根据乡镇获取所辖所有村的图片
	 * @return
	 */
	@RequestMapping(value="getPicture")  
	public @ResponseBody Object getPicture(){
		try {
			//List<Map<String,Object>> r1=new LinkedList<Map<String,Object>>();
			Map<String,Object> r2=new HashMap<String, Object>();
			List<Integer> v=villageService.getAllTownId();
			
			for (Integer integer : v) {
				Integer townId=integer;
				List<StVillage> list = villageService.getPicture(townId);
				List<Map<String,String>> result=new LinkedList<Map<String,String>>();
				Map<String,String> temp;
				for (StVillage stVillage : list) {
					temp=new HashMap<String, String>();
					temp.put("villageId", stVillage.getId()+"");
					temp.put("pic", stVillage.getPic());
					result.add(temp);
				}
				r2.put(townId+"", result);
			}
			return KePuResult.ok(ResultConstant.code_ok, "获取成功", r2);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取乡镇轮播图  区分不同乡镇 
	 * @return
	 */
	@RequestMapping(value="getCarousel/{villageId}")
	public @ResponseBody Object getCarousel(@PathVariable Integer villageId){
		try {
			//  根据类型获取轮播
			return villageService.getCarousel(5,villageId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取乡镇新闻列表
	 * @return
	 */
	@RequestMapping(value="getNews/{villageId}/{page}")
	public @ResponseBody Object getNews(@PathVariable Integer villageId,@PathVariable Integer page){
		try {
			return villageService.getNews(villageId,page,10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取乡镇新闻详情
	 * @return
	 */
	@RequestMapping(value="getNewsDetail/{newsId}")
	public @ResponseBody Object getNewsDetail(@PathVariable Integer newsId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}*/
			int userId=stUser2==null?-1:stUser2.getUserid();
			String appVersion=(String) request.getAttribute("appVersion");
			return villageService.getNewsDetail(userId,newsId,appVersion);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取乡镇新闻评论
	 * @return
	 */
	@RequestMapping(value="getNewsComment/{newsId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Integer newsId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}*/
			int userId=stUser2==null?-1:stUser2.getUserid();
			return villageService.getNewsComment(newsId,userId, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取评论回复列表
	 * @return
	 */
	@RequestMapping(value="getReply/{commentId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Long commentId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}*/
			int userId=stUser2==null?-1:stUser2.getUserid();
			return villageService.getCommentReply(commentId, userId,page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 发表新闻评论
	 * @return
	 */
	@RequestMapping(value="sentComment")
	public @ResponseBody Object sentComment(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment=map.get("comment");
			String newsId=map.get("newsId");
			if(map.containsKey("comment"))
				comment=map.get("comment");
			else{
				sb.append("comment").append(",");
			}
			if(map.containsKey("newsId"))
				newsId=map.get("newsId");
			else{
				sb.append("newsId").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			return userService.villageSentComment(user,Integer.valueOf(newsId),comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 回复评论
	 * @return
	 */
	@RequestMapping(value="replyComment")
	public @ResponseBody Object replyComment(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment=map.get("comment");
			String commentId=map.get("commentId");
			if(map.containsKey("comment"))
				comment=map.get("comment");
			else{
				sb.append("comment").append(",");
			}
			if(map.containsKey("commentId"))
				commentId=map.get("commentId");
			else{
				sb.append("commentId").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			return userService.villageReplyComment(user, Long.valueOf(commentId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 收藏新闻
	 * @return
	 */
	@RequestMapping(value="likeNews")
	public @ResponseBody Object likeNews(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId=map.get("newsId");
			if(map.containsKey("newsId"))
				newsId=map.get("newsId");
			else{
				sb.append("newsId").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			return villageService.likeNews(Integer.valueOf(newsId),user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我的收藏
	 * @return
	 */
	@RequestMapping(value="getMyLikeNews/{page}")
	public @ResponseBody Object getMyLikeNews(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			
			return villageService.getMyLikeNews(user.getUserid(), page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 删除收藏的新闻
	 * @return
	 */
	@RequestMapping(value="delete/likeNews")
	public @ResponseBody Object deletelikeNews(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId=map.get("newsId");
			if(map.containsKey("newsId"))
				newsId=map.get("newsId");
			else{
				sb.append("newsId").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			String[] newsIds=newsId.split(",");
			return villageService.deletelikeNews(newsIds, user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 举报新闻评论
	 * @return
	 */
	@RequestMapping(value="report/{commentId}")
	public @ResponseBody Object reportNewsComment(@PathVariable Long commentId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			return villageService.reportNewsComment(user.getUserid(), commentId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 评论/回复点赞
	 * @return
	 */
	@RequestMapping(value="praise/{type}/{typeId}")
	public @ResponseBody Object praise(@PathVariable Integer type,
			@PathVariable Long typeId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return villageService.praise(type,typeId,user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 新闻点赞/不喜欢
	 * @return
	 */
	@RequestMapping(value="dp/{newsId}/{type}/{operate}")
	public @ResponseBody Object dp(@PathVariable Integer newsId,@PathVariable Integer type,
			@PathVariable Integer operate,HttpServletRequest request){
		try {
			// type=1 点赞  type=2  不喜欢
			// operate=1 点击  operate=0 取消
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return villageService.dpNews(newsId, type, user.getUserid(), operate);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 乡镇新闻搜索接口
	 * @return
	 */
	@RequestMapping(value="search/{page}")
	public @ResponseBody Object search(@PathVariable Integer page,HttpServletRequest request){
		try {
			String query=request.getParameter("q");
			if(StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "搜索词不能为空","");	
			if(page==1)
				villageService.addHotSearch(query);
			return villageService.searchNews(query, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取热搜词汇
	 * @return
	 */
	@RequestMapping(value="getHotSearch")
	public @ResponseBody Object getHotSearch(HttpServletRequest request){
		try {
			return villageService.getHotSearch();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取乡村简介
	 */
	@RequestMapping(value="getVillageMessage/{villageId}")
	public @ResponseBody Object getVillageMessage(@PathVariable Integer villageId){
		try {
			 String content = villageService.getVillageMessage(villageId);
			 if(StringUtil.isEmpty(content))
				 return KePuResult.ok(ResultConstant.code_yewu, "乡村ID不存在或内容为空", "");
			 List<VillageContent> vc=JsonUtils.jsonToList(content, VillageContent.class);
			 return KePuResult.ok(ResultConstant.code_ok, "获取成功", vc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 乡镇新闻/公告汇总搜索接口
	 * @return
	 */
	@RequestMapping(value="searchTotal/{page}")
	public @ResponseBody Object searchTotal(@PathVariable Integer page,HttpServletRequest request){
		try {
			String query=request.getParameter("q");
			if(StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "搜索词不能为空","");	
			if(page==1)
				villageService.addHotSearch(query);
			return villageService.searchVillageTotal(query, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
