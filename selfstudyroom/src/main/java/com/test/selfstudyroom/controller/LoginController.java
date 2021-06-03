package com.test.selfstudyroom.controller;



import com.test.selfstudyroom.entity.Admins;
import com.test.selfstudyroom.service.impl.AdminsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private AdminsServiceImpl adminsService;

    @RequestMapping({"/"})
    public String index(){
        return "login-1.html";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public String insertNotice(@RequestParam(value = "username") String username,@RequestParam("password") String password){
        Admins admins = new Admins();
        if (username!=""&&password!=""){
            admins.setUser_name(username);
            admins.setPasswords(password);
            Admins admins1 = adminsService.findAdmins(admins);
            if (admins1!=null){
                System.out.println("登录成功！！！");
                return "OK";
            }
        }
        return "NO";
    }

}
