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
	 * ��ȡ����������Ŀ
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
	 * ��ȡ�ֲ�ͼ 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(@RequestParam(required=false) Integer type){
		try {
			//  �������ͻ�ȡ�ֲ�
			if(type==null)
				type=1;
			return newService.getCarousel(5,type);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * ��ȡ�����б�
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
	 * ��ȡ�Ƽ��������б�
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
	 * ��ȡ������Ŀ
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
	 * �ύ������
	 */
	@RequestMapping(value="submitAnswer")
	public @ResponseBody Object getQuestion(@RequestBody Map<String, Object> map,HttpServletRequest request){
		try {
			List<Map<String,String>> list=(List<Map<String, String>>) map.get("answerList");
			if(list.size()<3){
				return KePuResult.build(ResultConstant.code_param, "�����ύ������", "");	
			}
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return newService.submitAnswer(list,stUser2.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * ��ȡ��������
	 * @return
	 */
	@RequestMapping(value="getNewsDetail/{newsId}")
	public @ResponseBody Object getNewsDetail(@PathVariable Integer newsId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// ������ȡ�û���Ϣ
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "�û�id����", "");
			}*/
			int userId=stUser2==null?-1:stUser2.getUserid();
			String appVersion=(String) request.getAttribute("appVersion");
			
			return newService.getNewsDetail(userId,newsId,appVersion);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * ��ȡ��������
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
	 * ��ȡ���ۻظ��б�
	 * @return
	 */
	@RequestMapping(value="getReply/{commentId}/{page}")
	public @ResponseBody Object getNewsComment(@PathVariable Long commentId,@PathVariable Integer page,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			// ������ȡ�û���Ϣ
			StUser stUser2 = userService.getUserByToken(token);
			/*if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "�û�id����", "");
			}*/
			int userId=stUser2==null?-1:stUser2.getUserid();
			return newService.getCommentReply(commentId, userId,page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * ���������ӿ�
	 * @return
	 */
	@RequestMapping(value="search/{page}")
	public @ResponseBody Object search(@PathVariable Integer page,HttpServletRequest request){
		try {
			String query=request.getParameter("q");
			if(StringUtil.isEmpty(query))
				return KePuResult.build(ResultConstant.code_param, "�����ʲ���Ϊ��","");	
			if(page==1)
				newService.addHotSearch(query);
			return newService.searchNews(query, page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * ��ȡ���Ѵʻ�
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
