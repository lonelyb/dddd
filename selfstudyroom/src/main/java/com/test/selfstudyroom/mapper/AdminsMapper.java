package com.test.selfstudyroom.mapper;


import com.test.selfstudyroom.entity.Admins;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminsMapper {
    Admins findAdmins(Admins admins);
}
