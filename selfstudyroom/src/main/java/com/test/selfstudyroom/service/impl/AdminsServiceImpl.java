package com.test.selfstudyroom.service.impl;

import com.test.selfstudyroom.entity.Admins;
import com.test.selfstudyroom.mapper.AdminsMapper;
import com.test.selfstudyroom.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminsServiceImpl implements AdminsService {
    @Autowired
    private AdminsMapper adminsMapper;

    public Admins findAdmins(Admins admins){
        return adminsMapper.findAdmins(admins);
    }
    public Admins admins;
}
