package com.test.selfstudyroom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.selfstudyroom.entity.RoomEntity;
import com.test.selfstudyroom.service.RoomService;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 添加教室
     * @param room_id
     * @param room_state
     * @param room_number
     * @param room_address
     * @param room_time
     * @return
     */
    @RequestMapping(value = "/addRoom",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> RoomEntitySubmit(@RequestParam(value = "id",required = true) String room_id,
                                                @RequestParam(value = "static",required = true) String room_state,
                                                @RequestParam(value = "member") String room_number,
                                                @RequestParam(value = "address") String room_address,
                                                @RequestParam(value = "time") String room_time
                                                ){
        Map<String, String> ret = new HashMap<String, String>();
        System.out.println(room_id);
        System.out.println(room_state);
        System.out.println(room_number);
        System.out.println(room_address);
        System.out.println(room_time);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoom_id(room_id);

        if(room_state.equals("启用")){
            roomEntity.setState(true);
        }
        if(room_state.equals("禁用")){
            roomEntity.setState(false);
        }

        roomEntity.setRoom_num(room_number);
        roomEntity.setRoom_address(room_address);
        roomEntity.setRoom_time(room_time);

        System.out.println(roomEntity.toString());

        if(roomService.findRoomById(room_id)!=null){
            ret.put("type", "error");
            ret.put("msg", "该员工ID已注册，添加失败");
            return ret;
        }

        if(roomService.addRoom(roomEntity)<=0){
            ret.put("type", "error");
            ret.put("msg", "员工信息添加失败");
            return ret;
        }

        ret.put("type", "success");
        ret.put("msg", "员工信息添加成功");
        return ret;
    }


    /**
     * 更新教室信息
     * @param oldRoomId
     * @param room_id
     * @param room_state
     * @param room_number
     * @param room_address
     * @param room_time
     * @return
     */
    @RequestMapping(value = "/updateRoom",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateRoom(
                                          @RequestParam(value = "oldRoomId",required = true) String oldRoomId,
                                          @RequestParam(value = "id",required = true) String room_id,
                                          @RequestParam(value = "static",required = true) String room_state,
                                          @RequestParam(value = "member") String room_number,
                                          @RequestParam(value = "address") String room_address,
                                          @RequestParam(value = "time") String room_time
    ){
        Map<String, String> ret = new HashMap<>();

        RoomEntity newRoom = new RoomEntity();

        newRoom.setRoom_id(room_id);
        newRoom.setRoom_time(room_time);
        newRoom.setRoom_num(room_number);
        newRoom.setRoom_address(room_address);
        if(room_state.equals("启用"))
            newRoom.setState(true);
        if(room_state.equals("禁用"))
            newRoom.setState(false);

        System.out.println(oldRoomId);
        System.out.println(newRoom.toString());

        if(roomService.updateRoom(oldRoomId,newRoom)<=0){
            ret.put("type", "error");
            ret.put("msg", "该教室更新数据失败");
            return ret;
        }


        ret.put("type", "success");
        ret.put("msg", "该教室更新数据成功");
        return ret;
    }


    /**
     * 查找所有的教室
     * @return
     */
    @RequestMapping(value = "/findRooms",method = RequestMethod.GET)
    @ResponseBody
    public String findRooms(
            @RequestParam(value = "page",required = true) int page,
            @RequestParam(value = "limit",required = true) int limit){

        int before = limit*(page-1);
        int after = page*limit;
        List<RoomEntity> sum=roomService.findRooms(before,after);

        int count = roomService.findRoomsCount();
        JSONArray json=JSONArray.fromObject(sum);
        String js=json.toString();
        String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
        for(RoomEntity it:sum){
            System.out.println(it.toString());
        }
       return jso;
    }

    /**
     *删除教室ById
     * @param room_id
     * @return
     */
    @RequestMapping(value = "/deleteRoom",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteRoom(
            @RequestParam(value = "room_id",required = true) String room_id)
    {
        System.out.println(room_id);
        Map<String,String> sum= new HashMap<>();
        if(roomService.deleteRoomsById(room_id)<=0){
            sum.put("type", "error");
            sum.put("msg", "该员工不存在，删除失败");
        }else{
            sum.put("type", "success");
            sum.put("msg", "信息删除成功");
        }
        return sum;
    }

    /**
     * 删除多个教室
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteRooms",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteRooms(
            @RequestParam(value = "ids",required = true) String ids) throws JsonProcessingException {

        Map<String, String> ret = new HashMap<>();

        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);
        List<String> delIds =  (List<String>)mapper.readValue(ids, jt);


        if(roomService.deleteRooms(delIds)<=0){
            ret.put("type", "error");
            ret.put("msg", "删除该教室数据失败");
            return ret;
        }

        ret.put("type", "success");
        ret.put("msg", "删除"+delIds.size()+"个教室数据成功");
        return ret;
    }

    /**
     * 搜寻教室
     */
    @RequestMapping(value = "/searchRoom",method = RequestMethod.GET)
    @ResponseBody
    public String searchRoom(
            @RequestParam(value = "page",required = true) int page,
            @RequestParam(value = "limit",required = true) int limit,
            @RequestParam(value = "username",required = true) String room_id)
    {
            int before = limit*(page-1)+1;
            int after = page*limit;
            if(room_id.isEmpty()) return findRooms(before,after);

            RoomEntity roomEntity = roomService.findRoomById(room_id);
            JSONArray json;
            String js=null,jso;
            if(roomEntity!=null){
                json=JSONArray.fromObject(roomEntity);
                js=json.toString();
                jso="{\"code\":0,\"msg\":\"\",\"count\":"+1+",\"data\":"+js+"}";
            }
            else{
                jso="{\"code\":0,\"msg\":\"\",\"count\":"+0+",\"data\":"+js+"}";
            }

            return jso;
    }


}
