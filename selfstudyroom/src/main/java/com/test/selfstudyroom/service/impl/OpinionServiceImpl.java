package com.test.selfstudyroom.service.impl;

import com.test.selfstudyroom.entity.Opinion;
import com.test.selfstudyroom.mapper.OpinionMapper;
import com.test.selfstudyroom.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    public List<Opinion> selectAll (int before, int after){
        return opinionMapper.selectAll(before,after);
    }

    public int findOpinionCount(){
        return opinionMapper.findOpinionCount();
    }

    public Opinion selectById(Integer id){
        return opinionMapper.selectById(id);
    }

    public int delById(Integer id){
        return opinionMapper.delById(id);
    }

    public int deleteBatch(List<Integer> ids){
        return opinionMapper.deleteBatch(ids);
    }

}
