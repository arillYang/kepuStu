package com.kepu.mapper;

import com.kepu.pojo.StNewsQuestion;
import com.kepu.pojo.StNewsQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StNewsQuestionMapper {
    int countByExample(StNewsQuestionExample example);

    int deleteByExample(StNewsQuestionExample example);

    int deleteByPrimaryKey(Integer urid);

    int insert(StNewsQuestion record);

    int insertSelective(StNewsQuestion record);

    List<StNewsQuestion> selectByExample(StNewsQuestionExample example);

    StNewsQuestion selectByPrimaryKey(Integer urid);

    int updateByExampleSelective(@Param("record") StNewsQuestion record, @Param("example") StNewsQuestionExample example);

    int updateByExample(@Param("record") StNewsQuestion record, @Param("example") StNewsQuestionExample example);

    int updateByPrimaryKeySelective(StNewsQuestion record);

    int updateByPrimaryKey(StNewsQuestion record);
}