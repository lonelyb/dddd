package com.test.selfstudyroom.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.selfstudyroom.entity.Opinion;
import com.test.selfstudyroom.entity.Order;
import com.test.selfstudyroom.service.OrderService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderCOntroller {

    @Autowired
    private OrderService orderService;

    /*查询所有*/

    @GetMapping("/selectAllOrder")
    @ResponseBody
    public String selectAll(@RequestParam(value = "page",required = true) int page,
                            @RequestParam(value = "limit",required = true) int limit){

        int before = limit*(page-1);
        int after = page*limit;
        List<Order> Orders= orderService.selectAll(before,after);

        int count= orderService.findOrderCount();
        JSONArray json = JSONArray.fromObject(Orders);
        String js=json.toString();
        String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";

        for (Order it:Orders){
            System.out.println(it.toString());
        }


        return jso;
    }

    /*根据id查找*/
    @RequestMapping(value = "/findOrder",method = RequestMethod.GET)
    @ResponseBody
    public String selectById(@RequestParam(value = "page") int page,
                             @RequestParam(value = "limit") int limit,
                             @RequestParam(value = "username") String id){
        int before = limit*(page-1)+1;
        int after = page*limit;
        if(id.isEmpty()) return selectAll(before,after);

//        int b = Integer.valueOf(id).intValue();
        Order Order =orderService.selectById(id);

        String js=null,jso;
        if (Order!=null){
            JSONArray json= JSONArray.fromObject(Order);
            js=json.toString();
            jso="{\"code\":0,\"msg\":\"\",\"count\":"+1+",\"data\":"+js+"}";
        }
        else {
            jso="{\"code\":0,\"msg\":\"\",\"count\":"+0+",\"data\":"+js+"}";

        }
        return jso;
    }


    @PostMapping("/deleteOrder")
    public String delById(@RequestParam(value = "order_id") String id){
//        int b = Integer.valueOf(id).intValue();
        int a=orderService.delById(id);
        if (a>0){
            System.out.println("删除成功");
            return "OK";
        }
        else {
            System.out.println("删除失败");
            return "NO";
        }

    }


    @PostMapping("/deleteBatchOrder")
    public String deleteBatch(@RequestParam(value = "list") String list) throws JsonProcessingException {

        //创建jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用Jackson将json转为List<User>
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class,String.class);
        List<String> delIds = (List<String>) mapper.readValue(list, javaType);

        for (String x : delIds) {
//            Integer z = Integer.parseInt(x);
            System.out.println(x);
//            nums.add(x);
        }
        int i= orderService.deleteBatch(delIds);
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
