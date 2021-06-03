package com.test.selfstudyroom.service;


import com.test.selfstudyroom.entity.RoomEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 处理教室的业务数据
 */
public interface RoomService {
    /**
     * 添加教室
     */
    public int addRoom(RoomEntity roomEntity);
    /**
     *ID查找教室
     */
    public RoomEntity findRoomById(String room_id);
    /**
     * 查找所有教室
     */
    public List<RoomEntity> findRooms(int before,int after);
    /**
     * 查询教室的数量
     */
    public int findRoomsCount();
    /**
     * ID删除教室
     */
    public int deleteRoomsById(String room_id);
    /**
     *删除多个教室
     */
    public int deleteRooms(List<String> delIds);
    /**
     * 更新教室
     */
    public int updateRoom( String oldRoomId,  RoomEntity newRoom);
}
