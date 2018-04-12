package com.kepu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.ScoreDetailMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.ScoreDetail;
import com.kepu.pojo.ScoreDetailExample;
import com.kepu.service.ScoreDetailService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class ScoreDetailServiceImpl implements ScoreDetailService {

	@Autowired
	private ScoreDetailMapper sdmapper;


	@Override
	public Map<String, Object> findScoreDetail(PageBean pageBean, ScoreDetail scoreDetail) {
		Map<String, Object> map = new HashMap<String, Object>();
		ScoreDetailExample example = new ScoreDetailExample();
		example.setOrderByClause("time desc ");
		ScoreDetailExample.Criteria criteria=example.createCriteria();
//		if((scoreDetail.getScoreDetailId())!=null)
//			criteria.andScoreDetailIdEqualTo(scoreDetail.getScoreDetailId());
//		if((scoreDetail.getBuyUserPhone())!=null)
//			criteria.andBuyUserPhoneEqualTo(scoreDetail.getBuyUserPhone());
		if((scoreDetail.getBuyUserId())!=null)
			criteria.andBuyUserIdEqualTo(scoreDetail.getBuyUserId());
//		if((scoreDetail.getTransactionCurrencyType())!=null)
//			criteria.andTransactionCurrencyTypeEqualTo(scoreDetail.getTransactionCurrencyType());
		List<ScoreDetail> list = sdmapper.selectByExample(example);
		map.put("list", list);
		return map;
	}
}
