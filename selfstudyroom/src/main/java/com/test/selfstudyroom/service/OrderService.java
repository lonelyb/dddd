package com.test.selfstudyroom.service;



import com.test.selfstudyroom.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> selectAll (int before, int after);
    public int findOrderCount();
    public Order selectById(String id);
    public int delById(String id);
    public int deleteBatch(List<String> ids);
}
