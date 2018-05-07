package com.kepu.service.impl;



import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StLotteryJoinMapper;
import com.kepu.mapper.StLotteryMapper;
import com.kepu.mapper.StLotteryVoteMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryJoin;
import com.kepu.pojo.StLotteryJoinExample;
import com.kepu.pojo.StLotteryVote;
import com.kepu.pojo.StLotteryVoteExample;
import com.kepu.pojo.StUser;
import com.kepu.service.LotteryService;
import com.kepu.util.DateUtil;
import com.kepu.util.LinConstant;
import com.kepu.util.StringUtil;

@Service
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private StLotteryMapper lotteryMapper;
	@Autowired
	private StLotteryJoinMapper lotteryJoinMapper;
	@Autowired
	private StLotteryVoteMapper lotteryVoteMapper;
	@Autowired
	private JedisClient jedisClient;
	@Override
	public KePuResult getMessage(Integer lotteryId) {
		Map<String,String> map=new HashMap<String, String>();
		StLottery lottery = lotteryMapper.selectByPrimaryKey(lotteryId);
		if(lottery==null){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		if(lottery.getState()!=4){
			int state=getLotteryState(lotteryId);
			lottery.setState(state);
		}
		lottery.setViewnum(lottery.getViewnum()+1);
		lotteryMapper.updateByPrimaryKeySelective(lottery);
		map.put("title", lottery.getTitle());
		map.put("display", lottery.getDisplaypic());
		map.put("joinNum",lottery.getJoinnum().toString());
		map.put("voteNum", lottery.getVotenum().toString());
		map.put("viewNum", lottery.getViewnum().toString());
		map.put("endTime", DateUtil.formatDate(lottery.getJoinend(),LinConstant.formatStr)); //报名结束时间
		map.put("detail", lottery.getDetail());
		map.put("state", lottery.getState().toString());
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getVoteList(Integer page, Integer size, Integer aId,
			String search) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(page, size);
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("lotteryId", aId);
		if(StringUtil.isNotEmpty(search)){
			param.put("search", search);
		}
		List<StLotteryJoin> list = lotteryJoinMapper.getList(param);
		List<Map<String,String>> r=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StLotteryJoin content : list) {
			temp=new HashMap<String, String>();
			temp.put("number", content.getNumber());
			temp.put("voteNum",content.getVotenum().toString());
			temp.put("name", content.getName());
			temp.put("joinId", content.getUid()+"");
			temp.put("coverPic", content.getCoverpic());
			r.add(temp);
		}
		PageInfo<StLotteryJoin> pageInfo=new PageInfo<StLotteryJoin>(list);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("list", r);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getRankingList(Integer aId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StLotteryJoinExample example=new StLotteryJoinExample();
		example.setOrderByClause("voteNum desc");
		StLotteryJoinExample.Criteria criteria=example.createCriteria();
		criteria.andLotteryidEqualTo(aId);
		List<StLotteryJoin> list = lotteryJoinMapper.selectByExample(example);
		Map<String,String> temp;
		int i=1;
		List<Map<String,String>> r=new LinkedList<Map<String,String>>();
		for (StLotteryJoin stLotteryJoin : list) {
			temp=new HashMap<String, String>();
			temp.put("voteNum", stLotteryJoin.getVotenum().toString());
			temp.put("name", stLotteryJoin.getName());
			temp.put("joinId", stLotteryJoin.getUid().toString());
			temp.put("rank", i+++"");
			r.add(temp);
		}
		map.put("list", r);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getJoinerDetail(Integer joinId,Integer userId) {
		Map<String,String> map=new HashMap<String, String>();
		StLotteryJoin join = lotteryJoinMapper.selectByPrimaryKey(joinId);
		if(join==null||join.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("voteNum", join.getVotenum().toString());
		map.put("name", join.getName());
		map.put("content", join.getDescription());
		map.put("pics", join.getPics());
		map.put("vedios", join.getVedios());
		map.put("number", join.getNumber());
		// 是否允许投票    不允许3种情况: 1.未开始(投票开始之前),已结束    -->  提交后判断    2.已投票 3.自己帐号
		Integer state=getLotteryState(join.getLotteryid());
		map.put("state", state.toString());
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public Boolean checkVote(Integer joinId, Integer userId) {
		StLotteryVoteExample example=new StLotteryVoteExample();
		StLotteryVoteExample.Criteria criteria=example.createCriteria();
		criteria.andJoinidEqualTo(joinId);
		criteria.andUseridEqualTo(userId);
		List<StLotteryVote> r = lotteryVoteMapper.selectByExample(example);
		return r.size()!=0;
	}
	@Override
	public StLottery getStLotteryById(Integer activityId) {
		return lotteryMapper.selectByPrimaryKey(activityId);
	}
	@Override
	public KePuResult saveJoin(StLotteryJoin lotteryJoin) {
		int line=lotteryJoinMapper.insertSelective(lotteryJoin);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			StLottery l = lotteryMapper.selectByPrimaryKey(lotteryJoin.getLotteryid());
			l.setJoinnum(l.getJoinnum()+1);
			lotteryMapper.updateByPrimaryKeySelective(l);
			map.put("joinId", lotteryJoin.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "参与成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "参与失败", "");
		}
	}
	@Override
	public Integer getLotteryState(Integer aId) {
		StLottery lottery = lotteryMapper.selectByPrimaryKey(aId);
		if(lottery==null)
			return -1;
		int state=1;
		if(lottery.getState()<4){
			long now=new Date().getTime();
			if(lottery.getJoinstart().getTime()<now&&now<lottery.getJoinend().getTime()){
				state=2;
			}else if(lottery.getVotestart().getTime()<now&&now<lottery.getVoteend().getTime()){
				state=3;
			}else{
				state=4;
			}
		}
		return state;
	}
	@Override
	public StLottery getStLotteryByJoinId(Integer joinId) {
		StLotteryJoin join=lotteryJoinMapper.selectByPrimaryKey(joinId);
		return lotteryMapper.selectByPrimaryKey(join.getLotteryid());
	}
	@Override
	public KePuResult vote(Integer joinId, StUser user) {
		StLotteryJoin join=lotteryJoinMapper.selectByPrimaryKey(joinId);
		StLotteryVote vote=new StLotteryVote();
		vote.setJoinid(joinId);
		vote.setLotteryid(join.getLotteryid());
		StLottery l = lotteryMapper.selectByPrimaryKey(join.getLotteryid());
		vote.setVotetime(new Date());
		vote.setJoinuser(join.getUserid());
		vote.setUserid(user.getUserid());
		vote.setTownid(user.getTownid());
		vote.setCountryid(user.getArea());
		lotteryVoteMapper.insertSelective(vote);
		join.setVotenum(join.getVotenum()+1);
		lotteryJoinMapper.updateByPrimaryKeySelective(join);
		l.setVotenum(l.getVotenum()+1);
		lotteryMapper.updateByPrimaryKeySelective(l);
		return KePuResult.ok(ResultConstant.code_ok, "参与成功", "");
	}

	

	
}
