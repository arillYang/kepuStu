package com.kepu.controller;


import java.util.Date;
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
import com.kepu.dao.JedisClient;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StUser;
import com.kepu.pojo.community.TeachResult;
import com.kepu.service.CommunityService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;



@Controller
@RequestMapping("/team")
public class TeamController {
		
	@Autowired
	private CommunityService communityService;
	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	/**
	 * 获取推荐社团 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.getCarousel(stUser2.getUserid(),10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取所有社团
	 * @return
	 */
	@RequestMapping(value="getAllCommunity/{page}")
	public @ResponseBody Object getAllCommunity(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.getAllCommunity(stUser2.getUserid(), page, 10,null);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 搜索社团
	 * @return
	 */
	@RequestMapping(value="search/{page}")
	public @ResponseBody Object searchCommunity(@PathVariable Integer page,HttpServletRequest request){
		try {
			String query=request.getParameter("q");
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "搜索词不能为空","");	
			if(page==1)
				communityService.addHotSearch(query);
			return communityService.getAllCommunity(stUser2.getUserid(), page, 10,query);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取社团热搜词汇
	 * @return
	 */
	@RequestMapping(value="getHotSearch")
	public @ResponseBody Object getHotSearch(HttpServletRequest request){
		try {
			return communityService.getHotSearch();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我的社团
	 * @return
	 */
	@RequestMapping(value="MyCommunity")
	public @ResponseBody Object MyCommunity(@RequestParam(required=false) Long timestamp,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.getMyCommunity(stUser2.getUserid(),timestamp==null?715363200000L
					:timestamp);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 加入普通社团
	 * @return
	 */
	@RequestMapping(value="joinCommunity/{communityId}")
	public @ResponseBody Object joinCommunity(@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.joinCommunity(stUser2.getUserid(),communityId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 申请正规社团
	 * @return
	 */
	@RequestMapping(value="applyCommunity/{communityId}")
	public @ResponseBody Object applyCommunity(@RequestBody Map<String, String> map,@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String userName="";
			String mobile="";
			String IDcardZ="";
			String IDcardF="";
			String position="";
			String credential="";
			//
			String sex="";
			String birthday="";
			String photo="";
			String career="";
			String positional="";
			String education="";
			String selfIntroduction="";
			String IDcardZHold="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("userName")){
				userName=map.get("userName");
			}else{
				sb.append("userName").append(",");
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");
			}else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("IDcardZ"))
				IDcardZ=map.get("IDcardZ");
			else{
				sb.append("IDcardZ").append(",");
			}
			if(map.containsKey("IDcardF"))
				IDcardF=map.get("IDcardF");
			else{
				sb.append("IDcardF").append(",");
			}
			if(map.containsKey("position"))
				position=map.get("position");
			else{
				sb.append("position").append(",");
			}
			if(map.containsKey("credential"))
				credential=map.get("credential");
			else{
				sb.append("credential").append(",");
			}
			
			/*if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}*/
			return communityService.applyCommunity(stUser2.getUserid(),communityId, 
					 userName, mobile, IDcardZ,IDcardF, position, credential,
					  sex, birthday, photo,career, positional, education, selfIntroduction, IDcardZHold);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 退出社团
	 * @return
	 */
	@RequestMapping(value="quit/{communityId}")
	public @ResponseBody Object quitCommunity(@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.quitCommunity(stUser2.getUserid(),communityId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 发布社团帖子
	 * @return
	 */
	@RequestMapping(value="publishArticle/{communityId}")
	public @ResponseBody Object publishArticle(@RequestBody Map<String, String> map,@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			String temp=jedisClient.get("QPS_Article"+token);
			if(StringUtil.isNotEmpty(temp)){
				return KePuResult.build(ResultConstant.code_yewu, "请求太频繁,请稍后再试", "");
			}
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(communityId<10000){
				if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "非社团成员不能发表帖子", "");
				}
			}else{
				int area=stUser2.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "只能访问注册本地圈子", "");
			}
			String content=map.get("content")==null?"":map.get("content");
			String detailPics=map.get("detailPics")==null?"":map.get("detailPics");
			if(StringUtil.isEmpty(content)&&StringUtil.isEmpty(detailPics))
				return KePuResult.build(ResultConstant.code_param, "内容和配图不能同时为空", "");	
			jedisClient.set("QPS_Article"+token,"1");
			jedisClient.expire("QPS_Article"+token, 10);
			return communityService.publishArticle(stUser2.getUserid(),communityId, 
					content, detailPics);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	@RequestMapping(value="editArticle/{articleId}")
	public @ResponseBody Object editArticle(@RequestBody Map<String, String> map,@PathVariable Integer articleId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String content=map.get("content")==null?"":map.get("content");
			String detailPics=map.get("detailPics")==null?"":map.get("detailPics");
			if(StringUtil.isEmpty(content)&&StringUtil.isEmpty(detailPics))
				return KePuResult.build(ResultConstant.code_param, "内容和配图不能同时为空", "");	
			return communityService.editArticle(stUser2.getUserid(), articleId, content, detailPics);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	@RequestMapping(value="deleteArticle/{articleId}")
	public @ResponseBody Object deleteArticle(@PathVariable Integer articleId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.deleteArticle(stUser2.getUserid(), articleId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 社团帖子列表
	 * @return
	 */
	@RequestMapping(value="getArticle/{communityId}/{page}")
	public @ResponseBody Object getArticle(@PathVariable Integer communityId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(communityId<10000){
				if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "非社团成员不能浏览帖子", "");
				}
			}else{
				int area=stUser2.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "非本乡镇成员不能浏览帖子", "");
			}
			return communityService.getArticle(stUser2.getUserid(),communityId,page,10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 我发布的社团帖子/社团帖子
	 * @return
	 */
	@RequestMapping(value="getMyArticle/{page}")
	public @ResponseBody Object getArticle(@PathVariable Integer page,
			HttpServletRequest request,@RequestParam(required=false) Integer type){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return communityService.getMyArticle(stUser2.getUserid(), page, 10, type==null?1:type);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 帖子点赞/取消
	 * @return
	 */
	@RequestMapping(value="dp/{articleId}/{operate}")
	public @ResponseBody Object dp(@PathVariable Integer articleId,
			@PathVariable Integer operate,HttpServletRequest request){
		try {
			// operate=1 点击  operate=0 取消
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return communityService.dpArticle(articleId,user.getUserid(), operate);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 社团详情
	 * @return
	 */
	@RequestMapping(value="communityDetail/{communityId}")
	public @ResponseBody Object communityDetail(@PathVariable Integer communityId,
			@RequestParam(required=false) Long timestamp,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			Integer userId=stUser2.getUserid();
			if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
				//return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
				userId=null;
			}
			return communityService.getCommunityDetail(userId, communityId,timestamp==null?new Date(715363200000L):new Date(timestamp));
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 帖子详情
	 * @return
	 */
	@RequestMapping(value="articleDetail/{articleId}")
	public @ResponseBody Object articleDetail(@PathVariable Integer articleId,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			//StCommunity sc=communityService.getCommunityByArticleId(articleId);
			//Integer communityId=sc==null?-1:sc.getUid();
			Integer communityId=communityService.getCommunityIdByArticleId(articleId);
			if(communityId<10000){
				if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
				}
			}else{
				int area=stUser2.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非乡镇成员)", "");
			}
			return communityService.getArticleDetail(articleId,stUser2.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发表帖子评论
	 * @return
	 */
	@RequestMapping(value="sentArticleComment")
	public @ResponseBody Object sentArticleComment(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment=map.get("comment");
			String articleId=map.get("articleId");
			if(map.containsKey("comment"))
				comment=map.get("comment");
			else{
				sb.append("comment").append(",");
			}
			if(map.containsKey("articleId"))
				articleId=map.get("articleId");
			else{
				sb.append("articleId").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			//StCommunity sc=communityService.getCommunityByArticleId(Integer.valueOf(articleId));
			//Integer communityId=sc==null?-1:sc.getUid();
			Integer communityId=communityService.getCommunityIdByArticleId(Integer.valueOf(articleId));
			if(communityId<10000){
				if(!communityService.whetherMyJoin(user.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
				}
			}else{
				int area=user.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非乡镇成员)", "");
			}
			return communityService.sentComment(user,Integer.valueOf(articleId),comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 回复评论
	 * @return
	 */
	@RequestMapping(value="sentArticleReply")
	public @ResponseBody Object sentArticleReply(@RequestBody Map<String, String> map,HttpServletRequest request){
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
			//StCommunity sc=communityService.getCommunityByCommentId(Integer.valueOf(commentId)); 
			//Integer communityId=sc==null?-1:sc.getUid();
			Integer communityId=communityService.getCommunityIdByCommentId(Integer.valueOf(commentId));
			if(communityId<10000){
				if(!communityService.whetherMyJoin(user.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
				}
			}else{
				int area=user.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非乡镇成员)", "");
			}
			return communityService.replyComment(user, Integer.valueOf(commentId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	//  公告部分
	/**
	 * 获取公告列表
	 * @return
	 */
	@RequestMapping(value="getNotice/{communityId}/{page}")
	public @ResponseBody Object getNotice(@PathVariable Integer communityId,@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
				return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
			}
			return communityService.getNoticeList(communityId,page,10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布社团公告
	 * @param map
	 * @param communityId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="publishNotice/{communityId}")
	public @ResponseBody Object publishNotice(@RequestBody Map<String, String> map,@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!communityService.whetherAdmin(stUser2.getUserid(), communityId)){
				return KePuResult.build(ResultConstant.code_yewu, "非社团管理员不能发布公告", "");
			}
			String content=map.get("content")==null?"":map.get("content");
			String title=map.get("title")==null?"":map.get("title");
			String auchor=map.get("author")==null?"":map.get("author");
			if(StringUtil.isEmpty(content))
				return KePuResult.build(ResultConstant.code_param, "content不能为空", "");	
			if(StringUtil.isEmpty(title))
				return KePuResult.build(ResultConstant.code_param, "title不能为空", "");
			if(StringUtil.isEmpty(auchor))
				return KePuResult.build(ResultConstant.code_param, "author不能为空", "");
			return communityService.publishNotice(stUser2.getUserid(),communityId, 
					title,auchor,content);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 公告详情
	 * @return
	 */
	@RequestMapping(value="noticeDetail/{noticeId}")
	public @ResponseBody Object noticeDetail(@PathVariable Integer noticeId,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StCommunity community = communityService.getCommunityByNoticeId(noticeId);
			if(!communityService.whetherMyJoin(stUser2.getUserid(), community.getUid())){
				return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
			}
			return communityService.getNoticeDetail(noticeId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	//  教学部分
	/**
	 * 获取教学列表
	 * @return
	 */
	@RequestMapping(value="getTeach/{communityId}/{page}")
	public @ResponseBody Object getTeach(@PathVariable Integer communityId,@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
				return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
			}
			return communityService.getTeachList(communityId,page,10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布教学
	 * @param map
	 * @param communityId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="publishTeach/{communityId}")
	public @ResponseBody Object publishTeach(@RequestBody Map<String, Object> map,@PathVariable Integer communityId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!communityService.whetherAdmin(stUser2.getUserid(), communityId)){
				return KePuResult.build(ResultConstant.code_yewu, "非社团管理员不能发布教学", "");
			}
			String title=(String) (map.get("title")==null?"":map.get("title"));
			List<TeachResult> teachList=(List<TeachResult>) map.get("contentList");
			if(StringUtil.isEmpty(title))
				return KePuResult.build(ResultConstant.code_param, "title不能为空", "");
			return communityService.publishTeach(stUser2.getUserid(),communityId, 
					title,teachList);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 教学详情
	 * @return
	 */
	@RequestMapping(value="teachDetail/{teachId}")
	public @ResponseBody Object teachDetail(@PathVariable Integer teachId,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StCommunity community = communityService.getCommunityByTeachId(teachId);
			if(!communityService.whetherMyJoin(stUser2.getUserid(), community.getUid())){
				return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
			}
			return communityService.getTeachDetail(teachId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 评论通知列表
	 * @return
	 */
	@RequestMapping(value="getMyRemind/{communityId}")
	public @ResponseBody Object getMyRemind(@RequestParam(required=false) Long timestamp,@PathVariable Integer communityId,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(communityId<10000){
				if(!communityService.whetherMyJoin(stUser2.getUserid(), communityId)){
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非社团成员)", "");
				}
			}else{
				int area=stUser2.getArea();
				if(area!=communityId)
					return KePuResult.build(ResultConstant.code_yewu, "权限不足(非乡镇成员)", "");
			}
			return communityService.getMyRemind(stUser2.getUserid(),communityId,timestamp==null?new Date(715363200000L
					):new Date(timestamp));
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
