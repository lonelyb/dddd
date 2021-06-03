package com.test.selfstudyroom.mapper;

import com.test.selfstudyroom.entity.RoomEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {
    /**
     * 添加教室
     */
    public int addRoom(RoomEntity roomEntity);
    /**
     * ID查找教室
     */
    public RoomEntity findRoomById(String room_id);
    /**
     * 查找所有教室
     */
    public List<RoomEntity> findRooms(int before,int after);

    /**
     *查找教室的数量
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
    public int updateRoom(@Param("oldRoomId") String oldRoomId,@Param("newRoom") RoomEntity newRoom);
}
