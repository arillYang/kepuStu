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
import com.kepu.mapper.StActivityRecordMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StActivityRecordExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.activity.ActivityResult;
import com.kepu.pojo.user.UserStatistic;
import com.kepu.pojo.user.UserStatistic2;
import com.kepu.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private StActivityRecordMapper activityRecordMapper;
	@Override
	public Map<String, Object> getStatistic(PageBean pageBean,Map<String,Object> param) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<ActivityResult> list = activityRecordMapper.getActivityResult(param);
		if(param.containsKey("mobile")){
			for (ActivityResult activityResult : list) {
				double d=activityResult.getTotal();
				param.put("score", d);
				int paiming = activityRecordMapper.getPaiming(param);
				activityResult.setRowNum(paiming+1);
			}
		}else{
			int i=1;
			if(pageBean!=null){
				int size=pageBean.getPageSize();
				int page=pageBean.getPage();
				for (ActivityResult activityResult : list) {
					activityResult.setRowNum(i+++(page-1)*size);
				}
			}else{
				for (ActivityResult activityResult : list) {
					activityResult.setRowNum(i++);
				}
			}
		}
		PageInfo<ActivityResult> pageInfo=new PageInfo<ActivityResult>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}
	@Override
	public Map<String, Object> getDetail(PageBean pageBean, Integer userId,Date d1,Date d2) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		StActivityRecordExample example=new StActivityRecordExample();
		example.setOrderByClause("createtime desc");
		StActivityRecordExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		if(d1!=null)
			criteria.andCreatetimeGreaterThanOrEqualTo(d1);
		if(d2!=null)
			criteria.andCreatetimeLessThanOrEqualTo(d2);
		List<StActivityRecord> list = activityRecordMapper.selectByExample(example);
		PageInfo<StActivityRecord> pageInfo=new PageInfo<StActivityRecord>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}
	@Override
	public int setCoefficient(int type, double value) {
		StActivityRecordExample example=new StActivityRecordExample();
		StActivityRecordExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		StActivityRecord record=new StActivityRecord();
		record.setRest(value);
		return activityRecordMapper.updateByExampleSelective(record, example);
	}

}
