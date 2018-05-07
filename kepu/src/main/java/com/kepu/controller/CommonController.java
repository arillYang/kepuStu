package com.kepu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.ResultConstant;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StUser;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private SysService sysService;
	@Autowired
	private UserService userService;
	/**
	 * 获取热搜词汇
	 * @return
	 */
	@RequestMapping(value="getHotSearch/{type}")
	public @ResponseBody Object getHotSearch(@PathVariable Integer type){
		try {
			return sysService.getHotSearch(type);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 删除发布
	 * @return
	 */
	@RequestMapping(value="deltete/{type}/{typeId}")
	public @ResponseBody Object deltete(@PathVariable Integer type,@PathVariable Integer typeId,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			int userId=stUser2==null?-1:stUser2.getUserid();
			return sysService.deletePublish(type,typeId,userId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取评论列表
	 * @return
	 */
	@RequestMapping(value="getComment/{page}/{type}/{uid}")
	public @ResponseBody Object getNewsComment(@PathVariable Integer type,@PathVariable Integer page,
			@PathVariable Integer uid,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			int userId=stUser2==null?-1:stUser2.getUserid();
			return sysService.getComment(type,uid,userId, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取评论回复列表
	 * @return
	 */
	@RequestMapping(value="getReply/{commentId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Integer commentId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			int userId=stUser2==null?-1:stUser2.getUserid();
			return sysService.getCommentReply(commentId, userId,page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发表评论
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
			String type=map.get("type");
			String uid=map.get("uid");
			if(map.containsKey("comment"))
				comment=map.get("comment");
			else{
				sb.append("comment").append(",");
			}
			if(map.containsKey("uid"))
				uid=map.get("uid");
			else{
				sb.append("uid").append(",");
			}
			if(map.containsKey("type"))
				type=map.get("type");
			else{
				sb.append("type").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");
			}
			return sysService.sentComment(user,Integer.valueOf(uid),comment,Integer.valueOf(type));
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
			return sysService.replyComment(user, Integer.valueOf(commentId), comment);
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
			@PathVariable Integer typeId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return sysService.praise(type,typeId,user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
