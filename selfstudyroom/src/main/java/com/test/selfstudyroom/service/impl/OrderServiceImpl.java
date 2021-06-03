package com.test.selfstudyroom.service.impl;

import com.test.selfstudyroom.entity.Order;
import com.test.selfstudyroom.mapper.OrderMapper;
import com.test.selfstudyroom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> selectAll(int before, int after) {
        return orderMapper.selectAll(before, after);
    }

    @Override
    public int findOrderCount() {
        return orderMapper.findOrderCount();
    }

    @Override
    public Order selectById(String id) {
        return orderMapper.selectById(id);
    }

    @Override
    public int delById(String id) {
        return orderMapper.delById(id);
    }

    @Override
    public int deleteBatch(List<String> ids) {
        return orderMapper.deleteBatch(ids);
    }
}
