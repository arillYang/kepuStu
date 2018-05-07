package com.kepu.mapper;

import com.kepu.pojo.WithdrawCash;
import com.kepu.pojo.WithdrawCashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawCashMapper {
    int countByExample(WithdrawCashExample example);

    int deleteByExample(WithdrawCashExample example);

    int deleteByPrimaryKey(Integer wcId);

    int insert(WithdrawCash record);

    int insertSelective(WithdrawCash record);

    List<WithdrawCash> selectByExample(WithdrawCashExample example);

    WithdrawCash selectByPrimaryKey(Integer wcId);

    int updateByExampleSelective(@Param("record") WithdrawCash record, @Param("example") WithdrawCashExample example);

    int updateByExample(@Param("record") WithdrawCash record, @Param("example") WithdrawCashExample example);

    int updateByPrimaryKeySelective(WithdrawCash record);

    int updateByPrimaryKey(WithdrawCash record);

	WithdrawCash selectWithdrawCashByUserId(Integer buyUserId);
}