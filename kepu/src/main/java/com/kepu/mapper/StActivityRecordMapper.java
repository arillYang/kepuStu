package com.kepu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StActivityRecordExample;

public interface StActivityRecordMapper {
    int countByExample(StActivityRecordExample example);

    int deleteByExample(StActivityRecordExample example);

    int insert(StActivityRecord record);

    int insertSelective(StActivityRecord record);
    
	List<StActivityRecord>  selectBylist(int userId);

    List<StActivityRecord> selectByExample(StActivityRecordExample example);

    int updateByExampleSelective(@Param("record") StActivityRecord record, @Param("example") StActivityRecordExample example);

    int updateByExample(@Param("record") StActivityRecord record, @Param("example") StActivityRecordExample example);
    

	double getMyScore(@Param("param") Map param);
	
	int getPaiming(@Param("param") Map param);
	int updateShowName();
	List<StActivityRecord>  selectByMeitlist(int userId,String mtie);
	
	int findTmit(@Param("userId") int userId,@Param("time") String time);

	List<StActivityRecord>  selectByMeitScorList(int userId,String mtie);
		
	StActivityRecord selectByScoreList(int userId);
	
	List<StActivityRecord> selectStActivityRecord(@Param("userId") String userId);
	
	
	
	
	
}