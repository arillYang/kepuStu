package com.kepu.controller;

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
import com.kepu.service.NewService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewService newService;
	@Autowired
	private UserService userService;
	/**
	 * 获取顶部订阅栏目
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getTopnav")
	public @ResponseBody Object register(){
		try {
			return newService.getTopnav();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取轮播图 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(@RequestParam(required=false) Integer type){
		try {
			//  根据类型获取轮播
			if(type==null)
				type=1;
			return newService.getCarousel(5,type);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取新闻列表
	 * @return
	 */
	@RequestMapping(value="getNews/{type}/{page}")
	public @ResponseBody Object getNews(@PathVariable Integer type,@PathVariable Integer page){
		try {
			return newService.getNews(type,page,10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取推荐的新闻列表
	 */
	@RequestMapping(value="getCommend")
	public @ResponseBody Object getCommend(){
		try {
			return newService.getCommend();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取互动题目
	 */
	@RequestMapping(value="getQuestion/{newsId}")
	public @ResponseBody Object getQuestion(@PathVariable Integer newsId){
		try {
			return newService.getQuestion(newsId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 提交互动答案
	 */
	@RequestMapping(value="submitAnswer")
	public @ResponseBody Object getQuestion(@RequestBody Map<String, Object> map,HttpServletRequest request){
		try {
			List<Map<String,String>> list=(List<Map<String, String>>) map.get("answerList");
			if(list.size()<3){
				return KePuResult.build(ResultConstant.code_param, "至少提交三道题", "");	
			}
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return newService.submitAnswer(list,stUser2.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 获取新闻详情
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
			
			return newService.getNewsDetail(userId,newsId,appVersion);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取新闻评论
	 * @return
	 */
	@RequestMapping(value="getNewsComment/{newsId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Integer newsId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			int userId=stUser2==null?-1:stUser2.getUserid();
			return newService.getNewsComment(newsId,userId, page, 10);
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
			return newService.getCommentReply(commentId, userId,page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 新闻搜索接口
	 * @return
	 */
	@RequestMapping(value="search/{page}")
	public @ResponseBody Object search(@PathVariable Integer page,HttpServletRequest request){
		try {
			String query=request.getParameter("q");
			if(StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "搜索词不能为空","");	
			if(page==1)
				newService.addHotSearch(query);
			return newService.searchNews(query, page, 10);
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
			return newService.getHotSearch();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
