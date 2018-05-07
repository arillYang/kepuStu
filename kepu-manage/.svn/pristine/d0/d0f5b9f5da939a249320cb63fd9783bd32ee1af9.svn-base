package com.kepu.service;

import java.util.Map;

import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryJoin;


public interface VoteService {

	Map<String,Object> getVoteList(StLottery vote,Integer page,Integer size);
		
	StLottery getVoteById(Integer voteId);
	
	void deleteVoteById(Integer voteId,Integer type);
	
	void save(StLottery vote);
	
	Map<String,Object> getJoinerList(StLotteryJoin join,Integer page,Integer size);
	
	StLotteryJoin getVoteUserById(Integer joinId);
	
}
