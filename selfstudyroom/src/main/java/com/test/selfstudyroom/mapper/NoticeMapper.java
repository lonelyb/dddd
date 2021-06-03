package com.test.selfstudyroom.mapper;



import com.test.selfstudyroom.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

     /*添加通知*/
     Notice createNotice(Notice notice);

     /*查找所有通知*/
     List<Notice> selectAll (int before,int after);

     /*通知数量*/
     int findNoticesCount();

     /*通过id查找通知*/
     Notice selectById(Integer id);

     /*编辑通知*/
     int updateNotice(Notice notice);
     int findId(Notice notice);

     /*根据id删除通知*/
     int delById(Integer id);

     /*批量删除*/
     int deleteBatch(List<Integer> ids);
}
