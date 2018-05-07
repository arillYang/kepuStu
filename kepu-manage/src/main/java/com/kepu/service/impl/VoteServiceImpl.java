package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.StLotteryJoinMapper;
import com.kepu.mapper.StLotteryMapper;
import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryExample;
import com.kepu.pojo.StLotteryJoin;
import com.kepu.pojo.StLotteryJoinExample;
import com.kepu.service.VoteService;
import com.kepu.util.StringUtil;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private StLotteryMapper lotteryMapper;
	@Autowired
	private StLotteryJoinMapper lotteryJoinMapper;
	@Override
	public Map<String, Object> getVoteList(StLottery vote,Integer page, Integer size) {
		PageHelper.startPage(page, size);
		Map<String,Object> map=new HashMap<String, Object>();
		StLotteryExample example=new StLotteryExample();
		example.setOrderByClause("createtime desc");
		StLotteryExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(vote.getTitle()))
			criteria.andTitleLike("%"+vote.getTitle()+"%");
		List<StLottery> list = lotteryMapper.selectByExample(example);
		PageInfo<StLottery> pageInfo=new PageInfo<StLottery>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}
	@Override
	public StLottery getVoteById(Integer voteId) {
		return lotteryMapper.selectByPrimaryKey(voteId);
	}
	@Override
	public void deleteVoteById(Integer voteId,Integer type) {
		if(type==-1)
			lotteryMapper.deleteByPrimaryKey(voteId);	
		else if(type==4){
			StLottery vote = lotteryMapper.selectByPrimaryKey(voteId);
			vote.setState(4);
			lotteryMapper.updateByPrimaryKeySelective(vote);
		}
	}
	@Override
	public void save(StLottery vote) {
		if(vote!=null){
			if(vote.getUid()!=null){
				lotteryMapper.updateByPrimaryKeySelective(vote);
			}else{
				vote.setCreatetime(new Date());
				lotteryMapper.insertSelective(vote);
			}
		}
		
	}
	@Override
	public Map<String, Object> getJoinerList(StLotteryJoin join, Integer page,
			Integer size) {
		PageHelper.startPage(page, size);
		Map<String,Object> map=new HashMap<String, Object>();
		StLotteryJoinExample example=new StLotteryJoinExample();
		example.setOrderByClause("votenum desc");
		StLotteryJoinExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(join.getTitle()))
			criteria.andTitleLike("%"+join.getTitle()+"%");
		List<StLotteryJoin> list = lotteryJoinMapper.selectByExample(example);
		PageInfo<StLotteryJoin> pageInfo=new PageInfo<StLotteryJoin>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}
	@Override
	public StLotteryJoin getVoteUserById(Integer joinId) {
		// TODO Auto-generated method stub
		return lotteryJoinMapper.selectByPrimaryKey(joinId);
	}
	

}
