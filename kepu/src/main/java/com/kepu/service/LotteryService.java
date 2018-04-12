package com.kepu.service;


import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryJoin;
import com.kepu.pojo.StUser;
public interface LotteryService {
	
	KePuResult getMessage(Integer aId);
	
	KePuResult getVoteList(Integer page,Integer size,Integer aId,String search);
	
	KePuResult getRankingList(Integer aId);
	
	KePuResult getJoinerDetail(Integer joinId,Integer userId);
	
	Boolean checkVote(Integer joinUser,Integer userId);
	
	StLottery getStLotteryById(Integer activityId);
	
	KePuResult saveJoin(StLotteryJoin lotteryJoin);
	
	Integer getLotteryState(Integer aId);
	
	StLottery getStLotteryByJoinId(Integer joinId);
	
	KePuResult vote(Integer joinId, StUser user);
}
