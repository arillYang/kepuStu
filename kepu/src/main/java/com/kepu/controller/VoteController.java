package com.kepu.controller;



import java.util.Date;
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
import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryJoin;
import com.kepu.pojo.StUser;
import com.kepu.service.LotteryService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;

@Controller
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private LotteryService lotteryService;
	
	
	/**
	 * ��ȡ��ǰ������ҳ����Ϣ
	 *   ����ͼƬ�� ��������ͶƱ�����������������ʱ�䣬����򣬽�����ʶ
	 * @return
	 */
	@RequestMapping(value="main/{activityId}")
	public @ResponseBody Object main(@PathVariable int activityId){
		try {
			return lotteryService.getMessage(activityId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * �������б�  �����µ���
	 * ���԰�������������
	 * ���,Ʊ��,����
	 */
	@RequestMapping(value="getJoinList/{activityId}")
	public @ResponseBody Object search(@PathVariable Integer activityId,@RequestParam(required=false) String search,
			@RequestParam(required=false) Integer page,HttpServletRequest request){
		try {
			return lotteryService.getVoteList(page==null?1:page, 10, activityId, search);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * ��ǰ����
	 * ����,����,Ʊ��
	 */
	@RequestMapping(value="getRanking/{activityId}")
	public @ResponseBody Object getRanking(@PathVariable Integer activityId,HttpServletRequest request){
		try {
			return lotteryService.getRankingList(activityId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * �μӱ���(����)
	 * ����ʱ��  ��ʼ-����
	 */
	@RequestMapping(value="join/{activityId}")
	public @ResponseBody Object join(@PathVariable int activityId,@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			// ��֤����ʱ��
			StLottery vote = lotteryService.getStLotteryById(activityId);
			if(vote==null||vote.getState()!=1){
				return KePuResult.ok(ResultConstant.code_yewu, "���ڱ���ʱ���ڲ���!", null);
			}
			String title="";
			String description="";
			String pics="";
			String vedios="";
			String name="";
			String address="";
			String mobile="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("title")){title=map.get("title");}else{sb.append("title").append(",");}
			if(map.containsKey("description")){description=map.get("description");}else{sb.append("description").append(",");}
			if(map.containsKey("pics")){pics=map.get("pics");}else{sb.append("pics").append(",");}
			if(map.containsKey("vedios")){vedios=map.get("vedios");} // �Ǳ���
			if(map.containsKey("name")){name=map.get("name");}else{sb.append("name").append(",");}
			if(map.containsKey("mobile")){mobile=map.get("mobile");}else{sb.append("mobile").append(",");}
			if(map.containsKey("address")){address=map.get("address");}else{sb.append("address").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "���²�������Ϊ��"+sb.toString(), "");	
			}
			StLotteryJoin join=new StLotteryJoin();
			join.setAddress(address);
			join.setTitle(title);
			join.setDescription(description);
			join.setPics(pics);
			join.setVedios(vedios);
			join.setName(name);
			join.setMobile(mobile);
			join.setLotteryid(activityId);
			join.setUserid(stUser2.getUserid());
			return lotteryService.saveJoin(join);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * ��ȡ����������
	 * @return  ��ǰƱ��,����,����,���,ͼƬ�б�,��Ƶ�б�
	 */
	@RequestMapping(value="getDetail/{joinId}")
	public @ResponseBody Object getDetail(@PathVariable Integer joinId,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser = userService.getUserByToken(token);
			return lotteryService.getJoinerDetail(joinId,stUser.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * ͶƱ
	 * @return
	 */
	@RequestMapping(value="zh/{joinId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer joinId,HttpServletRequest request){
		try {
			// ���ͶƱʱ��
			StLottery vote = lotteryService.getStLotteryByJoinId(joinId);
			Long now=new Date().getTime();
			if(vote.getState()==4||vote.getVotestart().getTime()>now||vote.getVoteend().getTime()<now){
				return KePuResult.ok(ResultConstant.code_yewu, "����ͶƱʱ���ڲ���!", null);
			}
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser = userService.getUserByToken(token);
			if(lotteryService.checkVote(joinId, stUser.getUserid()))
				return KePuResult.ok(ResultConstant.code_yewu, "��ͶƱ��", null);
			return lotteryService.vote(joinId, stUser);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
