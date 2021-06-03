package com.test.selfstudyroom.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomEntity {

    private String id;
    private boolean state;
    private String room_id;
    private String room_num;
    private String room_time;
    private String room_address;

    public String getRoom_address() {
        return room_address;
    }

    public void setRoom_address(String room_address) {
        this.room_address = room_address;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public String getRoom_time() {
        return room_time;
    }

    public void setRoom_time(String room_time) {
        this.room_time = room_time;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "state=" + state +
                ", room_id='" + room_id + '\'' +
                ", room_num='" + room_num + '\'' +
                ", room_time='" + room_time + '\'' +
                ", room_address='" + room_address + '\'' +
                '}';
    }
}
