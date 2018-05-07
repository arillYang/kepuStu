package com.kepu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.ScoreDetail;
import com.kepu.pojo.ScoreDetailExample;
import com.kepu.pojo.WithdrawCash;


@Service
public interface WithdrawCashService {
	public Map<String, Object> findWithdrawCash(PageBean pageBean, WithdrawCash withdrawCash);
}
