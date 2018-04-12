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
	 * 获取当前比赛主页面信息
	 *   顶部图片， 参与数，投票数，访问量，活动结束时间，活动规则，结束标识
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
	 * 参赛者列表  按最新倒序
	 * 可以按姓名或编号搜素
	 * 编号,票数,姓名
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
	 * 当前排名
	 * 排名,姓名,票数
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
	 * 参加比赛(报名)
	 * 限制时间  开始-结束
	 */
	@RequestMapping(value="join/{activityId}")
	public @ResponseBody Object join(@PathVariable int activityId,@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			// 验证报名时间
			StLottery vote = lotteryService.getStLotteryById(activityId);
			if(vote==null||vote.getState()!=1){
				return KePuResult.ok(ResultConstant.code_yewu, "请在报名时间内参与!", null);
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
			if(map.containsKey("vedios")){vedios=map.get("vedios");} // 非必须
			if(map.containsKey("name")){name=map.get("name");}else{sb.append("name").append(",");}
			if(map.containsKey("mobile")){mobile=map.get("mobile");}else{sb.append("mobile").append(",");}
			if(map.containsKey("address")){address=map.get("address");}else{sb.append("address").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
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
	 * 获取参赛者详情
	 * @return  当前票数,姓名,介绍,编号,图片列表,视频列表
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
	 * 投票
	 * @return
	 */
	@RequestMapping(value="zh/{joinId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer joinId,HttpServletRequest request){
		try {
			// 检测投票时间
			StLottery vote = lotteryService.getStLotteryByJoinId(joinId);
			Long now=new Date().getTime();
			if(vote.getState()==4||vote.getVotestart().getTime()>now||vote.getVoteend().getTime()<now){
				return KePuResult.ok(ResultConstant.code_yewu, "请在投票时间内参与!", null);
			}
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser = userService.getUserByToken(token);
			if(lotteryService.checkVote(joinId, stUser.getUserid()))
				return KePuResult.ok(ResultConstant.code_yewu, "已投票！", null);
			return lotteryService.vote(joinId, stUser);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
