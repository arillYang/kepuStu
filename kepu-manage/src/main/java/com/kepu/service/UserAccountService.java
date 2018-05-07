package com.kepu.service;

import com.kepu.pojo.StUserAccount;

/***
 * 会员的账户
 * @author Administrator
 *
 */
public interface UserAccountService {

	
    StUserAccount selectByPrimaryKey(Integer userid);
	
    int updateByPrimaryKey(StUserAccount stUserAccount);				//修改账户的信息
    
}
