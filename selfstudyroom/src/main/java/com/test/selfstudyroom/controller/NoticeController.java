package com.test.selfstudyroom.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.selfstudyroom.entity.Notice;
import com.test.selfstudyroom.service.impl.NoticeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/addNotice",method=RequestMethod.POST)
    @ResponseBody
    public String insertNotice(@RequestParam(value = "id") String id,@RequestParam("time") String time){

        Notice notice =new Notice();
        if (id!=""&&time!=""){
            notice.setUser_name(id);
            notice.setNotice_content(time);
            Notice notice1=noticeService.createNotice(notice);
        }
        if (notice!=null){
            return "OK";
        }
        else {
            return "NO";
        }

    }

    @PostMapping("/update")
    @ResponseBody
    public String updateNotice(@RequestParam(value = "oldId") String oldId, @RequestParam(value = "id") String id,@RequestParam("time") String time){
        System.out.println(oldId);
        System.out.println(id);
        System.out.println(time);
        Notice notice = new Notice();
        int i = Integer.parseInt(oldId);
        notice.setId(i);
        notice.setUser_name(id);
        notice.setNotice_content(time);
        int j=noticeService.updateNotice(notice);
        if(j>0){
            System.out.println("修改成功");
            return "OK";
        }
        else {
            System.out.println("修改失败");
            return "NO";
        }


    }

    /*查询所有*/

    @GetMapping("/selectAll")
    @ResponseBody
    public String selectAll(@RequestParam(value = "page",required = true) int page,
                            @RequestParam(value = "limit",required = true) int limit){

        int before = limit*(page-1);
        int after = page*limit;
        List<Notice> notices= noticeService.selectAll(before,after);

        int count= noticeService.findRoomsCount();
        JSONArray json = JSONArray.fromObject(notices);
        String js=json.toString();
        String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";

        for (Notice it:notices){
            System.out.println(it.toString());
        }


        return jso;
    }

    /*根据id查找*/
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ResponseBody
    public String selectById(@RequestParam(value = "page") int page,
                             @RequestParam(value = "limit") int limit,
                             @RequestParam(value = "username") String id){
        int before = limit*(page-1)+1;
        int after = page*limit;
        if(id.isEmpty()) return selectAll(before,after);

        int b = Integer.valueOf(id).intValue();
        Notice notice =noticeService.selectById(b);

        String js=null,jso;
        if (notice!=null){
            JSONArray json= JSONArray.fromObject(notice);
            js=json.toString();
            jso="{\"code\":0,\"msg\":\"\",\"count\":"+1+",\"data\":"+js+"}";
        }
        else {
            jso="{\"code\":0,\"msg\":\"\",\"count\":"+0+",\"data\":"+js+"}";

        }
        return jso;
    }



    @PostMapping("/delete")
    public String delById(@RequestParam(value = "id") String id){
        int b = Integer.valueOf(id).intValue();
        int a=noticeService.delById(b);
        if (a>0){
            System.out.println("删除成功");
            return "OK";
        }
        else {
            System.out.println("删除失败");
            return "NO";
        }

    }

    @PostMapping("/deleteBatch")
    public String deleteBatch(@RequestParam(value = "ids") String ids) throws JsonProcessingException{

        //创建jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用Jackson将json转为List<User>
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,String.class);
        List<String> delIds = (List<String>) mapper.readValue(ids, javaType);
        List<Integer> nums = new ArrayList<>();

        for (String x : delIds) {
            Integer z = Integer.parseInt(x);
            System.out.println(z);
            nums.add(z);
        }
        int i= noticeService.deleteBatch(nums);
        if (i>0){
            System.out.println("删除成功");

            return "OK";
        }
        else {
            System.out.println("删除失败");

            return "NO";
        }
    }



}
