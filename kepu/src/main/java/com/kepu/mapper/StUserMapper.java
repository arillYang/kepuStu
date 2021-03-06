package com.kepu.mapper;

import com.kepu.pojo.StProduct;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StUserMapper {
    int countByExample(StUserExample example);

    int deleteByExample(StUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(StUser record);

    int insertSelective(StUser record);

    List<StUser> selectByExample(StUserExample example);

    StUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") StUser record, @Param("example") StUserExample example);

    int updateByExample(@Param("record") StUser record, @Param("example") StUserExample example);

    int updateByPrimaryKeySelective(StUser record);

    int updateByPrimaryKey(StUser record);

	StUser fandStUserlist(Integer buyUserId);

	
	
}