package com.kepu.mapper;

import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.StUserAccountExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StUserAccountMapper {
	
	
	
    int countByExample(StUserAccountExample example);

    int deleteByExample(StUserAccountExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(StUserAccount record);

    int insertSelective(StUserAccount record);
    
    List<StUserAccount> selectBylist();

    List<StUserAccount> selectByExample(StUserAccountExample example);

    StUserAccount selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") StUserAccount record, @Param("example") StUserAccountExample example);

    int updateByExample(@Param("record") StUserAccount record, @Param("example") StUserAccountExample example);

    int updateByPrimaryKeySelective(StUserAccount record);

    int updateByPrimaryKey(StUserAccount record);
 
    StUserAccount selectBylScoreList(int userId);
    
    int updateUserBalanceAndScore(Map map);
    
    Map<String,String> getUserBalanceAndScore(int userId);
    
    StUserAccount fandByPrimaryKeySelectivelist(Integer userid);

}