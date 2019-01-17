package com.winter.Controller;

import com.alibaba.fastjson.JSON;
import com.winter.model.User;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

//import javax.annotation.Resource;


@RestController
//@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    //@Resource(name="userService")
    //private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(@RequestBody String userString){
        System.out.println("/user/add --------pass."+userString);
        User user1=new User();
        user1.setUserId(5);
        user1.setUserName("Jxb");
        user1.setPassword("1111111");
        System.out.println(JSON.toJSON(user1));

        User user = JSON.parseObject(userString,User.class);
        System.out.println("======"+user.toString());
        System.out.println("/user/add --------user initialize pass.");
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/select", produces = {"application/json;charset=UTF-8"})
    public void selectUser(){
        System.out.println("select user-----pass");

        List al=userService.findAllUser(1,10);

        for (int i=0; i<al.size(); i++){
            System.out.println("NO"+ i+al.get(i).toString());

        }

        //return userService.findAllUser()
    }


    @RequestMapping(value="/index")
    public String index(Map<String,Object> map){
        map.put("user","Mi Zilong");
        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.findAllUser(pageNum,pageSize); }
}

