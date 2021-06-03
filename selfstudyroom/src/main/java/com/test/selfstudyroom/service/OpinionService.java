package com.test.selfstudyroom.service;

import com.test.selfstudyroom.entity.Opinion;

import java.util.List;

public interface OpinionService {
    public List<Opinion> selectAll (int before, int after);
    public int findOpinionCount();
    public Opinion selectById(Integer id);
    public int delById(Integer id);
    public int deleteBatch(List<Integer> ids);
}
