package com.test.selfstudyroom.mapper;


import com.test.selfstudyroom.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /*查找所有通知*/
    List<Order> selectAll (int before, int after);

    /*通知数量*/
    int findOrderCount();

    /*通过id查找通知*/
    Order selectById(String id);


    /*根据id删除通知*/
    int delById(String id);

    /*批量删除*/
    int deleteBatch(List<String> ids);
}
