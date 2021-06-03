package com.test.selfstudyroom.service.impl;

import com.test.selfstudyroom.entity.RoomEntity;
import com.test.selfstudyroom.mapper.RoomMapper;
import com.test.selfstudyroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public int addRoom(RoomEntity roomEntity) {
        return roomMapper.addRoom(roomEntity);
    }

    @Override
    public RoomEntity findRoomById(String room_id) {
        return roomMapper.findRoomById(room_id);
    }

    @Override
    public List<RoomEntity> findRooms(int before,int after) {
        return roomMapper.findRooms(before,after);
    }

    @Override
    public int findRoomsCount() {
        return roomMapper.findRoomsCount();
    }

    @Override
    public int deleteRoomsById(String room_id) {
        return roomMapper.deleteRoomsById(room_id);
    }

    @Override
    public int deleteRooms(List<String> delIds) {
        return roomMapper.deleteRooms(delIds);
    }

    @Override
    public int updateRoom(String oldRoomId, RoomEntity newRoom) {
        return roomMapper.updateRoom(oldRoomId,newRoom);
    }

}
