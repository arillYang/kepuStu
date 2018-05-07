package com.kepu.mapper;

import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StAuthenticCompanyMapper {
    int countByExample(StAuthenticCompanyExample example);

    int deleteByExample(StAuthenticCompanyExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(StAuthenticCompany record);

    int insertSelective(StAuthenticCompany record);

    List<StAuthenticCompany> selectByExample(StAuthenticCompanyExample example);

    StAuthenticCompany selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") StAuthenticCompany record, @Param("example") StAuthenticCompanyExample example);

    int updateByExample(@Param("record") StAuthenticCompany record, @Param("example") StAuthenticCompanyExample example);

    int updateByPrimaryKeySelective(StAuthenticCompany record);

    int updateByPrimaryKey(StAuthenticCompany record);
}