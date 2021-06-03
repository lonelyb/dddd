package com.test.selfstudyroom.mapper;


import com.test.selfstudyroom.entity.Opinion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OpinionMapper {
    /*查找所有通知*/
    List<Opinion> selectAll (int before, int after);

    /*通知数量*/
    int findOpinionCount();

    /*通过id查找通知*/
    Opinion selectById(Integer id);


    /*根据id删除通知*/
    int delById(Integer id);

    /*批量删除*/
    int deleteBatch(List<Integer> ids);
}
