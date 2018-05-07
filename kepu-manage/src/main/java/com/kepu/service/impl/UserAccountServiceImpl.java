package com.kepu.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.StUserAccount;
import com.kepu.service.UserAccountService;

/**
 * ��Ա�˻�����
 * @author Administrator
 *
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private StUserAccountMapper stAccountUserMapper;
	
	@Autowired
	private StUserMapper stUserMapper;
	
	@Override
	public StUserAccount selectByPrimaryKey(Integer userid) {
		StUserAccount stUserAccount = stAccountUserMapper.selectByPrimaryKey(userid);
		return stUserAccount;
	}

	/**
	 * �޸��˻�����Ϣ
	 */
	@Override
	public int updateByPrimaryKey(StUserAccount stUserAccount) {
		// TODO Auto-generated method stub
		int result = 0;
		if(stUserAccount.getUserid()!=null){
			result = stAccountUserMapper.updateByPrimaryKeySelective(stUserAccount);
			if(result>0){
				LOG.info("�˻���Ϣ�޸ĳɹ���");
			}else{
				LOG.info("�˻���Ϣ�޸�ʧ�ܣ�");
			}
		}
		return result;
	}

}
