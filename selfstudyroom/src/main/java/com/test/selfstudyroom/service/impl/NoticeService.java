package com.test.selfstudyroom.service.impl;


import com.test.selfstudyroom.entity.Notice;
import com.test.selfstudyroom.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    public Notice createNotice(Notice notice){
        return noticeMapper.createNotice(notice);
    }
    public List<Notice> selectAll(int before,int after){
        return noticeMapper.selectAll(before, after);
    }

    public int findRoomsCount() {
        return noticeMapper.findNoticesCount();
    }

    public Notice selectById(Integer id){
        return noticeMapper.selectById(id);
    }
    public int updateNotice(Notice notice){
        return noticeMapper.updateNotice(notice);
    }
    public int findId(Notice notice){
        return noticeMapper.findId(notice);
    }

    public int delById(Integer id){
        return noticeMapper.delById(id);
    }
    public int deleteBatch(List<Integer> ids){
        return noticeMapper.deleteBatch(ids);
    }
}
